package georgestoica.management.http.controller.data;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.Date;

/**
 * Created on 28/10/2020.
 */
@Data
@Introspected
public class UserManagementResponse {
    private String username;
    private String email;
    private Date lastUpdateTs;
}
