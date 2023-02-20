package ng.com.sokoto.web.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.SubscriptionDto;
import ng.com.sokoto.mapper.SubscriptionMapper;
import ng.com.sokoto.repository.RequestTypeRepository;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.service.FacilityService;
import ng.com.sokoto.service.InvoiceManager;
import ng.com.sokoto.service.SubscriptionService;
import ng.com.sokoto.service.event.EventMessage;
import ng.com.sokoto.web.domain.*;
import ng.com.sokoto.web.domain.enumeration.StatusEnum;
import ng.com.sokoto.web.dto.ApprovalRouteDto;
import ng.com.sokoto.web.dto.RequestTypeDto;
import ng.com.sokoto.web.mapper.ApprovalRouteMapper;
import ng.com.sokoto.web.mapper.RequestMapper;
import ng.com.sokoto.web.mapper.RequestTypeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RequestTypeService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private final InvoiceManager invoiceManager;
    private final RequestTypeRepository repository;
    private final RequestTypeMapper requestTypeMapper;
    private final ApprovalRouteMapper approvalRouteMapper;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionService subscriptionService;

    private final RequestService requestService;
    private final RequestMapper requestMapper;
    private final FacilityService facilityService;

    public RequestTypeService(
        RequestTypeRepository repository,
        RequestTypeMapper requestTypeMapper,
        ApprovalRouteMapper approvalRouteMapper,
        SubscriptionMapper subscriptionMapper,
        SubscriptionService subscriptionService,
        InvoiceManager invoiceManager,
        RequestService requestService,
        RequestMapper requestMapper,
        FacilityService facilityService
    ) {
        this.repository = repository;
        this.requestTypeMapper = requestTypeMapper;
        this.approvalRouteMapper = approvalRouteMapper;
        this.subscriptionMapper = subscriptionMapper;
        this.subscriptionService = subscriptionService;
        this.invoiceManager = invoiceManager;
        this.requestService = requestService;
        this.requestMapper = requestMapper;

        this.facilityService = facilityService;
    }

    public RequestTypeDto save(RequestTypeDto requestTypeDto) {
        RequestType entity = requestTypeMapper.toEntity(requestTypeDto);
        return requestTypeMapper.toDto(repository.save(entity));
    }

    public RequestType save(RequestType requestType) {
        return repository.save(requestType);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public RequestTypeDto findById(String id) {
        return requestTypeMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<RequestTypeDto> findByCondition(RequestTypeDto requestTypeDto, Pageable pageable) {
        Page<RequestType> entityPage = repository.findAll(pageable);
        List<RequestType> entities = entityPage.getContent();
        return new PageImpl<>(requestTypeMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public RequestTypeDto update(RequestTypeDto requestTypeDto, String id) {
        RequestTypeDto data = findById(id);
        RequestType entity = requestTypeMapper.toEntity(requestTypeDto);
        BeanUtils.copyProperties(data, entity);
        return save(requestTypeMapper.toDto(entity));
    }

    @Transactional
    public RequestTypeDto addApprovalRoute(ApprovalRouteDto approvalRouteDto) {
        RequestTypeDto requestTypeDto = findById(approvalRouteDto.getRequestTypeID());
        RequestType requestType = requestTypeMapper.toEntity(requestTypeDto);
        ApprovalRoute approvalRoute = approvalRouteMapper.toEntity(approvalRouteDto);
        approvalRoute.setId(requestType.getId());
        requestType = requestType.addApprovalRoute(approvalRoute);
        requestType = save(requestType);
        return requestTypeMapper.toDto(requestType);
    }

    public Request initiateRequest(SubscriptionDto subscriptionDto) {
        RequestType requestType = requestTypeMapper.toEntity(findById(subscriptionDto.getTransType()));

        Request request = requestType.initiateRequest(subscriptionMapper.toEntity(subscriptionDto));
        request = requestService.save(request);
        return request;
    }

    public Request approve(String requestID) {
        Request request = requestMapper.toEntity(requestService.findById(requestID));
        RequestType requestType = requestTypeMapper.toEntity(findById(request.getRequestType()));
        request = requestType.approveRequest(request);
        request = requestService.save(request);
        if ("approved".equalsIgnoreCase(request.getStatus())) {
            Subscription subscription = (Subscription) request.getPayload();
            //subscription=subscriptionService.save(subscription);
            Facility facility = subscription.getFacility();
            facility.setStatus(StatusEnum.Occupied);
            facilityService.save(facility);
            applicationEventPublisher.publishEvent(new EventMessage<Subscription>("Raise Invoice", subscription, invoiceManager));
        }
        return request;
    }
}
