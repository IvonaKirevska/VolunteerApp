package mk.ukim.finki.kiii.volunteerapp.service.application.impl;

import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateUserDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayUserDto;
import mk.ukim.finki.kiii.volunteerapp.service.application.UserApplicationService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public List<DisplayUserDto> findAll() {
        return DisplayUserDto.from(userService.findAll());
    }

    @Override
    public Optional<DisplayUserDto> findById(Long id) {
        return userService.findById(id).map(DisplayUserDto::from);
    }

    @Override
    public DisplayUserDto save(CreateUserDto createUserDto) {
        return DisplayUserDto.from(userService.save(createUserDto.toUser()));
    }

    @Override
    public Optional<DisplayUserDto> update(Long id, CreateUserDto createUserDto) {
        return userService.update(id, createUserDto.toUser()).map(DisplayUserDto::from);
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }
}
