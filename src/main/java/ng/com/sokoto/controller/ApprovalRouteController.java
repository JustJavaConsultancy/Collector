package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.util.AsyncUtil;
import ng.com.sokoto.web.dto.ApprovalRouteDto;
import ng.com.sokoto.web.dto.RequestTypeDto;
import ng.com.sokoto.web.service.ApprovalRouteService;
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

@RequestMapping("/api/approval-route")
@RestController
@Slf4j
@Api("approval-route")
public class ApprovalRouteController {

    private final ApprovalRouteService approvalRouteService;
    private final RequestTypeService requestTypeService;
    private final AsyncUtil asyncUtil;

    public ApprovalRouteController(ApprovalRouteService approvalRouteService, RequestTypeService requestTypeService, AsyncUtil asyncUtil) {
        this.approvalRouteService = approvalRouteService;
        this.requestTypeService = requestTypeService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<ApiResponse>> save(@RequestBody @Validated ApprovalRouteDto approvalRouteDto) {
        return asyncUtil.asyncMono(() -> {
            RequestTypeDto requestTypeDto = requestTypeService.addApprovalRoute(approvalRouteDto);
            ApiResponse<RequestTypeDto> apiResponse = new ApiResponse<>("Success", HttpStatus.OK.value(), requestTypeDto);
            return ResponseEntity.ok(apiResponse);
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApprovalRouteDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            ApprovalRouteDto approvalRoute = approvalRouteService.findById(id);
            return ResponseEntity.ok(approvalRoute);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(approvalRouteService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            approvalRouteService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<ApprovalRouteDto>>> pageQuery(
        ApprovalRouteDto approvalRouteDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<ApprovalRouteDto> approvalRoutePage = approvalRouteService.findByCondition(approvalRouteDto, pageable);
            return ResponseEntity.ok(approvalRoutePage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated ApprovalRouteDto approvalRouteDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            approvalRouteService.update(approvalRouteDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
