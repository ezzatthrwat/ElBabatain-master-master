package app.ideal.family.elbabatain.ui.news;

import android.content.Context;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import java.util.List;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.news.News_Model;

public class News_Adapter extends RecyclerView.Adapter<News_Adapter.ViewHolder>{


    private Context context ;

    private List<News_Model> newsModelList ;

    private RequestManager requestManager ;


    private OnlyOneNews_Activity onlyOneNews_activity;


    void setNews_Adapter(Context context, List<News_Model> newsModelList, RequestManager requestManager, OnlyOneNews_Activity onlyOneNews_activity){

        this.context = context ;
        this.newsModelList = newsModelList ;
        this.requestManager = requestManager;
        this.onlyOneNews_activity = onlyOneNews_activity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        return new ViewHolder(mLayoutInflater.inflate(R.layout.news_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.BindData(newsModelList.get(holder.getAdapterPosition()));

        holder.Reade_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , OnlyOneNews_Activity.class);
                intent.putExtra("A" ,  newsModelList.get(position));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Title , Reade_More;
        ImageView News_Image;
        TextView Description ;
        CardView News_Container ;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.News_Title);
            Reade_More = itemView.findViewById(R.id.Read_More);
            News_Image = itemView.findViewById(R.id.News_Image);
            Description = itemView.findViewById(R.id.News_Description);
            Description.setMovementMethod(LinkMovementMethod.getInstance());


        }

        void BindData(News_Model news_model){

            if (news_model != null){

                Title.setText(news_model.getTitle());

                requestManager.load(news_model.getImgUrl()).into(News_Image);
//                Glide.with(itemView).load(news_model.getImgUrl()).into(News_Image);
                Description.setText( news_model.getDescription());
            }
        }
    }
}
