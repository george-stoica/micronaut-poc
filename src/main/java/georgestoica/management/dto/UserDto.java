package georgestoica.management.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created on 28/10/2020.
 */
@Data
public class UserDto {
    private String username;
    private String email;
    private Date lastUpdateTs;

    public UserDto() {
    }

    public UserDto(String username, String email, Date lastUpdateTs) {
        this.username = username;
        this.email = email;
        this.lastUpdateTs = lastUpdateTs;
    }
}
