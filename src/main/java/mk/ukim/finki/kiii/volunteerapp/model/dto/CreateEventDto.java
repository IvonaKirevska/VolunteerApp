package mk.ukim.finki.kiii.volunteerapp.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateEventDto(
        String title,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        @Schema(type = "string", example = "09:30:00")
                LocalTime time,
        String location,
        Integer maxParticipants,
        String category,
        Long organizerId
) {
    public Event toEvent(User organizer){
        return new Event(title,description,date,time,location,maxParticipants,category,organizer);
    }
}
