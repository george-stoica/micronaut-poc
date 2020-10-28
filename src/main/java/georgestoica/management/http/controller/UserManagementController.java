package georgestoica.management.http.controller;

import georgestoica.management.http.controller.data.CreateUserRequest;
import georgestoica.management.http.controller.data.UpdateUserRequest;
import georgestoica.management.http.controller.data.UserManagementResponse;
import georgestoica.management.service.UserManagementService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;

import javax.inject.Inject;

/**
 * Created on 28/10/2020.
 */
@Controller("/users")
public class UserManagementController {
    private UserManagementService userManagementService;

    @Inject
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    public Single<HttpResponse<UserManagementResponse>> createUser(@Body CreateUserRequest request) {
        var newUser = userManagementService.createUser(request.toDto());

        return newUser.flatMap(userDto -> {
            var responseBody = new UserManagementResponse();
            responseBody.setUsername(userDto.getUsername());
            responseBody.setEmail(userDto.getEmail());
            responseBody.setLastUpdateTs(userDto.getLastUpdateTs());

            return Single.just(HttpResponse.created(responseBody));
        });
    }

    @Put("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Single<HttpResponse<UserManagementResponse>> updateUser(@Body UpdateUserRequest request, String username) {
        var updateData = request.toDto();
        updateData.setUsername(username);

        var newUser = userManagementService.updateUser(updateData);

        return newUser.flatMap(userDto -> {
            var responseBody = new UserManagementResponse();
            responseBody.setUsername(userDto.getUsername());
            responseBody.setEmail(userDto.getEmail());
            responseBody.setLastUpdateTs(userDto.getLastUpdateTs());

            return Single.just(HttpResponse.ok(responseBody));
        });
    }

    @Get("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Single<HttpResponse<UserManagementResponse>> createUser(String username) {
        var foundUser = userManagementService.findUser(username);

        return foundUser.flatMap(userDto -> {
            var responseBody = new UserManagementResponse();
            responseBody.setUsername(userDto.getUsername());
            responseBody.setEmail(userDto.getEmail());
            responseBody.setLastUpdateTs(userDto.getLastUpdateTs());

            return Single.just(HttpResponse.ok(responseBody));
        });
    }
}
