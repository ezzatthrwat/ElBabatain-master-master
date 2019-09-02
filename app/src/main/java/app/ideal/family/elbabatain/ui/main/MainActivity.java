package app.ideal.family.elbabatain.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.sunzn.banner.library.Banner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.main.AdsImg_model;
import app.ideal.family.elbabatain.network.main.HeaderExample_Model;
import app.ideal.family.elbabatain.util.ScreenUtils;
import app.ideal.family.elbabatain.viewmodels.ViewModelProviderFactory;
import dagger.android.support.DaggerAppCompatActivity;

import static app.ideal.family.elbabatain.util.BubbleAnimation.DoAnimation;


public class MainActivity extends DaggerAppCompatActivity {



    private List<HeaderExample_Model> HeaderData = new ArrayList<>();
    List<AdsImg_model> adsImgslist = new ArrayList<>();


    private static final String TAG = "MainActivity";

    private RecyclerView thridRV ;

    @Inject
    GridLayoutManager gridLayoutManager ;

    @Inject
    GridRecyclerViewAdapter adapter ;

    @Inject
    ViewModelProviderFactory providerFactory ;
    MainViewModel mainViewModel ;
    MainAdsViewModel mainAdsViewModel ;

    @Inject
    RequestManager requestManager ;

    private Banner banner ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        mainAdsViewModel = ViewModelProviders.of(this , providerFactory).get(MainAdsViewModel.class);
        initRecyclerView();
        ImageSlider();

        configFirstRecyclerView();
        setSliderViews();

        }

        private void initRecyclerView(){
            thridRV =  findViewById(R.id.GridRecyclerView);
            thridRV.setHasFixedSize(true);
            thridRV.setLayoutManager(gridLayoutManager);

        }

    private void configFirstRecyclerView() {

        int screenWidth = ScreenUtils.getScreenWidth(this);
        final int itemWidth = screenWidth / 3;



        mainViewModel.MHeaders.removeObservers(this);
        mainViewModel.MHeaders.observe(this, headersEntities -> {

            HeaderData.clear();
            HeaderData.addAll(headersEntities);


                Collections.reverse(HeaderData);
                //setAdapter
                adapter.setGridRecyclerViewAdapter(MainActivity.this, HeaderData, itemWidth , requestManager);
                thridRV.setAdapter(adapter);



        });



    }

    private void ImageSlider(){

         banner = findViewById(R.id.imageSlider);

        banner.setOnItemBindListener(new Banner.OnItemBindListener() {
            @Override
            public void onItemBind(int i, Object o, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                requestManager.load(adsImgslist.get(i).getImgUrl()).into(imageView);
            }
        });
    }

    private void setSliderViews() {

        mainAdsViewModel.adsImg_models.removeObservers(MainActivity.this);
        mainAdsViewModel.adsImg_models.observe(this, new Observer<List<AdsImg_model>>() {
        @Override
        public void onChanged(List<AdsImg_model> adsImg_models) {

            adsImgslist.clear();
            adsImgslist.addAll(adsImg_models);

            if (adsImgslist.size() > 0 ){


                    banner.setBannerData(adsImgslist);

//                mainAdsViewModel.adsImg_models.removeObservers(MainActivity.this);

            }

        }


    });

        Log.e(TAG, "setSliderViews: " + adsImgslist.size() );


    }



    @Override
    protected void onResume() {
        super.onResume();

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            runOnUiThread(() -> DoAnimation(thridRV , getApplicationContext()));

            //Do something after 100ms
        }, 100);

    }


    @Override
    protected void onRestart() {
        super.onRestart();

        configFirstRecyclerView();
        setSliderViews();

    }
}
