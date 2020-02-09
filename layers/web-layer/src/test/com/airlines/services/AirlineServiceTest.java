package com.airlines.services;

import com.airlines.entities.AirlineEntity;
import com.airlines.repositories.AirlineRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AirlineServiceTest extends TestCase {
    private static final URI TESTING_SERVICE = URI.create("http://localhost:8080/airline-details");
    private AirlineEntity airlineEntityOne;
    private AirlineEntity airlineEntityTwo;
    private List<AirlineEntity> airlineEntities;

    @Autowired
    private MockMvc mockMvc;
    @Mock
    AirlineRepository airlineRepository;
    @InjectMocks
    AirlineService airlineService;

    @Before
    public void init() {
        this.stubAirlines();
        this.stubRequestContext();
    }

    private void stubAirlines() {
        airlineEntityOne = new AirlineEntity("AA", "AAL", "American Airlines", "1-800-433-7300");
        airlineEntityTwo = new AirlineEntity("BB", "BBL", "British airways", "1-900-433-7300");
        airlineEntities = Arrays.asList(airlineEntityOne, airlineEntityTwo);
    }

    private void stubRequestContext() {
        MockHttpServletRequest request = MockMvcRequestBuilders
                .get(TESTING_SERVICE)
                .buildRequest(new MockServletContext());
        final RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);
    }

    @Test
    public void shouldGetAllAirlines() {
        when(airlineRepository.findAll()).thenReturn(airlineEntities);

        assertEquals(airlineService.getAllAirlines(), airlineEntities);
    }

    @Test
    public void shouldGetAllAirlineNames() {
        when(airlineRepository.findAll()).thenReturn(airlineEntities);

        assertEquals(airlineService.getAllAirlineNames(), Arrays.asList(airlineEntityOne.getName(), airlineEntityTwo.getName()));
    }

    @Test
    public void shouldGetAirlineByIata() {
        when(airlineRepository.findByIata(anyString())).thenReturn(airlineEntityOne);

        assertEquals(airlineService.getAirlineByIata("iata"), airlineEntityOne);
    }

    @Test(expected = ErrorService.class)
    public void shouldThrowErrorServiceWhenIataNotValid() {
        String searchIata = "iata";
        ErrorService error = new ErrorService(searchIata);
        when(airlineRepository.findByIata(anyString())).thenReturn(null);

        assertEquals(airlineService.getAirlineByIata(searchIata), error);
    }

    @Test
    public void shouldAddAirline() {
        ResponseEntity<?> response = airlineService.addAirline(airlineEntityOne);

        verify(airlineRepository).save(airlineEntityOne);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldUpdateAirlineWithNewParameter() {
        when(airlineRepository.findByIata(airlineEntityOne.getIata())).thenReturn(airlineEntityOne);
        ResponseEntity<?> response = airlineService.updateAirline(airlineEntityTwo, airlineEntityOne.getIata());

        assertEquals(airlineEntityOne.getName(), airlineEntityTwo.getName());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldCreateNewAirlineWithNewParameter() {
        when(airlineRepository.findByIata(airlineEntityOne.getIata())).thenReturn(null);
        ResponseEntity<?> response = airlineService.updateAirline(airlineEntityTwo, airlineEntityOne.getIata());

        verify(airlineRepository).save(airlineEntityTwo);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldDeleteAirline() {
        String searchIata = "iata";
        when(airlineRepository.findByIata(searchIata)).thenReturn(airlineEntityOne);
        ResponseEntity<?> response = airlineService.removeAirline(searchIata);

        verify(airlineRepository).delete(airlineEntityOne);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void shouldReturnNotAllowedMethodExceptionWhenUpdateAirline() throws Exception {
        MockHttpServletRequestBuilder request = post(TESTING_SERVICE)
                .accept(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(new AirlineEntity()));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
    }
}
