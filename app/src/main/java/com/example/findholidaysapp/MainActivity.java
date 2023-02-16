package com.example.findholidaysapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.findholidaysapp.adapters.HolidayAdapter;
import com.example.findholidaysapp.model.Holiday;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetDataFromInternet.AsyncResponse {

    List<Holiday> holidayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        holidayList = new ArrayList<>();
    }

    @Override
    public void onResume(){
        super.onResume();

        URL url = null;
        try {
            url = new URL("https://calendarific.com/api/v2/holidays?api_key=5191aab2f84f1fbd1f7604b320420962e72fc0d4&country=RU&year=2023");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        new GetDataFromInternet(this).execute(url);
    }

    @Override
    public void processFinished(String output) {
        Log.d("AAA", output);

        try {
            JSONObject jsonOutput = new JSONObject(output);
            JSONArray holidays = jsonOutput.getJSONObject("response").getJSONArray("holidays");


            for (int i = 0; i < holidays.length(); i++){
                JSONObject jsonHoliday = holidays.getJSONObject(i);

                String name = jsonHoliday.getString("name");
                String date = jsonHoliday.getJSONObject("date").getString("iso");


                holidayList.add(new Holiday(name, date));

            }

            RecyclerView rv = findViewById(R.id.rv);
            rv.setLayoutManager(new LinearLayoutManager(this));
            HolidayAdapter adapter = new HolidayAdapter(holidayList);

            rv.setAdapter(adapter);

        } catch (JSONException e) {

            Log.e("AAA", "ERROR");
        }
    }
}