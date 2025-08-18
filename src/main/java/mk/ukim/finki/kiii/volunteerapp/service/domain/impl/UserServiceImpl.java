package mk.ukim.finki.kiii.volunteerapp.service.domain.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.exceptions.IncorrectPasswordException;
import mk.ukim.finki.kiii.volunteerapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        if (user.getUsername()==null || user.getUsername().isEmpty() || user.getEmail()==null || user.getEmail().isEmpty() || user.getPassword()==null || user.getPassword().isEmpty() || user.getRole()==null){
            throw new IllegalArgumentException();
        }
        User user1 = new User(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
        return this.userRepository.save(user1);
    }

    @Override
    public Optional<User> update(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                        existingUser.setUsername(user.getUsername());
                    }
                    if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                        existingUser.setEmail(user.getEmail());
                    }
                    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                        existingUser.setPassword(user.getPassword());
                    }
                    if (user.getRole() !=null){
                        existingUser.setRole(user.getRole());
                    }
                    return userRepository.save(existingUser);
                });
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User register(User user) {
        return userRepository.save(new User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getEmail()
        ));
    }

    @Override
    public User login(String username, String password) {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new IncorrectPasswordException();
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
