package app.ideal.family.elbabatain.ui.events;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.events.Events_Model;

import static app.ideal.family.elbabatain.util.BubbleAnimation.DoAnimation;

public class Events_Adapter  extends RecyclerView.Adapter<Events_Adapter.ViewHolder>{


    private Context context ;

    private List<Events_Model> eventsModelList ;

    void setEvents_Adapter(Context context, List<Events_Model> eventsModelList){

        this.context = context ;
        this.eventsModelList = eventsModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        return new ViewHolder(mLayoutInflater.inflate(R.layout.event_list_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.BindData(eventsModelList.get(holder.getAdapterPosition()));


    }

    @Override
    public int getItemCount() {
        return eventsModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView TitleTv ;
        TextView DateTV ;
        TextView EndDateTV ;
        TextView EventLinkUrl ;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            TitleTv =itemView.findViewById(R.id.TitleTV);
            DateTV =itemView.findViewById(R.id.DateTV);
            EndDateTV = itemView.findViewById(R.id.EndDateTV);
            EventLinkUrl = itemView.findViewById(R.id.EventLinkUrl);
        }
        void BindData(Events_Model eventsModel) {


            TitleTv.setText(eventsModel.getTitle());
            DateTV.setText(eventsModel.getStartDate());
            EndDateTV.setText(eventsModel.getEndDate());
            EventLinkUrl.setText(eventsModel.getUrl());
            EventLinkUrl.setMovementMethod(LinkMovementMethod.getInstance());

//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent  intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(eventsModel.getUrl()));
//                    context.startActivity(intent);
//                    DoAnimation(v , context);
//                }
//            });
        }
    }
}
