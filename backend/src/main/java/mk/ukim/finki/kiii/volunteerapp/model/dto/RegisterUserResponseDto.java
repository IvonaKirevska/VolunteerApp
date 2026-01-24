package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;

public record RegisterUserResponseDto (
        String username,
        String email
){
    public static RegisterUserResponseDto from(User user){
        return new RegisterUserResponseDto(
                user.getUsername(),
                user.getEmail()
        );
    }
}
