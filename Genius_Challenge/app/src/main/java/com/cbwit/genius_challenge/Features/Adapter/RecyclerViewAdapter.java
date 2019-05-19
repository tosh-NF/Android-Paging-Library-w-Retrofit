package com.cbwit.genius_challenge.Features.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cbwit.genius_challenge.Features.Model.POJO.User;
import com.cbwit.genius_challenge.R;

public class RecyclerViewAdapter extends PagedListAdapter<User,RecyclerViewAdapter.CustomViewHolder> {

    private Context context;
    private RequestManager g;

    public RecyclerViewAdapter(Context context){
        /*
        diffcallback: determine if two items are the same
         */
        super(DIFF_CALLBACK);
        this.context = context;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView Name, Email;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            Name = mView.findViewById(R.id.Name);
            Email = mView.findViewById(R.id.Email);
            CardView cv = mView.findViewById(R.id.card_view_friend);
            coverImage = cv.findViewById(R.id.coverImage);
            g = Glide.with(itemView);
        }
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row,parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.CustomViewHolder holder, int position) {

        User item = getItem(position);
        Log.i("ADAPTER","BOUND VIEW HOLDER");
        if(item!=null)
        {
            holder.Name.setText(item.getFirst_name() +" " + item.getLast_name());
            holder.Email.setText(item.getEmail());
            g.load(item.getAvatar())
                    .into(holder.coverImage);
        }
    }

    private static DiffUtil.ItemCallback<User> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<User>() {
                @Override
                public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    Log.i("ADAPTER","same: " + (oldItem.getId()==newItem.getId()));
                    return oldItem.getId()==newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    Log.i("ADAPTER","equal: " + (oldItem.equals(newItem)));
                    return oldItem.equals(newItem);
                }
            };



}
