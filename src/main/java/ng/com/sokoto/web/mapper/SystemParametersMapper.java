package ng.com.sokoto.web.mapper;

import ng.com.sokoto.mapper.EntityMapper;
import ng.com.sokoto.web.domain.SystemParameters;
import ng.com.sokoto.web.dto.SystemParametersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SystemParametersMapper extends EntityMapper<SystemParametersDto, SystemParameters> {}
