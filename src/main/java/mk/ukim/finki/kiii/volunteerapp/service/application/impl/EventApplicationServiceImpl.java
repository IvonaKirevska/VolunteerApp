package mk.ukim.finki.kiii.volunteerapp.service.application.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayEventDto;
import mk.ukim.finki.kiii.volunteerapp.service.application.EventApplicationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.EventService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;
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
        User organizer = userService.findById(createEventDto.organizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        return DisplayEventDto.from(eventService.save(createEventDto.toEvent(organizer), createEventDto));
    }

    @Override
    public Optional<DisplayEventDto> update(Long id, CreateEventDto createEventDto) {
        User organizer = userService.findById(createEventDto.organizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        return eventService.update(id, createEventDto.toEvent(organizer)).map(DisplayEventDto::from);
    }

    @Override
    public void deleteById(Long id) {
        eventService.deleteById(id);
    }

}
