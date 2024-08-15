package org.example.homework67;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);

    }

    public static EntityManager manager() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = managerFactory.createEntityManager();

        return manager;
    }

    public List<Event> getAllEvents() {
        TypedQuery<Event> query = manager().createQuery("select e from Event e", Event.class);
        List<Event> events = query.getResultList();

        return events;
    }

    public Event getEventById(int id) {
        Event event = manager().find(Event.class, id);

        return event;
    }

    public List<Event> getPublishedEvents() {
        TypedQuery<Event> query = manager().createQuery("select e from Event e where e.published = true", Event.class);
        List<Event> events = query.getResultList();

        return events;
    }

    public Event getEventByName(String name) {
        TypedQuery<Event> query = manager().createQuery("select e from Event e where e.name = :name", Event.class);
        query.setParameter("name", name);

        Optional<Event> optional = query.getResultStream().findAny();

        if (optional.isPresent()) {
            return optional.get();
        } throw new EntityNotFoundException();
    }
}
