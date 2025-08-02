package com.hackerrank.api.controller;

import com.hackerrank.api.model.Event;
import com.hackerrank.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RestController
@RequestMapping(”/event”)
public class EventController {

```
@Autowired
private EventService eventService;

// GET /event/{id} - Return event by ID
@GetMapping("/{id}")
public ResponseEntity<Event> getEventById(@PathVariable Long id) {
Optional<Event> event = eventService.getEventById(id);
if (event.isPresent()) {
return ResponseEntity.ok(event.get());
} else {
return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
}

// GET /event/top3?by={by} - Return top 3 events by field
@GetMapping("/top3")
public ResponseEntity<?> getTop3Events(@RequestParam String by) {
if ("cost".equals(by)) {
List<Event> events = eventService.getTop3ByCost();
return ResponseEntity.ok(events);
} else if ("duration".equals(by)) {
List<Event> events = eventService.getTop3ByDuration();
return ResponseEntity.ok(events);
} else {
return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}
}

// GET /event/total?by={by} - Return total sum by field
@GetMapping("/total")
public ResponseEntity<?> getTotal(@RequestParam String by) {
if ("cost".equals(by)) {
Double total = eventService.getTotalCost();
return ResponseEntity.ok(total != null ? total : 0.0);
} else if ("duration".equals(by)) {
Long total = eventService.getTotalDuration();
return ResponseEntity.ok(total != null ? total : 0L);
} else {
return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}
}

// GET /scan/report/eventDashboard - Return grouped report
@GetMapping("/scan/report/eventDashboard")
public ResponseEntity<List<Map<String, Object>>> getEventDashboard() {
List<Object[]> locationStats = eventService.getLocationStats();
List<Map<String, Object>> result = new ArrayList<>();

for (Object[] stat : locationStats) {
Map<String, Object> locationData = new HashMap<>();
locationData.put("location", stat[0]);
locationData.put("totalEvents", stat[1]);

// Calculate cost duration ratio and round to 3 decimal places
Double costDurationRatio = (Double) stat[2];
if (costDurationRatio != null) {
BigDecimal bd = new BigDecimal(costDurationRatio);
bd = bd.setScale(3, RoundingMode.HALF_UP);
locationData.put("costDurationRatio", bd.doubleValue());
} else {
locationData.put("costDurationRatio", 0.0);
}

result.add(locationData);
}

return ResponseEntity.ok(result);
}
```

}
