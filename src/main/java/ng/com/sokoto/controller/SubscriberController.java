package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.SubscriberDto;
import ng.com.sokoto.service.ResourceNotFoundException;
import ng.com.sokoto.service.SubscriberService;
import ng.com.sokoto.util.AsyncUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/subscriber")
@RestController
@Slf4j
@Api("subscriber")
public class SubscriberController {

    private final SubscriberService subscriberService;
    private final AsyncUtil asyncUtil;

    public SubscriberController(SubscriberService subscriberService, AsyncUtil asyncUtil) {
        this.subscriberService = subscriberService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated SubscriberDto subscriberDto) {
        return asyncUtil.asyncMono(() -> {
            subscriberService.save(subscriberDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<SubscriberDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            SubscriberDto subscriber = subscriberService.findById(id);
            return ResponseEntity.ok(subscriber);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(subscriberService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            subscriberService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<SubscriberDto>>> pageQuery(
        SubscriberDto subscriberDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<SubscriberDto> subscriberPage = subscriberService.findByCondition(subscriberDto, pageable);
            return ResponseEntity.ok(subscriberPage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated SubscriberDto subscriberDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            subscriberService.update(subscriberDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
