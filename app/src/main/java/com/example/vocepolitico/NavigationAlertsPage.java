package com.example.vocepolitico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class NavigationAlertsPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.navigation_alerts, container, false);
    }

    public static NavigationAlertsPage newInstance() {
        return new NavigationAlertsPage();
    }
}