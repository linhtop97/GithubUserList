package vn.com.misa.githubuser.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.com.misa.githubuser.data.model.UserResponse;

/**
 * Created by framgia on 25/04/2017.
 */

public interface GithubService {
    @GET("users/list")
    Call<GithubModel> getGithub(@Query("sort") String sortValue);

    @GET("search/users")
    Call<UserResponse> listUser(@Query("per_page") String limit, @Query("q") String name);
}