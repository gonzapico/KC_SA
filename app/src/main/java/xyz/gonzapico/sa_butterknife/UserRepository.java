package xyz.gonzapico.sa_butterknife;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by gonzapico on 23/10/2017.
 */

public class UserRepository {
  final static List<ViewModel> mUsers = new ArrayList<>();

  private UserRepository() {
    // nop
  }

  private static List<ViewModel> getOriginalUserList() {
    final List<ViewModel> users = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      users.add(new ViewModel("Item " + i, "http://lorempixel.com/500/500/sports/" + i));
    }

    return users;
  }

  public static List<ViewModel> getUpListOfUsers(){

    if (mUsers.size() == 0){
      for (int i = 1; i <= 10; i++) {
        mUsers.add(new ViewModel("Item " + i, "http://lorempixel.com/500/500/sports/" + i));
      }
    }

    return mUsers;
  }

  public static List<ViewModel> getUserListSortedByName() {
    final List<ViewModel> actorList = getOriginalUserList();

    Collections.sort(actorList, new Comparator<ViewModel>() {
      @Override public int compare(ViewModel a1, ViewModel a2) {
        return a1.getTitle().compareTo(a2.getTitle());
      }
    });

    return actorList;
  }

  public static void addElement(ViewModel viewModel) {
    mUsers.add(viewModel);
  }
}
