package george.stoica.management.persistence;

import george.stoica.management.persistence.entity.User;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created on 28/10/2020.
 */
@JdbcRepository(dialect = Dialect.H2)
public interface UserRepository extends CrudRepository<User, Long> {
    @Executable
    Optional<User> findByUsername(String username);
}
