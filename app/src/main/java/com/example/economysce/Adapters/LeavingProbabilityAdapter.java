package com.example.economysce.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.economysce.Class.LeavingProbability;
import com.example.economysce.R;
import java.util.List;

public class LeavingProbabilityAdapter extends RecyclerView.Adapter<LeavingProbabilityAdapter.ViewHolder> {
    private Context context;
    private List<LeavingProbability> leavingProbabilityList;
    public LeavingProbabilityAdapter(Context context, List<LeavingProbability> leavingProbabilityList ){
        this.context = context;
        this.leavingProbabilityList = leavingProbabilityList;
    }
    @NonNull
    @Override
    public LeavingProbabilityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_reduction_leaving_probability_view, parent, false);
        return new LeavingProbabilityAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull LeavingProbabilityAdapter.ViewHolder holder, int position) {
        LeavingProbability leavingProbability = leavingProbabilityList.get(position);
        holder.Age.setText(leavingProbability.getFromAge()+"-"+leavingProbability.getToAge());
        holder.Fired.setText(leavingProbability.getFiredPercent()+"%");
        holder.Resign.setText(leavingProbability.getResignPercent()+"%");
    }
    @Override
    public int getItemCount() { return leavingProbabilityList.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Age;
        public TextView Fired;
        public TextView Resign;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Age = itemView.findViewById(R.id.Age);
            Fired = itemView.findViewById(R.id.Fired);
            Resign = itemView.findViewById(R.id.Resign);
        }
    }
}