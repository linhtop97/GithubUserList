package vn.com.misa.githubuser.data.api;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
    @GET("forecast/{key}/{location}")
    Call<ResponseBody> getWeather(@Path("key") String key,
                                  @Path("location") String location);
}