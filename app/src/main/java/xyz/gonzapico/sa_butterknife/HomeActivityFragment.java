package xyz.gonzapico.sa_butterknife;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

  @BindView(R.id.tvWelcome) TextView tvWelcome;
  @BindView(R.id.etInput) EditText etInput;
  @OnTextChanged(value = R.id.etInput, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  void afterTextChanged(Editable editable){
    tvWelcome.setText(editable);
  }
  Unbinder unbinder;
  @OnClick(R.id.btScroll) void goToScroll(View view){
    Intent intentScroll = new Intent(mActivity, ScrollingActivity.class);
    mActivity.startActivity(intentScroll);
  }

  @OnClick(R.id.btSelect) void goToSelect(View view){
    Intent intentScroll = new Intent(mActivity, PickActivity.class);
    mActivity.startActivity(intentScroll);
  }

  private HomeActivity mActivity;

  public HomeActivityFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    unbinder = ButterKnife.bind(this, view);
    mActivity = (HomeActivity) getActivity();
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
