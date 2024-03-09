package bs23.practical.usermanagement.controller;

import bs23.practical.usermanagement.entity.User;
import bs23.practical.usermanagement.payload.*;
import bs23.practical.usermanagement.service.UserEventService;
import bs23.practical.usermanagement.service.UserManagementService;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserManagementController {

    private final UserManagementService userManagementService;
    private final UserEventService userEventService;
    private final Gson jsonConverter;
    private final ApiResponseEntityFactory apiResponseEntityFactory;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreationRequestDTO userCreationRequestDTO) {
        try {
            User newUser = userManagementService.createUser(UserCreationRequestDTO.toDomain(userCreationRequestDTO));

            userEventService.produceUserEvent(UserEvent.toUserEvent(HttpMethod.POST.name(), jsonConverter.toJson(newUser)));

            return this.apiResponseEntityFactory.successResponse(UserResponseDTO.fromDomain(newUser));
        } catch (Exception exception) {
            return this.apiResponseEntityFactory
                    .errorResponse("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@Valid @PathVariable("userId") @NotNull UUID userId) {
        try {
            return this.apiResponseEntityFactory.successResponse(
                    UserResponseDTO.fromDomain(userManagementService.getUser(userId)));
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.apiResponseEntityFactory
                    .errorResponse(entityNotFoundException.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return this.apiResponseEntityFactory
                    .errorResponse("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable("userId") @NotNull UUID userId,
                                        @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        try {
            User updatedUser = userManagementService
                    .updateUser(userId, UpdateUserRequestDTO.toDomain(updateUserRequestDTO));

            userEventService.produceUserEvent(UserEvent
                    .toUserEvent(HttpMethod.PUT.name(), jsonConverter.toJson(updatedUser)));

            return this.apiResponseEntityFactory.successResponse(UserResponseDTO.fromDomain(updatedUser));
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.apiResponseEntityFactory
                    .errorResponse(entityNotFoundException.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return this.apiResponseEntityFactory
                    .errorResponse("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable("userId") @NotNull UUID userId) {
        try {
            userManagementService.deleteUser(userId);

            userEventService.produceUserEvent(UserEvent
                    .toUserEvent(HttpMethod.DELETE.name(), "{\"userId\":\"" + userId + "\"}"));

            return this.apiResponseEntityFactory.successResponse(Boolean.TRUE);
        } catch (EntityNotFoundException entityNotFoundException) {
            return this.apiResponseEntityFactory
                    .errorResponse(entityNotFoundException.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return this.apiResponseEntityFactory
                    .errorResponse("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
