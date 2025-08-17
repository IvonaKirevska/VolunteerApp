package mk.ukim.finki.kiii.volunteerapp.model.dto;

import mk.ukim.finki.kiii.volunteerapp.model.domain.User;
import mk.ukim.finki.kiii.volunteerapp.model.enumerations.Role;

public record CreateUserDto (
        String name,
         String email,
         String password,
         Role role){
    public User toUser(){
        return new User(name, email, password, role);
    }
}
