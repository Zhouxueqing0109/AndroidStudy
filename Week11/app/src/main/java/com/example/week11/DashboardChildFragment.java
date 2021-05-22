package com.example.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui.databinding.FragmentDashboardChildBinding;

import java.text.MessageFormat;

public class DashboardChildFragment extends Fragment {
    private static final String ARGUMENT_POSITION = "argument_position";
    private FragmentDashboardChildBinding binding;

    public static DashboardChildFragment newInstance(int position) {
        DashboardChildFragment fragment = new DashboardChildFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardChildBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            int position = getArguments().getInt(ARGUMENT_POSITION);
            binding.tvDashboard.setText(MessageFormat.format("{0} {1}",
                    getString(R.string.title_dashboard), position));
        }
    }
}