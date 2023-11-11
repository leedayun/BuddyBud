package com.swe.buddybud;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyWillows_Adapter extends RecyclerView.Adapter<MyWillows_Adapter.ViewHolder>{
    private ArrayList<MyWillowsData> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView nameText;
        TextView lastMsgText;
        TextView lastTimeText;

        Context context;

        ViewHolder(View v) {
            super(v);
            context = v.getContext();
            nameText = (TextView) v.findViewById(R.id.willow_id_txt);
            lastMsgText = (TextView)v.findViewById(R.id.willow_lastmsg_txt);
            lastTimeText = (TextView)v.findViewById(R.id.willow_lastmsg_time);
            profileImage = (CircleImageView) v.findViewById(R.id.willow_profile_img);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("MyWillows_Adapter","Element " + getAdapterPosition() + " clicked.");
                    Intent intent = new Intent(context, WillowChatActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    MyWillows_Adapter(ArrayList<MyWillowsData> list){
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Context context = parent.getContext() ;
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.willowlist_recycleview_item, parent, false);
        //MyWillows_Adapter.ViewHolder vh = new MyWillows_Adapter.ViewHolder(view) ;
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //MyWillowsData data = mData.get(position);
        holder.nameText.setText(mData.get(position).getUserId());
        holder.profileImage.setImageResource(mData.get(position).getImgResId());
        holder.lastMsgText.setText(mData.get(position).getLastMsg());
        holder.lastTimeText.setText(mData.get(position).getLastMsgTime().format(DateTimeFormatter.ofPattern("yy/MM-dd HH:mm")));
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
