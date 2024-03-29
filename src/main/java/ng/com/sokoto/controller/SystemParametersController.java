package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.util.AsyncUtil;
import ng.com.sokoto.web.dto.SystemParametersDto;
import ng.com.sokoto.web.service.SystemParametersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/system-parameters")
@RestController
@Slf4j
@Api("system-parameters")
public class SystemParametersController {

    private final SystemParametersService systemParametersService;
    private final AsyncUtil asyncUtil;

    public SystemParametersController(SystemParametersService systemParametersService, AsyncUtil asyncUtil) {
        this.systemParametersService = systemParametersService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated SystemParametersDto systemParametersDto) {
        return asyncUtil.asyncMono(() -> {
            systemParametersService.save(systemParametersDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<SystemParametersDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            SystemParametersDto systemParameters = systemParametersService.findById(id);
            return ResponseEntity.ok(systemParameters);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(systemParametersService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            systemParametersService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<SystemParametersDto>>> pageQuery(
        SystemParametersDto systemParametersDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<SystemParametersDto> systemParametersPage = systemParametersService.findByCondition(systemParametersDto, pageable);
            return ResponseEntity.ok(systemParametersPage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(
        @RequestBody @Validated SystemParametersDto systemParametersDto,
        @PathVariable("id") String id
    ) {
        return asyncUtil.asyncMono(() -> {
            systemParametersService.update(systemParametersDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
