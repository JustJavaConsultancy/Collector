package ng.com.sokoto.mapper;

import ng.com.sokoto.dto.InternalAccountDto;
import ng.com.sokoto.web.domain.InternalAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternalAccountMapper extends EntityMapper<InternalAccountDto, InternalAccount> {}
