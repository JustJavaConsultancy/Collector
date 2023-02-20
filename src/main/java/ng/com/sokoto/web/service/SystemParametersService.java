package ng.com.sokoto.web.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.repository.SystemParametersRepository;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.web.domain.SystemParameters;
import ng.com.sokoto.web.domain.Wallet;
import ng.com.sokoto.web.dto.SystemParametersDto;
import ng.com.sokoto.web.mapper.SystemParametersMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class SystemParametersService {

    private final SystemParametersRepository repository;
    private final SystemParametersMapper systemParametersMapper;

    public SystemParametersService(SystemParametersRepository repository, SystemParametersMapper systemParametersMapper) {
        this.repository = repository;
        this.systemParametersMapper = systemParametersMapper;
    }

    public SystemParametersDto save(SystemParametersDto systemParametersDto) {
        SystemParameters entity = systemParametersMapper.toEntity(systemParametersDto);
        return systemParametersMapper.toDto(repository.save(entity));
    }

    public SystemParameters save(SystemParameters systemParameters) {
        return repository.save(systemParameters);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public SystemParametersDto findById(String id) {
        return systemParametersMapper.toDto(repository.findById(id).orElse(null));
    }

    public Page<SystemParametersDto> findByCondition(SystemParametersDto systemParametersDto, Pageable pageable) {
        Page<SystemParameters> entityPage = repository.findAll(pageable);
        List<SystemParameters> entities = entityPage.getContent();
        return new PageImpl<>(systemParametersMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public SystemParametersDto update(SystemParametersDto systemParametersDto, String id) {
        SystemParametersDto data = findById(id);
        SystemParameters entity = systemParametersMapper.toEntity(systemParametersDto);
        BeanUtils.copyProperties(data, entity);
        return save(systemParametersMapper.toDto(entity));
    }

    public SystemParameters load() {
        SystemParameters systemParameters = systemParametersMapper.toEntity(findById("1"));
        System.out.println(" The SystemsParameter=======================================" + systemParameters);
        if (systemParameters == null) {
            systemParameters = new SystemParameters();
            systemParameters.setId("1");
            Wallet collectorWallet = Wallet.createNewWallet("Collection", StringUtils.leftPad("1", 10, "0"));
            systemParameters.setCollectorWallet(collectorWallet);
            systemParameters = save(systemParameters);
        }
        return systemParameters;
    }
}
