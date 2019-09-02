package app.ideal.family.elbabatain.di.viewModel;

import androidx.lifecycle.ViewModelProvider;

import app.ideal.family.elbabatain.viewmodels.ViewModelProviderFactory;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {


    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

}

