package app.ideal.family.elbabatain.di.who_are_we;


import androidx.lifecycle.ViewModel;

import app.ideal.family.elbabatain.di.viewModel.ViewModelKey;
import app.ideal.family.elbabatain.ui.whoarewe.WhoAreWe_ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class WhoAreWe_ViewModel_Module {

    @WhoAreWeScope
    @Binds
    @IntoMap
    @ViewModelKey(WhoAreWe_ViewModel.class)
    public abstract ViewModel bindWhoAreWeViewModel(WhoAreWe_ViewModel whoAreWe_viewModel);
}
