package ng.com.sokoto.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.web.dto.pouchii.AuthRequest;
import ng.com.sokoto.web.dto.pouchii.AuthResponse;
import ng.com.sokoto.web.dto.pouchii.CreateWalletExternal;
import ng.com.sokoto.web.dto.pouchii.CreateWalletExternalResponse;
import ng.com.sokoto.web.rest.vm.LoginVM;
import ng.com.sokoto.web.service.UserJWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PouchiiClient {

    private final Logger log = LoggerFactory.getLogger(PouchiiClient.class);
    private String BASE_URL = "https://walletdemo.remita.net/api";
    private String REGISTER = "/wallet-external";
    private String STSL_SCHEME = "53797374656d73706563732077616c6c6574";
    private String AUTH = "/authenticate";

    private final WebClient webClient;

    public PouchiiClient(WebClient webClient) {
        this.webClient = webClient;
    }

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
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(jsonString);
                    authResponse.setMessage((String) jsonObject.get("message"));
                    authResponse.setToken((String) jsonObject.get("token"));
                    JSONArray jsonArray = jsonObject.getJSONArray("walletAccountList");

                    //                    AuthResponse.WalletAccount walletAccount = (AuthResponse.WalletAccount) jsonArray.get(0);
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
}
