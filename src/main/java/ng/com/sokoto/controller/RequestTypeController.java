package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.SubscriptionDto;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.util.AsyncUtil;
import ng.com.sokoto.web.domain.Request;
import ng.com.sokoto.web.dto.RequestTypeDto;
import ng.com.sokoto.web.service.RequestService;
import ng.com.sokoto.web.service.RequestTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/request-type")
@RestController
@Slf4j
@Api("request-type")
public class RequestTypeController {

    private final RequestTypeService requestTypeService;
    private final RequestService requestService;
    private final AsyncUtil asyncUtil;

    public RequestTypeController(RequestTypeService requestTypeService, RequestService requestService, AsyncUtil asyncUtil) {
        this.requestTypeService = requestTypeService;
        this.requestService = requestService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<ApiResponse>> save(@RequestBody @Validated RequestTypeDto requestTypeDto) {
        return asyncUtil.asyncMono(() -> {
            RequestTypeDto requestTypeDto1 = requestTypeService.save(requestTypeDto);
            ApiResponse<RequestTypeDto> apiResponse = new ApiResponse<>("Success", HttpStatus.OK.value(), requestTypeDto1);
            return ResponseEntity.ok(apiResponse);
        });
    }

    @PostMapping("request/init")
    public Mono<ResponseEntity<ApiResponse>> initiateRequest(@RequestBody @Validated SubscriptionDto subscriptionDto) {
        return asyncUtil.asyncMono(() -> {
            Request request = requestTypeService.initiateRequest(subscriptionDto);
            ApiResponse<Request> apiResponse = new ApiResponse<Request>("Success", HttpStatus.OK.value(), request);
            return ResponseEntity.ok(apiResponse);
        });
    }

    @GetMapping("request/subscriber/{login}")
    public Mono<ResponseEntity<ApiResponse>> initiateRequest(@PathVariable("login") String login) {
        return asyncUtil.asyncMono(() -> {
            List<Request> requests = requestService.findSubscriberRequest(login);
            ApiResponse<List<Request>> apiResponse = new ApiResponse<List<Request>>("Success", HttpStatus.OK.value(), requests);
            return ResponseEntity.ok(apiResponse);
        });
    }

    @GetMapping("request/approver/{login}")
    public Mono<ResponseEntity<ApiResponse>> awaitingApprovalRequest(@PathVariable("login") String login) {
        return asyncUtil.asyncMono(() -> {
            List<Request> requests = requestService.findRequestAwaitingApprover(login);

            ApiResponse<List<Request>> apiResponse = new ApiResponse<List<Request>>("Success", HttpStatus.OK.value(), requests);
            return ResponseEntity.ok(apiResponse);
        });
    }

    @PostMapping("request/approve/{requestId}")
    public Mono<ResponseEntity<ApiResponse>> approve(@PathVariable("requestId") String requestId) {
        return asyncUtil.asyncMono(() -> {
            Request request = requestTypeService.approve(requestId);
            ApiResponse<Request> apiResponse = new ApiResponse<Request>("Success", HttpStatus.OK.value(), request);
            return ResponseEntity.ok(apiResponse);
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<RequestTypeDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            RequestTypeDto requestType = requestTypeService.findById(id);
            return ResponseEntity.ok(requestType);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(requestTypeService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            requestTypeService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<RequestTypeDto>>> pageQuery(
        RequestTypeDto requestTypeDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<RequestTypeDto> requestTypePage = requestTypeService.findByCondition(requestTypeDto, pageable);
            return ResponseEntity.ok(requestTypePage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated RequestTypeDto requestTypeDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            requestTypeService.update(requestTypeDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
