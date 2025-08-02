package com.hackerrank.api.controller;

import com.hackerrank.api.model.Event;
import com.hackerrank.api.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

```
@Mock
private EventService eventService;

@InjectMocks
private EventController eventController;

private MockMvc mockMvc;
private ObjectMapper objectMapper;

@BeforeEach
void setUp() {
mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
objectMapper = new ObjectMapper();
}

@Test
public void testGetEventById_Success() throws Exception {
Event event = new Event("Soft Summit", "Xton City", 8, 30.0);
event.setId(1L);

when(eventService.getEventById(1L)).thenReturn(Optional.of(event));

mockMvc.perform(get("/event/1"))
.andExpect(status().isOk())
.andExpect(content().contentType(MediaType.APPLICATION_JSON))
.andExpect(jsonPath("$.id").value(1))
.andExpect(jsonPath("$.name").value("Soft Summit"))
.andExpect(jsonPath("$.location").value("Xton City"))
.andExpect(jsonPath("$.duration").value(8))
.andExpect(jsonPath("$.cost").value(30.0));
}

@Test
public void testGetEventById_NotFound() throws Exception {
when(eventService.getEventById(anyLong())).thenReturn(Optional.empty());

mockMvc.perform(get("/event/999"))
.andExpect(status().isNotFound());
}

@Test
public void testGetTop3ByCost_Success() throws Exception {
List<Event> events = Arrays.asList(
new Event("Event1", "Location1", 5, 10.0),
new Event("Event2", "Location2", 6, 20.0),
new Event("Event3", "Location3", 7, 30.0)
);
events.get(0).setId(1L);
events.get(1).setId(2L);
events.get(2).setId(3L);

when(eventService.getTop3ByCost()).thenReturn(events);

mockMvc.perform(get("/event/top3?by=cost"))
.andExpect(status().isOk())
.andExpect(content().contentType(MediaType.APPLICATION_JSON))
.andExpect(jsonPath("$").isArray())
.andExpect(jsonPath("$.length()").value(3))
.andExpect(jsonPath("$[0].cost").value(10.0))
.andExpect(jsonPath("$[1].cost").value(20.0))
.andExpect(jsonPath("$[2].cost").value(30.0));
}

@Test
public void testGetTop3ByDuration_Success() throws Exception {
List<Event> events = Arrays.asList(
new Event("Event1", "Location1", 3, 10.0),
new Event("Event2", "Location2", 5, 20.0),
new Event("Event3", "Location3", 8, 30.0)
);
events.get(0).setId(1L);
events.get(1).setId(2L);
events.get(2).setId(3L);

when(eventService.getTop3ByDuration()).thenReturn(events);

mockMvc.perform(get("/event/top3?by=duration"))
.andExpect(status().isOk())
.andExpect(content().contentType(MediaType.APPLICATION_JSON))
.andExpect(jsonPath("$").isArray())
.andExpect(jsonPath("$.length()").value(3))
.andExpect(jsonPath("$[0].duration").value(3))
.andExpect(jsonPath("$[1].duration").value(5))
.andExpect(jsonPath("$[2].duration").value(8));
}

@Test
public void testGetTop3_InvalidAttribute() throws Exception {
mockMvc.perform(get("/event/top3?by=invalid"))
.andExpect(status().isBadRequest());
}

@Test
public void testGetTotalCost_Success() throws Exception {
when(eventService.getTotalCost()).thenReturn(150.0);

mockMvc.perform(get("/event/total?by=cost"))
.andExpect(status().isOk())
.andExpect(content().contentType(MediaType.APPLICATION_JSON))
.andExpect(content().string("150.0"));
}

@Test
public void testGetTotalDuration_Success() throws Exception {
when(eventService.getTotalDuration()).thenReturn(25L);

mockMvc.perform(get("/event/total?by=duration"))
.andExpect(status().isOk())
.andExpect(content().contentType(MediaType.APPLICATION_JSON))
.andExpect(content().string("25"));
}

@Test
public void testGetTotal_InvalidAttribute() throws Exception {
mockMvc.perform(get("/event/total?by=invalid"))
.andExpect(status().isBadRequest());
}

@Test
public void testGetEventDashboard_Success() throws Exception {
List<Object[]> locationStats = Arrays.asList(
new Object[]{"antarctica", 3L, 20.987},
new Object[]{"antarctica", 6L, 50.7}
);

when(eventService.getLocationStats()).thenReturn(locationStats);

mockMvc.perform(get("/event/scan/report/eventDashboard"))
.andExpect(status().isOk())
.andExpect(content().contentType(MediaType.APPLICATION_JSON))
.andExpect(jsonPath("$").isArray())
.andExpect(jsonPath("$.length()").value(2))
.andExpect(jsonPath("$[0].location").value("antarctica"))
.andExpect(jsonPath("$[0].totalEvents").value(3))
.andExpect(jsonPath("$[0].costDurationRatio").value(20.987))
.andExpect(jsonPath("$[1].location").value("antarctica"))
.andExpect(jsonPath("$[1].totalEvents").value(6))
.andExpect(jsonPath("$[1].costDurationRatio").value(50.7));
}
```

}
