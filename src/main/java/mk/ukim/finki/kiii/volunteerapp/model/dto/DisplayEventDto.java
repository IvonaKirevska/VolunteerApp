package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayEventDto(Long id, String title, String description, LocalDate date, LocalTime time, String location, Integer maxParticipants, String category, LocalDateTime createdAt, String organizerName) {
    public static DisplayEventDto from(Event event){
        return new DisplayEventDto(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getTime(),
                event.getLocation(),
                event.getMaxParticipants(),
                event.getCategory(),
                event.getCreatedAt(),
                event.getOrganizer().getUsername()
        );
    }

    public static List<DisplayEventDto> from(List<Event> events){
        return events.stream().map(DisplayEventDto::from).collect(Collectors.toList());
    }
}
