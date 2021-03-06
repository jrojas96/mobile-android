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
import com.teamgym.fitgym.activities.gymcompany.AddEditActivityTypeActivity;
import com.teamgym.fitgym.adapters.gymcompany.ActivityTypesAdapter;
import com.teamgym.fitgym.models.ActivityType;
import com.teamgym.fitgym.models.GymCompany;
import com.teamgym.fitgym.network.ActivityTypeApiService;
import com.teamgym.fitgym.network.IActionPostServiceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityTypesFragment extends Fragment {
    RecyclerView activityTypesRecyclerView;
    ActivityTypesAdapter activityTypesAdapter;
    RecyclerView.LayoutManager activityTypesLayoutManager;
    List<ActivityType> activityTypes;
    int activityTypesOldSize = 0;
    GymCompany gymCompany;
    String tkn = "";

    public ActivityTypesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_types, container, false);

        gymCompany = GymCompany.from(getActivity().getIntent().getExtras());
        tkn = getActivity().getIntent().getStringExtra("token");

        FloatingActionButton fabAddActivityType = (FloatingActionButton) view.findViewById(R.id.addActivityTypeButton);
        fabAddActivityType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddEditActivityTypeActivity.class);
                intent.putExtras(gymCompany.toBundle());
                intent.putExtra("token", tkn);
                activityTypesOldSize = activityTypes.size();
                context.startActivity(intent);
            }
        });

        activityTypesRecyclerView = (RecyclerView) view.findViewById(R.id.activityTypesRecyclerView);
        activityTypes = new ArrayList<>();
        activityTypesAdapter = new ActivityTypesAdapter(activityTypes);
        activityTypesAdapter.setTkn(tkn);
        activityTypesLayoutManager = new GridLayoutManager(view.getContext(), 1);
        activityTypesRecyclerView.setAdapter(activityTypesAdapter);
        activityTypesRecyclerView.setLayoutManager(activityTypesLayoutManager);
        updateActivityTypes();
        return view;
    }

    private void updateActivityTypes() {
        ActivityTypeApiService.getActivityTypesByGymId(tkn, gymCompany, new IActionPostServiceResult<List<ActivityType>>() {
            @Override
            public void execute(List<ActivityType> result) {
                activityTypes = result;
                activityTypesAdapter.setActivityTypes(activityTypes);
                activityTypesAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        activityTypesAdapter.verifyIfItemChanged(tkn);
        if (activityTypesOldSize == 0) return;
        ActivityTypeApiService.getActivityTypesByGymId(tkn, gymCompany, new IActionPostServiceResult<List<ActivityType>>() {
            @Override
            public void execute(List<ActivityType> activityTypes) {
                if (activityTypesOldSize != activityTypes.size()) {
                    activityTypesAdapter.setActivityTypes(activityTypes);
                    activityTypesAdapter.notifyItemInserted(activityTypes.size() - 1);
                }
            }
        });
    }
}
