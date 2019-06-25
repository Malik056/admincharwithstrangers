package com.speedystudios.chatwithstrangersadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RecyclerViewClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_click);

        String u_ID = getIntent().getStringExtra("uId");
        String f_rId = getIntent().getStringExtra("fid");

        TextView user = findViewById(R.id.uId);
        TextView Ruser = findViewById(R.id.rId);

        user.setText(u_ID);
        Ruser.setText(f_rId);
    }
}
