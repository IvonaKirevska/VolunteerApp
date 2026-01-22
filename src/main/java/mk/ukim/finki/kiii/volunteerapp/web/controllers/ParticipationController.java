package mk.ukim.finki.kiii.volunteerapp.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.service.application.ParticipationApplicationService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.EventService;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/participations")
@Tag(name = "Participation API", description = "Endpoints for managing participations.")
public class ParticipationController {

    private final ParticipationApplicationService participationApplicationService;
    private final UserService userService;
    private final EventService eventService;

    public ParticipationController(ParticipationApplicationService participationApplicationService, UserService userService, EventService eventService) {
        this.participationApplicationService = participationApplicationService;
        this.userService = userService;
        this.eventService = eventService;
    }

    @Operation(summary = "Get all participations", description = "Retrieves a list of all available participations.")
    @GetMapping
    public List<DisplayParticipationDto> findAll(){
        return this.participationApplicationService.findAll();
    }


    @Operation(summary = "Get participation by ID", description = "Find an participation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayParticipationDto> findById(@PathVariable Long id){
        return this.participationApplicationService.findById(id)
                .map(a-> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Join on an existing event")
    @PostMapping("/{id}/join")
    public ResponseEntity<String> joinEvent(@PathVariable Long id) {
        participationApplicationService.joinEvent(id);
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "Leave an event")
    @DeleteMapping("/{eventId}/leave")
    public ResponseEntity<Void> leaveEvent(@PathVariable Long eventId, Authentication authentication) {

        String username = authentication.getName();

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventService.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        participationApplicationService.leaveEvent(user, event);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<String>> getParticipants(@PathVariable Long id){
        List<Participation> participations = participationApplicationService.findByEventId(id);

        List<String> participantNames = participations.stream()
                .map(p->p.getUser().getUsername())
                .collect(Collectors.toList());

        return ResponseEntity.ok(participantNames);
    }
}
