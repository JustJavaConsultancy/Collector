package ng.com.sokoto.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class SubscriptionBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static SubscriptionDto getDto() {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setId("1");
        return dto;
    }
}
