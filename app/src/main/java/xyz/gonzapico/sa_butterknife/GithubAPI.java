package xyz.gonzapico.sa_butterknife;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.gonzapico.sa_butterknife.getDataUser.GetDataUserModel;

/**
 * Created by gonzapico on 24/10/2017.
 */

public interface GithubAPI {

  @GET("/users/{username}") Call<GetDataUserModel> getUserInfo(@Path("username") String userGithub);
}
