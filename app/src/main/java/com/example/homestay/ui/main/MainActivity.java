package com.example.homestay.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.homestay.R;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

@PerActivity
public class MainActivity extends BaseActivity {

    private static final String EXTRA_TAB_OPEN = "tab open";
    @Inject
    MainPresenter<MainView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_explore, R.id.navigation_trips, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        int index = getIntent().getIntExtra(EXTRA_TAB_OPEN, 0);
        if(index == 1) navController.navigate(R.id.navigation_trips);
        if(index == 2) navController.navigate(R.id.navigation_profile);
        setUp();
    }

    @Override
    protected void setUp() {

    }

    public static Intent getIntentMainActivity(Context context, int indexTabOpen) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TAB_OPEN, indexTabOpen);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

}
