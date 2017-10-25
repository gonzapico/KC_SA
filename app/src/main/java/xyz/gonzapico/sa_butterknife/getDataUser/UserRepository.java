package xyz.gonzapico.sa_butterknife.getDataUser;

import javax.inject.Inject;
import retrofit2.Call;
import xyz.gonzapico.sa_butterknife.GithubAPI;
import xyz.gonzapico.sa_butterknife.di.DaggerDataComponent;
import xyz.gonzapico.sa_butterknife.di.DataModule;

/**
 * Created by gonzapico on 24/10/2017.
 */

public class UserRepository {

  @Inject GithubAPI githubAPIService;

  public UserRepository() {
    DaggerDataComponent.builder().
        dataModule(new DataModule("https://api.github.com/")).
        build().
        inject(this);
  }

  public Call<GetDataUserModel> getGithubInfoBy(String username) {
    return githubAPIService.getUserInfo(username);
  }
}
