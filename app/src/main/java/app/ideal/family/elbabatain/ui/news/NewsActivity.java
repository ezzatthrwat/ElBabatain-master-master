package app.ideal.family.elbabatain.ui.news;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.RequestManager;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.news.News_Model;
import app.ideal.family.elbabatain.viewmodels.ViewModelProviderFactory;
import dagger.android.support.DaggerAppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import static app.ideal.family.elbabatain.util.BubbleAnimation.DoAnimation;

public class NewsActivity extends DaggerAppCompatActivity {

    private static final String TAG = "NewsActivity";

   private ImageView News_Logo;

    @Inject
    Bundle bundle;

    @Inject
    OnlyOneNews_Activity onlyOneNews_activity;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    News_Adapter news_adapter;

    @Inject
    ViewModelProviderFactory providerFactory;
    NewsViewModel newsViewModel;

    @Inject
    RequestManager requestManager;

    RecyclerView News_RecyclerView;

    NestedScrollView News_NestedScroll;

    CircleImageView icon_To_Up ;

    private int PageNumber = 1 ;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_avtivity);

        newsViewModel = ViewModelProviders.of(this , providerFactory).get(NewsViewModel.class);

        initRecyclerView();
        initWidget();
        ObserveOnNewsData();
    }

    private void initRecyclerView(){

        News_RecyclerView = findViewById(R.id.News_RecyclerView) ;
        News_RecyclerView.setHasFixedSize(true);
        News_RecyclerView.setLayoutManager(linearLayoutManager);
        News_RecyclerView.setAnimation(AnimationUtils.loadAnimation(this ,R.anim.fade_transition_animation_from_left));

        News_RecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems ) >= (totalItemCount))
                        {
//                            progresParRecyclerPaging.setVisibility(View.VISIBLE);
                            PageNumber++ ;
                            Log.e(TAG, "onScrolled: " +PageNumber );
                            Log.e(TAG, "onScrolled: " +"Loading...." );
                            ObserveOnNewsData();
                            loading = false;
                            //Do pagination.. i.e. fetch new data
                        }
                    }

                }


            }
        });
//
//        TextView TitleTextView = findViewById(R.id.TitleTextView);
//        TitleTextView.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_from_up));




    }


    private void initWidget(){

        News_Logo = findViewById(R.id.News_Logo);
        ImageView news_Back_arrow = findViewById(R.id.News_Back_arrow);
        news_Back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        LinearLayout linear_NewsActivity = findViewById(R.id.Linear_NewsActivity);
        linear_NewsActivity.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_animation_from_left));

        icon_To_Up = findViewById(R.id.icon_To_Up);
        News_NestedScroll = findViewById(R.id.News_NestedScroll);


        News_NestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY >= oldScrollY){


                    if (icon_To_Up.getVisibility() == View.VISIBLE){
                        icon_To_Up.setVisibility(View.GONE);
                    }


                }else{
                    if (scrollY == 0) {
                        icon_To_Up.setVisibility(View.GONE);
                    }else{
                        icon_To_Up.setVisibility(View.VISIBLE);
                        DoAnimation(icon_To_Up , NewsActivity.this);


                    }
                }
            }
        });

    }

    private void ObserveOnNewsData(){

        newsViewModel.getAllNewsDataFromDatabase(PageNumber).observe(this, new Observer<List<News_Model>>() {
            @Override
            public void onChanged(List<News_Model> news_models) {

                if (news_models != null){

                    Collections.reverse(news_models);
                    news_adapter.setNews_Adapter(NewsActivity.this , news_models ,requestManager  , onlyOneNews_activity);
                    News_RecyclerView.setAdapter(news_adapter);
                    loading = true;
                }


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            runOnUiThread(() -> DoAnimation(News_Logo , getApplicationContext()));
            //Do something after 100ms
        }, 100);
    }

    public void onScrollToUpIcon(View view) {

        News_NestedScroll.scrollTo(0 , 0 );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
