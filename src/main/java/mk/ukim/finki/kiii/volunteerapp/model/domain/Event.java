package mk.ukim.finki.kiii.volunteerapp.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private Integer maxParticipants;
    private String category;
    private LocalDateTime createdAt;
    @ManyToOne
    private User organizer;
    @OneToMany(mappedBy = "event")
    private List<Participation> participations;

    public Event(String title, String description, LocalDate date, LocalTime time, String location, Integer maxParticipants, String category, User organizerId) {
        this.title=title;
        this.description=description;
        this.date=date;
        this.time=time;
        this.location=location;
        this.maxParticipants=maxParticipants;
        this.category=category;
        this.organizer=organizerId;
    }

    public Event(String title, String description, LocalDate date, LocalTime time, String location, Integer maxParticipants, String category, Long id) {

    }
}
