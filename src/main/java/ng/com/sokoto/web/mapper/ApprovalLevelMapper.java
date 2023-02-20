package ng.com.sokoto.web.mapper;

import ng.com.sokoto.mapper.EntityMapper;
import ng.com.sokoto.web.domain.ApprovalLevel;
import ng.com.sokoto.web.dto.ApprovalLevelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApprovalLevelMapper extends EntityMapper<ApprovalLevelDto, ApprovalLevel> {}
