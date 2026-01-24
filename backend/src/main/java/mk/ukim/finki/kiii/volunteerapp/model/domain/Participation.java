package mk.ukim.finki.kiii.volunteerapp.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.kiii.volunteerapp.model.enumerations.Role;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Event event;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime joinedAt;


    public Participation(User userId, Event eventId) {
        this.user=userId;
        this.event=eventId;
    }

    public Participation(User user, Event event, Role role, LocalDateTime joinedAt) {
        this.user = user;
        this.event = event;
        this.role = role;
        this.joinedAt = joinedAt;
    }
}
