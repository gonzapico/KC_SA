package xyz.gonzapico.sa_butterknife;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import xyz.gonzapico.sa_butterknife.getDataUser.GetDataUserPresenter;
import xyz.gonzapico.sa_butterknife.getDataUser.GetDataUserView;

public class HomeActivity extends BaseKCActivity implements GetDataUserView {

  public static final String USERNAME = "gonzapico";
  @Nullable @BindView(R.id.fab) FloatingActionButton fab;
  @Nullable @BindView(R.id.autoTVFilms) AutoCompleteTextView autoTVFilms;

  @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
  @BindView(R.id.navigationView) NavigationView navigationView;
  @BindView(R.id.frameLayout) FrameLayout frameLayout;

  @BindString(R.string.fab_title) String fabTitle;
  @BindString(R.string.fab_action) String fabAction;

  @BindArray(R.array.films_list) String[] filmFilter;

  @OnClick(R.id.fab) void fabListener(View v) {
    Snackbar.make(v, fabTitle, Snackbar.LENGTH_LONG).setAction(fabAction, null).show();
  }

  private GetDataUserPresenter mGetDataUserPresenter;

  @Override public int getLayoutId() {
    return R.layout.activity_home;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setUpDrawerLayout();
    mGetDataUserPresenter = new GetDataUserPresenter();
  }

  @Override protected void onResume() {
    super.onResume();
    mGetDataUserPresenter.attachView(this);
    mGetDataUserPresenter.getGithubUser(USERNAME);
  }

  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onStop() {
    super.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mGetDataUserPresenter.detachView();
  }

  private void setUpDrawerLayout() {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
              case R.id.nav_static_list:
                replaceFragment(new StaticListFragment());
                break;
              case R.id.nav_dynamic_list:
                replaceFragment(new StaticListFragment());
                break;
              case R.id.nav_firebase_list:
                replaceFragment(new FirebaseListFragment());
                break;
              case R.id.nav_search:
                replaceFragment(new SearchFragment());
                break;
              case R.id.nav_select:
                replaceFragment(PickFragment.newInstance());
                break;
            }
            // Snackbar.make(getCurrentFocus(), item.getItemId() + " pressed", Snackbar.LENGTH_LONG).show();
            item.setChecked(true);
            drawerLayout.closeDrawers();
            return true;
          }
        });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void renderUsername(String username) {
    TextView tvBio = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvName);
    tvBio.setText(username);
  }

  @Override public void renderBio(String bio) {
    TextView tvBio = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvBio);
    tvBio.setText(bio);
  }

  @Override public void renderImage(Bitmap bitmap) {
    ImageView avatar = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivAvatar);
    avatar.setImageBitmap(bitmap);
  }

  @Override public void renderImageUrl(String url) {
    ImageView avatar = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivAvatar);
    Picasso.with(this).load(url).into(avatar);
  }
}
