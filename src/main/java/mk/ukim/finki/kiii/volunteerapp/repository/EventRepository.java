package mk.ukim.finki.kiii.volunteerapp.repository;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
