package georgestoica;

import georgestoica.management.http.controller.data.CreateUserRequest;
import georgestoica.management.http.controller.data.UpdateUserRequest;
import georgestoica.management.http.controller.data.UserManagementResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 28/10/2020.
 */
@MicronautTest
public class UserManagementControllerTest {
    @Inject
    @Client("/")
    RxHttpClient httpClient;

    @Test
    public void testCreateNewUser() {
        var request = HttpRequest.POST("/users",
                new CreateUserRequest("testUser", "pwd", "test@home.hk"));

        var response = httpClient.toBlocking()
                .exchange(request, UserManagementResponse.class);

        assertEquals(HttpStatus.CREATED, response.status());
        assertEquals("testUser", response.body().getUsername());
        assertEquals("test@home.hk", response.body().getEmail());
    }

    @Test
    public void testFindUser() {
        // create test user

        var createRequest = HttpRequest.POST("/users",
                new CreateUserRequest("testUser", "pwd", "test@home.hk"));

        httpClient.toBlocking().exchange(createRequest, UserManagementResponse.class);

        // find

        var request = HttpRequest.GET("/users/testUser");
        var response = httpClient.toBlocking().exchange(request, UserManagementResponse.class);

        assertEquals(HttpStatus.OK, response.status());
        assertEquals("testUser", response.body().getUsername());
        assertEquals("test@home.hk", response.body().getEmail());
    }

    @Test
    public void testUpdateUser() {
        // create test user

        var createRequest = HttpRequest.POST("/users",
                new CreateUserRequest("testUser", "pwd", "test@home.hk"));

        httpClient.toBlocking().exchange(createRequest, UserManagementResponse.class);

        // run update

        var request = HttpRequest.PUT("/users/testUser", new UpdateUserRequest("newemail@home.hk"));
        var response = httpClient.toBlocking().exchange(request, UserManagementResponse.class);

        assertEquals(HttpStatus.OK, response.status());
        assertEquals("testUser", response.body().getUsername());
        assertEquals("newemail@home.hk", response.body().getEmail());
    }
}
