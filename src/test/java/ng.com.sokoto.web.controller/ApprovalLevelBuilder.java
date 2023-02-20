package ng.com.sokoto.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import ng.com.sokoto.web.dto.ApprovalLevelDto;

public class ApprovalLevelBuilder {

    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ApprovalLevelDto getDto() {
        ApprovalLevelDto dto = new ApprovalLevelDto();
        dto.setId("1");
        return dto;
    }
}
