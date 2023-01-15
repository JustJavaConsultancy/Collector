package ng.com.sokoto.mapper;

import ng.com.sokoto.dto.FacilityTypeDto;
import ng.com.sokoto.web.domain.FacilityType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacilityTypeMapper extends EntityMapper<FacilityTypeDto, FacilityType> {}
