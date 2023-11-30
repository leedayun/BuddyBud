package com.swe.buddybud.willow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.swe.buddybud.R;
import com.swe.buddybud.common.RetrofitClient;
import com.swe.buddybud.user.LoginData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SentWillow_Adapter extends RecyclerView.Adapter<SentWillow_Adapter.ViewHolder>{
    private ArrayList<SentWillowData> mData;

    public void setmData(ArrayList<SentWillowData> mData) {
        this.mData = mData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView nameText;
        Button cancelBtn;
        int receiveUserNo;
        private WillowApiService willowApiService = RetrofitClient.getService(WillowApiService.class);

        public ViewHolder(View v) {
            super(v) ;
            nameText = (TextView)v.findViewById(R.id.sent_willow_id_txt);
            profileImage = (CircleImageView) v.findViewById(R.id.sent_willow_profile_img);
            cancelBtn = (Button) v.findViewById(R.id.request_cancel_btn);

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO : deleteWillow -> get sentWillow -> notifyDataSetChanged
                    //make post body
                    Map<String, String> fields = new HashMap<>();
                    Gson gson = new Gson();

                    fields.put("receiver_no", String.valueOf(LoginData.getLoginUserNo()));
                    fields.put("sender_no", String.valueOf(receiveUserNo));

                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), gson.toJson(fields));

                    //post acceptwillow
                    Call<WillowApiData> call = willowApiService.acceptWillow(requestBody);
                    call.enqueue(new Callback<WillowApiData>() {
                        @Override
                        public void onResponse(Call<WillowApiData> call, Response<WillowApiData> response) {
                            WillowApiData userResponse = response.body();

                            // User Info Insert 성공시
                            if(userResponse.getDeleteWillowResult()){

                            }
                            else {
                                Toast.makeText(view.getContext(),"cancel Request Network Error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        @Override
                        public void onFailure(Call<WillowApiData> call, Throwable t) {
                            Toast.makeText(view.getContext(),"cancel Request Network Error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    });
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
