package ucd.app.rest;

import retrofit2.Call;
import retrofit2.http.*;
import ucd.app.entities.Complaint;
import ucd.app.entities.User;

import java.util.List;

public interface ApiService {

    @GET("/user")
    Call<List<User>> fetchUsers();

    @GET("/user/{user}")
    Call<User> fetchUser(@Path("user") String idUser);

    @POST("/user")
    Call<User> insertUser(@Query("email") String email, @Query("name") String name, @Query("password")
            String password, @Query("inspector") Boolean inspector);

    @PUT("/user/{user}")
    Call<User> updateUser(@Path("user") String idUser);

    @DELETE("/user/{user}")
    Call<User> deleteUser(@Path("user") String idUser);

    @GET("/complaint")
    Call<List<Complaint>> fetchComplaints();

    @GET("/complaint/{complaint}")
    Call<Complaint> fetchComplaint(@Path("complaint") String idComplaint);

    @POST("/complaint")
    Call<Complaint> insertComplaint();

    @PUT("/complaint/{complaint}")
    Call<Complaint> updateComplaint(@Path("complaint") String idComplaint);

    @DELETE("/complaint/{complaint}")
    Call<Complaint> deleteComplaint(@Path("complaint") String idComplaint);

    @GET("/ranking")
    Call<List<User>> fetchRanking();

    @GET("/login")
    Call<User> login(@Query("email") String email, @Query("password") String password);
}
