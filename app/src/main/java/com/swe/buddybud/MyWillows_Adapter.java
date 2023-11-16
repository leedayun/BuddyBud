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
    public int addData(MyWillowsData data) {
        if(this.mData.add(data))
            return mData.size()-1;
        else return 0;
    }

    public void setmData(ArrayList<MyWillowsData> mData) {
        this.mData = mData;
    }

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
                    Intent intent = new Intent(context, WillowChatActivity.class);
                    /*
                    TODO: get global login data
                    private SharedPreferences preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    intent.putExtra("USER_ID",preferences.getString("userid","user1"));
                     */
                    intent.putExtra("USER_ID","user1");
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
        if(mData.get(position).getLastMsgTime()!=null){
            holder.lastTimeText.setText(mData.get(position).getLastMsgTime().format(DateTimeFormatter.ofPattern("yy/MM-dd HH:mm")));
            holder.lastMsgText.setText(mData.get(position).getLastMsg());
        } else {
            holder.lastTimeText.setText("");
            holder.lastMsgText.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
