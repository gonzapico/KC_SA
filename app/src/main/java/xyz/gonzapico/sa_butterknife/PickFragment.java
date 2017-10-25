package xyz.gonzapico.sa_butterknife;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickFragment extends Fragment {

  @BindViews({ R.id.cbFrance, R.id.cbGermany, R.id.cbItaly }) CheckBox[] countries;
  final ButterKnife.Action<CheckBox> UNSELECT_ALL = new ButterKnife.Action<CheckBox>() {
    @Override public void apply(@NonNull CheckBox view, int index) {
      view.setChecked(false);
    }
  };

  final ButterKnife.Setter<CheckBox, Boolean> CHECK = new ButterKnife.Setter<CheckBox, Boolean>() {
    @Override public void set(@NonNull CheckBox view, Boolean value, int index) {
      view.setChecked(value != null ? value : false);
    }
  };

  @OnClick(R.id.btnSelectAll) void selectAll() {
    ButterKnife.apply(countries, CHECK, true);
  }

  @OnClick(R.id.btnUnselectAll) void unSelectAll() {
    ButterKnife.apply(countries, UNSELECT_ALL);
  }

  public PickFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @return A new instance of fragment PickFragment.
   */
  public static PickFragment newInstance() {
    PickFragment fragment = new PickFragment();
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.content_pick, container, false);
    ButterKnife.bind(this, view);
    return view;
  }
}
