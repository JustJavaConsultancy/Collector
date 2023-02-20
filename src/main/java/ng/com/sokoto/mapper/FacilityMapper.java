package ng.com.sokoto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ng.com.sokoto.dto.FacilityDto;
import ng.com.sokoto.service.FacilityTypeService;
import ng.com.sokoto.service.PayItemTypeService;
import ng.com.sokoto.web.domain.Facility;
import ng.com.sokoto.web.domain.PayItemType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class FacilityMapper {

    @Autowired
    FacilityTypeService facilityTypeService;

    @Autowired
    PayItemTypeService payItemTypeService;

    @Autowired
    PayItemTypeMapper payItemTypeMapper;

    @Mapping(target = "category", expression = "java(facilityTypeService.finEntitydById(facilityDto.getCategory()))")
    public abstract Facility toEntity(FacilityDto facilityDto);

    @Mapping(target = "category", source = "category.id")
    public abstract FacilityDto toDto(Facility facility);

    public abstract List<Facility> toEntity(List<FacilityDto> facilityDtos);

    public abstract List<FacilityDto> toDto(List<Facility> facility);

    public Collection<PayItemType> map(Collection<String> values) {
        ArrayList<PayItemType> payItemTypes = new ArrayList<>();
        for (String id : values) {
            payItemTypes.add(payItemTypeMapper.toEntity(payItemTypeService.findById(Long.valueOf(id))));
        }

        return payItemTypes;
    }

    public Collection<String> mapping(Collection<PayItemType> values) {
        ArrayList<String> payItemTypes = new ArrayList<>();
        for (PayItemType payItemType : values) {
            payItemTypes.add(String.valueOf(payItemType.getId()));
        }
        return payItemTypes;
    }
}
