package ng.com.sokoto.web.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.repository.ApprovalLevelRepository;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.web.domain.ApprovalLevel;
import ng.com.sokoto.web.dto.ApprovalLevelDto;
import ng.com.sokoto.web.mapper.ApprovalLevelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ApprovalLevelService {

    private final ApprovalLevelRepository repository;
    private final ApprovalLevelMapper approvalLevelMapper;

    public ApprovalLevelService(ApprovalLevelRepository repository, ApprovalLevelMapper approvalLevelMapper) {
        this.repository = repository;
        this.approvalLevelMapper = approvalLevelMapper;
    }

    public ApprovalLevelDto save(ApprovalLevelDto approvalLevelDto) {
        ApprovalLevel entity = approvalLevelMapper.toEntity(approvalLevelDto);
        return approvalLevelMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public ApprovalLevelDto findById(String id) {
        return approvalLevelMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<ApprovalLevelDto> findByCondition(ApprovalLevelDto approvalLevelDto, Pageable pageable) {
        Page<ApprovalLevel> entityPage = repository.findAll(pageable);
        List<ApprovalLevel> entities = entityPage.getContent();
        return new PageImpl<>(approvalLevelMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ApprovalLevelDto update(ApprovalLevelDto approvalLevelDto, String id) {
        ApprovalLevelDto data = findById(id);
        ApprovalLevel entity = approvalLevelMapper.toEntity(approvalLevelDto);
        BeanUtils.copyProperties(data, entity);
        return save(approvalLevelMapper.toDto(entity));
    }
}
