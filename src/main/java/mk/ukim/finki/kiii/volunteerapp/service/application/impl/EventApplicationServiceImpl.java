package mk.ukim.finki.kiii.volunteerapp.service.application.impl;

import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayEventDto;
import mk.ukim.finki.kiii.volunteerapp.service.application.EventApplicationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventApplicationServiceImpl implements EventApplicationService {

    private final EventService eventService;

    public EventApplicationServiceImpl(EventService eventService) {
        this.eventService = eventService;
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
        return DisplayEventDto.from(eventService.save(createEventDto.toEvent(), createEventDto));
    }

    @Override
    public Optional<DisplayEventDto> update(Long id, CreateEventDto createEventDto) {
        return eventService.update(id, createEventDto.toEvent()).map(DisplayEventDto::from);
    }

    @Override
    public void deleteById(Long id) {
        eventService.deleteById(id);
    }
}
