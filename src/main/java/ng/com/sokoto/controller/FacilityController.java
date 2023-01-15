package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.FacilityDto;
import ng.com.sokoto.service.FacilityService;
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

@RequestMapping("/api/facilities")
@RestController
@Slf4j
@Api("facilities")
public class FacilityController {

    private final FacilityService facilityService;
    private final AsyncUtil asyncUtil;

    public FacilityController(FacilityService facilityService, AsyncUtil asyncUtil) {
        this.facilityService = facilityService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated FacilityDto facilityDto) {
        return asyncUtil.asyncMono(() -> {
            facilityService.save(facilityDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<FacilityDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            FacilityDto facility = facilityService.findById(id);
            return ResponseEntity.ok(facility);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(facilityService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            facilityService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<FacilityDto>>> pageQuery(
        FacilityDto facilityDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<FacilityDto> facilityPage = facilityService.findByCondition(facilityDto, pageable);
            return ResponseEntity.ok(facilityPage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated FacilityDto facilityDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            facilityService.update(facilityDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
