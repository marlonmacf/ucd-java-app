package ucd.app.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;
import ucd.app.entities.Complaint;
import ucd.app.entities.User;

public interface ApiService {

    @POST("/login")
    Call<User> login(@Query("email") String email, @Query("password") String password);

    @GET("/ranking")
    Call<List<User>> fetchRanking();

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

    @POST("/complaint")
    Call<Complaint> insertComplaint(@Query("latitude") String latitude, @Query("longitude") String longitude,
                                    @Query("description") String description, @Query("idUser") Integer idUser,
                                    @Query("photosBase") String photosBase);

    @GET("/complaint/{complaint}/inspect")
    Call<Complaint> inspectComplaints(@Path("complaint") Integer idComplaint, @Query("idInspector") Integer idInspector);

    @GET("/complaint/{complaint}/check")
    Call<Complaint> checkComplaints(@Path("complaint") Integer idComplaint);

    @GET("/complaint/{complaint}/denounce")
    Call<Complaint> denounceComplaints(@Path("complaint") Integer idComplaint);
}
