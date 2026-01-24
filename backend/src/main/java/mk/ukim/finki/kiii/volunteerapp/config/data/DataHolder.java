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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public DataHolder(UserRepository userRepository,
                           EventRepository eventRepository,
                           ParticipationRepository participationRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.participationRepository = participationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        participationRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();

        User organizer1 = new User(null, "Ivan", "organizer1@example.com",
                passwordEncoder.encode("ivan"), null, null);
        User organizer2 = new User(null, "Ana", "organizer2@example.com",
                passwordEncoder.encode("ana"), null, null);
        User organizer3 = new User(null, "Marko", "organizer3@example.com",
                passwordEncoder.encode("marko"), null, null);

        User volunteer1 = new User(null, "Petar", "volunteer1@example.com",
                passwordEncoder.encode("petar"), null, null);
        User volunteer2 = new User(null, "Maja", "volunteer2@example.com",
                passwordEncoder.encode("maja"), null, null);
        User volunteer3 = new User(null, "Stefan", "volunteer3@example.com",
                passwordEncoder.encode("stefan"), null, null);
        User volunteer4 = new User(null, "Elena", "volunteer4@example.com",
                passwordEncoder.encode("elena"), null, null);
        User volunteer5 = new User(null, "Nikola", "volunteer5@example.com",
                passwordEncoder.encode("nikola"), null, null);

        userRepository.saveAll(List.of(organizer1, organizer2, organizer3,
                volunteer1, volunteer2, volunteer3, volunteer4, volunteer5));

        Event event1 = new Event(null,
                "Чистење на парк",
                "Волонтерска акција за чистење на градскиот парк.",
                LocalDate.now().plusDays(7),
                LocalTime.of(10, 0),
                "Градски парк",
                20,
                "екологија",
                LocalDateTime.now(),
                organizer1,
                null);

        Event event2 = new Event(null,
                "Помош во старечки дом",
                "Волонтерска помош за стари лица.",
                LocalDate.now().plusDays(10),
                LocalTime.of(9, 30),
                "Старечки дом 'Свети Никола'",
                15,
                "социјална",
                LocalDateTime.now(),
                organizer2,
                null);

        Event event3 = new Event(null,
                "Собирање на донации",
                "Организација на донаторска акција за деца без родители.",
                LocalDate.now().plusDays(5),
                LocalTime.of(12, 0),
                "Центар за социјална поддршка",
                25,
                "хуманитарна",
                LocalDateTime.now(),
                organizer1,
                null);

        Event event4 = new Event(null,
                "Засадување дрвја",
                "Акција за засадување нови дрвја во населбата.",
                LocalDate.now().plusDays(14),
                LocalTime.of(11, 0),
                "Населба Карпош",
                30,
                "екологија",
                LocalDateTime.now(),
                organizer3,
                null);

        eventRepository.saveAll(List.of(event1, event2, event3, event4));

        Participation p1 = new Participation(null, volunteer1, event1, Role.VOLUNTEER, LocalDateTime.now());
        Participation p2 = new Participation(null, volunteer2, event1, Role.VOLUNTEER, LocalDateTime.now());
        Participation p3 = new Participation(null, volunteer3, event2, Role.VOLUNTEER, LocalDateTime.now());
        Participation p4 = new Participation(null, volunteer4, event3, Role.VOLUNTEER, LocalDateTime.now());
        Participation p5 = new Participation(null, volunteer5, event4, Role.VOLUNTEER, LocalDateTime.now());
        Participation p6 = new Participation(null, organizer1, event1, Role.ORGANIZER, LocalDateTime.now());
        Participation p7 = new Participation(null, organizer2, event2, Role.ORGANIZER, LocalDateTime.now());
        Participation p8 = new Participation(null, organizer1, event3, Role.ORGANIZER, LocalDateTime.now());
        Participation p9 = new Participation(null, organizer3, event4, Role.ORGANIZER, LocalDateTime.now());

        participationRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9));
    }
}
