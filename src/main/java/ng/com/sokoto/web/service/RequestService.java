package ng.com.sokoto.web.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.repository.RequestRepository;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.web.domain.Request;
import ng.com.sokoto.web.dto.RequestDto;
import ng.com.sokoto.web.mapper.RequestMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RequestService {

    private final RequestRepository repository;
    private final RequestMapper requestMapper;

    public RequestService(RequestRepository repository, RequestMapper requestMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
    }

    public RequestDto save(RequestDto requestDto) {
        Request entity = requestMapper.toEntity(requestDto);
        return requestMapper.toDto(repository.save(entity));
    }

    public Request save(Request request) {
        return repository.save(request);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public RequestDto findById(String id) {
        return requestMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<RequestDto> findByCondition(RequestDto requestDto, Pageable pageable) {
        Page<Request> entityPage = repository.findAll(pageable);
        List<Request> entities = entityPage.getContent();
        return new PageImpl<>(requestMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public RequestDto update(RequestDto requestDto, String id) {
        RequestDto data = findById(id);
        Request entity = requestMapper.toEntity(requestDto);
        BeanUtils.copyProperties(data, entity);
        return save(requestMapper.toDto(entity));
    }

    public List<Request> findSubscriberRequest(String subscriber) {
        return repository.findByRequestOwner(subscriber);
    }

    public List<Request> findRequestAwaitingApprover(String approver) {
        return repository.findByCurrentApprover(approver);
    }
}
