package ng.com.sokoto.mapper;

import ng.com.sokoto.dto.JournalDto;
import ng.com.sokoto.web.domain.Journal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JournalMapper extends EntityMapper<JournalDto, Journal> {}
