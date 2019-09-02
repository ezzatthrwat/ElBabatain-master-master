package app.ideal.family.elbabatain.di.news;

import app.ideal.family.elbabatain.ui.news.OnlyOneNews_Activity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class OnlyOneNews_Fragment_Module {

    @ContributesAndroidInjector()
    abstract OnlyOneNews_Activity contribute_OnlyOneNews_fragment();

}
