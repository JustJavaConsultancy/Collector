package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.InternalAccountDto;
import ng.com.sokoto.service.InternalAccountService;
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

@RequestMapping("/internal-account")
@RestController
@Slf4j
@Api("internal-account")
public class InternalAccountController {

    private final InternalAccountService internalAccountService;
    private final AsyncUtil asyncUtil;

    public InternalAccountController(InternalAccountService internalAccountService, AsyncUtil asyncUtil) {
        this.internalAccountService = internalAccountService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated InternalAccountDto internalAccountDto) {
        return asyncUtil.asyncMono(() -> {
            internalAccountService.save(internalAccountDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<InternalAccountDto>> findById(@PathVariable("id") Long id) {
        return asyncUtil.asyncMono(() -> {
            InternalAccountDto internalAccount = internalAccountService.findById(id);
            return ResponseEntity.ok(internalAccount);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Long id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(internalAccountService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            internalAccountService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<InternalAccountDto>>> pageQuery(
        InternalAccountDto internalAccountDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<InternalAccountDto> internalAccountPage = internalAccountService.findByCondition(internalAccountDto, pageable);
            return ResponseEntity.ok(internalAccountPage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated InternalAccountDto internalAccountDto, @PathVariable("id") Long id) {
        return asyncUtil.asyncMono(() -> {
            internalAccountService.update(internalAccountDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
