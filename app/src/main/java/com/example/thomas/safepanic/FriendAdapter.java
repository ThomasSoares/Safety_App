package com.example.thomas.safepanic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {

    private List<Friend> friendsList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name;

        public MyViewHolder(View view)
        {
            super(view);
            name=view.findViewById(R.id.name);

        }
    }

    public FriendAdapter(List<Friend> friendsList)
    {
        this.friendsList=friendsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_list_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Friend friend=friendsList.get(i);
        myViewHolder.name.setText(friend.getName());

    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}
