package ng.com.sokoto.service;

import ng.com.sokoto.web.dto.pouchii.CreateWalletExternal;
import ng.com.sokoto.web.dto.pouchii.CreateWalletExternalResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PouchiiClient {

    private String BASE_URL = "https://walletdemo.remita.net/api";
    private String REGISTER = "/wallet-external";
    private String STSL_SCHEME = "53797374656d73706563732077616c6c6574";

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
}
