package ng.com.sokoto.controller;

import com.google.gson.Gson;
import java.util.Collections;
import ng.com.sokoto.dto.FacilityDto;
import ng.com.sokoto.service.FacilityService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
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
public class FacilityControllerTest {

    private static final String ENDPOINT_URL = "/api/facility";

    @InjectMocks
    private FacilityController facilityController;

    @Mock
    private FacilityService facilityService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc =
            MockMvcBuilders
                .standaloneSetup(facilityController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<FacilityDto> page = new PageImpl<>(Collections.singletonList(FacilityBuilder.getDto()));

        Mockito.when(facilityService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL).accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(facilityService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(facilityService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(facilityService.findById(ArgumentMatchers.anyLong())).thenReturn(FacilityBuilder.getDto());

        mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(facilityService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(facilityService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(facilityService.save(ArgumentMatchers.any(FacilityDto.class))).thenReturn(FacilityBuilder.getDto());

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(ENDPOINT_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(FacilityBuilder.getDto()))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(facilityService, Mockito.times(1)).save(ArgumentMatchers.any(FacilityDto.class));
        Mockito.verifyNoMoreInteractions(facilityService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(facilityService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(FacilityBuilder.getDto());

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .put(ENDPOINT_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(FacilityBuilder.getDto()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(facilityService, Mockito.times(1)).update(ArgumentMatchers.any(FacilityDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(facilityService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(facilityService).deleteById(ArgumentMatchers.anyLong());
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .delete(ENDPOINT_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(FacilityBuilder.getIds()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(facilityService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(facilityService);
    }
}
