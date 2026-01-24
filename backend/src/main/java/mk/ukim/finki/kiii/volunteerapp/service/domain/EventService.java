package mk.ukim.finki.kiii.volunteerapp.service.domain;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.exceptions.AccessDeniedException;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();
    Optional<Event> findById(Long id);
    Event save(Event event, User organizer);
    Optional<Event> update(Long id, Event event,String username);
    void deleteById(Long id, Long requestingUserId) throws AccessDeniedException;
}
