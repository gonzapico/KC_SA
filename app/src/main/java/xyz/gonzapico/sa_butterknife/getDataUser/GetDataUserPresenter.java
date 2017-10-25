package xyz.gonzapico.sa_butterknife.getDataUser;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import xyz.gonzapico.sa_butterknife.HomeActivity;
import xyz.gonzapico.sa_butterknife.VolleyHandler;

/**
 * Created by gonzapico on 23/10/2017.
 */

public class GetDataUserPresenter {

  private final static String TAG = GetDataUserPresenter.class.getSimpleName();
  public static final String GITHUB_USERS = "https://api.github.com/users/";

  private GetDataUserView mGithubUserView;
  private UserRepository mUserRepository;

  public GetDataUserPresenter() {
    mUserRepository = new UserRepository();
  }

  public void attachView(GetDataUserView githubUserView) {
    mGithubUserView = githubUserView;
  }

  public void detachView() {
    VolleyHandler.getInstance((HomeActivity) mGithubUserView).cancelPendingRequests(TAG);
    mGithubUserView = null;
  }

  /***
   * Use case
   */
  public void getGithubUser(String username) {
    mUserRepository.getGithubInfoBy(username).
        enqueue(new Callback<GetDataUserModel>() {
          @Override public void onResponse(Call<GetDataUserModel> call, retrofit2.Response<GetDataUserModel> response) {
            if (response.isSuccessful()){
              try {
                GetDataUserModel currentUser = response.body();
                mGithubUserView.renderBio(currentUser.getBio());
                mGithubUserView.renderUsername(currentUser.getName());
                mGithubUserView.renderImageUrl(currentUser.getAvatarUrl());
              }
              catch (Exception e){
                Log.e(TAG, e.getMessage());
              }
            }
            else {
              Log.d(TAG, response.toString());
            }
          }

          @Override public void onFailure(Call<GetDataUserModel> call, Throwable t) {
            Log.e(TAG, t.getMessage());
          }
        });
  }

  /***
   * Use case
   *
  public void getGithubUser(String username) {
    JsonObjectRequest githubUserRequest =
        new JsonObjectRequest(Request.Method.GET, GITHUB_USERS + username, null,
            new Response.Listener<JSONObject>() {

              @Override public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<GetDataUserModel> jsonAdapter = moshi.adapter(GetDataUserModel.class);

                GetDataUserModel githubUserModel = null;
                try {
                  githubUserModel = jsonAdapter.fromJson(response.toString());
                  mGithubUserView.renderUsername(githubUserModel.getName());
                  mGithubUserView.renderBio(githubUserModel.getBio());
                  getGithubImageWithCache(githubUserModel.getAvatarUrl());
                } catch (IOException error) {
                  Log.e(TAG, error.getMessage());
                }
              }
            }, new Response.ErrorListener() {

          @Override public void onErrorResponse(VolleyError error) {
            Log.e(TAG, error.getMessage());
          }
        });

    VolleyHandler.getInstance((HomeActivity) mGithubUserView).addToRequestQueue(githubUserRequest, TAG);
  }
   */

  /***
   * Use case
   *
   * Version 1.- with Image Loader inside Singleton
   */
  public void getGithubImageWithCache(String urlImage){
    ImageLoader imageLoader = VolleyHandler.getInstance((HomeActivity) mGithubUserView).getImageLoader();
    imageLoader.get(urlImage, new ImageLoader.ImageListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "Image Load Error: " + error.getMessage());
      }

      @Override
      public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
        Bitmap bitmap = response.getBitmap();
        if (bitmap != null) {
          mGithubUserView.renderImage(bitmap);
        }
      }
    });
  }

  /***
   * Use case
   *
   * Version 2.- with Image Request
   */
  public void getGithubImage(String urlImage) {
    ImageRequest request = new ImageRequest(urlImage, new Response.Listener<Bitmap>() {
      @Override public void onResponse(Bitmap bitmap) {
        if (bitmap != null) {
          mGithubUserView.renderImage(bitmap);
        }
      }
    }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,
        new Response.ErrorListener() {
          public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "Image Load Error: " + error.getMessage());
          }
        });

    VolleyHandler.getInstance((HomeActivity) mGithubUserView).addToRequestQueue(request);
  }
}
