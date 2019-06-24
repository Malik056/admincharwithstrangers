package com.speedystudios.chatwithstrangersadmin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Report_Details extends AppCompatActivity {


    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<String> rusers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__details);


        DatabaseReference reports = FirebaseDatabase.getInstance().getReference().child("reports");
        reports.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {
                if(pDataSnapshot.getChildren().iterator().next().hasChildren())
                {
                    pDataSnapshot.getChildren().iterator().next().getRef();
                    String userID = pDataSnapshot.getKey();
                    String reportedUser = pDataSnapshot.getChildren().iterator().next().getKey();
                    String desc = userID + " reprted user " + reportedUser;
                    String title = "Report";
                    users.add(userID);
                    rusers.add(reportedUser);


                }

                RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(Report_Details.this, users, rusers);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Report_Details.this));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot pDataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError pDatabaseError) {

            }
        });

    }
}
