package com.example.economysce.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economysce.Class.Employee;
import com.example.economysce.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<Employee> employees;
    public UserAdapter(Context context, List<Employee> employees){
        this.context = context;
        this.employees = employees;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_data_view, parent, false);
        return new UserAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.Id.setText("."+ employee.getId());
        holder.FirstName.setText("שם: " + employee.getFirstName());
        holder.LastName.setText(", שם משפחה: " + employee.getLastName());
        holder.Gender.setText(", מין: " + employee.getGender());
        holder.Birthday.setText("תאריך לידה: "+ employee.getBirthDay().getDay()+"/"+ employee.getBirthDay().getMonth()+"/"+ employee.getBirthDay().getYear() + " (" + getYears(employee.getBirthDay().getDate()) + ")");
        holder.StartWorkDate.setText("תאריך תחילת עבודה: "+ employee.getStartWork().getDay()+"/"+ employee.getStartWork().getMonth()+"/"+ employee.getStartWork().getYear());
        holder.Salary.setText("שכר: "+ employee.getSalary());
        holder.Seniority.setText("ותק: "+ String.format("%.02f", employee.getSeniority()));
        if(!employee.getReasonForLeaving().equals(""))
            holder.ReasonForLeaving.setText("סיבת עזיבה: "+ employee.getReasonForLeaving());
        else
            holder.ReasonForLeaving.setText("סיבת עזיבה: -");
        if(employee.getSection14StartDate() == null) {
            holder.Section14StartDate.setText("תאריך קבלת סעיף 14: -");
            holder.Section14Percent.setText("אחוז סעיף 14: -");
        }
        else {
            holder.Section14StartDate.setText("תאריך קבלת סעיף 14: " + employee.getSection14StartDate().getDay() + "/" + employee.getSection14StartDate().getMonth() + "/" + employee.getSection14StartDate().getYear());
            holder.Section14Percent.setText("אחוז סעיף 14: " + (employee.getSection14Percent()*100) + "%");
        }
        holder.AssetValue.setText("שווי נכס: "+ employee.getAssetValue());
        if(employee.getLeftDate() == null)
            holder.LeftDate.setText("תאריך עזיבה: -");
        else
            holder.LeftDate.setText("תאריך עזיבה: " + employee.getLeftDate().getDate()+"/"+ employee.getLeftDate().getMonth()+"/"+ employee.getLeftDate().getYear());
        holder.Deposits.setText("הפקדות: " + employee.getDeposits());
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
    public int getItemCount() { return employees.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Id, FirstName, Deposits, LastName, Gender, Birthday, ReasonForLeaving;
        public TextView StartWorkDate, Salary, Seniority, Section14StartDate, Section14Percent, AssetValue, LeftDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.Id);
            FirstName = itemView.findViewById(R.id.FirstName);
            LastName = itemView.findViewById(R.id.LastName);
            Gender = itemView.findViewById(R.id.Gender);
            Birthday = itemView.findViewById(R.id.Birthday);
            StartWorkDate = itemView.findViewById(R.id.StartWorkDate);
            Salary = itemView.findViewById(R.id.Salary);
            Seniority = itemView.findViewById(R.id.Seniority);
            Section14StartDate = itemView.findViewById(R.id.Section14StartDate);
            Section14Percent = itemView.findViewById(R.id.Section14Percent);
            AssetValue = itemView.findViewById(R.id.AssetValue);
            LeftDate = itemView.findViewById(R.id.LeftDate);
            ReasonForLeaving = itemView.findViewById(R.id.ReasonForLeaving);
            Deposits = itemView.findViewById(R.id.Deposits);
        }
    }
}
