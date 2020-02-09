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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

@RunWith(MockitoJUnitRunner.class)
public class AirlineServiceTest extends TestCase {
    private static final URI TESTING_SERVICE = URI.create("http://localhost:8080/");
    private AirlineEntity airlineEntityOne;
    private AirlineEntity airlineEntityTwo;
    private List<AirlineEntity> airlineEntities;

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
        when(airlineRepository.findByIata(anyString())).thenReturn(null);
        String searchIata = "iata";
        ErrorService error = new ErrorService(searchIata);
        assertEquals(airlineService.getAirlineByIata(searchIata), error);
    }

    @Test
    public void shouldAddAirline() {

    }

    @Test
    void shouldUpdateAirlineWithNewParameter() {

    }

    @Test
    void shouldCreateNewAirlineWithNewParameter() {

    }

    @Test
    void shouldDeleteAirline() {

    }
}
