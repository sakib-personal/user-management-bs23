package bs23.practical.usermanagement.service;

import bs23.practical.usermanagement.entity.User;
import bs23.practical.usermanagement.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserManagementService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUser(UUID id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User does not exists."));
    }

    @Transactional
    public User updateUser(UUID id, User userToUpdate) throws EntityNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User does not exists."));

        user.setFirstName(userToUpdate.getFirstName());
        user.setLastName(userToUpdate.getLastName());
        user.setDateOfBirth(userToUpdate.getDateOfBirth());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID id) throws EntityNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User does not exists."));

        userRepository.delete(user);
    }
}
