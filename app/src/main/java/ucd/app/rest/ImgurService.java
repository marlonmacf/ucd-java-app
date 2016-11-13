package ucd.app.rest;

import retrofit2.Call;
import retrofit2.http.*;
import ucd.app.entities.Imgur;

public interface ImgurService {

    @Multipart
    @POST("/3/image")
    Call<Imgur>  postImage(
            @Header("Authorization") String auth,
            @Part("image") String image
//            @Query("title") String title,
//            @Query("description") String description,
//            @Query("album") String albumId,
//            @Query("account_url") String username,
    );
}
