package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;

public record RegisterUserRequestDto(
        String username,
        String email,
        String password
) {
    public User toUser(){
        return new User(username, email, password);
    }
}
