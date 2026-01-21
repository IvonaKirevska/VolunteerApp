package mk.ukim.finki.kiii.volunteerapp.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.enumerations.Role;
import mk.ukim.finki.kiii.volunteerapp.model.exceptions.AccessDeniedException;
import mk.ukim.finki.kiii.volunteerapp.model.exceptions.ParticipationNotFoundException;
import mk.ukim.finki.kiii.volunteerapp.repository.EventRepository;
import mk.ukim.finki.kiii.volunteerapp.repository.ParticipationRepository;
import mk.ukim.finki.kiii.volunteerapp.repository.UserRepository;
import mk.ukim.finki.kiii.volunteerapp.service.domain.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, ParticipationRepository participationRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.participationRepository = participationRepository;
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
    public Event save(Event event, User organizer) {
        Event eventToSave = new Event(
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getTime(),
                event.getLocation(),
                event.getMaxParticipants(),
                event.getCategory(),
                organizer
        );

        eventToSave.setCreatedAt(LocalDateTime.now());

        Event savedEvent = eventRepository.save(eventToSave);

        Participation participation = new Participation(
                organizer,
                savedEvent,
                Role.ORGANIZER ,
                LocalDateTime.now()
        );

        participationRepository.save(participation);
        return savedEvent;
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
                    return eventRepository.save(existingEvent);
                });
    }

    @Override
    public void deleteById(Long id, Long requestingUserId) throws AccessDeniedException {
//        Participation participation = participationRepository
//                .findByUserAndEvent(id, requestingUserId)
//                .orElseThrow(() -> new ParticipationNotFoundException(id));
        Participation participation = participationRepository
                .findByEventAndUser(id, requestingUserId)
                .orElseThrow(()-> new RuntimeException("Participation not found"));

        if (participation.getRole()!=Role.ORGANIZER){
            throw new AccessDeniedException();
        }
        eventRepository.deleteById(id);
    }

}
