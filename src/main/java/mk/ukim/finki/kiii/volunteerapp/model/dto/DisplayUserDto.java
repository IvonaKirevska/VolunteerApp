package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.enumerations.Role;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserDto (Long id, String name, String email, Role role, LocalDateTime createdAt){
    public static DisplayUserDto from(User user){
        return new DisplayUserDto(user.getId(),user.getUsername(), user.getEmail(), user.getRole(), user.getCreatedAt());
    }
    public static List<DisplayUserDto> from(List<User> users){
        return users.stream().map(DisplayUserDto::from).collect(Collectors.toList());
    }
}
