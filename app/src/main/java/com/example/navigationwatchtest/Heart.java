package com.example.navigationwatchtest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Heart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Heart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView heartbeatTextView;
    private int heartbeatCount = 50;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private Runnable heartbeatRunnable;

    public Heart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Heart.
     */
    // TODO: Rename and change types and number of parameters
    public static Heart newInstance(String param1, String param2) {
        Heart fragment = new Heart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_heart, container, false);
        View rootView = inflater.inflate(R.layout.fragment_heart, container, false);
        Button button3;
        button3 = rootView.findViewById(R.id.button3);
        heartbeatTextView = rootView.findViewById(R.id.textView3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3.setText("END");

                // 使用 Runnable 模拟心跳
                heartbeatRunnable = new Runnable() {
                    @Override
                    public void run() {
                        heartbeatCount++;
                        updateHeartbeat();
                        mainHandler.postDelayed(this, 1000);
                    }
                };

                mainHandler.post(heartbeatRunnable); // 启动心跳
            }
        });

        return rootView;
    }
    private void updateHeartbeat() {
        heartbeatTextView.setText(String.valueOf(heartbeatCount));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Integer integer = getArguments().getInt("heartbeat");
        //TextView textView = getView().findViewById(R.id.textView3);
        //textView.setText(String.valueOf(integer));

        ImageButton imageButton;
        imageButton = getView().findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_heart_to_home2);
            }
        });
    }
    public void onDestroyView() {
        super.onDestroyView();
        mainHandler.removeCallbacks(heartbeatRunnable); // 停止心跳
    }
}