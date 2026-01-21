package mk.ukim.finki.kiii.volunteerapp.service.application.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.enumerations.Role;
import mk.ukim.finki.kiii.volunteerapp.repository.ParticipationRepository;
import mk.ukim.finki.kiii.volunteerapp.service.application.ParticipationApplicationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.EventService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.ParticipationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Participation joinEvent(Long eventId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Event event = eventService.findById(eventId)
                .orElseThrow(()->new RuntimeException("Event not found"));

        Optional<Participation> existing = participationRepository.findByEventAndUser(event.getId(), user.getId());
        if (existing.isPresent()){
            throw new RuntimeException("User already joined this event");
        }

        Participation participation=new Participation();
        participation.setUser(user);
        participation.setEvent(event);
        participation.setJoinedAt(LocalDateTime.now());
        participation.setRole(Role.VOLUNTEER);

        return participationRepository.save(participation);
    }

    @Override
    public void leaveEvent(User user, Event event) {
        Optional<Participation> participation=participationRepository.findByEventAndUser(event.getId(), user.getId());
        participation.ifPresent(participationRepository::delete);
    }

    @Override
    public List<Participation> findByEventId(Long eventId) {
        return participationRepository.findAll()
                .stream()
                .filter(p->p.getEvent().getId().equals(eventId))
                .collect(Collectors.toList());
    }
}
