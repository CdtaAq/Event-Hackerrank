package com.hackerrank.api.model;

import javax.persistence.*;

@Entity
@Table(name = “events”)
public class Event {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

```
private String name;
private String location;
private Integer duration;
private Double cost;

// Default constructor
public Event() {}

// Constructor with parameters
public Event(String name, String location, Integer duration, Double cost) {
this.name = name;
this.location = location;
this.duration = duration;
this.cost = cost;
}

// Getters and Setters
public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public Integer getDuration() {
return duration;
}

public void setDuration(Integer duration) {
this.duration = duration;
}

public Double getCost() {
return cost;
}

public void setCost(Double cost) {
this.cost = cost;
}
```

}
