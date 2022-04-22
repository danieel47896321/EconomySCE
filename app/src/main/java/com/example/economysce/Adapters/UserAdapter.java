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

import java.util.Calendar;
import java.util.Date;
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
        holder.fragmentUserName.setText("שם: " + user.getName());
        holder.fragmentUserLastName.setText(", שם משפחה: " + user.getLastName());
        holder.fragmentUserGender.setText(", מין: " + user.getGender());
        holder.fragmentUserBirthDay.setText("תאריך לידה: "+user.getBirthDay().getDate()+"/"+user.getBirthDay().getMonth()+"/"+user.getBirthDay().getYear() + " (" + getYears(user.getBirthDay()) + ")");
        holder.fragmentUserStartWork.setText("תאריך תחילת עבודה: "+user.getStartWork().getDate()+"/"+user.getStartWork().getMonth()+"/"+user.getStartWork().getYear());
        holder.fragmentUserSalary.setText("שכר: "+user.getSalary());
        holder.fragmentUserVetek.setText("ותק: "+user.getVetek());
        if(!user.getReasonForLeaving().equals(""))
            holder.fragmentUserLeftReason.setText("סיבת עזיבה: "+user.getReasonForLeaving());
        else
            holder.fragmentUserLeftReason.setText("סיבת עזיבה: -");
        if(user.getAcceptSection14() == null) {
            holder.fragmentUserSection14.setText("תאריך קבלת סעיף 14: -");
            holder.fragmentUserPercent.setText("אחוז סעיף 14: -");
        }
        else {
            holder.fragmentUserSection14.setText("תאריך קבלת סעיף 14: " + user.getAcceptSection14().getDate() + "/" + user.getAcceptSection14().getMonth() + "/" + user.getAcceptSection14().getYear());
            holder.fragmentUserPercent.setText("אחוז סעיף 14: " + user.getPercentSection14());
        }
        holder.fragmentUserAssetValue.setText("שווי נכס: "+user.getAssetValue());
        if(user.getLeftDate() == null)
            holder.fragmentUserLeftDate.setText("תאריך עזיבה: -");
        else
            holder.fragmentUserLeftDate.setText("תאריך עזיבה: " + user.getLeftDate().getDate()+"/"+user.getLeftDate().getMonth()+"/"+user.getLeftDate().getYear());
    }
    private int getYears(Date date){
        int years = Calendar.getInstance().get(Calendar.YEAR) - date.getYear();
        if(date.getMonth() >  Calendar.getInstance().get(Calendar.MONTH) ||
                (date.getMonth() ==  Calendar.getInstance().get(Calendar.MONTH) &&
                        date.getDate() > Calendar.getInstance().get(Calendar.DAY_OF_WEEK)))
            years -=1;
        return years;
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
        public TextView fragmentUserVetek;
        public TextView fragmentUserSection14;
        public TextView fragmentUserPercent;
        public TextView fragmentUserAssetValue;
        public TextView fragmentUserLeftDate;
        public TextView fragmentUserLeftReason;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fragmentUserId = itemView.findViewById(R.id.fragmentUserId);
            fragmentUserName = itemView.findViewById(R.id.fragmentUserName);
            fragmentUserLastName = itemView.findViewById(R.id.fragmentUserLastName);
            fragmentUserGender = itemView.findViewById(R.id.fragmentUserGender);
            fragmentUserBirthDay = itemView.findViewById(R.id.fragmentUserBirthDay);
            fragmentUserStartWork = itemView.findViewById(R.id.fragmentUserStartWork);
            fragmentUserSalary = itemView.findViewById(R.id.fragmentUserSalary);
            fragmentUserVetek = itemView.findViewById(R.id.fragmentUserVetek);
            fragmentUserSection14 = itemView.findViewById(R.id.fragmentUserSection14);
            fragmentUserPercent = itemView.findViewById(R.id.fragmentUserPercent);
            fragmentUserAssetValue = itemView.findViewById(R.id.fragmentUserAssetValue);
            fragmentUserLeftDate = itemView.findViewById(R.id.fragmentUserLeftDate);
            fragmentUserLeftReason = itemView.findViewById(R.id.fragmentUserLeftReason);
        }
    }
}
