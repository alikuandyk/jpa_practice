package org.example.homework67;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {
    @Id
    private int id;
    private String name;
    private LocalDateTime created;
    private boolean published;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + created +
                ", published=" + published +
                '}';
    }
}