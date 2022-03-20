package com.example.economysce.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.economysce.R;
import com.example.economysce.Class.User;

import java.util.List;
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> users;
    public UserAdapter(Context context, List<User> users ){
        this.context = context;
        this.users = users;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_data_view, parent, false);
        return new UserAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.fragmentUserId.setText("."+user.getId());
        holder.fragmentUserName.setText(holder.fragmentUserName.getText()+user.getName());
        holder.fragmentUserLastName.setText(holder.fragmentUserLastName.getText()+user.getLastName());
        holder.fragmentUserGender.setText(holder.fragmentUserGender.getText()+user.getGender());
        holder.fragmentUserBirthDay.setText(holder.fragmentUserBirthDay.getText()+""+user.getBirthDay().getDate()+"/"+user.getBirthDay().getMonth()+"/"+user.getBirthDay().getYear());
        holder.fragmentUserStartWork.setText(holder.fragmentUserStartWork.getText()+""+user.getStartWork().getDate()+"/"+user.getStartWork().getMonth()+"/"+user.getStartWork().getYear());
        holder.fragmentUserSalary.setText(holder.fragmentUserSalary.getText()+""+user.getSalary().doubleValue());
        if(user.getAcceptSection14().getDate() == 1 && user.getAcceptSection14().getMonth() == 1 && user.getAcceptSection14().getYear() == 1) { }
        else {
            holder.fragmentUserSection14.setText(holder.fragmentUserSection14.getText() + "" + user.getAcceptSection14().getDate() + "/" + user.getAcceptSection14().getMonth() + "/" + user.getAcceptSection14().getYear());
            holder.fragmentUserPercent.setText(holder.fragmentUserPercent.getText() + "" + user.getPercentSection14());
        }
        holder.fragmentUserAssetValue.setText(holder.fragmentUserAssetValue.getText()+""+user.getAssetValue().doubleValue());
        if(user.getLeftDate().getDate() == 1 && user.getLeftDate().getMonth() == 1 && user.getLeftDate().getYear() == 1)
            holder.fragmentUserLeftDate.setText(holder.fragmentUserLeftDate.getText()+"-");
        else
            holder.fragmentUserLeftDate.setText(holder.fragmentUserLeftDate.getText()+""+user.getLeftDate().getDate()+"/"+user.getLeftDate().getMonth()+"/"+user.getLeftDate().getYear());
    }
    @Override
    public int getItemCount() { return users.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView fragmentUserId;
        public TextView fragmentUserName;
        public TextView fragmentUserLastName;
        public TextView fragmentUserGender;
        public TextView fragmentUserBirthDay;
        public TextView fragmentUserStartWork;
        public TextView fragmentUserSalary;
        public TextView fragmentUserSection14;
        public TextView fragmentUserPercent;
        public TextView fragmentUserAssetValue;
        public TextView fragmentUserLeftDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fragmentUserId = itemView.findViewById(R.id.fragmentUserId);
            fragmentUserName = itemView.findViewById(R.id.fragmentUserName);
            fragmentUserLastName = itemView.findViewById(R.id.fragmentUserLastName);
            fragmentUserGender = itemView.findViewById(R.id.fragmentUserGender);
            fragmentUserBirthDay = itemView.findViewById(R.id.fragmentUserBirthDay);
            fragmentUserStartWork = itemView.findViewById(R.id.fragmentUserStartWork);
            fragmentUserSalary = itemView.findViewById(R.id.fragmentUserSalary);
            fragmentUserSection14 = itemView.findViewById(R.id.fragmentUserSection14);
            fragmentUserPercent = itemView.findViewById(R.id.fragmentUserPercent);
            fragmentUserAssetValue = itemView.findViewById(R.id.fragmentUserAssetValue);
            fragmentUserLeftDate = itemView.findViewById(R.id.fragmentUserLeftDate);
        }
    }
}
