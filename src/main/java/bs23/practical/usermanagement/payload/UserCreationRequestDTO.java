package bs23.practical.usermanagement.payload;

import bs23.practical.usermanagement.entity.User;
import bs23.practical.usermanagement.util.DateUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationRequestDTO {
    @NotBlank(message = "First name must be at least 3 characters long")
    @Size(min = 3, message = "First name must be at least 3 characters long")
    private String firstName;

    @NotBlank(message = "Last name must be at least 3 characters long")
    @Size(min = 3, message = "Last name must be at least 3 characters long")
    private String lastName;

    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Date of birth cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dateOfBirth;

    public static User toDomain(UserCreationRequestDTO userCreationRequestDTO) {
        return User.builder()
                .userName(userCreationRequestDTO.getEmail())
                .firstName(userCreationRequestDTO.getFirstName())
                .lastName(userCreationRequestDTO.getLastName())
                .email(userCreationRequestDTO.getEmail())
                .dateOfBirth(DateUtil.toLocalDate(userCreationRequestDTO.getDateOfBirth()))
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
    }
}
