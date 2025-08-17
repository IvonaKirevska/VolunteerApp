package mk.ukim.finki.kiii.volunteerapp.config.data;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.enumerations.Role;
import mk.ukim.finki.kiii.volunteerapp.repository.EventRepository;
import mk.ukim.finki.kiii.volunteerapp.repository.ParticipationRepository;
import mk.ukim.finki.kiii.volunteerapp.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class DataHolder {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;
    //private final PasswordEncoder passwordEncoder;

    public DataHolder(UserRepository userRepository,
                           EventRepository eventRepository,
                           ParticipationRepository participationRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.participationRepository = participationRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        participationRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();

        // Креираме организатор и волонтер корисници
        User organizer = new User(null, "Ivan Organizer", "organizer@example.com",
                "password1", Role.ORGANIZER, LocalDateTime.now(), null, null);

        User volunteer = new User(null, "Petar Volunteer", "volunteer@example.com",
                "password2", Role.VOLUNTEER, LocalDateTime.now(), null, null);

        userRepository.saveAll(List.of(organizer, volunteer));

        // Креираме настан
        Event event = new Event(null,
                "Чистење на парк",
                "Волонтерска акција за чистење на градскиот парк.",
                LocalDate.now().plusDays(7),
                LocalTime.of(10, 0),
                "Градски парк",
                20,
                "екологија",
                LocalDateTime.now(),
                organizer,
                null);

        eventRepository.save(event);

        // Креираме пријавување (Participation)
        Participation participation = new Participation(null, volunteer, event, LocalDateTime.now());

        participationRepository.save(participation);
    }
}
