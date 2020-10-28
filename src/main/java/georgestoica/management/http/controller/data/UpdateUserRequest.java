package georgestoica.management.http.controller.data;

import georgestoica.management.dto.UserDto;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

/**
 * Created on 28/10/2020.
 */
@Data
@Introspected
public class UpdateUserRequest {
    private String email;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String email) {
        this.email = email;
    }

    public UserDto toDto() {
        var dto = new UserDto();
        dto.setEmail(email);
        return dto;
    }
}
