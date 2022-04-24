package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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

    private View animateButton(View button, Integer duration) {
        animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.blink);
        animation.setDuration(duration);
        button.startAnimation(animation);

        return button;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        myToolbar = findViewById(R.id.nav_view);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        myToolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.navigation_home:
                        toast = Toast.makeText(MenuActivity.this, "Home", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 600);
                        toast.show();

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
                    case R.id.navigation_dashboard:
//                        Toast.makeText(MenuActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
//                        DisplayMetrics displayMetrics = new DisplayMetrics();
//                        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//                        int height = displayMetrics.heightPixels;
//                        int width = displayMetrics.widthPixels;
//                        Toast.makeText(MenuActivity.this, String.valueOf(width) + " " + String.valueOf(height), Toast.LENGTH_LONG).show();
                        toast = Toast.makeText(MenuActivity.this,"Dashboard", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 600);
                        toast.show();

                        button = myToolbar.findViewById(R.id.navigation_dashboard);
                        animateButton(button, ANIMATION_TIME);

                        fragment = NavigationDashboardPage.newInstance();
                        openFragment(fragment);

                        button.postOnAnimationDelayed(new Runnable() {
                            @Override
                            public void run() {
                                button.clearAnimation();
                            }
                        }, 1000);
                        break;
                    case R.id.navigation_notifications:
                        toast = Toast.makeText(MenuActivity.this, "Notifications", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 600);
                        toast.show();

                        button = myToolbar.findViewById(R.id.navigation_notifications);
                        animateButton(button, ANIMATION_TIME);

                        fragment = NavigationAlertsPage.newInstance();
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

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
