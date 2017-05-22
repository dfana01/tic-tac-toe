package dfb.com.tictactoe.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;


import dfb.com.tictactoe.databinding.ActivityGameBinding;
import dfb.com.tictactoe.R;
import dfb.com.tictactoe.viewmodel.GameViewModel;

public class GameActivity extends Activity {

    private GameViewModel gameViewModel;
    private ActivityGameBinding gameActivityBiding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
    }

    private void initDataBinding() {
        gameActivityBiding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        gameViewModel = new GameViewModel();
        gameActivityBiding.setMainViewModel(gameViewModel);
        gameActivityBiding.setHandlers(new Handlers());
    }

    private void beginTransition(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final ViewGroup lyt = (ViewGroup) findViewById(R.id.lyt);
            TransitionSet set = new TransitionSet().addTransition(new Fade())
                    .setInterpolator(new LinearOutSlowInInterpolator());
            TransitionManager.beginDelayedTransition(lyt, set);
        }
    }

    public class Handlers{

        public void onClickQuite(View view){
            startActivity(new Intent(view.getContext(), MainActivity.class));
        }

        public void onClickPause(View view){
            beginTransition();
            findViewById(R.id.lyt_pause).setVisibility(View.VISIBLE);
        }

        public void onClickResumen(View view){
            beginTransition();
            findViewById(R.id.lyt_pause).setVisibility(View.INVISIBLE);
        }

        public void onClickGoToMenu(View view){
            startActivity(new Intent(view.getContext(), MainActivity.class));
        }

        public void onClickRestart(View view){
            gameViewModel.reset();
            beginTransition();
            findViewById(R.id.lyt_pause).setVisibility(View.INVISIBLE);
        }

        public void onPlay(View view, int position){
            gameViewModel.playerMove(position);
        }
    }
}
