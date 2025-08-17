package mk.ukim.finki.kiii.volunteerapp.service.application;

import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayParticipationDto;

import java.util.List;
import java.util.Optional;

public interface EventApplicationService {
    List<DisplayEventDto> findAll();
    Optional<DisplayEventDto> findById(Long id);
    DisplayEventDto save(CreateEventDto createEventDto);
    Optional<DisplayEventDto> update(Long id, CreateEventDto createEventDto);
    void deleteById(Long id);
}
