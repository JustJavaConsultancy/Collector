package ng.com.sokoto.web.service;

import ng.com.sokoto.repository.UserRepository;
import ng.com.sokoto.security.SecurityUtils;
import ng.com.sokoto.service.PouchiiClient;
import ng.com.sokoto.web.domain.User;
import ng.com.sokoto.web.dto.pouchii.PaymentTransactionDTO;
import ng.com.sokoto.web.dto.pouchii.SendMoneyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final PouchiiClient pouchiiClient;
    private final UserRepository userRepository;

    public PaymentService(PouchiiClient pouchiiClient, UserRepository userRepository) {
        this.pouchiiClient = pouchiiClient;
        this.userRepository = userRepository;
    }

    public Mono<PaymentTransactionDTO> pay(SendMoneyDTO sendMoneyDTO) {
        Mono<PaymentTransactionDTO> paymentTransactionDTOMono = SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .flatMap(user -> Mono.just(user.getPouchiiToken()))
            .doOnNext(token -> log.info("The user token: {}", token))
            .flatMap(token -> pouchiiClient.sendMoney(sendMoneyDTO, token));

        Mono<User> destAccOwner = userRepository.findByWalletAccount(sendMoneyDTO.getDestAccountNumber());
        Mono<User> srcAccOwner = userRepository.findByWalletAccount(sendMoneyDTO.getSourceAccountNumber());

        return Mono
            .zip(paymentTransactionDTOMono, destAccOwner, srcAccOwner)
            .flatMap(tuple -> {
                PaymentTransactionDTO transactionDTO = tuple.getT1();
                User destOwner = tuple.getT2();
                User srcOwner = tuple.getT3();

                destOwner.setBalance(destOwner.getBalance() + sendMoneyDTO.getAmount());
                srcOwner.setBalance(srcOwner.getBalance() - sendMoneyDTO.getAmount());

                return Mono.zip(userRepository.save(destOwner), userRepository.save(srcOwner)).map(resultTuple -> transactionDTO);
            });
    }
}
