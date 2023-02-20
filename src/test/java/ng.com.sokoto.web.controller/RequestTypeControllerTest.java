package ng.com.sokoto.web.controller;

import com.google.gson.Gson;
import java.util.Collections;
import ng.com.sokoto.controller.RequestTypeController;
import ng.com.sokoto.web.dto.RequestTypeDto;
import ng.com.sokoto.web.service.RequestTypeService;
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
public class RequestTypeControllerTest {

    private static final String ENDPOINT_URL = "/api/request-type";

    @InjectMocks
    private RequestTypeController requesttypeController;

    @Mock
    private RequestTypeService requesttypeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc =
            MockMvcBuilders
                .standaloneSetup(requesttypeController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<RequestTypeDto> page = new PageImpl<>(Collections.singletonList(RequestTypeBuilder.getDto()));

        Mockito.when(requesttypeService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL).accept(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(requesttypeService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(requesttypeService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(requesttypeService.findById(ArgumentMatchers.anyString())).thenReturn(RequestTypeBuilder.getDto());

        mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(requesttypeService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(requesttypeService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(requesttypeService.save(ArgumentMatchers.any(RequestTypeDto.class))).thenReturn(RequestTypeBuilder.getDto());

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(ENDPOINT_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(RequestTypeBuilder.getDto()))
            )
            .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(requesttypeService, Mockito.times(1)).save(ArgumentMatchers.any(RequestTypeDto.class));
        Mockito.verifyNoMoreInteractions(requesttypeService);
    }

    @Test
    public void update() throws Exception {
        Mockito
            .when(requesttypeService.update(ArgumentMatchers.any(), ArgumentMatchers.anyString()))
            .thenReturn(RequestTypeBuilder.getDto());

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .put(ENDPOINT_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(RequestTypeBuilder.getDto()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito
            .verify(requesttypeService, Mockito.times(1))
            .update(ArgumentMatchers.any(RequestTypeDto.class), ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(requesttypeService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(requesttypeService).deleteById(ArgumentMatchers.anyString());
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .delete(ENDPOINT_URL + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(CustomUtils.asJsonString(RequestTypeBuilder.getIds()))
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(requesttypeService, Mockito.times(1)).deleteById(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(requesttypeService);
    }
}
