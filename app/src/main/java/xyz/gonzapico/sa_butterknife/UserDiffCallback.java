package xyz.gonzapico.sa_butterknife;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import java.util.List;

/**
 * Created by gonzapico on 22/10/2017.
 */

public class UserDiffCallback extends DiffUtil.Callback {

  private final List<ViewModel> mOldUserList;
  private final List<ViewModel> mNewUserList;

  public UserDiffCallback(List<ViewModel> oldEmployeeList, List<ViewModel> newEmployeeList) {
    this.mOldUserList = oldEmployeeList;
    this.mNewUserList = newEmployeeList;
  }

  @Override public int getOldListSize() {
    return mOldUserList.size();
  }

  @Override public int getNewListSize() {
    return mNewUserList.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return mOldUserList.get(oldItemPosition).getTitle() == mNewUserList.get(
        newItemPosition).getTitle();
  }

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    final ViewModel oldEmployee = mOldUserList.get(oldItemPosition);
    final ViewModel newEmployee = mNewUserList.get(newItemPosition);

    return oldEmployee.getTitle().equals(newEmployee.getTitle());
  }

  @Nullable
  @Override
  public Object getChangePayload(int oldItemPosition, int newItemPosition) {
    // Implement method if you're going to use ItemAnimator
    return super.getChangePayload(oldItemPosition, newItemPosition);
  }
}
