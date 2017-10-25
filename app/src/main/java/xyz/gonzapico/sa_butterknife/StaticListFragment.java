package xyz.gonzapico.sa_butterknife;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaticListFragment extends Fragment implements ScrollingAdapter.OnItemClickListener {

  @BindView(R.id.rvScroll) RecyclerView rvScroll;


  ScrollingAdapter adapter;
  public StaticListFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.content_scrolling, container, false);
    ButterKnife.bind(this, view);
    setUpRV();
    return view;
  }

  private void setUpRV(){
    rvScroll.setHasFixedSize(true);
    rvScroll.setLayoutManager(new GridLayoutManager(getContext(), 2));
    adapter = new ScrollingAdapter(UserRepository.getUserListSortedByName(), this);
    rvScroll.setAdapter(adapter);
  }

  @Override public void onItemClick(View view, ViewModel viewModel) {
    DetailActivity.navigate((BaseKCActivity) getActivity(), view.findViewById(R.id.ivImageCard), viewModel);

  }

}
