package com.example.project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FragmentFriend extends Fragment {

    TextView friendName;

    RecyclerView recyclerView;
    RecyclerAdapter3 recyclerAdapter;

    RecyclerAdapter3.RecyclerViewClickListener listener;

    Button back3;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Task> myList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_friend, container, false);
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        myList = new ArrayList<>();

        MainActivity ob = (MainActivity) getActivity();
        ob.frag_no=3;
        friendName = view.findViewById(R.id.friendName);
        friendName.setText(ob.myFriend);

        setOnClickListener();

        recyclerView = view.findViewById(R.id.friendsTask);
        recyclerAdapter = new com.example.project.RecyclerAdapter3(myList, listener);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAlpha(0);
        recyclerView.setTranslationX(100);
        recyclerView.animate().alpha(1).translationXBy(-100).setDuration(1000);

        //create list task of uid of friends


        db.collection("users").document(ob.UID).collection("Tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            myList.clear();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Task tt=document.toObject(Task.class);
                                Log.d("mylist2",tt.getName());
                                //check if today's date is same as task's added date
//                                Log.d("mydate","databse "+tt.getDate());
//                                Log.d("mydate","today "+ LocalDate.now().toString());
                                if(tt.getDate().equals(LocalDate.now().toString()))
                                    myList.add(tt);
                            }

//                            show list from here
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(recyclerAdapter);

                        } else {
                            Log.d("mylist2", "Error getting documents: ", task.getException());
                        }
                    }
                });
        back3 = view.findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment session = new FragmentSession();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.container, session).commit();
            }
        });
        return view;
    }

    private void setOnClickListener() {
        listener = new RecyclerAdapter3.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

            }
        };
    }
}