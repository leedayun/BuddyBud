package com.swe.buddybud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class IncomingWillow_Adapter extends RecyclerView.Adapter<IncomingWillow_Adapter.ViewHolder>{
    private ArrayList<IncomingWillowData> mData;

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView nameText;
        TextView infoText;

        public ViewHolder(View v) {
            super(v) ;
            nameText = (TextView)v.findViewById(R.id.incoming_id_txt);
            infoText = (TextView)v.findViewById(R.id.incoming_info_txt);
            profileImage = (CircleImageView) v.findViewById(R.id.receiving_profile_img);
        }
    }

    IncomingWillow_Adapter(ArrayList<IncomingWillowData> list){
        this.mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.incomingwillow_recycleview_item, parent, false);
        //IncomingWillow_Adapter.ViewHolder vh = new IncomingWillow_Adapter.ViewHolder(view);
        return new ViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //Log.d("IncomingWillowAdapter","Element" + position + "set" + mData.toString());

        //IncomingWillowData data = mData.get(position);
        holder.nameText.setText(mData.get(position).getUserId());
        holder.profileImage.setImageResource(mData.get(position).getImgResId());
        holder.infoText.setText(mData.get(position).getDept() + "\n" + mData.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
