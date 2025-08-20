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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/join")
    public ResponseEntity<Participation> joinEvent(@RequestBody CreateParticipationDto createParticipationDto) {
        Participation participation = participationApplicationService.joinEvent(createParticipationDto);
        return ResponseEntity.ok(participation);
    }

    @Operation(summary = "Leave an event")
    @DeleteMapping("/leave")
    public ResponseEntity<Void> leaveEvent(@RequestParam Long userId, @RequestParam Long eventId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventService.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        participationApplicationService.leaveEvent(user, event);
        return ResponseEntity.ok().build();
    }
}
