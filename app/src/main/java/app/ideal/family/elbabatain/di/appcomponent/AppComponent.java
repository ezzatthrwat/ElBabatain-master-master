package app.ideal.family.elbabatain.di.appcomponent;

import android.app.Application;

import javax.inject.Singleton;

import app.ideal.family.elbabatain.AppRepository;
import app.ideal.family.elbabatain.di.ActivityBuildersModel;
import app.ideal.family.elbabatain.di.BaseApplication;
import app.ideal.family.elbabatain.di.viewModel.ViewModelFactoryModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class ,
                ActivityBuildersModel.class,
                AppModule.class,
                ViewModelFactoryModule.class ,

        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

AppRepository appRepository();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
