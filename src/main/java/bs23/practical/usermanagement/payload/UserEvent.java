package bs23.practical.usermanagement.payload;

import bs23.practical.usermanagement.util.DateUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEvent {
    @NotBlank
    private String httpMethodName;
    @NotNull
    private String userDetails;
    @NotBlank
    private String operationPerformedAt;

    public static UserEvent toUserEvent(String httpMethodName, String user) {
        return UserEvent.builder()
                .httpMethodName(httpMethodName)
                .userDetails(user)
                .operationPerformedAt(DateUtil.toDateTimeString(LocalDateTime.now()))
                .build();
    }
}
