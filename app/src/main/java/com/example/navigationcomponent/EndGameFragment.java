package com.example.navigationcomponent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EndGameFragment extends Fragment {

    private Button btn_restart_game;
    private TextView tv_winner_desc, tv_computer_wins_result, tv_user_wins_result;

    public EndGameFragment() {
        // Required empty public constructor
    }

    public static EndGameFragment newInstance(String param1, String param2) {
        EndGameFragment fragment = new EndGameFragment();
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
        return inflater.inflate(R.layout.fragment_end_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_winner_desc = view.findViewById(R.id.tv_winner_desc);
        tv_user_wins_result = view.findViewById(R.id.tv_user_wins_result);
        tv_computer_wins_result = view.findViewById(R.id.tv_computer_wins_result);

        final NavController navController = Navigation.findNavController(view);
        int computer_wins = EndGameFragmentArgs.fromBundle(getArguments()).getComputerWinScore();
        int user_wins = EndGameFragmentArgs.fromBundle(getArguments()).getUserWinScore();

        if(user_wins > computer_wins){
            tv_winner_desc.setText("User Wins");
        }
        else if( computer_wins > user_wins){
            tv_winner_desc.setText("Computer Wins");
        }
        else{
            tv_winner_desc.setText("Its a Draw");
        }

        tv_computer_wins_result.setText(String.valueOf(computer_wins));
        tv_user_wins_result.setText(String.valueOf(user_wins));

        btn_restart_game = view.findViewById(R.id.btn_restart_game);
        btn_restart_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.startFragment2, true).build();
                navController.navigate(R.id.action_endGameFragment2_to_startFragment2, null, navOptions);
            }
        });
    }
}