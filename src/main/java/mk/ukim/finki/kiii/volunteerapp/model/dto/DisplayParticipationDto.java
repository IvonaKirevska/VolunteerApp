package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.enumerations.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayParticipationDto(Long id, Long userId, Long eventId, LocalDateTime joinedAt, Role role) {
    public static DisplayParticipationDto from(Participation participation){
        return new DisplayParticipationDto(
                participation.getId(),
                participation.getUser().getId(),
                participation.getEvent().getId(),
                participation.getJoinedAt(),
                participation.getRole()
        );
    }

    public static List<DisplayParticipationDto> from(List<Participation> participations){
        return participations.stream().map(DisplayParticipationDto::from).collect(Collectors.toList());
    }
}
