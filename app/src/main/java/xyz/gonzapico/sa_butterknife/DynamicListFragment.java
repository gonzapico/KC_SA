package xyz.gonzapico.sa_butterknife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicListFragment extends Fragment implements ScrollingAdapter.OnItemClickListener {

  @BindView(R.id.rvScroll) RecyclerView rvScroll;


  ScrollingAdapter adapter;
  public DynamicListFragment() {
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
