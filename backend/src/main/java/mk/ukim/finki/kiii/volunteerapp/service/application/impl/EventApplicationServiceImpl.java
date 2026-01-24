package mk.ukim.finki.kiii.volunteerapp.service.application.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.exceptions.AccessDeniedException;
import mk.ukim.finki.kiii.volunteerapp.service.application.EventApplicationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.EventService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventApplicationServiceImpl implements EventApplicationService {

    private final EventService eventService;
    private final UserService userService;

    public EventApplicationServiceImpl(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @Override
    public List<DisplayEventDto> findAll() {
        return DisplayEventDto.from(eventService.findAll());
    }

    @Override
    public Optional<DisplayEventDto> findById(Long id) {
        return eventService.findById(id).map(DisplayEventDto::from);
    }

    @Override
    public DisplayEventDto save(CreateEventDto createEventDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User organizer = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        Event event = createEventDto.toEvent(organizer);

        Event savedEvent = eventService.save(event, organizer);

        return DisplayEventDto.from(savedEvent);    }

    @Override
    public Optional<DisplayEventDto> update(Long id, CreateEventDto createEventDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User organizer = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Organizer not found"));
        return eventService.update(id, createEventDto.toEvent(organizer), organizer.getUsername()).map(DisplayEventDto::from);
    }

    @Override
    public void deleteById(Long id, Long requestingUserId) throws AccessDeniedException {
        eventService.deleteById(id, requestingUserId);
    }

}
