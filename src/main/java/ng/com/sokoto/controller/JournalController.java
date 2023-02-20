package ng.com.sokoto.controller;

import io.swagger.annotations.Api;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.JournalDto;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.service.JournalService;
import ng.com.sokoto.util.AsyncUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/journal")
@RestController
@Slf4j
@Api("journal")
public class JournalController {

    private final JournalService journalService;
    private final AsyncUtil asyncUtil;

    public JournalController(JournalService journalService, AsyncUtil asyncUtil) {
        this.journalService = journalService;
        this.asyncUtil = asyncUtil;
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> save(@RequestBody @Validated JournalDto journalDto) {
        return asyncUtil.asyncMono(() -> {
            journalService.save(journalDto);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<JournalDto>> findById(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            JournalDto journal = journalService.findById(id);
            return ResponseEntity.ok(journal);
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            Optional
                .ofNullable(journalService.findById(id))
                .orElseThrow(() -> {
                    //log.error("Unable to delete non-existent data！");
                    return new ResourceNotFoundException();
                });
            journalService.deleteById(id);
            return ResponseEntity.ok().build();
        });
    }

    @GetMapping("/page-query")
    public Mono<ResponseEntity<Page<JournalDto>>> pageQuery(
        JournalDto journalDto,
        @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return asyncUtil.asyncMono(() -> {
            Page<JournalDto> journalPage = journalService.findByCondition(journalDto, pageable);
            return ResponseEntity.ok(journalPage);
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(@RequestBody @Validated JournalDto journalDto, @PathVariable("id") String id) {
        return asyncUtil.asyncMono(() -> {
            journalService.update(journalDto, id);
            return ResponseEntity.ok().build();
        });
    }
}
