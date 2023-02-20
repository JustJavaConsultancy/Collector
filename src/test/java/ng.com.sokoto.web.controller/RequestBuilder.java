package ng.com.sokoto.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import ng.com.sokoto.web.dto.RequestDto;

public class RequestBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static RequestDto getDto() {
        RequestDto dto = new RequestDto();
        dto.setId("1");
        return dto;
    }
}
