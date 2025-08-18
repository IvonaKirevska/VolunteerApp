package mk.ukim.finki.kiii.volunteerapp.service.application.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.repository.ParticipationRepository;
import mk.ukim.finki.kiii.volunteerapp.service.application.ParticipationApplicationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.EventService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.ParticipationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipationApplicationServiceImpl implements ParticipationApplicationService {

    private final ParticipationService participationService;
    private final ParticipationRepository participationRepository;
    private final UserService userService;
    private final EventService eventService;

    public ParticipationApplicationServiceImpl(ParticipationService participationService, ParticipationRepository participationRepository, UserService userService, EventService eventService) {
        this.participationService = participationService;
        this.participationRepository = participationRepository;
        this.userService = userService;
        this.eventService = eventService;
    }

    @Override
    public List<DisplayParticipationDto> findAll() {
        return DisplayParticipationDto.from(participationService.findAll());
    }

    @Override
    public Optional<DisplayParticipationDto> findById(Long id) {
        return participationService.findById(id).map(DisplayParticipationDto::from);
    }

    @Override
    public DisplayParticipationDto save(CreateParticipationDto createParticipationDto) {
        User user = userService.findById(createParticipationDto.userId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        Event event = eventService.findById(createParticipationDto.eventId())
                .orElseThrow(()-> new RuntimeException("Event not found"));

        return DisplayParticipationDto.from(participationService.save(createParticipationDto.toParticipation(user, event)));
    }

    @Override
    public Optional<DisplayParticipationDto> update(Long id, CreateParticipationDto createParticipationDto) {
        User user = userService.findById(createParticipationDto.userId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        Event event = eventService.findById(createParticipationDto.eventId())
                .orElseThrow(()-> new RuntimeException("Event not found"));

        return participationService.update(id, createParticipationDto.toParticipation(user, event)).map(DisplayParticipationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        participationService.deleteById(id);
    }

//    @Override
//    public Participation joinEvent(User user, Event event) {
//        Optional<Participation> existing=participationRepository.findByUserAndEvent(user, event);
//        if (existing.isPresent()){
//            throw new RuntimeException("User already joined this event");
//        }
//
//        Participation participation=new Participation();
//        participation.setUser(user);
//        participation.setEvent(event);
//        participation.setJoinedAt(LocalDateTime.now());
//        return participationRepository.save(participation);
//    }

    @Override
    public Participation joinEvent(CreateParticipationDto createParticipationDto) {
        User user = userService.findById(createParticipationDto.userId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        Event event = eventService.findById(createParticipationDto.eventId())
                .orElseThrow(()-> new RuntimeException("Event not found"));


        Optional<Participation> existing=participationRepository.findByUserAndEvent(user, event);
        if (existing.isPresent()){
            throw new RuntimeException("User already joined this event");
        }

        Participation participation=new Participation();
        participation.setUser(user);
        participation.setEvent(event);
        participation.setJoinedAt(LocalDateTime.now());
        return participationRepository.save(participation);
    }

    @Override
    public void leaveEvent(User user, Event event) {
        Optional<Participation> participation=participationRepository.findByUserAndEvent(user, event);
        participation.ifPresent(participationRepository::delete);
    }
}
