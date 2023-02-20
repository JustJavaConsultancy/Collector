package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import javax.ws.rs.QueryParam;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.PaymentDTO;
import ng.com.sokoto.dto.SubscriptionDto;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.service.SubscriptionService;
import ng.com.sokoto.util.AsyncUtil;
import ng.com.sokoto.web.domain.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/subscription")
@RestController
@Slf4j
@Api("subscription")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final AsyncUtil asyncUtil;

    public SubscriptionController(SubscriptionService subscriptionService, AsyncUtil asyncUtil) {
        this.subscriptionService = subscriptionService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated SubscriptionDto subscriptionDto) {
        return asyncUtil.asyncMono(() -> {
            subscriptionService.save(subscriptionDto);
            return ResponseEntity.ok().build();
        });
    }

    @PostMapping("/pay")
    public Mono<ResponseEntity<ApiResponse>> makePayment(@RequestBody PaymentDTO paymentDTO) {
        return asyncUtil.asyncMono(() -> {
            Subscription subscription = subscriptionService.makePayment(paymentDTO.getSubscriptionId(), paymentDTO.getAmount());
            ApiResponse<Subscription> apiResponse = new ApiResponse<Subscription>("Success", HttpStatus.OK.value(), subscription);
            return ResponseEntity.ok(apiResponse);
        });
    }

    @GetMapping("/single")
    public Mono<ResponseEntity<SubscriptionDto>> findById(@RequestParam("id") String id) {
        return asyncUtil.asyncMono(() -> {
            SubscriptionDto subscription = subscriptionService.findById(id);
            return ResponseEntity.ok(subscription);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(subscriptionService.findById(id))
                .orElseThrow(() -> {
                    // log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            subscriptionService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/fetch")
    public Mono<ResponseEntity<ApiResponse>> pageQuery(
        SubscriptionDto subscriptionDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<Subscription> subscriptionPage = subscriptionService.findByCondition(subscriptionDto, pageable);
            ApiResponse<Page<Subscription>> apiResponse = new ApiResponse<Page<Subscription>>(
                "Success",
                HttpStatus.OK.value(),
                subscriptionPage
            );
            return ResponseEntity.ok(apiResponse);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated SubscriptionDto subscriptionDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            subscriptionService.update(subscriptionDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
