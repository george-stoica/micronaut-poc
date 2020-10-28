package george.stoica.management.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created on 28/10/2020.
 */
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "last_update_ts")
    private Date lastUpdateTs;
}
