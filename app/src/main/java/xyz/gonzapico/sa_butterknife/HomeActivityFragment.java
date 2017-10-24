package xyz.gonzapico.sa_butterknife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

  @BindView(R.id.tvWelcome) TextView tvWelcome;
  @BindView(R.id.etInput) EditText etInput;

  @OnTextChanged(value = R.id.etInput, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  void afterTextChanged(Editable editable) {
    tvWelcome.setText(editable);
  }

  Unbinder unbinder;

  public HomeActivityFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
