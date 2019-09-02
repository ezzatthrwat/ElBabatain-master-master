package app.ideal.family.elbabatain.di.appcomponent;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import app.ideal.family.elbabatain.AppRepository;
import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.util.Constant;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class AppModule {

    @Singleton
    @Provides
    static Retrofit providerRetrofitInstance(Application application){

         int REQUEST_TIMEOUT = 60;

        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .build();

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        httpClient.addInterceptor(interceptor);

//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                Request.Builder requestBuilder = original.newBuilder()
//                        .addHeader("Accept", "application/json")
//                        .addHeader("Content-Type", "application/json");
//
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });


        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(httpClient)
                /*.addCallAdapterFactory(RxJava2CallAdapterFactory.create())*/
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    @Singleton
    @Provides
    static RequestOptions providerRequestOptions(){

        return RequestOptions
                .placeholderOf(R.drawable.text_logo)
                .error(android.R.drawable.ic_menu_close_clear_cancel);
    }


    @Singleton
    @Provides
    static RequestManager providerRequestManager(Application application , RequestOptions requestOptions){

        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }

//    @Singleton
//    @Provides
//    static AppRepository prodviderAppRepository(){
//        return new AppRepository();
//    }

}
