package app.ideal.family.elbabatain.ui.contain;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.R;

import app.ideal.family.elbabatain.network.contain.Contain_Model;
import app.ideal.family.elbabatain.viewmodels.ViewModelProviderFactory;
import dagger.android.support.DaggerAppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import static app.ideal.family.elbabatain.util.BubbleAnimation.DoAnimation;

public class ContainActivity extends DaggerAppCompatActivity implements TitleAdapter.OnItemClickListener {

    RecyclerView containRecyclerView ;
    RecyclerView TitlesRecyclerView ;

    ImageView Contain_Logo  , Contain_Back_arrow ;

    TextView TitleTextView ;

    NestedScrollView nestedScrollView ;

    CircleImageView icon_To_Up ;

    LinearLayout Linear_ContainActivity ;


    @Inject
    ContainAdapter  containAdapter ;

    @Inject
    TitleAdapter titleAdapter ;

    @Inject
    LinearLayoutManager  linearLayoutManager ;

    @Inject
    ViewModelProviderFactory providerFactory ;
    ContainViewModel containViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contani);

        containViewModel = ViewModelProviders.of(this, providerFactory).get(ContainViewModel.class);

        initWidget();
        getContainsData();
    }

    private void initWidget(){
        Linear_ContainActivity = findViewById(R.id.Linear_ContainActivity);
        Linear_ContainActivity.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_animation_from_left));

        containRecyclerView = findViewById(R.id.ContantRecyclerView);
        containRecyclerView.setHasFixedSize(true);
        containRecyclerView.setLayoutManager(linearLayoutManager);
//        containRecyclerView.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_from_up));

        TitlesRecyclerView = findViewById(R.id.containTitles_RecyclerView);
        TitlesRecyclerView.setHasFixedSize(true);
//        TitlesRecyclerView.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_animation_right));
        TitlesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        TitleTextView = findViewById(R.id.TitleTextView);
//        TitleTextView.setAnimation(AnimationUtils.loadAnimation(this , R.anim.fade_transition_from_up));

        nestedScrollView = findViewById(R.id.NestedScroll_1);

        Contain_Logo = findViewById(R.id.Contain_Logo);
        Contain_Back_arrow = findViewById(R.id.Contain_Back_arrow);
        Contain_Back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        icon_To_Up = findViewById(R.id.icon_To_Up);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
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
                    }
                }
            }
        });

    }
    private void getContainsData(){

        int Default = 0   ;
        Intent intent = getIntent();

        int ID = intent.getIntExtra("HEADER_ID" , Default) ;

        TitleTextView.setText(intent.getStringExtra("HEADER_Title"));

        containViewModel.getAllContains(ID).observe(this, new Observer<List<Contain_Model>>() {
            @Override
            public void onChanged(List<Contain_Model> contain_models) {

                containAdapter.setContainAdapter(ContainActivity.this , contain_models);
                titleAdapter.setTitleAdapter(ContainActivity.this , contain_models);
                containRecyclerView.setAdapter(containAdapter);
                TitlesRecyclerView.setAdapter(titleAdapter);


            }
        });

    }


    @Override
    public void onItemClick(int position) {

//        nestedScrollView.post(new Runnable() {
//            @Override
//            public void run() {
//              nestedScrollView.scrollTo(0 , containRecyclerView.scrollToPosition(position-1));
//
//            }
//        });

        containRecyclerView.scrollToPosition(position);
        float y = containRecyclerView.getY() + containRecyclerView.getChildAt(position).getY();

        nestedScrollView.scrollTo(0 , (int)  y );

    }


    @Override
    protected void onResume() {
        super.onResume();

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            runOnUiThread(() -> DoAnimation(Contain_Logo , getApplicationContext()));
            //Do something after 100ms
        }, 100);

    }

//    @Override
//    public void onToUpItemClick(int position) {
//        float y = TitlesRecyclerView.getY() + TitlesRecyclerView.getChildAt(position).getY();
//        nestedScrollView.scrollTo(0 , (int)  y );
//
//        TitlesRecyclerView.scrollToPosition(position);
//
//    }

    public void onScrollToUpIcon(View view) {

        nestedScrollView.scrollTo(0 , 0 );
    }
}
