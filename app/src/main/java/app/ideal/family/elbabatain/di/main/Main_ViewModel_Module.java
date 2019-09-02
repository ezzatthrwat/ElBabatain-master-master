package app.ideal.family.elbabatain.di.main;

import androidx.lifecycle.ViewModel;

import app.ideal.family.elbabatain.di.viewModel.ViewModelKey;
import app.ideal.family.elbabatain.ui.contain.ContainViewModel;
import app.ideal.family.elbabatain.ui.main.MainViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class Main_ViewModel_Module {


    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindContainViewModel(MainViewModel mainViewModel);
}
