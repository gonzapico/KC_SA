package xyz.gonzapico.sa_butterknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gonzapico on 19/10/2017.
 */

public abstract class BaseKCActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  public abstract int getLayoutId();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    ButterKnife.bind(this);

    initToolbar();
  }

  private void initToolbar() {
    setSupportActionBar(toolbar);
    final ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black);
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  public void replaceFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.frameLayout, fragment)
        .commit();
  }
}
