package tech.fnplus.enrollme.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tech.fnplus.enrollme.Model;

public interface api {
    String BASE_URL = "https://api.meetup.com/";

    @GET("geek-meetup-chennai")
    Call<List<Model>> getDetails();
}
