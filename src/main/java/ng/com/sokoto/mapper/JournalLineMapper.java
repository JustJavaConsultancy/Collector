package ng.com.sokoto.mapper;

import ng.com.sokoto.dto.JournalLineDto;
import ng.com.sokoto.web.domain.JournalLine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JournalLineMapper extends EntityMapper<JournalLineDto, JournalLine> {}
