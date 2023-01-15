package ng.com.sokoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
//import ng.com.sokoto.dto.FacilityTypeDto;

import java.util.Collections;
import java.util.List;
import ng.com.sokoto.dto.FacilityTypeDto;

public class FacilityTypeBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static FacilityTypeDto getDto() {
        FacilityTypeDto dto = new FacilityTypeDto();
        dto.setId("1");
        return dto;
    }
}
