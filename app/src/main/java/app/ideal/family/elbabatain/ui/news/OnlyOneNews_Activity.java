package app.ideal.family.elbabatain.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.news.News_Model;
import dagger.android.support.DaggerAppCompatActivity;

public class OnlyOneNews_Activity extends DaggerAppCompatActivity {

    private ImageView News_Image ;
    private TextView Title ;
     private TextView Des;

    private static final String TAG = "OnlyOneNews_Activity";

    @Inject
    RequestManager requestManager ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlyone_news);

        initWidget();

        onViewCreated();
    }


    public void onViewCreated() {

        Intent intent = getIntent();



            News_Model news_model = (News_Model) intent.getSerializableExtra("A");

//             String title = Data.getString("Title");
//             String image = Data.getString("ImageUrl");
//             String des = Data.getString("Description");
//
//             Log.e(TAG, "onViewCreated: "+             news_model.getDescription());

             requestManager.load(news_model.getImgUrl()).into(News_Image);
             Title.setText(news_model.getTitle());
//             Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();
             Des.setText(news_model.getDescription());
        Des.setMovementMethod(LinkMovementMethod.getInstance());


    }


    private void initWidget(){

        Title = findViewById(R.id.News_Title);

        News_Image = findViewById(R.id.News_Image);

        Des = findViewById(R.id.News_Description);

    }
}
