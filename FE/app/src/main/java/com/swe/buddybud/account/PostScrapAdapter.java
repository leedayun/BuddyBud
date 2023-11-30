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
            title = view.findViewById(R.id.title);
            content = view.findViewById(R.id.content);
            thumbs_up_num = view.findViewById(R.id.thumbsUpNumber);
            comment_num = view.findViewById(R.id.commentNumber);
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
    public void onBindViewHolder(PostScrapAdapter.ViewHolder holder, int position) {
        PostScrapData item = items.get(position);

        // Assuming you have TextViews for userid, date, title, content, thumbs_up_num, and comment_num
        holder.userid.setText(item.getUserid());
        holder.date.setText(item.getDate());
        holder.title.setText(item.getTitle());
        String limitedContent = limitString(item.getContent(), 100); // 100자로 제한
        holder.content.setText(limitedContent);

        // Convert integer values to String before setting them on TextView
        holder.thumbs_up_num.setText(String.valueOf(item.getThumbsUpNumber()));
        holder.comment_num.setText(String.valueOf(item.getCommentNumber()));
    }

    private String limitString(String str, int limit) {
        // 문자열이 null이거나, 제한 길이보다 짧은 경우 그대로 반환
        if (str == null || str.length() <= limit) {
            return str;
        }

        // 제한 길이까지의 문자열 + "..."
        return str.substring(0, limit) + "...";
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
