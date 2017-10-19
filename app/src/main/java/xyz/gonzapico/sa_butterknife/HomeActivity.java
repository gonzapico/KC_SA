package xyz.gonzapico.sa_butterknife;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HomeActivity extends BaseKCActivity {


  @Nullable @BindView(R.id.fab) FloatingActionButton fab;
  @BindString(R.string.fab_title) String fabTitle;
  @BindString(R.string.fab_action) String fabAction;
  @Nullable @BindView(R.id.autoTVFilms) AutoCompleteTextView autoTVFilms;
  @BindArray(R.array.films_list) String[] filmFilter;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.nav_view) NavigationView navigationView;
  @BindView(R.id.frameLayout) FrameLayout frameLayout;
  @OnClick(R.id.fab) void fabListener(View v){
    Snackbar.make(v, fabTitle, Snackbar.LENGTH_LONG).setAction(fabAction, null).show();
  }

  public static final String AVATAR_URL = "http://lorempixel.com/200/200/people/";

  @Override public int getLayoutId() {
    return R.layout.activity_home;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setUpAvatarMenu();
    setUpDrawerLayout();
  }

  private void setUpAvatarMenu(){
    final ImageView avatar = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.avatar);
    int randomInt = new Random().nextInt(10 - 1) + 1;
    Picasso.with(this).load(AVATAR_URL + randomInt + "/").transform(new CircleTransform()).into(avatar);
  }

  private void setUpDrawerLayout(){
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
          case R.id.nav_list:
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ScrollingFragment()).commit();
            break;
          case R.id.nav_search:
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeActivityFragment()).commit();
            break;
          case R.id.nav_select:
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, PickFragment.newInstance()).commit();
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

}
