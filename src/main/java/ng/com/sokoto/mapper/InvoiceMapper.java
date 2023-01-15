package ng.com.sokoto.mapper;

import ng.com.sokoto.dto.InvoiceDto;
import ng.com.sokoto.web.domain.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper extends EntityMapper<InvoiceDto, Invoice> {}
