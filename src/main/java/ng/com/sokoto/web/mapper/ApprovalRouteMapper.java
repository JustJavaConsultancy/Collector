package ng.com.sokoto.web.mapper;

import ng.com.sokoto.mapper.EntityMapper;
import ng.com.sokoto.web.domain.ApprovalRoute;
import ng.com.sokoto.web.dto.ApprovalRouteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApprovalRouteMapper extends EntityMapper<ApprovalRouteDto, ApprovalRoute> {}
