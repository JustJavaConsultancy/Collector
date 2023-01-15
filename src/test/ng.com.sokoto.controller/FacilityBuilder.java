package ng.com.sokoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import ng.com.sokoto.dto.FacilityDto;

public class FacilityBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static FacilityDto getDto() {
        FacilityDto dto = new FacilityDto();
        dto.setId("1");
        return dto;
    }
}
