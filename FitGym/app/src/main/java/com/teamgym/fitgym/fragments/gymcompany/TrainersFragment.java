package com.teamgym.fitgym.fragments.gymcompany;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamgym.fitgym.R;
import com.teamgym.fitgym.activities.gymcompany.AddEditPersonalTrainer;
import com.teamgym.fitgym.adapters.gymcompany.PTrainersAdapter;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.models.PTrainer;
import com.teamgym.fitgym.network.IActionPostServiceResult;
import com.teamgym.fitgym.network.PTrainerApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainersFragment extends Fragment {
    RecyclerView trainersRecyclerView;
    PTrainersAdapter trainersAdapter;
    RecyclerView.LayoutManager trainersLayoutManager;
    List<PTrainer> trainers;
    int trainersOldSize = 0;
    GymCompany gymCompany;

    static int REQUEST_CODE_ADD = 2;
    static int REQUEST_CODE_DETAILS = 1;
    static int RESULT_CODE_OK = 200;
    static int RESULT_CODE_NOT_OK = 300;

    public TrainersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainers, container, false);

        gymCompany = GymCompany.from(getActivity().getIntent().getExtras());

        FloatingActionButton fabAddTrainer = (FloatingActionButton) view.findViewById(R.id.addTrainerButton);
        fabAddTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddEditPersonalTrainer.class);
                intent.putExtras(gymCompany.toBundle());
                trainersOldSize = trainers.size();
                context.startActivity(intent);
                //startActivityForResult(intent, 2);
            }
        });

        trainersRecyclerView = (RecyclerView) view.findViewById(R.id.trainersRecyclerView);
        trainers = new ArrayList<>();
        trainersAdapter = new PTrainersAdapter(trainers);
        trainersLayoutManager = new GridLayoutManager(view.getContext(), 2);
        trainersRecyclerView.setAdapter(trainersAdapter);
        trainersRecyclerView.setLayoutManager(trainersLayoutManager);
        updateTrainers();
        return view;
    }

    private void updateTrainers() {
        PTrainerApiService.getTrainersByGymId(gymCompany, new IActionPostServiceResult<List<PTrainer>>() {
            @Override
            public void execute(List<PTrainer> result) {
                trainers = result;
                trainersAdapter.setTrainers(trainers);
                trainersAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        trainersAdapter.verifyIfItemChanged();
        if (trainersOldSize == 0) return;
        PTrainerApiService.getTrainersByGymId(gymCompany, new IActionPostServiceResult<List<PTrainer>>() {
            @Override
            public void execute(List<PTrainer> trainers) {
                if(trainersOldSize != trainers.size()) {
                    trainersAdapter.setTrainers(trainers);
                    trainersAdapter.notifyItemInserted(trainers.size() - 1);
                }
            }
        });
    }
}