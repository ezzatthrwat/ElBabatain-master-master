package app.ideal.family.elbabatain.ui.main;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.SplashScreen;
import app.ideal.family.elbabatain.network.main.HeaderExample_Model;
import app.ideal.family.elbabatain.ui.contactus.ContactUs;
import app.ideal.family.elbabatain.ui.contain.ContainActivity;
import app.ideal.family.elbabatain.ui.events.EventActivity;
import app.ideal.family.elbabatain.ui.news.NewsActivity;
import app.ideal.family.elbabatain.ui.whoarewe.WhoAreWe;
import de.hdodenhof.circleimageview.CircleImageView;

import static app.ideal.family.elbabatain.util.BubbleAnimation.DoAnimation;

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.ViewHolder> {

    private List<HeaderExample_Model> mDataList;
    private LayoutInflater mLayoutInflater;
    private int mItemWidth;

    private Context context ;


    private RequestManager requestManager ;


    void setGridRecyclerViewAdapter(Context context,
                                    List<HeaderExample_Model> dataList,
                                    int itemWidth, RequestManager requestManager){
        this.context = context;
        this.mDataList = dataList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mItemWidth = itemWidth;
        this.requestManager = requestManager ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_item, parent, false), mItemWidth);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


//        DoAnimation(holder.imageView , context);


        holder.bindData(mDataList.get(position));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (mDataList.get(holder.getAdapterPosition()).getTitle()) {

                    case "تواصل معنا" :
                        context.startActivity(new Intent(context  , ContactUs.class));
                        break;
                    case "اخبار الاسرة":
                        context.startActivity(new Intent(context  , NewsActivity.class));
                        break;
                    case "تقويم المناسبات":

                        context.startActivity(new Intent(context , EventActivity.class));
                        break;
                    case "من نحن":
                        context.startActivity(new Intent(context , WhoAreWe.class));
                        break;
                    case "YouTube":
                        GoToYouTube();
                        break;
                    case "Twitter":
                              GoTOTwitter();
                        break;
                    case "Instagram":
                              GoToInstgram();
                        break;

                    case "WhatsApp":
                               SendByWhatsApp();
                        break;
                    case "Snapchat":
                               GoT0Snapchat();
                           break;

                    default:

                        Intent intent = new Intent((Activity) context, ContainActivity.class);
                        intent.putExtra("HEADER_ID", mDataList.get(holder.getAdapterPosition()).getId());
                        intent.putExtra("HEADER_Title", mDataList.get(holder.getAdapterPosition()).getTitle() );

                        context.startActivity(intent);
                        break;
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }


    private void GoTOTwitter(){
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=familyalbabtain")));
        }catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/familyalbabtain")));
        }

    }
    private void SendByWhatsApp() {

        boolean installed = appInstalledOrNot();
        if(installed) {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW );
            sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+966555229193"));
            sendIntent.setPackage("com.whatsapp");
            context.startActivity(sendIntent);


        } else {
            Toast.makeText(context, "WhatsApp Not Installed !!", Toast.LENGTH_LONG).show();
        }

    }
    private void GoT0Snapchat(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + "Familyalbabtain"));
            intent.setPackage("com.snapchat.android");
            context.startActivity(intent);
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + "Familyalbabtain")));
        }
    }
    private void GoToInstgram(){
        Uri uri = Uri.parse("http://instagram.com/_u/familyalbabtain");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            context.startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/familyalbabtain")));
        }

    }
    private boolean appInstalledOrNot() {
        PackageManager pm = context.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
    private void GoToYouTube(){
        Intent sendIntent = new Intent(Intent.ACTION_VIEW );
        sendIntent.setData(Uri.parse("https://www.youtube.com/familyalbabtain"));
        sendIntent.setPackage("com.google.android.youtube");

        context.startActivity(sendIntent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private CircleImageView imageView;
        private TextView textView;

        ViewHolder(View itemView, int itemWidth) {
            super(itemView);

            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = itemWidth;

            imageView = (CircleImageView) itemView.findViewById(R.id.view_item_icon);
            textView = (TextView) itemView.findViewById(R.id.view_item_title);

        }

        void bindData(HeaderExample_Model itemData) {

            if (itemData != null) {
                itemView.setVisibility(View.VISIBLE);
                requestManager.load(itemData.getImgUrl()).into(imageView);
//                Glide.with(itemView).load(itemData.getImgUrl()).into(imageView);
                textView.setText(itemData.getTitle());
            } else {
                itemView.setVisibility(View.GONE);
            }
        }
    }
}
