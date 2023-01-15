package ng.com.sokoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import ng.com.sokoto.dto.JournalDto;

public class JournalBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static JournalDto getDto() {
        JournalDto dto = new JournalDto();
        dto.setId("1");
        return dto;
    }
}
