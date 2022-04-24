package com.example.vocepolitico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class NavigationDashboardPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.navigation_dashboard, container, false);
    }

    public static NavigationDashboardPage newInstance() {
        return new NavigationDashboardPage();
    }
}