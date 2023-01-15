package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.PayItemTypeDto;
import ng.com.sokoto.mapper.PayItemTypeMapper;
import ng.com.sokoto.repository.PayItemTypeRepository;
import ng.com.sokoto.web.domain.PayItemType;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class PayItemTypeService {

    private final PayItemTypeRepository repository;
    private final PayItemTypeMapper payItemTypeMapper;

    public PayItemTypeService(PayItemTypeRepository repository, PayItemTypeMapper payItemTypeMapper) {
        this.repository = repository;
        this.payItemTypeMapper = payItemTypeMapper;
    }

    public PayItemTypeDto save(PayItemTypeDto payItemTypeDto) {
        PayItemType entity = payItemTypeMapper.toEntity(payItemTypeDto);
        return payItemTypeMapper.toDto(repository.save(entity));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public PayItemTypeDto findById(long id) {
        return payItemTypeMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<PayItemTypeDto> findByCondition(PayItemTypeDto payItemTypeDto, Pageable pageable) {
        Page<PayItemType> entityPage = repository.findAll(pageable);
        List<PayItemType> entities = entityPage.getContent();
        return new PageImpl<>(payItemTypeMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public PayItemTypeDto update(PayItemTypeDto payItemTypeDto, Long id) {
        PayItemTypeDto data = findById(id);
        PayItemType entity = payItemTypeMapper.toEntity(payItemTypeDto);
        BeanUtils.copyProperties(data, entity);
        return save(payItemTypeMapper.toDto(entity));
    }
}
