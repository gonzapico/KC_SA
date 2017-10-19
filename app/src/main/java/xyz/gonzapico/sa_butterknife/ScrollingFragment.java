package xyz.gonzapico.sa_butterknife;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScrollingFragment extends Fragment implements ScrollingAdapter.OnItemClickListener {

  @BindView(R.id.rvScroll) RecyclerView rvScroll;

  private static List<ViewModel> items = new ArrayList<>();
  static {
    for (int i = 1; i <= 10; i++) {
      items.add(new ViewModel("Item " + i, "http://lorempixel.com/500/500/sports/" + i));
    }
  }
  public ScrollingFragment() {
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
    rvScroll.setAdapter(new ScrollingAdapter(items, this));
  }

  @Override public void onItemClick(View view, ViewModel viewModel) {
    DetailActivity.navigate((BaseKCActivity) getActivity(), view.findViewById(R.id.ivImageCard), viewModel);

  }
}
