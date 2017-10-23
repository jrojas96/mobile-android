package com.teamgym.fitgym.networking;

import android.content.res.Resources;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.teamgym.fitgym.R;
import com.teamgym.fitgym.models.PTrainer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 22/10/2017.
 */

public class PTrainerApiService {
    public static List<PTrainer> trainers = new ArrayList<>();
    public static PTrainer trainer = new PTrainer();

    public static void getTrainers(final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.PERSONAL_TRAINERS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            trainers = PTrainer.from(response.getJSONArray("personalTrainers"));
                            action.execute(trainers);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
    }

    public static void getTrainers(int gymCompanyId, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.PERSONAL_TRAINERS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addQueryParameter("gymCompanyId", String.valueOf(gymCompanyId))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            trainers = PTrainer.from(response.getJSONArray("personalTrainers"));
                            action.execute(trainers);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
    }

    public static void getTrainers(int gymCompanyId, String query, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.PERSONAL_TRAINERS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addQueryParameter("gymCompanyId", String.valueOf(gymCompanyId))
                .addQueryParameter("query", query)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            trainers = PTrainer.from(response.getJSONArray("personalTrainers"));
                            action.execute(trainers);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
    }

    public static void getTrainer(int personalTrainerId, final IActionPostServiceResult action) {
        AndroidNetworking.get(FitGymApiService.PERSONAL_TRAINERS)
                .setTag(R.string.app_name)
                .setPriority(Priority.LOW)
                .addPathParameter("id", String.valueOf(personalTrainerId))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(Resources.getSystem().getString(R.string.app_name), response.getString("message"));
                                return;
                            }
                            trainer = PTrainer.from(response.getJSONObject("personalTrainers"));
                            action.execute(trainers);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(Resources.getSystem().getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
    }
}