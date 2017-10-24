package xyz.gonzapico.sa_butterknife.getDataUser;

import android.graphics.Bitmap;

/**
 * Created by gonzapico on 06/10/2017.
 */

public interface GetDataUserView {

  void renderUsername(String username);

  void renderBio(String bio);

  void renderImage(Bitmap bitmap);
}

