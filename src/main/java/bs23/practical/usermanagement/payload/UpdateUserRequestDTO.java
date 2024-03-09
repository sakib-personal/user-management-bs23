package bs23.practical.usermanagement.payload;

import bs23.practical.usermanagement.entity.User;
import bs23.practical.usermanagement.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequestDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    public static User toDomain(UpdateUserRequestDTO userCreationRequestDTO) {
        return User.builder()
                .firstName(userCreationRequestDTO.getFirstName())
                .lastName(userCreationRequestDTO.getLastName())
                .dateOfBirth(DateUtil.toLocalDate(userCreationRequestDTO.getDateOfBirth()))
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
