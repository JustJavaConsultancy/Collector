package ng.com.sokoto.mapper;

import ng.com.sokoto.dto.PayItemTypeDto;
import ng.com.sokoto.web.domain.PayItemType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PayItemTypeMapper extends EntityMapper<PayItemTypeDto, PayItemType> {}
