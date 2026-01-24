package mk.ukim.finki.kiii.volunteerapp.service.application.impl;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.dto.*;
import mk.ukim.finki.kiii.volunteerapp.service.application.UserApplicationService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.kiii.volunteerapp.service.domain.UserService;
import mk.ukim.finki.kiii.volunteerapp.heplers.JwtHelper;
import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
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

    @Override
    public Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto) {
        User user=userService.register(registerUserRequestDto.toUser());
        RegisterUserResponseDto registerUserResponseDto = RegisterUserResponseDto.from(user);
        return Optional.of(registerUserResponseDto);
    }

    @Override
    public Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto) {
        User user=userService.login(loginUserRequestDto.username(), loginUserRequestDto.password());
        String token = jwtHelper.generateToken(user);
        return Optional.of(new LoginUserResponseDto(token));
    }

    @Override
    public Optional<RegisterUserResponseDto> findByUsername(String username) {
        return userService.findByUsername(username).map(RegisterUserResponseDto::from);
    }
}
