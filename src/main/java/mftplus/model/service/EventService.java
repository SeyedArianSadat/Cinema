package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Event;
import mftplus.model.repository.EventRepository;

import java.util.List;

public class EventService implements Service<Event, Integer> {
    @Getter
    private static EventService service = new EventService();

    private EventService() {

    }

    @Override
    public void save(Event event) throws Exception {
        try (EventRepository eventRepository = new EventRepository()) {
            eventRepository.save(event);
        }
    }

    @Override
    public void edit(Event event) throws Exception {
        try (EventRepository eventRepository = new EventRepository()) {
            eventRepository.edit(event);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try (EventRepository eventRepository = new EventRepository()) {
            eventRepository.delete(id);
        }

    }

    @Override
    public List<Event> findAll() throws Exception {
        try (EventRepository eventRepository = new EventRepository()) {
            return eventRepository.findAll();
        }

    }

    @Override
    public Event findById(Integer id) throws Exception {
        try (EventRepository eventRepository = new EventRepository()) {
            return eventRepository.findById(id);
        }

    }
    public Event findByTitle(String title) throws Exception {
        try (EventRepository eventRepository = new EventRepository()) {
            Event event=eventRepository.findByTitle(title);
            if (event != null) {
                return event;
            }else {
                throw  new Exception();
            }
        }
    }

}
