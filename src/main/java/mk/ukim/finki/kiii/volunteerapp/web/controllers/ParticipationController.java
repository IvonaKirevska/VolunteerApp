package mk.ukim.finki.kiii.volunteerapp.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateUserDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayUserDto;
import mk.ukim.finki.kiii.volunteerapp.service.application.ParticipationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
@Tag(name = "Participation API", description = "Endpoints for managing participations.")
public class ParticipationController {

    private final ParticipationApplicationService participationApplicationService;

    public ParticipationController(ParticipationApplicationService participationApplicationService) {
        this.participationApplicationService = participationApplicationService;
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

    @Operation(summary = "Add a new participation", description = "Creates a new participation based on the given ParticipationDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayParticipationDto> save(@RequestBody CreateParticipationDto createParticipationDto) {
        return ResponseEntity.ok(participationApplicationService.save(createParticipationDto));
    }

    @Operation(summary = "Update an existing participation", description = "Updates an participation by its ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayParticipationDto> update(@PathVariable Long id, @RequestBody CreateParticipationDto createParticipationDto) {
        return this.participationApplicationService.update(id, createParticipationDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an participation", description = "Deletes an participation by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (participationApplicationService.findById(id).isPresent()) {
            participationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
