package ng.com.sokoto.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import ng.com.sokoto.web.dto.RequestTypeDto;

public class RequestTypeBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static RequestTypeDto getDto() {
        RequestTypeDto dto = new RequestTypeDto();
        dto.setId("1");
        return dto;
    }
}
