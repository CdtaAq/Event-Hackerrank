package com.hackerrank.api.repository;

import com.hackerrank.api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

```
// Find top 3 events ordered by cost ascending
List<Event> findTop3ByOrderByCostAsc();

// Find top 3 events ordered by duration ascending
List<Event> findTop3ByOrderByDurationAsc();

// Custom query to get sum of cost for all events
@Query("SELECT SUM(e.cost) FROM Event e")
Double getTotalCost();

// Custom query to get sum of duration for all events
@Query("SELECT SUM(e.duration) FROM Event e")
Long getTotalDuration();

// Query to group by location and get totals
@Query("SELECT e.location, COUNT(e), SUM(e.cost)/SUM(e.duration) FROM Event e GROUP BY e.location")
List<Object[]> getLocationStats();
```

}
