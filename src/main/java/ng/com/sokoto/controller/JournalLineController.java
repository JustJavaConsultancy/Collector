package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.JournalLineDto;
import ng.com.sokoto.service.JournalLineService;
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

@RequestMapping("/journal-line")
@RestController
@Slf4j
@Api("journal-line")
public class JournalLineController {

    private final JournalLineService journalLineService;
    private final AsyncUtil asyncUtil;

    public JournalLineController(JournalLineService journalLineService, AsyncUtil asyncUtil) {
        this.journalLineService = journalLineService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated JournalLineDto journalLineDto) {
        return asyncUtil.asyncMono(() -> {
            journalLineService.save(journalLineDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<JournalLineDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            JournalLineDto journalLine = journalLineService.findById(id);
            return ResponseEntity.ok(journalLine);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(journalLineService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            journalLineService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<JournalLineDto>>> pageQuery(
        JournalLineDto journalLineDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<JournalLineDto> journalLinePage = journalLineService.findByCondition(journalLineDto, pageable);
            return ResponseEntity.ok(journalLinePage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated JournalLineDto journalLineDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            journalLineService.update(journalLineDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
