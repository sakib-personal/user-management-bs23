package bs23.practical.usermanagement.payload;

import bs23.practical.usermanagement.entity.User;
import bs23.practical.usermanagement.util.DateUtil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String createdAt;
    private String updatedAt;

    public static UserResponseDTO fromDomain(User user) {
        return UserResponseDTO.builder()
                .id(user.getId().toString())
                .userName(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dateOfBirth(DateUtil.toDateString(user.getDateOfBirth()))
                .createdAt(DateUtil.toDateTimeString(user.getCreatedAt()))
                .updatedAt(DateUtil.toDateTimeString(user.getUpdatedAt()))
                .build();
    }
}
