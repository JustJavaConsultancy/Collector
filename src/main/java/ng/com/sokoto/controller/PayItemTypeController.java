package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.PayItemTypeDto;
import ng.com.sokoto.service.PayItemTypeService;
import ng.com.sokoto.service.ResourceNotFoundException;
import ng.com.sokoto.util.AsyncUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/pay-item-type")
@RestController
@Slf4j
@Api("pay-item-type")
public class PayItemTypeController {

    private final PayItemTypeService payItemTypeService;
    private final AsyncUtil asyncUtil;

    public PayItemTypeController(PayItemTypeService payItemTypeService, AsyncUtil asyncUtil) {
        this.payItemTypeService = payItemTypeService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated PayItemTypeDto payItemTypeDto) {
        return asyncUtil.asyncMono(() -> {
            payItemTypeService.save(payItemTypeDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PayItemTypeDto>> findById(@PathVariable("id") Long id) {
        return asyncUtil.asyncMono(() -> {
            PayItemTypeDto payItemType = payItemTypeService.findById(id);
            return ResponseEntity.ok(payItemType);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Long id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(payItemTypeService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            payItemTypeService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<PayItemTypeDto>>> pageQuery(
        PayItemTypeDto payItemTypeDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<PayItemTypeDto> payItemTypePage = payItemTypeService.findByCondition(payItemTypeDto, pageable);
            return ResponseEntity.ok(payItemTypePage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated PayItemTypeDto payItemTypeDto, @PathVariable("id") Long id) {
        return asyncUtil.asyncMono(() -> {
            payItemTypeService.update(payItemTypeDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
