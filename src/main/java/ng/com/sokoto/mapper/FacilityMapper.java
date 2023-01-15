package ng.com.sokoto.mapper;

import java.util.List;
import ng.com.sokoto.dto.FacilityDto;
import ng.com.sokoto.service.FacilityTypeService;
import ng.com.sokoto.web.domain.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class FacilityMapper {

    @Autowired
    FacilityTypeService facilityTypeService;

    @Mapping(target = "category", expression = "java(facilityTypeService.finEntitydById(facilityDto.getCategory()))")
    public abstract Facility toEntity(FacilityDto facilityDto);

    @Mapping(target = "category", source = "category.id")
    public abstract FacilityDto toDto(Facility facility);

    public abstract List<Facility> toEntity(List<FacilityDto> facilityDtos);

    public abstract List<FacilityDto> toDto(List<Facility> facility);
}
