package mk.ukim.finki.kiii.volunteerapp.repository;

import mk.ukim.finki.kiii.volunteerapp.model.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    @Query("SELECT p FROM Participation p WHERE p.event.id = :eventId AND p.user.id = :userId")
    Optional<Participation> findByEventAndUser(Long eventId, Long userId);
}
