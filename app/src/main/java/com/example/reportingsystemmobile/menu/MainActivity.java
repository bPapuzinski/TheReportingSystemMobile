package com.example.reportingsystemmobile.menu;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.reportingsystemmobile.R;
import com.example.reportingsystemmobile.login.LoginFragment;
import com.example.reportingsystemmobile.myreports.ReportListFragment;
import com.example.reportingsystemmobile.register.RegisterActivity;
import com.example.reportingsystemmobile.report.ReportFragment;
import com.example.reportingsystemmobile.reportdetails.ReportDetailsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;
    private Fragment fragment;
    private Toolbar toolbar;
    int TAG_CODE_PERMISSION_LOCATION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if(!connect()) {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }
        fragment = null;

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = findViewById(R.id.drawer_layout);

        nvDrawer = findViewById(R.id.nav_view);
        userLogoutMenu();
        setupDrawerContent(nvDrawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_register:
                fragmentClass = RegisterActivity.class;
                break;
            case R.id.nav_report:
                fragmentClass = ReportFragment.class;
                break;
            case R.id.nav_report_list:
                fragmentClass = ReportListFragment.class;
                break;
            case R.id.nav_login:
            default:
                fragmentClass = LoginFragment.class;
        }

        replaceFragment(fragmentClass);
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    public void replaceFragment(Class fragmentClass) {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentViewer, fragment).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void replaceFragmentWithReportDetails(int id) {
        try {
            fragment = (Fragment) new ReportDetailsFragment(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentViewer, fragment).commit();
    }

    public void changeUsernameInHeaderMenu(String username) {
        TextView textView =  nvDrawer.getHeaderView(0).findViewById(R.id.usernameHeader_textview);
        textView.setText(username);
    }

    public void userLoggedMenu() {
        nvDrawer.getMenu().setGroupVisible(R.id.login_register, false);
        nvDrawer.getMenu().setGroupVisible(R.id.user_logged, true);
    }

    public void userLogoutMenu() {
        nvDrawer.getMenu().setGroupVisible(R.id.login_register, true);
        nvDrawer.getMenu().setGroupVisible(R.id.user_logged, false);
    }

    public Menu getMenu() {
        return  nvDrawer.getMenu();
    }

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
