package xyz.gonzapico.sa_butterknife;

/**
 * Created by gonzapico on 18/10/2017.
 */

public class ViewModel {

  public ViewModel(String title, String url){
    this.title = title;
    this.urlImage = url;
  }

  private String title;
  private String urlImage;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrlImage() {
    return urlImage;
  }

  public void setUrlImage(String urlImage) {
    this.urlImage = urlImage;
  }
}
