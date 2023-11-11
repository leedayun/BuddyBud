package com.swe.buddybud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class WillowChat_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int LEFT_CONTENT = 0;
    public static final int RIGHT_CONTENT = 1;
    private ArrayList<ChatData> mData;

    private String userid;

    WillowChat_Adapter(ArrayList<ChatData> list, String userid){
        mData = list;
        this.userid = userid;
    }

    public class ReceivingViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImg;
        TextView name;
        TextView message;
        TextView sendTime;
        ReceivingViewHolder(View v) {
            super(v);
            profileImg = v.findViewById(R.id.receiving_profile_img);
            name = v.findViewById(R.id.receiving_sender_txt);
            message = v.findViewById(R.id.receiving_message_txt);
            sendTime = v.findViewById(R.id.receiving_time_text);
        }
    }

    public class SendingViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView sendTime;
        SendingViewHolder(View v) {
            super(v) ;
            message = v.findViewById(R.id.sending_message_txt);
            sendTime = v.findViewById(R.id.sending_time_text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mData.get(position).getName().equals(userid)){
            return RIGHT_CONTENT;
        } else {
            return LEFT_CONTENT;
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(viewType == LEFT_CONTENT)
        {
            view = inflater.inflate(R.layout.receiving_message, parent, false);
            return new ReceivingViewHolder(view);
        }
        else
        {
            view = inflater.inflate(R.layout.sending_message, parent, false);
            return new SendingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ReceivingViewHolder){
            ((ReceivingViewHolder) holder).message.setText(mData.get(position).getMessage());
            ((ReceivingViewHolder) holder).name.setText(mData.get(position).getName());
            ((ReceivingViewHolder) holder).sendTime.setText(mData.get(position).getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm")));
            ((ReceivingViewHolder) holder).profileImg.setImageResource(mData.get(position).getImgResId());
        } else if(holder instanceof SendingViewHolder){
            ((SendingViewHolder) holder).message.setText(mData.get(position).getMessage());
            ((SendingViewHolder) holder).sendTime.setText(mData.get(position).getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm")));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
