package xyz.gonzapico.sa_butterknife;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

  @BindView(R.id.rvScroll) RecyclerView rvScroll;
  @BindArray(R.array.scroll_data) String[] scrollDataArray;
  @BindView(R.id.toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;

  private static List<ViewModel> items = new ArrayList<>();

  static {
    for (int i = 1; i <= 10; i++) {
      items.add(new ViewModel("Item " + i, "http://lorempixel.com/500/500/animals/" + i));
    }
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scrolling);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });

    ButterKnife.bind(this);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }



}
