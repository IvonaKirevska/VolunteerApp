package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserDto (Long id, String name, String email){
    public static DisplayUserDto from(User user){
        return new DisplayUserDto(user.getId(),user.getUsername(), user.getEmail());
    }
    public static List<DisplayUserDto> from(List<User> users){
        return users.stream().map(DisplayUserDto::from).collect(Collectors.toList());
    }
}
