package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.InvoiceDto;
import ng.com.sokoto.mapper.InvoiceMapper;
import ng.com.sokoto.repository.InvoiceRepository;
import ng.com.sokoto.web.domain.Invoice;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class InvoiceService {

    private final InvoiceRepository repository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceService(InvoiceRepository repository, InvoiceMapper invoiceMapper) {
        this.repository = repository;
        this.invoiceMapper = invoiceMapper;
    }

    public InvoiceDto save(InvoiceDto invoiceDto) {
        Invoice entity = invoiceMapper.toEntity(invoiceDto);
        return invoiceMapper.toDto(repository.save(entity));
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public InvoiceDto findById(String id) {
        return invoiceMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<InvoiceDto> findByCondition(InvoiceDto invoiceDto, Pageable pageable) {
        Page<Invoice> entityPage = repository.findAll(pageable);
        List<Invoice> entities = entityPage.getContent();
        return new PageImpl<>(invoiceMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public InvoiceDto update(InvoiceDto invoiceDto, String id) {
        InvoiceDto data = findById(id);
        Invoice entity = invoiceMapper.toEntity(invoiceDto);
        BeanUtils.copyProperties(data, entity);
        return save(invoiceMapper.toDto(entity));
    }
}
