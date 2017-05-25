package dfb.com.tictactoe.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import dfb.com.tictactoe.R;
import dfb.com.tictactoe.constants.Keys;
import dfb.com.tictactoe.model.Game;
import dfb.com.tictactoe.viewmodel.GameViewModel;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup lyt = (ViewGroup) findViewById(R.id.lyt);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionSet set = new TransitionSet().addTransition(new Fade())
                            .setInterpolator(new LinearOutSlowInInterpolator());
                    TransitionManager.beginDelayedTransition(lyt, set);
                }
                findViewById(R.id.lyt_launch).setVisibility(View.INVISIBLE);
            }
        }, 1500);

        findViewById(R.id.btn_multi_player).setOnClickListener(this);
        findViewById(R.id.btn_single_player).setOnClickListener(this);
    }

    private void play(int gameModality){
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra(Keys.KEY_GAME_MODALITY,gameModality);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.btn_multi_player):
                play(Game.MULTI_PLAYER);
                break;
            case (R.id.btn_single_player):
                play(Game.SINGLE_PLAYER);
                break;
        }
    }
}
