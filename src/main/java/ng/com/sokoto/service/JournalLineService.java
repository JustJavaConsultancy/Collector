package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.JournalLineDto;
import ng.com.sokoto.mapper.JournalLineMapper;
import ng.com.sokoto.repository.JournalLineRepository;
import ng.com.sokoto.web.domain.JournalLine;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class JournalLineService {

    private final JournalLineRepository repository;
    private final JournalLineMapper journalLineMapper;

    public JournalLineService(JournalLineRepository repository, JournalLineMapper journalLineMapper) {
        this.repository = repository;
        this.journalLineMapper = journalLineMapper;
    }

    public JournalLineDto save(JournalLineDto journalLineDto) {
        JournalLine entity = journalLineMapper.toEntity(journalLineDto);
        return journalLineMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public JournalLineDto findById(String id) {
        return journalLineMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<JournalLineDto> findByCondition(JournalLineDto journalLineDto, Pageable pageable) {
        Page<JournalLine> entityPage = repository.findAll(pageable);
        List<JournalLine> entities = entityPage.getContent();
        return new PageImpl<>(journalLineMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public JournalLineDto update(JournalLineDto journalLineDto, String id) {
        JournalLineDto data = findById(id);
        JournalLine entity = journalLineMapper.toEntity(journalLineDto);
        BeanUtils.copyProperties(data, entity);
        return save(journalLineMapper.toDto(entity));
    }
}
