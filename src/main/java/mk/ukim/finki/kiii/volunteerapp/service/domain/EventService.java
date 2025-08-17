package mk.ukim.finki.kiii.volunteerapp.service.domain;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();
    Optional<Event> findById(Long id);
    Event save(Event event, CreateEventDto createEventDto);
    Optional<Event> update(Long id, Event event);
    void deleteById(Long id);
}
