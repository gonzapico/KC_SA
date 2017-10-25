package xyz.gonzapico.sa_butterknife;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import java.util.List;

/**
 * Created by gonzapico on 22/10/2017.
 */

public class UserDiffCallback extends DiffUtil.Callback {

  private final List<User> mOldUserList;
  private final List<User> mNewUserList;

  public UserDiffCallback(List<User> oldUserList, List<User> newUserList) {
    this.mOldUserList = oldUserList;
    this.mNewUserList = newUserList;
  }

  @Override public int getOldListSize() {
    return mOldUserList.size();
  }

  @Override public int getNewListSize() {
    return mNewUserList.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return mOldUserList.get(oldItemPosition).getName() == mNewUserList.get(
        newItemPosition).getName();
  }

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    final User oldUser = mOldUserList.get(oldItemPosition);
    final User newUser = mNewUserList.get(newItemPosition);

    return oldUser.getName().equals(newUser.getName());
  }

  @Nullable
  @Override
  public Object getChangePayload(int oldItemPosition, int newItemPosition) {
    // Implement method if you're going to use ItemAnimator
    return super.getChangePayload(oldItemPosition, newItemPosition);
  }
}
