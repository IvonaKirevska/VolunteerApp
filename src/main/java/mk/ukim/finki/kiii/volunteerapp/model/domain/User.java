package mk.ukim.finki.kiii.volunteerapp.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.kiii.volunteerapp.model.enumerations.Role;
import org.apache.catalina.LifecycleState;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "organizer")
    private List<Event> events;
    @OneToMany(mappedBy = "user")
    private List<Participation> participations;

    public User(String name, String email, String password, Role role) {
        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;
    }
}
