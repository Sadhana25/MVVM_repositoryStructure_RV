package com.definelabs.myapplication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.definelabs.myapplication.adapter.SharedMatchAdapter;
import com.definelabs.myapplication.database.Match;
import com.definelabs.myapplication.databinding.FragmentSavematchBinding;
import com.definelabs.myapplication.viewModel.MatchViewModel;

import java.util.ArrayList;
import java.util.List;

public class SaveMatchFragment extends Fragment implements SharedMatchAdapter.ItemClickListenerSave {
    private static final String TAG = "SaveMatchFragment";
    private MatchViewModel viewmodel;
    private FragmentSavematchBinding binding;
    private List<Match> matchlistele = new ArrayList<>();
    private LiveData<List<Match>> databaseData = new MutableLiveData<>();
    private RecyclerView recyclerView;
    private SharedMatchAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: share fragment");

        viewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())
                .create(MatchViewModel.class);
        binding = FragmentSavematchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        viewmodel.getSaveMatchList().observe(getViewLifecycleOwner(), new Observer<List<Match>>() {
            @Override
            public void onChanged(List<Match> matchList) {
                Log.d(TAG, "onChanged: matchlist observer  " + matchList.size());
                if (matchList.size() == 0) {
                    Log.d(TAG, "onChanged: list is null");
                    viewmodel.isDataExistDb().postValue(false);
                } else {
                    initRecyclerView(matchList);
                    viewmodel.isDataExistDb().postValue(true);
                }
            }
        });

        viewmodel.isDataExistDb().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, "onChanged:isDataExistDb static" + aBoolean);
                if (aBoolean) {
                    binding.tvNodata.setVisibility(View.GONE);
                } else {
                    binding.tvNodata.setVisibility(View.VISIBLE);
                    binding.shareRecyclerView.setVisibility(View.GONE);
                }
            }
        });

        return root;
    }

    private void initRecyclerView(List<Match> matches) {
        mAdapter = new SharedMatchAdapter(getActivity(), matches, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.shareRecyclerView.setLayoutManager(layoutManager);
        binding.shareRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMatchClick(Match match) {
        viewmodel.deleteMatchVm(match.getMatch_id());
    }
}