package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.SubscriberDto;
import ng.com.sokoto.mapper.SubscriberMapper;
import ng.com.sokoto.repository.SubscriberRepository;
import ng.com.sokoto.web.domain.FacilityType;
import ng.com.sokoto.web.domain.Subscriber;
import ng.com.sokoto.web.domain.SubscriberWallet;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class SubscriberService {

    private final SubscriberRepository repository;
    private final SubscriberMapper subscriberMapper;

    public SubscriberService(SubscriberRepository repository, SubscriberMapper subscriberMapper) {
        this.repository = repository;
        this.subscriberMapper = subscriberMapper;
    }

    public SubscriberDto save(SubscriberDto subscriberDto) {
        Subscriber entity = subscriberMapper.toEntity(subscriberDto);
        SubscriberWallet wallet = new SubscriberWallet();
        wallet.setWalletNumber(subscriberDto.getPhoneNumber());
        wallet.setAmount(0.00D);
        wallet.setName(subscriberDto.getFirstName());
        entity.setWallet(wallet);
        return subscriberMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public SubscriberDto findById(String id) {
        return subscriberMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Subscriber finEntitydById(String id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<SubscriberDto> findByCondition(SubscriberDto subscriberDto, Pageable pageable) {
        Page<Subscriber> entityPage = repository.findAll(pageable);
        List<Subscriber> entities = entityPage.getContent();
        return new PageImpl<>(subscriberMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public SubscriberDto update(SubscriberDto subscriberDto, String id) {
        SubscriberDto data = findById(id);
        Subscriber entity = subscriberMapper.toEntity(subscriberDto);
        BeanUtils.copyProperties(data, entity);
        return save(subscriberMapper.toDto(entity));
    }
}
