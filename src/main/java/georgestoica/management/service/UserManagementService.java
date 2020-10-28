package georgestoica.management.service;

import georgestoica.management.dto.UserDto;
import georgestoica.management.persistence.UserRepository;
import georgestoica.management.persistence.entity.User;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

/**
 * Created on 28/10/2020.
 */
@Singleton
public class UserManagementService {
    private UserRepository userRepository;

    @Inject
    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<UserDto> createUser(UserDto newUserData) {
        var userEntity = new User();
        userEntity.setUsername(newUserData.getUsername());
        userEntity.setEmail(newUserData.getEmail());
        userEntity.setLastUpdateTs(Date.from(Instant.now()));

        return Single.just(Optional.of(userRepository.save(userEntity))
                .map(this::mapFromEntity).orElseThrow());
    }

    @Transactional
    public Single<UserDto> updateUser(UserDto newUserData) {
        // first find existing by username
        var existing = userRepository.findByUsername(newUserData.getUsername());

        return existing.map(existingUser -> {
            // update
            var userEntity = new User();
            userEntity.setUsername(existingUser.getUsername());
            userEntity.setEmail(newUserData.getEmail());
            userEntity.setLastUpdateTs(Date.from(Instant.now()));

            return Single.just(
                    Optional.of(userRepository.update(userEntity))
                            .map(this::mapFromEntity).orElseThrow()
            );
        }).orElseThrow();
    }

    public Single<UserDto> findUser(String username) {
        return Single.just(
                userRepository.findByUsername(username)
                        .map(this::mapFromEntity).orElseThrow()
        );
    }

    private UserDto mapFromEntity(User userEntity) {
        return new UserDto(
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getLastUpdateTs()
        );
    }
}
