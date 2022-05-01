package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MenuActivity extends AppCompatActivity {
    public Fragment fragment;
    public Toast toast;
    public Animation animation;
    public View button;
    public androidx.appcompat.widget.Toolbar myToolbar;
    public int ANIMATION_TIME = 1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        String stringURL = "https://www.politize.com.br/mandato-dos-politicos/";
        String stringURL2 = "https://www.politize.com.br/eleicoes-para-presidente-2/?doing_wp_cron=1650928608.8153030872344970703125";
        String stringURL3 = "https://www.politize.com.br/eleicoes-para-presidente-2/?doing_wp_cron=1650928608.8153030872344970703125";


        myToolbar = findViewById(R.id.nav_view);
        fragment = NavigationHomePage.newInstance();
        openFragment(fragment);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        myToolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.navigation_info:
//                        toast = Toast.makeText(MenuActivity.this, "Info", Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0, 600);
//                        toast.show();

                        button = myToolbar.findViewById(R.id.navigation_info);
                        animateButton(button, ANIMATION_TIME);

                        fragment = NavigationInfoPage.newInstance();
                        openFragment(fragment);

                        button.postOnAnimationDelayed(new Runnable() {
                            @Override
                            public void run() {
                                button.clearAnimation();
                            }
                        }, 1000);

                        break;

                    case R.id.navigation_home:
//                        toast = Toast.makeText(MenuActivity.this,"Home", Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 600);
//                        toast.show();

                        button = myToolbar.findViewById(R.id.navigation_home);
                        animateButton(button, ANIMATION_TIME);

                        fragment = NavigationHomePage.newInstance();
                        openFragment(fragment);

                        button.postOnAnimationDelayed(new Runnable() {
                            @Override
                            public void run() {
                                button.clearAnimation();
                            }
                        }, 1000);
                        break;

                    case R.id.navigation_ranking:
//                        toast = Toast.makeText(MenuActivity.this, "Ranking", Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0, 600);
//                        toast.show();

                        button = myToolbar.findViewById(R.id.navigation_ranking);
                        animateButton(button, ANIMATION_TIME);

                        fragment = NavigationRankingPage.newInstance();
                        openFragment(fragment);

                        button.postOnAnimationDelayed(new Runnable() {
                            @Override
                            public void run() {
                                button.clearAnimation();
                            }
                        }, 1000);
                        break;
                }
                return true;
            }
        });
    }

    private View animateButton(View button, Integer duration) {
        animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.blink);
        animation.setDuration(duration);
        button.startAnimation(animation);

        return button;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_app, menu);
        return true;
    }

    private void setSupportActionBar(Toolbar myToolbar) {
    }

    public void goToHTML(View view) {
        Intent intent = new Intent(this, HTMLRead.class);
        startActivity(intent);
        finish();
    }
}
