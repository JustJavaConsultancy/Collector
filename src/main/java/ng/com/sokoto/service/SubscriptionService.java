package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.SubscriptionDto;
import ng.com.sokoto.mapper.SubscriptionMapper;
import ng.com.sokoto.repository.SubscriptionRepository;
import ng.com.sokoto.service.Exception.InsufficientFundException;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.service.event.EventMessage;
import ng.com.sokoto.web.domain.Subscription;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class SubscriptionService {

    private final SubscriptionRepository repository;
    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private SubscriptionPayment subscriptionPayment;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public SubscriptionService(SubscriptionRepository repository, SubscriptionMapper subscriptionMapper) {
        this.repository = repository;
        this.subscriptionMapper = subscriptionMapper;
    }

    public SubscriptionDto save(SubscriptionDto subscriptionDto) {
        Subscription entity = subscriptionMapper.toEntity(subscriptionDto);
        return subscriptionMapper.toDto(repository.save(entity));
    }

    public Subscription save(Subscription subscription) {
        if (subscription.getId() == null) subscription.setId(
            String.valueOf(StringUtils.leftPad("" + sequenceGeneratorService.generateSequence(Subscription.SEQUENCE_NAME), 10, "0"))
        );

        return repository.save(subscription);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public SubscriptionDto findById(String id) {
        return subscriptionMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Subscription findSubscriptionById(String id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Subscription> findByCondition(SubscriptionDto subscriptionDto, Pageable pageable) {
        Page<Subscription> entityPage = repository.findAll(pageable);
        List<Subscription> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    public SubscriptionDto update(SubscriptionDto subscriptionDto, String id) {
        SubscriptionDto data = findById(id);
        Subscription entity = subscriptionMapper.toEntity(subscriptionDto);
        BeanUtils.copyProperties(data, entity);
        return save(subscriptionMapper.toDto(entity));
    }

    public Subscription makePayment(String subscriptionId, Double amount) {
        Subscription subscription = findSubscriptionById(subscriptionId);

        System.out.println(" The Subscription Here subscription =======" + subscription);
        System.out.println(" The Subscription Here subscription.getSubscriber()=======" + subscription.getSubscriber());
        System.out.println(
            " The Subscription Here subscription.getSubscriber().getWallet() =======" + subscription.getSubscriber().getWallet()
        );
        if (subscription.getSubscriber().getWallet().getAmount() < amount) throw new InsufficientFundException(
            "Insufficient Fund in Your Wallet"
        );
        applicationEventPublisher.publishEvent(new EventMessage<Subscription>("Raise Invoice", subscription, subscriptionPayment));
        return subscription;
    }
}
