package ng.com.sokoto.service;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.FacilityDto;
import ng.com.sokoto.mapper.FacilityMapper;
import ng.com.sokoto.repository.FacilityRepository;
import ng.com.sokoto.web.domain.Facility;
import ng.com.sokoto.web.domain.Subscriber;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class FacilityService {

    private final FacilityRepository repository;
    private final FacilityMapper facilityMapper;

    public FacilityService(FacilityRepository repository, FacilityMapper facilityMapper) {
        this.repository = repository;
        this.facilityMapper = facilityMapper;
    }

    public FacilityDto save(FacilityDto facilityDto) {
        Facility entity = facilityMapper.toEntity(facilityDto);
        return facilityMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public FacilityDto findById(String id) {
        Optional<Facility> facility = repository.findById(id);

        System.out.println(" The Actual Facility retrieved===" + facility.get());

        return facilityMapper.toDto(facility.orElseThrow(ResourceNotFoundException::new));
    }

    public Facility finEntitydById(String id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<FacilityDto> findByCondition(FacilityDto facilityDto, Pageable pageable) {
        Page<Facility> entityPage = repository.findAll(pageable);
        List<Facility> entities = entityPage.getContent();
        return new PageImpl<>(facilityMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public FacilityDto update(FacilityDto facilityDto, String id) {
        FacilityDto data = findById(id);
        Facility entity = facilityMapper.toEntity(facilityDto);
        BeanUtils.copyProperties(data, entity);
        return save(facilityMapper.toDto(entity));
    }
}
