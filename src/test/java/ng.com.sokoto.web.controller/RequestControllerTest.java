package ng.com.sokoto.web.controller;

//import com.google.gson.Gson;
import com.google.inject.matcher.Matchers;
import java.util.Collections;
import ng.com.sokoto.controller.RequestController;
import ng.com.sokoto.web.dto.RequestDto;
import ng.com.sokoto.web.service.RequestService;
//import org.apache.http.client.methods.RequestBuilder;
//import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;*/
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
public class RequestControllerTest {

    private static final String ENDPOINT_URL = "/api/request";

    @InjectMocks
    private RequestController requestController;

    @Mock
    private RequestService requestService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc =
            MockMvcBuilders
                .standaloneSetup(requestController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<RequestDto> page = new PageImpl<>(Collections.singletonList(RequestBuilder.getDto()));

        Mockito.when(requestService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);
        /*        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(requestService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(requestService);*/

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(requestService.findById(ArgumentMatchers.anyString())).thenReturn(RequestBuilder.getDto());

        mockMvc
            .perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(requestService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(requestService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(requestService.save(ArgumentMatchers.any(RequestDto.class))).thenReturn(RequestBuilder.getDto());
        /*        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(RequestBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(requestService, Mockito.times(1)).save(ArgumentMatchers.any(RequestDto.class));
        Mockito.verifyNoMoreInteractions(requestService);*/
    }

    @Test
    public void update() throws Exception {
        Mockito.when(requestService.update(ArgumentMatchers.any(), ArgumentMatchers.anyString())).thenReturn(RequestBuilder.getDto());
        /*        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(RequestBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(requestService, Mockito.times(1)).update(ArgumentMatchers.any(RequestDto.class), ArgumentMatchers.anyString());
        Mockito.verifyNoMoreInteractions(requestService);*/
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(requestService).deleteById(ArgumentMatchers.anyString());
        /*        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(RequestBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(requestService, Mockito.times(1)).deleteById(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(requestService);*/
    }
}
