package ng.com.sokoto.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import ng.com.sokoto.web.dto.ApprovalRouteDto;

public class ApprovalRouteBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ApprovalRouteDto getDto() {
        ApprovalRouteDto dto = new ApprovalRouteDto();
        dto.setId("1");
        return dto;
    }
}
