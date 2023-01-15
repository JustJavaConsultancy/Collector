package ng.com.sokoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import ng.com.sokoto.dto.PayItemTypeDto;

public class PayItemTypeBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static PayItemTypeDto getDto() {
        PayItemTypeDto dto = new PayItemTypeDto();
        dto.setId(Long.valueOf("1"));
        return dto;
    }
}
