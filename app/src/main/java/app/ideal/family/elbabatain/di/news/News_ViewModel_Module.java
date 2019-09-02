package app.ideal.family.elbabatain.di.news;

import androidx.lifecycle.ViewModel;

import app.ideal.family.elbabatain.di.viewModel.ViewModelKey;
import app.ideal.family.elbabatain.ui.news.NewsViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class News_ViewModel_Module {

    @NewsScope
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel.class)
    public abstract ViewModel bindNewViewModel(NewsViewModel newsViewModel);
}
