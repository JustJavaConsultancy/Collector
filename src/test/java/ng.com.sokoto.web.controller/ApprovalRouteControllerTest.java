package ng.com.sokoto.web.controller;

import com.google.gson.Gson;
import java.util.Collections;
import ng.com.sokoto.controller.ApprovalRouteController;
import ng.com.sokoto.web.dto.ApprovalRouteDto;
import ng.com.sokoto.web.service.ApprovalRouteService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ApprovalRouteControllerTest {

    private static final String ENDPOINT_URL = "/api/approval-route";

    @InjectMocks
    private ApprovalRouteController approvalrouteController;

    @Mock
    private ApprovalRouteService approvalrouteService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc =
            MockMvcBuilders
                .standaloneSetup(approvalrouteController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<ApprovalRouteDto> page = new PageImpl<>(Collections.singletonList(ApprovalRouteBuilder.getDto()));

        Mockito.when(approvalrouteService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL).accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(approvalrouteService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(approvalrouteService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(approvalrouteService.findById(ArgumentMatchers.anyString())).thenReturn(ApprovalRouteBuilder.getDto());

        mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(approvalrouteService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(approvalrouteService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(approvalrouteService.save(ArgumentMatchers.any(ApprovalRouteDto.class))).thenReturn(ApprovalRouteBuilder.getDto());

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(ENDPOINT_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(ApprovalRouteBuilder.getDto()))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(approvalrouteService, Mockito.times(1)).save(ArgumentMatchers.any(ApprovalRouteDto.class));
        Mockito.verifyNoMoreInteractions(approvalrouteService);
    }

    @Test
    public void update() throws Exception {
        Mockito
            .when(approvalrouteService.update(ArgumentMatchers.any(), ArgumentMatchers.anyString()))
            .thenReturn(ApprovalRouteBuilder.getDto());

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .put(ENDPOINT_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(ApprovalRouteBuilder.getDto()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito
            .verify(approvalrouteService, Mockito.times(1))
            .update(ArgumentMatchers.any(ApprovalRouteDto.class), ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(approvalrouteService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(approvalrouteService).deleteById(ArgumentMatchers.anyString());
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .delete(ENDPOINT_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(ApprovalRouteBuilder.getIds()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(approvalrouteService, Mockito.times(1)).deleteById(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(approvalrouteService);
    }
}
