package com.gebeya.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gebeya.myapplication.R;
import com.gebeya.myapplication.core.Employee;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{

    private Context context;
    private List<Employee> list;


    public EmployeeAdapter(Context applicationContext, List<Employee> myCartList) {
        this.context = applicationContext;
        this.list = myCartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_employee_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();

        Employee item = list.get(position);

        holder.EmployeeName.setText(item.getEmployeeName());

        holder.EmployeeSalary.setText(item.getEmployeeSalary());

        holder.EmployeeAge.setText(item.getEmployeeAge());
    }


    public void setfilter(List<Employee>EmployeeList){
        list=EmployeeList;
        notifyDataSetChanged();

    }

    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView EmployeeName,EmployeeSalary,EmployeeAge;
        ImageView addQuantity,subtractQuantity;
        RecyclerView mList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            EmployeeName = itemView.findViewById(R.id.Employee_name);
            EmployeeSalary=itemView.findViewById(R.id.Employee_salary);
            EmployeeAge=itemView.findViewById(R.id.Employee_age);

        }
    }

    public void removeAt(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
}
