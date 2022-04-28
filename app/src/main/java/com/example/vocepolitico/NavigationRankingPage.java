package com.example.vocepolitico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

public class NavigationRankingPage extends Fragment {
    public static WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.navigation_ranking, container, false);
        webView = view.findViewById(R.id.politic_rank_webview);

        String url = "https://www.politicos.org.br";
//        String url = "https://www.google.com";

        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);

        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    public static NavigationRankingPage newInstance() {
        return new NavigationRankingPage();
    }
}