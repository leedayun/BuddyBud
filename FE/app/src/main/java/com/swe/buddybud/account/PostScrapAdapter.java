package com.swe.buddybud.account;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.swe.buddybud.R;
import com.swe.buddybud.account.PostScrapData;

import java.util.List;

public class PostScrapAdapter extends RecyclerView.Adapter<PostScrapAdapter.ViewHolder> {

    private List<PostScrapData> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userid, date, title, content, thumbs_up_num, comment_num;

        public ViewHolder(View view) {
            super(view);
            userid = view.findViewById(R.id.userid);
            date = view.findViewById(R.id.date);
            title = view.findViewById(R.id.feedTitle);
            content = view.findViewById(R.id.feedContent);
            thumbs_up_num = view.findViewById(R.id.feedThumbsUpNumber);
            comment_num = view.findViewById(R.id.feedCommentNumber);
        }
    }

    public PostScrapAdapter(List<PostScrapData> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.account_post_or_scrap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostScrapData item = items.get(position);
        holder.userid.setText(item.getUserid());
        holder.date.setText(item.getDate());
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
        holder.thumbs_up_num.setText(item.getThumbsUpNumber());
        holder.comment_num.setText(item.getCommentNumber());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
