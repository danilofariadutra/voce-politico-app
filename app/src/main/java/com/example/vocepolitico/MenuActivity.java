package com.example.vocepolitico;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.nav_view);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        myToolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast toast;
//                if (menuItem.getItemId() == R.id.favorite)
                switch(menuItem.getItemId()) {
                    case R.id.navigation_home:
                        toast = Toast.makeText(MenuActivity.this, "Home", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 600);
                        toast.show();
                        break;
                    case R.id.navigation_dashboard:
//                        Toast.makeText(MenuActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
                        DisplayMetrics displayMetrics = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        int height = displayMetrics.heightPixels;
                        int width = displayMetrics.widthPixels;
//                        Toast.makeText(MenuActivity.this, String.valueOf(width) + " " + String.valueOf(height), Toast.LENGTH_LONG).show();
                        toast = Toast.makeText(MenuActivity.this,"Dashboard", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 600);
                        toast.show();
                        break;
                    case R.id.navigation_notifications:
                        toast = Toast.makeText(MenuActivity.this, "Notifications", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 600);
                        toast.show();
                        break;
                }
                return true;
            }
        });
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
