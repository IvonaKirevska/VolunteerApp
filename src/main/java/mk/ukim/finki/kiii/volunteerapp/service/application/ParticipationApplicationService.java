package mk.ukim.finki.kiii.volunteerapp.service.application;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateUserDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayParticipationDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayUserDto;

import java.util.List;
import java.util.Optional;

public interface ParticipationApplicationService {
    List<DisplayParticipationDto> findAll();
    Optional<DisplayParticipationDto> findById(Long id);
    Participation joinEvent(CreateParticipationDto createParticipationDto);
    void leaveEvent(User user, Event event);
}
