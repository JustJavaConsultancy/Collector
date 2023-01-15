package ng.com.sokoto.service;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.FacilityTypeDto;
import ng.com.sokoto.mapper.FacilityTypeMapper;
import ng.com.sokoto.repository.FacilityTypeRepository;
import ng.com.sokoto.web.domain.FacilityType;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class FacilityTypeService {

    private final FacilityTypeRepository repository;
    private final FacilityTypeMapper facilityTypeMapper;

    public FacilityTypeService(FacilityTypeRepository repository, FacilityTypeMapper facilityTypeMapper) {
        this.repository = repository;
        this.facilityTypeMapper = facilityTypeMapper;
    }

    public FacilityTypeDto save(FacilityTypeDto facilityTypeDto) {
        FacilityType entity = facilityTypeMapper.toEntity(facilityTypeDto);
        return facilityTypeMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public FacilityTypeDto findById(String id) {
        System.out.println("1 Inside the real Services===" + id);
        Optional<FacilityType> facilityType = repository.findById(id);
        System.out.println("2 Inside the real Services===" + facilityType);
        return facilityTypeMapper.toDto(facilityType.orElseThrow(ResourceNotFoundException::new));
    }

    public FacilityType finEntitydById(String id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<FacilityTypeDto> findByCondition(FacilityTypeDto facilityTypeDto, Pageable pageable) {
        Page<FacilityType> entityPage = repository.findAll(pageable);
        List<FacilityType> entities = entityPage.getContent();
        return new PageImpl<>(facilityTypeMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public FacilityTypeDto update(FacilityTypeDto facilityTypeDto, String id) {
        FacilityTypeDto data = findById(id);
        FacilityType entity = facilityTypeMapper.toEntity(facilityTypeDto);
        BeanUtils.copyProperties(data, entity);
        return save(facilityTypeMapper.toDto(entity));
    }
}
