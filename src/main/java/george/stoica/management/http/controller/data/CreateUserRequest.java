package george.stoica.management.http.controller.data;

import george.stoica.management.dto.UserDto;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

/**
 * Created on 28/10/2020.
 */
@Data
@Introspected
public class CreateUserRequest {
    private String username;
    private String password;
    private String email;
    private Date lastUpdateTs;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastUpdateTs = Date.from(Instant.now());
    }

    public UserDto toDto() {
        return new UserDto(username, email, lastUpdateTs);
    }
}
