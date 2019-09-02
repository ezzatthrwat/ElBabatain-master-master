package app.ideal.family.elbabatain.di.contain;


import androidx.lifecycle.ViewModel;

import app.ideal.family.elbabatain.di.viewModel.ViewModelKey;
import app.ideal.family.elbabatain.ui.contain.ContainViewModel;
import app.ideal.family.elbabatain.ui.main.MainViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class Contain_ViewModel_Module {



    @ContainScope
    @Binds
    @IntoMap
    @ViewModelKey(ContainViewModel.class)
    public abstract ViewModel bindMainViewModel(ContainViewModel containViewModel);
}
