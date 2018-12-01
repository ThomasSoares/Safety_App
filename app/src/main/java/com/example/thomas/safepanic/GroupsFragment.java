package com.example.thomas.safepanic;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GroupsFragment extends Fragment {

    private List<Friend> friendList=new ArrayList<>();
    private RecyclerView recyclerView;
    private FriendAdapter mFriendAdapter;
    View parentHolder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder= inflater.inflate(R.layout.fragment_groups, container, false);

        recyclerView=parentHolder.findViewById(R.id.recyclerView);
        mFriendAdapter=new FriendAdapter(friendList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mFriendAdapter);

        prepareFriends();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void OnClick(View view, int position) {
                Friend friend= friendList.get(position);
                Toast.makeText(getContext(), friend.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return parentHolder;
    }

    private void prepareFriends()
    {
        Friend af=new Friend("Add Friend");
        friendList.add(af);
        for(int i=0;i<10;i++)
        {
            Friend friend=new Friend("Ujwal Sighania");
            friendList.add(friend);

            friend=new Friend("Thomas Soares");
            friendList.add(friend);

            friend=new Friend("Suravarapu Gautam");
            friendList.add(friend);

            friend=new Friend("Grishma Thapa");
            friendList.add(friend);

            friend=new Friend("Minto Varghese");
            friendList.add(friend);

            friend=new Friend("KJ Vaishnavi");
            friendList.add(friend);

            friend=new Friend("Shashank Maurya");
            friendList.add(friend);
        }


        mFriendAdapter.notifyDataSetChanged();
    }

}
