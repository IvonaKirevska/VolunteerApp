package mk.ukim.finki.kiii.volunteerapp.repository;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
