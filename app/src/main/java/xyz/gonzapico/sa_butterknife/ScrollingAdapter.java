package xyz.gonzapico.sa_butterknife;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by gonzapico on 16/10/2017.
 */

public class ScrollingAdapter extends RecyclerView.Adapter<ScrollingAdapter.ScrollViewHolder> implements View.OnClickListener {

  private List<ViewModel> listOfElements;
  private OnItemClickListener onItemClickListener;

  public ScrollingAdapter(List<ViewModel> elements, OnItemClickListener onItemClickListener){
    listOfElements = elements;
    this.onItemClickListener = onItemClickListener;
  }

  @Override public ScrollViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View viewInflated = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_scroll, parent, false);
    viewInflated.setOnClickListener(this);
    ScrollViewHolder scrollViewHolder = new ScrollViewHolder(viewInflated);
    return scrollViewHolder;
  }

  @Override public void onBindViewHolder(final ScrollViewHolder holder, final int position) {
    holder.mTextContent.setText(listOfElements.get(position).getTitle());
    Picasso.with(holder.ivContent.getContext()).load(listOfElements.get(position).getUrlImage()).into(holder.ivContent);
    holder.itemView.setTag(listOfElements.get(position));
  }

  @Override public int getItemCount() {
    return listOfElements.size();
  }

  @Override public void onClick(View view) {
    onItemClickListener.onItemClick(view, (ViewModel) view.getTag());
  }

  public void addElement(ViewModel viewModel) {

    // final UserDiffCallback diffCallback = new UserDiffCallback(this.listOfElements, viewModel);
    // final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

    /*
    this.listOfElements.clear();
    this.listOfElements.addAll(viewModel);
    diffResult.dispatchUpdatesTo(this);
    */
    // diffResult.dispatchUpdatesTo(this);
  }

  public static class ScrollViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tvRowText) TextView mTextContent;
    @BindView(R.id.ivImageCard) ImageView ivContent;

    public ScrollViewHolder(View v){
      super(v);
      ButterKnife.bind(this, v);
    }

  }

  public interface OnItemClickListener {

    void onItemClick(View view, ViewModel viewModel);

  }

  public void swapItems(List<ViewModel> actors) {
    final UserDiffCallback diffCallback = new UserDiffCallback(this.listOfElements, actors);
    final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

    this.listOfElements.clear();
    this.listOfElements.addAll(actors);
    diffResult.dispatchUpdatesTo(this);
  }
}
