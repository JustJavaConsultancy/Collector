package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.InvoiceDto;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.service.InvoiceService;
import ng.com.sokoto.util.AsyncUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/invoice")
@RestController
@Slf4j
@Api("invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final AsyncUtil asyncUtil;

    public InvoiceController(InvoiceService invoiceService, AsyncUtil asyncUtil) {
        this.invoiceService = invoiceService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated InvoiceDto invoiceDto) {
        return asyncUtil.asyncMono(() -> {
            invoiceService.save(invoiceDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<InvoiceDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            InvoiceDto invoice = invoiceService.findById(id);
            return ResponseEntity.ok(invoice);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(invoiceService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            invoiceService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<InvoiceDto>>> pageQuery(
        InvoiceDto invoiceDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<InvoiceDto> invoicePage = invoiceService.findByCondition(invoiceDto, pageable);
            return ResponseEntity.ok(invoicePage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated InvoiceDto invoiceDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            invoiceService.update(invoiceDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
