package bs23.practical.usermanagement.service;

import bs23.practical.usermanagement.entity.User;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public interface UserManagementService {
    User createUser(User newUser);
    User getUser(UUID id) throws EntityNotFoundException;
    User updateUser(UUID id, User userToUpdate) throws EntityNotFoundException;
    void deleteUser(UUID id) throws EntityNotFoundException;
}
