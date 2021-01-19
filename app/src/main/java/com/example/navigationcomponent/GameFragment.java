package com.example.navigationcomponent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameFragment extends Fragment {

    private Button btn_finish_game;
    private Button btn_check_result;
    private EditText et_user_input;
    private TextView tv_computer_input, tv_computer_wins, tv_user_wins;
    private int computer_wins = 0, user_wins = 0;
    private String []choices = {"rock","paper","scissor"};

    public GameFragment() {
        // Required empty public constructor
    }


    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        btn_finish_game = view.findViewById(R.id.btn_finish_game);
        btn_check_result = view.findViewById(R.id.btn_check_result);
        et_user_input = view.findViewById(R.id.et_input);
        tv_computer_input = view.findViewById(R.id.tv_computer_input);
        tv_computer_wins = view.findViewById(R.id.tv_computer_wins);
        tv_user_wins = view.findViewById(R.id.tv_user_wins);

        btn_finish_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections navDirections = GameFragmentDirections.actionGameFragment2ToEndGameFragment2(computer_wins,user_wins);
                navController.navigate(navDirections);
            }
        });

        btn_check_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkResult();
            }
        });
    }

    private void checkResult(){
        String user_input = et_user_input.getText().toString();
        String computer_input = choices[new Random().nextInt(choices.length)];
        tv_computer_input.setText(computer_input);
        Toast toast;

        if(user_input.equalsIgnoreCase("scissor") || user_input.equalsIgnoreCase("paper") || user_input.equalsIgnoreCase("rock")){
            if(user_input.equalsIgnoreCase("scissor")){
                if(computer_input.equalsIgnoreCase("scissor")) {
                    toast = Toast.makeText(getActivity(), "Its a Draw", Toast.LENGTH_LONG);
                }
                else if(computer_input.equalsIgnoreCase("paper")) {
                    toast = Toast.makeText(getActivity(), "Computer wins", Toast.LENGTH_LONG);
                    computer_wins++;
                    tv_computer_wins.setText(String.valueOf(computer_wins));
                }
                else {
                    toast = Toast.makeText(getActivity(), "User wins", Toast.LENGTH_LONG);
                    user_wins++;
                    tv_user_wins.setText(String.valueOf(user_wins));
                }
            }
            else if(user_input.equalsIgnoreCase("rock")){
                if(computer_input.equalsIgnoreCase("scissor")) {
                    toast = Toast.makeText(getActivity(), "User wins", Toast.LENGTH_LONG);
                    user_wins++;
                    tv_user_wins.setText(String.valueOf(user_wins));
                }
                else if(computer_input.equalsIgnoreCase("paper")) {
                    toast = Toast.makeText(getActivity(), "Computer wins", Toast.LENGTH_LONG);
                    computer_wins++;
                    tv_computer_wins.setText(String.valueOf(computer_wins));
                }
                else {
                    toast = Toast.makeText(getActivity(), "Its a draw", Toast.LENGTH_LONG);
                }
            }
            else {
                if(computer_input.equalsIgnoreCase("scissor")) {
                    toast = Toast.makeText(getActivity(), "Computer wins", Toast.LENGTH_LONG);
                    computer_wins++;
                    tv_computer_wins.setText(String.valueOf(computer_wins));
                }
                else if(computer_input.equalsIgnoreCase("paper")) {
                    toast = Toast.makeText(getActivity(), "Its a draw", Toast.LENGTH_LONG);
                }
                else {
                    toast = Toast.makeText(getActivity(), "User wins", Toast.LENGTH_LONG);
                    user_wins++;
                    tv_user_wins.setText(String.valueOf(user_wins));
                }
            }
        }
        else{
            toast = Toast.makeText(getActivity(),"Invalid Input",Toast.LENGTH_LONG);
        }
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }
}