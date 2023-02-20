package ng.com.sokoto.web.mapper;

import ng.com.sokoto.mapper.EntityMapper;
import ng.com.sokoto.web.domain.RequestType;
import ng.com.sokoto.web.dto.RequestTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestTypeMapper extends EntityMapper<RequestTypeDto, RequestType> {}
