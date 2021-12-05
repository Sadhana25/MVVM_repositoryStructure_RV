package com.definelabs.myapplication.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.definelabs.myapplication.adapter.MatchesListAdapter;
import com.definelabs.myapplication.database.Match;
import com.definelabs.myapplication.databinding.FragmentAllmatchBinding;
import com.definelabs.myapplication.model.Venue;
import com.definelabs.myapplication.viewModel.MatchViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllMatchFragment extends Fragment implements MatchesListAdapter.ItemClickListener {
    private static final String TAG = "AllMatchFragment";
    private List<Venue> venueList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MatchesListAdapter mAdapter;
    ProgressBar loadingIndicator;
    private MatchViewModel viewModel;
    private FragmentAllmatchBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAllmatchBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        viewModel = ViewModelProvider.AndroidViewModelFactory.
                getInstance(getActivity().getApplication())
                .create(MatchViewModel.class);

        viewModel.getAllMatchList().observe(getViewLifecycleOwner(), new Observer<List<Venue>>() {
            @Override
            public void onChanged(List<Venue> venues) {
                initRecyclerView(venues);
                viewModel.getIsLoading().postValue(false);
            }
        });

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), (Boolean isLoading) ->
        {
            if (isLoading) {
                binding.todoLoadingIndicator.setVisibility(View.VISIBLE);

            } else {
                binding.todoLoadingIndicator.setVisibility(View.GONE);
            }
        });

        return root;
    }

    private void initRecyclerView(List<Venue> venues) {
        Log.d(TAG, "onCreateView: recycler " + venues.size());
        mAdapter = new MatchesListAdapter(getActivity(), venues, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onMatchClick(Match match) {
        Log.d(TAG, "onMatchClick: " + match.getMatch_id());
        Toast.makeText(getActivity(), "Data Save", Toast.LENGTH_LONG).show();
        viewModel.insertMatchVM(match);

    }
}