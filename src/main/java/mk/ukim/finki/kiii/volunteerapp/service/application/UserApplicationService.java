package mk.ukim.finki.kiii.volunteerapp.service.application;

import mk.ukim.finki.kiii.volunteerapp.model.dto.CreateUserDto;
import mk.ukim.finki.kiii.volunteerapp.model.dto.DisplayUserDto;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    List<DisplayUserDto> findAll();
    Optional<DisplayUserDto> findById(Long id);
    DisplayUserDto save(CreateUserDto createUserDto);
    Optional<DisplayUserDto> update(Long id, CreateUserDto createUserDto);
    void deleteById(Long id);
}
