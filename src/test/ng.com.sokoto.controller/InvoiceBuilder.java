package ng.com.sokoto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class InvoiceBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static InvoiceDto getDto() {
        InvoiceDto dto = new InvoiceDto();
        dto.setId("1");
        return dto;
    }
}
