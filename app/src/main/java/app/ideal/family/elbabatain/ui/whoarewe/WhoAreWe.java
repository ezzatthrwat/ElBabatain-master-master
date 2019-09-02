package app.ideal.family.elbabatain.ui.whoarewe;


import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.who_are_we.WhoAreWe_Model;
import app.ideal.family.elbabatain.ui.news.NewsViewModel;
import app.ideal.family.elbabatain.viewmodels.ViewModelProviderFactory;
import dagger.android.support.DaggerAppCompatActivity;

import static app.ideal.family.elbabatain.util.BubbleAnimation.DoAnimation;

public class WhoAreWe extends DaggerAppCompatActivity {

    ImageView WhoAreWe_Logo  , WhoAreWe_Back_arrow ;

    TextView WhoAreWe_Text ;

    @Inject
    ViewModelProviderFactory providerFactory ;
    WhoAreWe_ViewModel whoAreWe_viewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_are_we);

        whoAreWe_viewModel = ViewModelProviders.of(this , providerFactory).get(WhoAreWe_ViewModel.class);

        initWidget();
        getWhoAreWeDataFromDatabase();
    }




    private void getWhoAreWeDataFromDatabase(){

        whoAreWe_viewModel.whoAreWe_modelLiveData.observe(this, new Observer<WhoAreWe_Model>() {
            @Override
            public void onChanged(WhoAreWe_Model whoAreWe_model) {

                if (whoAreWe_model != null) {
                    WhoAreWe_Text.setText(whoAreWe_model.getDescription());
                }
            }
        });
    }

    private void initWidget(){

        WhoAreWe_Text = findViewById(R.id.WhoAreWe_Text);
        WhoAreWe_Text.setMovementMethod(LinkMovementMethod.getInstance());

        WhoAreWe_Logo = findViewById(R.id.WhoAreWe_Logo);

        NestedScrollView WhoAreWe_Container =findViewById(R.id.WhoAreWe_Container);
        WhoAreWe_Container.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_transition_animation_from_left));

        WhoAreWe_Back_arrow = findViewById(R.id.WhoAreWe_Back_arrow);
        WhoAreWe_Back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            runOnUiThread(() -> DoAnimation(WhoAreWe_Logo , getApplicationContext()));
            //Do something after 100ms
        }, 100);
    }
}
