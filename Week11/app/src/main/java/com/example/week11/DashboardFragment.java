package com.example.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ui.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // ViewPager设置适配器
        final DashboardViewPagerAdapter adapter = new DashboardViewPagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(adapter);
        // 保持fragment的状态，而不是每次都创建新的Fragment
        binding.viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        // TabLayout与ViewPager联动
        binding.tabs.setupWithViewPager(binding.viewPager);
    }

    private static class DashboardViewPagerAdapter extends FragmentPagerAdapter {

        public DashboardViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return DashboardChildFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        // Tab的标题
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Dashboard " + position;
        }
    }
}