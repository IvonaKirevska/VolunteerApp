package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;

public record CreateUserDto (
        String name,
         String email,
         String password){
    public User toUser(){
        return new User(name, email, password);
    }
}
