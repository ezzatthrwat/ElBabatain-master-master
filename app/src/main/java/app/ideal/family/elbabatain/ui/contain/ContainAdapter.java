package app.ideal.family.elbabatain.ui.contain;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.contain.Contain_Model;

public class ContainAdapter extends RecyclerView.Adapter<ContainAdapter.ViewHolder> {


    public interface OnToUpClickListener {
        void onToUpItemClick( int position);
    }
//    private OnToUpClickListener onItemClickListener ;

    private Context context ;

    private List<Contain_Model> contain_model ;

    void setContainAdapter( Context context , List<Contain_Model> contain_model){

//        onItemClickListener = (OnToUpClickListener)context ;

        this.contain_model = contain_model ;
        this.context = context ;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        return new ViewHolder(mLayoutInflater.inflate(R.layout.contain_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.BindData(contain_model.get(holder.getAdapterPosition()) , holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return contain_model.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Title ;
        private TextView Description ;
//        private ImageView To_Up_BTN ;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.title_text);

            Description = itemView.findViewById(R.id.title_description) ;

//            To_Up_BTN = itemView.findViewById(R.id.To_up_BTN);

        }

        void BindData(Contain_Model contain_model , int position){

            if (contain_model != null){

                if (contain_model.getTitle() != null)
                Title.setText(contain_model.getTitle());
                if (contain_model.getDescription() != null)
                Description.setText(contain_model.getDescription());
                Description.setMovementMethod(LinkMovementMethod.getInstance());

//                To_Up_BTN.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
////                        onItemClickListener.onToUpItemClick(position);
//                    }
//                });
//            }
        }
    }
    }
}
