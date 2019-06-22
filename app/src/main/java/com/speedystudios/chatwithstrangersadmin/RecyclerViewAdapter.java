package com.speedystudios.chatwithstrangersadmin;
import android.content.Context;
import android.content.Intent;
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
    private ArrayList<String> MAC_ADDR = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> user_IDs, ArrayList<String> MAC_ADDRs ) {
        User_ID = user_IDs;
        MAC_ADDR = MAC_ADDRs;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.UserID.setText(User_ID.get(position));
        holder.MAC_ADDR.setText(MAC_ADDR.get(position));

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
            UserID = itemView.findViewById(R.id.UserID);
            MAC_ADDR = itemView.findViewById(R.id.MAC_Addr);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}

