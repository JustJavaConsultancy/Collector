package ng.com.sokoto.web.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.repository.ApprovalRouteRepository;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.web.domain.ApprovalRoute;
import ng.com.sokoto.web.dto.ApprovalRouteDto;
import ng.com.sokoto.web.mapper.ApprovalRouteMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ApprovalRouteService {

    private final ApprovalRouteRepository repository;
    private final ApprovalRouteMapper approvalRouteMapper;

    public ApprovalRouteService(ApprovalRouteRepository repository, ApprovalRouteMapper approvalRouteMapper) {
        this.repository = repository;
        this.approvalRouteMapper = approvalRouteMapper;
    }

    public ApprovalRouteDto save(ApprovalRouteDto approvalRouteDto) {
        ApprovalRoute entity = approvalRouteMapper.toEntity(approvalRouteDto);
        return approvalRouteMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public ApprovalRouteDto findById(String id) {
        return approvalRouteMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ApprovalRouteDto> findByCondition(ApprovalRouteDto approvalRouteDto, Pageable pageable) {
        Page<ApprovalRoute> entityPage = repository.findAll(pageable);
        List<ApprovalRoute> entities = entityPage.getContent();
        return new PageImpl<>(approvalRouteMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ApprovalRouteDto update(ApprovalRouteDto approvalRouteDto, String id) {
        ApprovalRouteDto data = findById(id);
        ApprovalRoute entity = approvalRouteMapper.toEntity(approvalRouteDto);
        BeanUtils.copyProperties(data, entity);
        return save(approvalRouteMapper.toDto(entity));
    }
}
