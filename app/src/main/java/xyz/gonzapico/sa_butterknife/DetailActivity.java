package xyz.gonzapico.sa_butterknife;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
  public static final String EXTRA_IMAGE = "extra_image";
  public static final String EXTRA_TITLE = "extra_title";

  @BindView(R.id.ablDetail) AppBarLayout apbDetail;
  @BindView(R.id.toolbarDetail) Toolbar toolbarDetail;
  @BindView(R.id.clbDetail) CollapsingToolbarLayout clbDetail;
  @BindView(R.id.ivDetail) ImageView ivDetail;
  @BindView(R.id.title) TextView tvTitle;

  public static void navigate(AppCompatActivity activity, View transitionImage, ViewModel viewModel) {
    Intent intent = new Intent(activity, DetailActivity.class);
    intent.putExtra(EXTRA_IMAGE, viewModel.getUrlImage());
    intent.putExtra(EXTRA_TITLE, viewModel.getTitle());
    activity.startActivity(intent);

    /*
    ActivityOptionsCompat
        options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
    activity.startActivity(intent, options.toBundle());
    */
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

    initActivityTransitions();
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);

    ViewCompat.setTransitionName(apbDetail, EXTRA_IMAGE);
    supportPostponeEnterTransition();

    setSupportActionBar(toolbarDetail);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
    clbDetail.setTitle(itemTitle);
    clbDetail.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

    Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(ivDetail, new Callback() {
      @Override public void onSuccess() {
        Bitmap bitmap = ((BitmapDrawable) ivDetail.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
          public void onGenerated(Palette palette) {
            applyPalette(palette);
          }
        });
      }

      @Override public void onError() {

      }
    });

    tvTitle.setText(itemTitle);
  }

  private void initActivityTransitions() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Slide transition = new Slide();
      transition.excludeTarget(android.R.id.statusBarBackground, true);
      getWindow().setEnterTransition(transition);
      getWindow().setReturnTransition(transition);
    }
  }

  private void applyPalette(Palette palette) {
    int primary = ContextCompat.getColor(this, R.color.primary);
    int primaryDark = getResources().getColor(R.color.primaryDark);
    clbDetail.setContentScrimColor(palette.getMutedColor(primary));
    clbDetail.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
  }
}
