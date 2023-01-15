package ng.com.sokoto.mapper;

import java.util.List;
import ng.com.sokoto.dto.FacilityDto;
import ng.com.sokoto.dto.SubscriberDto;
import ng.com.sokoto.dto.SubscriptionDto;
import ng.com.sokoto.mapper.EntityMapper;
import ng.com.sokoto.service.FacilityService;
import ng.com.sokoto.service.FacilityTypeService;
import ng.com.sokoto.service.SubscriberService;
import ng.com.sokoto.service.SubscriptionService;
import ng.com.sokoto.web.domain.Facility;
import ng.com.sokoto.web.domain.Subscriber;
import ng.com.sokoto.web.domain.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SubscriptionMapper {

    @Autowired
    SubscriberService subscriberService;

    @Autowired
    FacilityService facilityService;

    @Mapping(target = "subscriber", expression = "java(subscriberService.finEntitydById(subscriberDto.getSubscriber()))")
    @Mapping(target = "facility", expression = "java(facilityService.finEntitydById(subscriberDto.getFacility()))")
    public abstract Subscription toEntity(SubscriptionDto subscriberDto);

    @Mapping(target = "subscriber", source = "subscriber.id")
    @Mapping(target = "facility", source = "facility.id")
    public abstract SubscriptionDto toDto(Subscription subscriber);

    public abstract List<Subscription> toEntity(List<SubscriptionDto> subscriptionDtos);

    public abstract List<SubscriptionDto> toDto(List<Subscription> subscriptions);
}
