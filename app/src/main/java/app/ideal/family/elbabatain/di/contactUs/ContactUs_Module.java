package app.ideal.family.elbabatain.di.contactUs;

import app.ideal.family.elbabatain.network.contact_us.ContactUsApi;
import app.ideal.family.elbabatain.network.contact_us.ContactUs_Model;
import app.ideal.family.elbabatain.network.contact_us.ContactUs_Send_Body;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ContactUs_Module {


    @ContactUsScope
    @Provides
    static ContactUsApi provideContactUsApi (Retrofit retrofit){
        return retrofit.create(ContactUsApi.class);
    }

    @ContactUsScope
    @Provides
    static ContactUs_Send_Body provideContactUs_model (){
        return new ContactUs_Send_Body();
    }

}
