package xyz.gonzapico.sa_butterknife.di;

import dagger.Component;
import javax.inject.Singleton;
import xyz.gonzapico.sa_butterknife.getDataUser.UserRepository;

/**
 * Created by gonzapico on 24/10/2017.
 */

@Singleton
@Component(modules={DataModule.class})
public interface DataComponent {

  void inject(UserRepository where);
}
