package mk.ukim.finki.kiii.volunteerapp.service.domain.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        if (user.getName()==null || user.getName().isEmpty() || user.getEmail()==null || user.getEmail().isEmpty() || user.getPassword()==null || user.getPassword().isEmpty() || user.getRole()==null){
            throw new IllegalArgumentException();
        }
        User user1 = new User(user.getName(), user.getEmail(), user.getPassword(), user.getRole());
        return this.userRepository.save(user1);
    }

    @Override
    public Optional<User> update(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (user.getName() != null && !user.getName().isEmpty()) {
                        existingUser.setName(user.getName());
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
}
