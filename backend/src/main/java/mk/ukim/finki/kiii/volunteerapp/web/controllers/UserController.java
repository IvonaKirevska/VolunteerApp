package mk.ukim.finki.kiii.volunteerapp.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.kiii.volunteerapp.model.dto.*;
import mk.ukim.finki.kiii.volunteerapp.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for managing users.")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Get all users", description = "Retrieves a list of all available users.")
    @GetMapping
    public List<DisplayUserDto> findAll(){
        return this.userApplicationService.findAll();
    }

    @Operation(summary = "Get user by ID", description = "Find an user by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayUserDto> findById(@PathVariable Long id){
        return this.userApplicationService.findById(id)
                .map(a-> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new user", description = "Creates a new user based on the given UserDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayUserDto> save(@RequestBody CreateUserDto userDto) {
        return ResponseEntity.ok(userApplicationService.save(userDto));
    }

    @Operation(summary = "Update an existing user", description = "Updates an user by its ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayUserDto> update(@PathVariable Long id, @RequestBody CreateUserDto userDto) {
        return this.userApplicationService.update(id, userDto)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an user", description = "Deletes an user by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (userApplicationService.findById(id).isPresent()) {
            userApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto registerUserRequestDto){
        return userApplicationService
                .register(registerUserRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Log in")
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody LoginUserRequestDto loginUserRequestDto){
        return userApplicationService
                .login(loginUserRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
