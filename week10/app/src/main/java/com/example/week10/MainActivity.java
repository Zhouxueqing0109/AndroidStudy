package com.example.week10;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ui.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            binding.toolbar.setTitle(item.getTitle());
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    binding.viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    binding.viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    binding.viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        // 保持fragment的状态，而不是每次都创建新的Fragment
        binding.viewPager.setOffscreenPageLimit(adapter.getCount() - 1);

        binding.navigation.setOnNavigationItemSelectedListener(navigationItemSelectListener);
        bindNavigationDrawer();
        initTitle();
    }

    // FloatingActionButton的点击事件
    public void onFabClicked(View view) {
        showShareSnackbar();
    }

    private void bindNavigationDrawer() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final int id = item.getItemId();
                if (id == R.id.nav_tool) {
                    showToolSnackbar();
                } else if (id == R.id.nav_share) {
                    showShareSnackbar();
                } else if (id == R.id.nav_gallery) {
                    showGallerySnackbar();
                } else if (id == R.id.nav_send) {
                    showSendSnackbar();
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void showToolSnackbar() {
        Snackbar.make(binding.navigation, "Tool", Snackbar.LENGTH_SHORT).show();
    }

    private void showShareSnackbar() {
        Snackbar.make(binding.navigation, "Share", Snackbar.LENGTH_SHORT).show();
    }

    private void showGallerySnackbar() {
        Snackbar.make(binding.navigation, "Galley", Snackbar.LENGTH_SHORT).show();
    }

    private void showSendSnackbar() {
        Snackbar.make(binding.navigation, "Send", Snackbar.LENGTH_SHORT).show();
    }

    private void initTitle() {
        binding.toolbar.post(new Runnable() {
            @Override
            public void run() {
                binding.toolbar.setTitle(binding.navigation.getMenu().getItem(0).getTitle());
            }
        });
    }

    private static class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance();
                case 1:
                    return DashboardFragment.newInstance();
                case 2:
                    return NotificationFragment.newInstance();
            }
            return HomeFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}