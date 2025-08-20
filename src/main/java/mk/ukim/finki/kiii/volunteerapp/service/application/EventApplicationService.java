package mk.ukim.finki.kiii.volunteerapp.service.application;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayEventDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.exceptions.AccessDeniedException;

import java.util.List;
import java.util.Optional;

public interface EventApplicationService {
    List<DisplayEventDto> findAll();
    Optional<DisplayEventDto> findById(Long id);
    DisplayEventDto save(CreateEventDto createEventDto);
    Optional<DisplayEventDto> update(Long id, CreateEventDto createEventDto);
    void deleteById(Long id, Long requestingUserId) throws AccessDeniedException;
}
