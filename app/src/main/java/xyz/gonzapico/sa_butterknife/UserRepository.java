package xyz.gonzapico.sa_butterknife;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by gonzapico on 23/10/2017.
 */

public class UserRepository {
  final static List<User> mUsers = new ArrayList<>();

  private UserRepository() {
    // nop
  }

  private static List<User> getOriginalUserList() {
    final List<User> users = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      users.add(new User("Item " + i, "http://lorempixel.com/500/500/sports/" + i));
    }

    return users;
  }

  public static List<User> setUpListOfUsers(){

    if (mUsers.size() == 0){
      for (int i = 1; i <= 10; i++) {
        mUsers.add(new User("Item " + i, "http://lorempixel.com/500/500/sports/" + i));
      }
    }

    return mUsers;
  }

  public static List<User> getUserListSortedByName() {
    final List<User> userList = getOriginalUserList();

    Collections.sort(userList, new Comparator<User>() {
      @Override public int compare(User a1, User a2) {
        return a1.getName().compareTo(a2.getName());
      }
    });

    return userList;
  }

  public static void addElement(User viewModel) {
    mUsers.add(viewModel);
  }
}
