package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.JournalDto;
import ng.com.sokoto.mapper.JournalMapper;
import ng.com.sokoto.repository.JournalRepository;
import ng.com.sokoto.web.domain.Journal;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class JournalService {

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
}
