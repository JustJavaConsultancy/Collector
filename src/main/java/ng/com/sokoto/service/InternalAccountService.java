package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.InternalAccountDto;
import ng.com.sokoto.mapper.InternalAccountMapper;
import ng.com.sokoto.repository.InternalAccountRepository;
import ng.com.sokoto.web.domain.InternalAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class InternalAccountService {

    private final InternalAccountRepository repository;
    private final InternalAccountMapper internalAccountMapper;

    public InternalAccountService(InternalAccountRepository repository, InternalAccountMapper internalAccountMapper) {
        this.repository = repository;
        this.internalAccountMapper = internalAccountMapper;
    }

    public InternalAccountDto save(InternalAccountDto internalAccountDto) {
        InternalAccount entity = internalAccountMapper.toEntity(internalAccountDto);
        return internalAccountMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public InternalAccountDto findById(Long id) {
        return internalAccountMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<InternalAccountDto> findByCondition(InternalAccountDto internalAccountDto, Pageable pageable) {
        Page<InternalAccount> entityPage = repository.findAll(pageable);
        List<InternalAccount> entities = entityPage.getContent();
        return new PageImpl<>(internalAccountMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public InternalAccountDto update(InternalAccountDto internalAccountDto, Long id) {
        InternalAccountDto data = findById(id);
        InternalAccount entity = internalAccountMapper.toEntity(internalAccountDto);
        BeanUtils.copyProperties(data, entity);
        return save(internalAccountMapper.toDto(entity));
    }
}
