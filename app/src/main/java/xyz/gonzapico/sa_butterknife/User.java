package xyz.gonzapico.sa_butterknife;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by gonzapico on 20/10/2017.
 */

@IgnoreExtraProperties
public class User {

  public User(){

  }
  private String name;
  private String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
