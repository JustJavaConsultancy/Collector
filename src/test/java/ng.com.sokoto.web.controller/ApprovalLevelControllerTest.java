package ng.com.sokoto.web.controller;

import com.google.gson.Gson;
import java.util.Collections;
import ng.com.sokoto.controller.ApprovalLevelController;
import ng.com.sokoto.web.dto.ApprovalLevelDto;
import ng.com.sokoto.web.service.ApprovalLevelService;
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
public class ApprovalLevelControllerTest {

    private static final String ENDPOINT_URL = "/api/approval-level";

    @InjectMocks
    private ApprovalLevelController approvallevelController;

    @Mock
    private ApprovalLevelService approvallevelService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc =
            MockMvcBuilders
                .standaloneSetup(approvallevelController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<ApprovalLevelDto> page = new PageImpl<>(Collections.singletonList(ApprovalLevelBuilder.getDto()));

        Mockito.when(approvallevelService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL).accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(approvallevelService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(approvallevelService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(approvallevelService.findById(ArgumentMatchers.anyString())).thenReturn(ApprovalLevelBuilder.getDto());

        mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(approvallevelService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(approvallevelService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(approvallevelService.save(ArgumentMatchers.any(ApprovalLevelDto.class))).thenReturn(ApprovalLevelBuilder.getDto());

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(ENDPOINT_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(ApprovalLevelBuilder.getDto()))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(approvallevelService, Mockito.times(1)).save(ArgumentMatchers.any(ApprovalLevelDto.class));
        Mockito.verifyNoMoreInteractions(approvallevelService);
    }

    @Test
    public void update() throws Exception {
        Mockito
            .when(approvallevelService.update(ArgumentMatchers.any(), ArgumentMatchers.anyString()))
            .thenReturn(ApprovalLevelBuilder.getDto());

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .put(ENDPOINT_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(ApprovalLevelBuilder.getDto()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito
            .verify(approvallevelService, Mockito.times(1))
            .update(ArgumentMatchers.any(ApprovalLevelDto.class), ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(approvallevelService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(approvallevelService).deleteById(ArgumentMatchers.anyString());
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .delete(ENDPOINT_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(ApprovalLevelBuilder.getIds()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(approvallevelService, Mockito.times(1)).deleteById(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(approvallevelService);
    }
}
