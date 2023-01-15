package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.SubscriptionDto;
import ng.com.sokoto.mapper.SubscriptionMapper;
import ng.com.sokoto.repository.SubscriptionRepository;
import ng.com.sokoto.web.domain.Subscription;
import org.springframework.beans.BeanUtils;
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

    public SubscriptionService(SubscriptionRepository repository, SubscriptionMapper subscriptionMapper) {
        this.repository = repository;
        this.subscriptionMapper = subscriptionMapper;
    }

    public SubscriptionDto save(SubscriptionDto subscriptionDto) {
        Subscription entity = subscriptionMapper.toEntity(subscriptionDto);
        return subscriptionMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public SubscriptionDto findById(String id) {
        return subscriptionMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<SubscriptionDto> findByCondition(SubscriptionDto subscriptionDto, Pageable pageable) {
        Page<Subscription> entityPage = repository.findAll(pageable);
        List<Subscription> entities = entityPage.getContent();
        return new PageImpl<>(subscriptionMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public SubscriptionDto update(SubscriptionDto subscriptionDto, String id) {
        SubscriptionDto data = findById(id);
        Subscription entity = subscriptionMapper.toEntity(subscriptionDto);
        BeanUtils.copyProperties(data, entity);
        return save(subscriptionMapper.toDto(entity));
    }
}
