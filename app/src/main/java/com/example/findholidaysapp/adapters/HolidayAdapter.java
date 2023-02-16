package com.example.findholidaysapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findholidaysapp.R;
import com.example.findholidaysapp.model.Holiday;

import org.w3c.dom.Text;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.MyViewHolder> {

    List<Holiday> holidayList;

    public HolidayAdapter(List<Holiday> holidayList){
        this.holidayList = holidayList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView holidayName;
        TextView holidayDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            holidayName = itemView.findViewById(R.id.name);
            holidayDate = itemView.findViewById(R.id.date);
        }

        public void bind(int position){
            Holiday holiday = holidayList.get(position);

            Log.d("AAA", holiday.getName() + "");
            holidayName.setText(holiday.getName());
            holidayName.setOnClickListener(v -> {
                Toast.makeText(holidayDate.getContext(), holiday.getName(), Toast.LENGTH_SHORT).show();
            });
            holidayDate.setText(holiday.getDate());
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return holidayList.size();
    }
}
