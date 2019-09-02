package app.ideal.family.elbabatain.di.events;

import androidx.lifecycle.ViewModel;

import app.ideal.family.elbabatain.di.viewModel.ViewModelKey;
import app.ideal.family.elbabatain.ui.contain.ContainViewModel;
import app.ideal.family.elbabatain.ui.events.Event_ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class Events_ViewModel_Module {

    @EventScope
    @Binds
    @IntoMap
    @ViewModelKey(Event_ViewModel.class)
    public abstract ViewModel bindEventViewModel(Event_ViewModel event_viewModel);



}
