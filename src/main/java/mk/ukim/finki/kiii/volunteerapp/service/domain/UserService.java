package mk.ukim.finki.kiii.volunteerapp.service.domain;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    Optional<User> update(Long id, User user);
    void deleteById(Long id);
}
