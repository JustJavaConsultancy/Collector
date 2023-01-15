package ng.com.sokoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class InternalAccountBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static InternalAccountDto getDto() {
        InternalAccountDto dto = new InternalAccountDto();
        dto.setId("1");
        return dto;
    }
}
