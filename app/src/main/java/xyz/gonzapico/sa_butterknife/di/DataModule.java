package xyz.gonzapico.sa_butterknife.di;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.gonzapico.sa_butterknife.GithubAPI;

/**
 * Created by gonzapico on 24/10/2017.
 */
@Module public class DataModule {

  private String baseUrl;

  public DataModule(String urlAPI) {
    baseUrl = urlAPI;
  }


  @Provides GithubAPI provideGithubService(Retrofit retrofit) {
    return retrofit.create(GithubAPI.class);
  }

  @Provides @Singleton Retrofit provideRetrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .client(okHttpClient)
        .build();
  }

  @Provides @Singleton OkHttpClient provideHttpClient(HttpLoggingInterceptor logging) {
    return new OkHttpClient.Builder().addInterceptor(logging).build();
  }

  @Provides @Singleton HttpLoggingInterceptor provideInterceptor(){
    return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
  }
}
