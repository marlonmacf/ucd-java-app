package ucd.app.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;
import ucd.app.entities.Complaint;
import ucd.app.entities.User;

public interface ApiService {

    @GET("/user")
    Call<List<User>> fetchUsers();

    @GET("/user/{user}")
    Call<User> fetchUser(@Path("user") String idUser);

    @POST("/user")
    Call<User> insertUser(@Query("email") String email, @Query("name") String name, @Query("password")
            String password, @Query("inspector") int inspector, @Query("score") Byte score);

    @PUT("/user/{user}")
    Call<User> updateUser(@Path("user") String idUser, @Query("email") String email, @Query("name") String name, @Query("password")
            String password, @Query("inspector") int inspector, @Query("score") Byte score);

    @DELETE("/user/{user}")
    Call<User> deleteUser(@Path("user") String idUser);

    @GET("/complaint")
    Call<List<Complaint>> fetchComplaints();

    @GET("/complaint/{complaint}")
    Call<Complaint> fetchComplaint(@Path("complaint") String idComplaint);

    @POST("/complaint")
    Call<Complaint> insertComplaint(@Query("latitude") String latitude, @Query("longitude") String longitude,
                                    @Query("description") String description, @Query("idUser") Integer idUser,
                                    @Query("photosBase") String photosBase);

    @PUT("/complaint/{complaint}")
    Call<Complaint> updateComplaint(@Path("complaint") String idComplaint);

    @DELETE("/complaint/{complaint}")
    Call<Complaint> deleteComplaint(@Path("complaint") String idComplaint);

    @GET("/ranking")
    Call<List<User>> fetchRanking();

    @GET("/login")
    Call<User> login(@Query("email") String email, @Query("password") String password);

    @Multipart
    @GET("/login")
    Call<User> imgurImageUpload(@Part("photosBase") byte[] binarFile);
}
