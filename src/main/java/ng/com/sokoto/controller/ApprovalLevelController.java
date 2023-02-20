package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.util.AsyncUtil;
import ng.com.sokoto.web.dto.ApprovalLevelDto;
import ng.com.sokoto.web.service.ApprovalLevelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/approval-level")
@RestController
@Slf4j
@Api("approval-level")
public class ApprovalLevelController {

    private final ApprovalLevelService approvalLevelService;
    private final AsyncUtil asyncUtil;

    public ApprovalLevelController(ApprovalLevelService approvalLevelService, AsyncUtil asyncUtil) {
        this.approvalLevelService = approvalLevelService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated ApprovalLevelDto approvalLevelDto) {
        return asyncUtil.asyncMono(() -> {
            approvalLevelService.save(approvalLevelDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApprovalLevelDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            ApprovalLevelDto approvalLevel = approvalLevelService.findById(id);
            return ResponseEntity.ok(approvalLevel);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(approvalLevelService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            approvalLevelService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<ApprovalLevelDto>>> pageQuery(
        ApprovalLevelDto approvalLevelDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<ApprovalLevelDto> approvalLevelPage = approvalLevelService.findByCondition(approvalLevelDto, pageable);
            return ResponseEntity.ok(approvalLevelPage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated ApprovalLevelDto approvalLevelDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            approvalLevelService.update(approvalLevelDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
