package ng.com.sokoto.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import ng.com.sokoto.web.dto.SystemParametersDto;

public class SystemParametersBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static SystemParametersDto getDto() {
        SystemParametersDto dto = new SystemParametersDto();
        dto.setId("1");
        return dto;
    }
}
