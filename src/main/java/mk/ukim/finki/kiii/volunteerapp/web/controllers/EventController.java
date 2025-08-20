package mk.ukim.finki.kiii.volunteerapp.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.exceptions.AccessDeniedException;
import mk.ukim.finki.kiii.volunteerapp.service.application.EventApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Event API", description = "Endpoints form managing events.")
public class EventController {
    private final EventApplicationService eventApplicationService;

    public EventController(EventApplicationService eventApplicationService) {
        this.eventApplicationService = eventApplicationService;
    }

    @Operation(summary = "Get all events", description = "Retrieves a list of all available events.")
    @GetMapping
    public List<DisplayEventDto> findAll(){
        return eventApplicationService.findAll();
    }

    @Operation(summary = "Get event by ID", description = "Finds an event by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayEventDto> findById(@PathVariable Long id){
        return eventApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new event", description = "Creates a new event based on the given EventDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayEventDto> save(@RequestBody CreateEventDto createEventDto){
        return ResponseEntity.ok(eventApplicationService.save(createEventDto));
    }

    @Operation(summary = "Update an existing event", description = "Updates an event by its ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayEventDto> update(@PathVariable Long id, @RequestBody CreateEventDto createEventDto) {
        return this.eventApplicationService.update(id, createEventDto)
                .map(a->ResponseEntity.ok().body(a))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an event", description = "Deletes an event by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User currentUser) throws AccessDeniedException {
        if (eventApplicationService.findById(id).isPresent()) {
            eventApplicationService.deleteById(id, currentUser.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
