package com.hackerrank.api.service;

import com.hackerrank.api.model.Event;
import com.hackerrank.api.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

```
@Autowired
private EventRepository eventRepository;

public Optional<Event> getEventById(Long id) {
return eventRepository.findById(id);
}

public List<Event> getTop3ByCost() {
return eventRepository.findTop3ByOrderByCostAsc();
}

public List<Event> getTop3ByDuration() {
return eventRepository.findTop3ByOrderByDurationAsc();
}

public Double getTotalCost() {
return eventRepository.getTotalCost();
}

public Long getTotalDuration() {
return eventRepository.getTotalDuration();
}

public List<Object[]> getLocationStats() {
return eventRepository.getLocationStats();
}
```

}
