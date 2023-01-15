package ng.com.sokoto.mapper;

import ng.com.sokoto.dto.SubscriberDto;
import ng.com.sokoto.web.domain.Subscriber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriberMapper extends EntityMapper<SubscriberDto, Subscriber> {}
