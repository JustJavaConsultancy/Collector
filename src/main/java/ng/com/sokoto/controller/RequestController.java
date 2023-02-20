package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.util.AsyncUtil;
import ng.com.sokoto.web.dto.RequestDto;
import ng.com.sokoto.web.service.RequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/request")
@RestController
@Slf4j
@Api("request")
public class RequestController {

    private final RequestService requestService;
    private final AsyncUtil asyncUtil;

    public RequestController(RequestService requestService, AsyncUtil asyncUtil) {
        this.requestService = requestService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated RequestDto requestDto) {
        return asyncUtil.asyncMono(() -> {
            requestService.save(requestDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<RequestDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            RequestDto request = requestService.findById(id);
            return ResponseEntity.ok(request);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(requestService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            requestService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<RequestDto>>> pageQuery(
        RequestDto requestDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<RequestDto> requestPage = requestService.findByCondition(requestDto, pageable);
            return ResponseEntity.ok(requestPage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated RequestDto requestDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            requestService.update(requestDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
