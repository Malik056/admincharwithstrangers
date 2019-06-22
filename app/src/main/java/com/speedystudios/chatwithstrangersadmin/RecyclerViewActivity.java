package com.speedystudios.chatwithstrangersadmin;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewActivity";

    //vars
    private ArrayList<String> mUsers = new ArrayList<>();
    private ArrayList<String> mMAC = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Log.d(TAG, "onCreate: started.");

        initdata();
    }

    private void initdata(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mUsers.add("User_ID: 6571");
        mMAC.add("MAC_Addr: AF3D:FF3D:AF3A:BCDE");

        mUsers.add("User_ID: 9570");
        mMAC.add("MAC_Addr: 003D:F23D:A93A:1CDE");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mUsers, mMAC);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
