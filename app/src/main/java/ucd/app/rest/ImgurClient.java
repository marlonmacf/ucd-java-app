package ucd.app.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImgurClient {

    public static final String MY_IMGUR_CLIENT_ID = "Client-ID 9b8bb875e1f7a39";
    public static final String MY_IMGUR_CLIENT_SECRET = "fbd14e2ecf679b517ce1e0c0bd5a06a75c144dfc";

    private static final String BASE_URL = "https://api.imgur.com";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
