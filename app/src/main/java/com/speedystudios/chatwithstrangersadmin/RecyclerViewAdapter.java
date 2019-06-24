package com.speedystudios.chatwithstrangersadmin;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by User on 1/1/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> User_ID = new ArrayList<>();
    private ArrayList<String> rID = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> user_IDs, ArrayList<String> rIDs ) {
        User_ID = user_IDs;
        rID = rIDs;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.UserID.setText(" User ID: " + User_ID.get(position));
        holder.MAC_ADDR.setText(" Reported User ID : " + rID.get(position));

    }

    @Override
    public int getItemCount() {
        return User_ID.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView UserID;
        TextView MAC_ADDR;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            UserID = itemView.findViewById(R.id.uId);
            MAC_ADDR = itemView.findViewById(R.id.rId);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}

