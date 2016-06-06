package ucd.domain.services;

import retrofit2.Call;
import retrofit2.http.*;
import ucd.domain.entities.User;

import java.util.List;

public interface UserService {

    @GET("/user")
    Call<List<User>> index();

    @GET("/user/{user}")
    Call<User> show(@Path("user") String idUser);

    @POST("/user")
    Call<User> store();

    @PUT("/user/{user}")
    Call<User> update(@Path("user") String idUser);

    @DELETE("/user/{user}")
    Call<User> destroy(@Path("user") String idUser);
}
