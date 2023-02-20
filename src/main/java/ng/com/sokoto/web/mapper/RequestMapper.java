package ng.com.sokoto.web.mapper;

import ng.com.sokoto.mapper.EntityMapper;
import ng.com.sokoto.web.domain.Request;
import ng.com.sokoto.web.dto.RequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RequestMapper extends EntityMapper<RequestDto, Request> {}
