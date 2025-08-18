package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;

public record RegisterUserRequestDto(
        String username,
        String password,
        String email
) {
    public User toUser(){
        return new User(username, password, email);
    }
}
