package xyz.gonzapico.sa_butterknife;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickActivity extends AppCompatActivity {

  @BindViews({ R.id.cbFrance, R.id.cbGermany, R.id.cbItaly }) CheckBox[] countries;
  final ButterKnife.Action<CheckBox> UNSELECT_ALL =
      new ButterKnife.Action<CheckBox>() {
    @Override public void apply(@NonNull CheckBox view, int index) {
      view.setChecked(false);
    }
  };

  final ButterKnife.Setter<CheckBox, Boolean> CHECK =
      new ButterKnife.Setter<CheckBox, Boolean>() {
    @Override public void set(@NonNull CheckBox view, Boolean value, int index) {
      view.setChecked(value !=  null ? value : false);
    }
  };
  @OnClick(R.id.btnSelectAll) void selectAll(){
    ButterKnife.apply(countries, CHECK, true);
  }

  @OnClick(R.id.btnUnselectAll) void unSelectAll(){
    ButterKnife.apply(countries, UNSELECT_ALL);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pick);
    ButterKnife.bind(this);
  }
}
