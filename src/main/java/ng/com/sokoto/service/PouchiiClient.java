package ng.com.sokoto.service;

import com.google.gson.Gson;
import ng.com.sokoto.service.dto.PasswordChangeDTO;
import ng.com.sokoto.web.dto.pouchii.*;
import ng.com.sokoto.web.rest.vm.LoginVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PouchiiClient {

    private final Logger log = LoggerFactory.getLogger(PouchiiClient.class);

    @Value("${pouchii.scheme}")
    private String STSL_SCHEME;

    @Value("${pouchii.demo-url}")
    private String BASE_URL;

    @Value("${pouchii.register}")
    private String REGISTER;

    @Value("${pouchii.authenticate}")
    private String AUTH;

    @Value("${pouchii.send-money}")
    private String SEND_MONEY;

    @Value("${pouchii.change-password}")
    private String CHANGE_PASSWORD;

    @Value("${pouchii.change-pin}")
    private String CHANGE_PIN;

    private final WebClient webClient;

    public PouchiiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    /*
    1. Registration
    2. Authentication
    3. Send Money
    4. Change Password
    5. Change PIN
     */
    public Mono<CreateWalletExternalResponse> createWallet(CreateWalletExternal walletExternal) {
        walletExternal.setScheme(STSL_SCHEME);
        return webClient
            .post()
            .uri(BASE_URL + REGISTER)
            .body(Mono.just(walletExternal), CreateWalletExternal.class)
            .retrieve()
            .bodyToMono(CreateWalletExternalResponse.class);
    }

    public Mono<AuthResponse> login(LoginVM loginVM) {
        AuthRequest authRequest = new AuthRequest();
        BeanUtils.copyProperties(loginVM, authRequest);
        authRequest.setScheme(STSL_SCHEME);

        return webClient
            .post()
            .uri(BASE_URL + AUTH)
            .body(Mono.just(authRequest), AuthRequest.class)
            .retrieve()
            .bodyToMono(Object.class)
            .flatMap(pouchiiResponse -> {
                AuthResponse authResponse = new AuthResponse();
                String jsonString = new Gson().toJson(pouchiiResponse);
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    authResponse.setMessage((String) jsonObject.get("message"));
                    authResponse.setToken((String) jsonObject.get("token"));
                    JSONArray jsonArray = jsonObject.getJSONArray("walletAccountList");

                    AuthResponse.WalletAccount walletAccount = new Gson()
                        .fromJson(jsonArray.getJSONObject(0).toString(), AuthResponse.WalletAccount.class);

                    authResponse.setWalletAccount(walletAccount);

                    log.info("Successful Pouchii Authentication: {}", authResponse);
                } catch (JSONException e) {
                    log.info("Error in Pouchii Authentication...");
                    return Mono.error(new RuntimeException(e));
                }
                log.info("Returning Pouchii Authentication...");
                return Mono.just(authResponse);
            });
    }

    public Mono<PaymentTransactionDTO> sendMoney(SendMoneyDTO sendMoneyDTO, String token) {
        return webClient
            .post()
            .uri(BASE_URL + SEND_MONEY)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .body(Mono.just(sendMoneyDTO), SendMoneyDTO.class)
            .retrieve()
            .bodyToMono(Object.class)
            .flatMap(pouchiiResponse -> {
                PaymentTransactionDTO paymentTransactionDTO;
                String jsonString = new Gson().toJson(pouchiiResponse);
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    String code = (String) jsonObject.get("code");

                    if (!"00".equalsIgnoreCase(code)) return Mono.error(new RuntimeException("Error processing payment"));

                    JSONObject transactionDTO = jsonObject.getJSONObject("paymentTransactionDTO");
                    paymentTransactionDTO = new Gson().fromJson(transactionDTO.toString(), PaymentTransactionDTO.class);

                    log.info("Successful Pouchii PaymentTransactionDTO: {}", paymentTransactionDTO);
                } catch (Exception e) {
                    log.info("Error in Pouchii PaymentTransactionDTO...");
                    return Mono.error(new RuntimeException(e));
                }
                log.info("Returning Pouchii PaymentTransactionDTO...");
                return Mono.just(paymentTransactionDTO);
            });
    }

    public Mono<String> changePassword(PasswordChangeDTO passwordChangeDTO, String token) {
        return webClient
            .post()
            .uri(BASE_URL + CHANGE_PASSWORD)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .body(Mono.just(passwordChangeDTO), PasswordChangeDTO.class)
            .retrieve()
            .bodyToMono(Object.class)
            .flatMap(pouchiiResponse -> {
                String responseString = new Gson().toJson(pouchiiResponse);
                log.info("Pouchii change password response: {}", responseString);
                return Mono.just(responseString);
            });
    }

    public Mono<String> changePin(ChangePinDTO changePinDTO, String token) {
        return webClient
            .post()
            .uri(BASE_URL + CHANGE_PIN)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .body(Mono.just(changePinDTO), ChangePinDTO.class)
            .retrieve()
            .bodyToMono(Object.class)
            .flatMap(pouchiiResponse -> {
                String responseString = new Gson().toJson(pouchiiResponse);
                log.info("Pouchii change pin response: {}", responseString);
                return Mono.just(responseString);
            });
    }
}
