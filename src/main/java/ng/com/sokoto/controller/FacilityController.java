package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.FacilityDto;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.service.FacilityService;
import ng.com.sokoto.util.AsyncUtil;
import ng.com.sokoto.web.domain.Facility;
import ng.com.sokoto.web.domain.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api")
@RestController
@Slf4j
@Api("facility")
public class FacilityController {

    private final FacilityService facilityService;
    private final AsyncUtil asyncUtil;

    public FacilityController(FacilityService facilityService, AsyncUtil asyncUtil) {
        this.facilityService = facilityService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping("/facility")
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated FacilityDto facilityDto) {
        return asyncUtil.asyncMono(() -> {
            facilityService.save(facilityDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/facility/{id}")
    public Mono<ResponseEntity<FacilityDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            FacilityDto facility = facilityService.findById(id);
            return ResponseEntity.ok(facility);
        });
    }

    @DeleteMapping("/facility/{id}")
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

    @GetMapping("/facilities")
    public Mono<ResponseEntity<Page<Facility>>> pageQuery(
        FacilityDto facilityDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        /*        System.out.println(" The pageable here===Page Number"+pageable.getPageNumber()
        +"  Page Size=="+pageable.getPageSize());*/
        return asyncUtil.asyncMono(() -> {
            Page<Facility> facilityPage = facilityService.findByCondition2(facilityDto, pageable);
            return ResponseEntity.ok(facilityPage);
        });
    }

    @GetMapping("/facilitiesbystatus/{status}")
    public Mono<ResponseEntity<Page<Facility>>> pageQueryByStatus(
        @PathVariable("status") StatusEnum status,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<Facility> facilityPage = facilityService.findByStatus(status, pageable);
            return ResponseEntity.ok(facilityPage);
        });
    }

    @PutMapping("/facility/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated FacilityDto facilityDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            facilityService.update(facilityDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
