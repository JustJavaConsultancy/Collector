package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.FacilityTypeDto;
import ng.com.sokoto.service.FacilityTypeService;
import ng.com.sokoto.service.ResourceNotFoundException;
import ng.com.sokoto.util.AsyncUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/facility-type")
@RestController
@Slf4j
@Api("facility-type")
public class FacilityTypeController {

    private final FacilityTypeService facilityTypeService;
    private final AsyncUtil asyncUtil;

    public FacilityTypeController(FacilityTypeService facilityTypeService, AsyncUtil asyncUtil) {
        this.facilityTypeService = facilityTypeService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated FacilityTypeDto facilityTypeDto) {
        return asyncUtil.asyncMono(() -> {
            facilityTypeService.save(facilityTypeDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<FacilityTypeDto>>> findById(@PathVariable("id") String id) {
        System.out.println(" The Sent Parameter inside Controller=====" + id);
        return asyncUtil.asyncMono(() -> {
            FacilityTypeDto facilityType = facilityTypeService.findById(id);
            ApiResponse<FacilityTypeDto> apiResponse = new ApiResponse<FacilityTypeDto>("Success", HttpStatus.OK.value(), facilityType);
            return ResponseEntity.ok(apiResponse);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(facilityTypeService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            facilityTypeService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<FacilityTypeDto>>> pageQuery(
        FacilityTypeDto facilityTypeDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<FacilityTypeDto> facilityTypePage = facilityTypeService.findByCondition(facilityTypeDto, pageable);
            return ResponseEntity.ok(facilityTypePage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated FacilityTypeDto facilityTypeDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            facilityTypeService.update(facilityTypeDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
