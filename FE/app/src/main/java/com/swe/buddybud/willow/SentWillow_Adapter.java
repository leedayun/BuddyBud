package com.swe.buddybud.willow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swe.buddybud.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SentWillow_Adapter extends RecyclerView.Adapter<SentWillow_Adapter.ViewHolder>{
    private ArrayList<SentWillowData> mData;

    public void setmData(ArrayList<SentWillowData> mData) {
        this.mData = mData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView nameText;
        Button cancelBtn;

        public ViewHolder(View v) {
            super(v) ;
            nameText = (TextView)v.findViewById(R.id.sent_willow_id_txt);
            profileImage = (CircleImageView) v.findViewById(R.id.sent_willow_profile_img);
            cancelBtn = (Button) v.findViewById(R.id.request_cancel_btn);

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO : deleteWillow -> get sentWillow -> notifyDataSetChanged
                    /*
                    int idx = getAdapterPosition();
                    mData.remove(idx);
                    notifyItemRemoved(idx);
                     */
                }
            });
        }
    }

    SentWillow_Adapter(ArrayList<SentWillowData> list){
        this.mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sentwillow_recycleview_item, parent, false);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.profileImage.setImageResource(mData.get(position).getImgResId());
        holder.nameText.setText(mData.get(position).getUserId());
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
