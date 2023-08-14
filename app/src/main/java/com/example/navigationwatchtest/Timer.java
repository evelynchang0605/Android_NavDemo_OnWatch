package com.example.navigationwatchtest;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Timer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Timer extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Chronometer chronometer;
    private Button button_start, button_stop;
    private boolean isRunning = false;
    private long pausedTime;

    public Timer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Timer.
     */
    // TODO: Rename and change types and number of parameters
    public static Timer newInstance(String param1, String param2) {
        Timer fragment = new Timer();
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
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        TextClock textClock;
        ImageView imageView_timer;
        chronometer = getView().findViewById(R.id.chronometer);
        button_start = getView().findViewById(R.id.button_start);
        button_stop = getView().findViewById(R.id.button_stop);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                String time = chronometer.getText().toString();
                if(time.equals("00:00")){
                    //Toast.makeText(MainActivity.this,"时间到了~",Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRunning){
                    chronometer.setBase(SystemClock.elapsedRealtime()-pausedTime);
                    chronometer.start();
                    isRunning = true;
                    button_start.setEnabled(false);
                    button_stop.setEnabled(true);
                }
            }
        });
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning){
                    chronometer.stop();
                    pausedTime = SystemClock.elapsedRealtime()-chronometer.getBase();
                    isRunning = false;
                    button_start.setEnabled(true);
                    button_stop.setEnabled(false);
                }
            }
        });

        ImageButton imageButton_home;
        imageButton_home = getView().findViewById(R.id.imageButton_home);
        imageButton_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_timer_to_home2);
            }
        });

    }
}