package mk.ukim.finki.kiii.volunteerapp.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;
    private LocalDateTime joinedAt;

    public Participation(User userId, Event eventId) {
        this.user=userId;
        this.event=eventId;
    }

}
