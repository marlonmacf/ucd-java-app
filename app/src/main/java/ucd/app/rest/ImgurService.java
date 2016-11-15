package ucd.app.rest;

import retrofit2.Call;
import retrofit2.http.*;
import ucd.app.entities.Imgur;

public interface ImgurService {

    @Multipart
    @POST("/3/image")
    Call<Imgur> postImage(@Header("Authorization") String auth, @Part("image") String image);

    @GET("/3/image/{id}")
    Call<Imgur> getImage(@Header("Authorization") String auth, @Path("id") String id);
}
