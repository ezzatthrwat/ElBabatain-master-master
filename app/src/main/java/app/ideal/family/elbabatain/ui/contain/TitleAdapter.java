package app.ideal.family.elbabatain.ui.contain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.ideal.family.elbabatain.R;
import app.ideal.family.elbabatain.network.contain.Contain_Model;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder>{

   private Context context;

    private List<Contain_Model> contain_model ;

    public interface OnItemClickListener {
        void onItemClick( int position);
    }
    private OnItemClickListener onItemClickListener ;

    void setTitleAdapter(Context context , List<Contain_Model> contain_model){

        onItemClickListener = (OnItemClickListener)context ;
        this.contain_model = contain_model ;
        this.context = context ;
    notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        return new ViewHolder(mLayoutInflater.inflate(R.layout.title_list_item, parent, false));

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
        private LinearLayout TitleContainer ;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.headerTitle);
            TitleContainer = itemView.findViewById(R.id.TitleContainer);
        }

        void BindData(Contain_Model contain_model , int position){

            TitleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(position);
                    }
                });
            if (contain_model != null){
                Title.setText(". " +contain_model.getTitle());
            }
        }

    }
}
