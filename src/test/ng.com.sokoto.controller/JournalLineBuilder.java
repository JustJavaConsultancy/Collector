package ng.com.sokoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JournalLineBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static JournalLineDto getDto() {
        JournalLineDto dto = new JournalLineDto();
        dto.setId("1");
        return dto;
    }
}
