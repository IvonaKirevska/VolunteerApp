package mk.ukim.finki.kiii.volunteerapp.service.domain.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.repository.EventRepository;
import mk.ukim.finki.kiii.volunteerapp.repository.UserRepository;
import mk.ukim.finki.kiii.volunteerapp.service.domain.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event save(Event event, CreateEventDto createEventDto) {
        User organizer = userRepository.findById(createEventDto.organizerId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event1 = new Event(event.getTitle(), event.getDescription(), event.getDate(), event.getTime(), event.getLocation(), event.getMaxParticipants(),event.getCategory(), organizer);
        return eventRepository.save(event1);
    }

    @Override
    public Optional<Event> update(Long id, Event event) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                   if (event.getTitle()!=null && !event.getTitle().isEmpty()){
                       existingEvent.setTitle(event.getTitle());
                   }
                    if (event.getDescription()!=null && !event.getDescription().isEmpty()){
                        existingEvent.setDescription(event.getDescription());
                    }
                    if (event.getDate()!=null){
                        existingEvent.setDate(event.getDate());
                    }
                    if (event.getTime()!=null){
                        existingEvent.setTime(event.getTime());
                    }
                    if (event.getLocation()!=null && !event.getLocation().isEmpty()){
                        existingEvent.setLocation(event.getLocation());
                    }
                    if (event.getMaxParticipants()!=null){
                        existingEvent.setMaxParticipants(event.getMaxParticipants());
                    }
                    if (event.getCategory()!=null && !event.getCategory().isEmpty()){
                        existingEvent.setCategory(event.getCategory());
                    }
                    if (event.getOrganizer()!=null){
                        existingEvent.setCategory(event.getCategory());
                    }
                    return eventRepository.save(existingEvent);
                });
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
