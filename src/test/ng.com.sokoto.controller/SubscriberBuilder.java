package ng.com.sokoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class SubscriberBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static SubscriberDto getDto() {
        SubscriberDto dto = new SubscriberDto();
        dto.setId("1");
        return dto;
    }
}
