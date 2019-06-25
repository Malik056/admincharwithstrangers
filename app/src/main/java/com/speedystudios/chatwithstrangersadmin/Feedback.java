package com.speedystudios.chatwithstrangersadmin;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Feedback extends AppCompatActivity {


    private ArrayList<String> fuser = new ArrayList<>();
    private ArrayList<String> feedbackOfUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        DatabaseReference feedback = FirebaseDatabase.getInstance().getReference().child("feedbacks");
        feedback.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot pDataSnapshot, @Nullable String pS) {

                String user = pDataSnapshot.getKey();
                for(DataSnapshot dss : pDataSnapshot.getChildren())
                {
                    if(dss.hasChildren())
                    {
                        dss.getRef();
                        String feedback = (String) dss.child("feedback").getValue();
                        String feed = " Feedback: " + feedback;
                        String desc = " User: " + user;
                        fuser.add(desc);
                        feedbackOfUser.add(feed);

                    }
                }

                RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(Feedback.this, fuser, feedbackOfUser);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Feedback.this));

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
