package ng.com.sokoto.service;

import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.JournalDto;
import ng.com.sokoto.mapper.JournalMapper;
import ng.com.sokoto.repository.JournalRepository;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.web.domain.Journal;
import ng.com.sokoto.web.domain.JournalLine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class JournalService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    private final JournalRepository repository;
    private final JournalMapper journalMapper;

    public JournalService(JournalRepository repository, JournalMapper journalMapper) {
        this.repository = repository;
        this.journalMapper = journalMapper;
    }

    public JournalDto save(JournalDto journalDto) {
        Journal entity = journalMapper.toEntity(journalDto);
        return journalMapper.toDto(repository.save(entity));
    }

    public Journal save(Journal journal) {
        return repository.save(journal);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public JournalDto findById(String id) {
        return journalMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<JournalDto> findByCondition(JournalDto journalDto, Pageable pageable) {
        Page<Journal> entityPage = repository.findAll(pageable);
        List<Journal> entities = entityPage.getContent();
        return new PageImpl<>(journalMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public JournalDto update(JournalDto journalDto, String id) {
        JournalDto data = findById(id);
        Journal entity = journalMapper.toEntity(journalDto);
        BeanUtils.copyProperties(data, entity);
        return save(journalMapper.toDto(entity));
    }

    public Journal createJournal(String description, String refSuffice, List<JournalLine> lines) {
        Journal journal = new Journal();
        String id = String.valueOf(StringUtils.leftPad("" + sequenceGeneratorService.generateSequence(Journal.SEQUENCE_NAME), 5, "0"));

        journal.setDescription(description);
        journal.setRefNumber(id + "_" + refSuffice);
        journal.setEntryDate(LocalDate.now());
        journal.setJournalLines(lines);
        journal = save(journal);
        return journal;
    }
}
