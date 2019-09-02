package app.ideal.family.elbabatain.di;

import app.ideal.family.elbabatain.di.contactUs.ContactUsScope;
import app.ideal.family.elbabatain.di.contactUs.ContactUs_Module;
import app.ideal.family.elbabatain.di.contain.ContainScope;
import app.ideal.family.elbabatain.di.contain.Contain_ViewModel_Module;
import app.ideal.family.elbabatain.di.contain.Contain_module;
import app.ideal.family.elbabatain.di.events.EventScope;
import app.ideal.family.elbabatain.di.events.Event_Module;
import app.ideal.family.elbabatain.di.events.Events_ViewModel_Module;
import app.ideal.family.elbabatain.di.main.MainScope;
import app.ideal.family.elbabatain.di.main.Main_Ads_ViewModel_Module;
import app.ideal.family.elbabatain.di.main.Main_ViewModel_Module;
import app.ideal.family.elbabatain.di.news.NewsScope;
import app.ideal.family.elbabatain.di.news.News_Module;
import app.ideal.family.elbabatain.di.news.News_ViewModel_Module;
import app.ideal.family.elbabatain.di.who_are_we.WhoAreWeScope;
import app.ideal.family.elbabatain.di.who_are_we.WhoAreWe_Module;
import app.ideal.family.elbabatain.di.who_are_we.WhoAreWe_ViewModel_Module;
import app.ideal.family.elbabatain.ui.contactus.ContactUs;
import app.ideal.family.elbabatain.ui.contain.ContainActivity;
import app.ideal.family.elbabatain.ui.events.EventActivity;
import app.ideal.family.elbabatain.ui.main.MainActivity;
import app.ideal.family.elbabatain.di.main.Main_Model;
import app.ideal.family.elbabatain.ui.news.NewsActivity;
import app.ideal.family.elbabatain.ui.news.OnlyOneNews_Activity;
import app.ideal.family.elbabatain.ui.whoarewe.WhoAreWe;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuildersModel {

    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    Main_Model.class ,
                    Main_ViewModel_Module.class ,
                    Main_Ads_ViewModel_Module.class

            }
    )
    public abstract MainActivity contributesMainActivity();



    @ContainScope
    @ContributesAndroidInjector(
            modules = {
                    Contain_module.class ,
                    Contain_ViewModel_Module.class
            }
    )
    public abstract ContainActivity contributesContainActivity();


    @NewsScope
    @ContributesAndroidInjector(
            modules = {
                    News_Module.class ,
                    News_ViewModel_Module.class ,
            }
    )
    public abstract NewsActivity contributesNewsActivity();

    @NewsScope
    @ContributesAndroidInjector()
    public abstract OnlyOneNews_Activity onlyOneNews_fragment();



    @EventScope
    @ContributesAndroidInjector(
            modules = {
                    Event_Module.class,
                    Events_ViewModel_Module.class
            }
    )
    public abstract EventActivity contributesEventActivity();


    @ContactUsScope
    @ContributesAndroidInjector(
            modules = {
                    ContactUs_Module.class
    }
    )
    public abstract ContactUs contributesContactUs();


    @WhoAreWeScope
    @ContributesAndroidInjector(
            modules = {
                    WhoAreWe_Module.class ,
                    WhoAreWe_ViewModel_Module.class
            }
    )
    public abstract WhoAreWe contributeWhoAreWe();


}
