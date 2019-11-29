package com.example.reportingsystemmobile.menu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.reportingsystemmobile.R;
import com.example.reportingsystemmobile.login.LoginActivity;
import com.example.reportingsystemmobile.myreports.ReportListActivity;
import com.example.reportingsystemmobile.register.RegisterActivity;
import com.example.reportingsystemmobile.report.ReportActivity;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private Fragment fragment;
    private Toolbar toolbar;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fragment = null;


        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawer = findViewById(R.id.drawer_layout);


        nvDrawer = findViewById(R.id.nav_view);
        userLogoutMenu();
        // Setup drawer view
        setupDrawerContent(nvDrawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_register:
                fragmentClass = RegisterActivity.class;
                break;
            case R.id.nav_report:
                fragmentClass = ReportActivity.class;
                break;
            case R.id.nav_report_list:
                fragmentClass = ReportListActivity.class;
                break;
            case R.id.nav_login:
            default:
                fragmentClass = LoginActivity.class;
        }

        replaceFragment(fragmentClass);

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    public void replaceFragment(Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentViewer, fragment).commit();
    }

    public void userLoggedMenu() {
        nvDrawer.getMenu().setGroupVisible(R.id.login_register, false);
        nvDrawer.getMenu().setGroupVisible(R.id.user_logged, true);
    }

    public void userLogoutMenu() {
        nvDrawer.getMenu().setGroupVisible(R.id.login_register, true);
        nvDrawer.getMenu().setGroupVisible(R.id.user_logged, false);
    }

    // for checking internet connection
    public boolean connect() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else {
            return false;
        }
    }
}
