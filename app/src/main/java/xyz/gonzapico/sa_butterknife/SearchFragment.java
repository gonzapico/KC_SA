package xyz.gonzapico.sa_butterknife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {

  @BindView(R.id.autoTVFilms) AutoCompleteTextView autoTVFilms;
  @BindArray(R.array.films_list) String[] filmFilter;

  @BindView(R.id.tvWelcome) TextView tvWelcome;
  @BindView(R.id.etInput) EditText etInput;

  @OnTextChanged(value = R.id.etInput, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
  void afterTextChanged(Editable editable) {
    tvWelcome.setText(editable);
  }

  Unbinder unbinder;

  public SearchFragment() {

  }

  private void setUpAutoComplete() {
    List<String> listOfFilms = Arrays.asList(filmFilter);
    ArrayAdapter<String> adapterFilmSearch =
        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, listOfFilms);
    autoTVFilms.setAdapter(adapterFilmSearch);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_search, container, false);
    unbinder = ButterKnife.bind(this, view);
    setUpAutoComplete();
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
