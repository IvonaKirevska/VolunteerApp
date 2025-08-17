package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;

public record CreateParticipationDto(User userId, Event eventId) {
    public Participation toParticipation(){
        return new Participation(userId, eventId);
    }
}
