package ng.com.sokoto.web.rest;

import ng.com.sokoto.web.dto.pouchii.PaymentTransactionDTO;
import ng.com.sokoto.web.dto.pouchii.SendMoneyDTO;
import ng.com.sokoto.web.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public Mono<PaymentTransactionDTO> pay(@RequestBody SendMoneyDTO sendMoneyDTO) {
        if ("walletToWallet".equalsIgnoreCase(sendMoneyDTO.getChannel())) {
            return paymentService.payToWallet(sendMoneyDTO);
        } else if ("walletToBank".equalsIgnoreCase(sendMoneyDTO.getChannel())) {
            return paymentService.payToBank(sendMoneyDTO);
        }
        return Mono.error(new RuntimeException("Invalid Payment Channel"));
    }
}
