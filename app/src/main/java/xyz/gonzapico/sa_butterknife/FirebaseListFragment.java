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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirebaseListFragment extends Fragment implements ScrollingAdapter.OnItemClickListener {

  @BindView(R.id.rvScroll) RecyclerView rvScroll;

  ScrollingAdapter adapter;
  public FirebaseListFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.content_scrolling, container, false);
    ButterKnife.bind(this, view);
    setUpRV();
    getDataFromFirebase();
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

  private void getDataFromFirebase(){
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    try {
      database.setPersistenceEnabled(true);
    }
    catch (Exception e){

    }
    DatabaseReference myRef = database.getReference();

    // Read from the database
    myRef.addChildEventListener(new ChildEventListener() {
      @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        User user = dataSnapshot.getValue(User.class);
        UserRepository.addElement(user);
        adapter.swapItems(UserRepository.setUpListOfUsers());
      }

      @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {

      }

      @Override public void onChildRemoved(DataSnapshot dataSnapshot) {

      }

      @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {

      }

      @Override public void onCancelled(DatabaseError databaseError) {

      }
    });
  }
}
