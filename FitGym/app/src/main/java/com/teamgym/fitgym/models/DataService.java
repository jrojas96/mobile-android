package com.teamgym.fitgym.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GNO on 26/09/2017.
 */

public class DataService {

    //Basic functions
    public List<ActivityType> getActivityTypes(){
        List<ActivityType> activityTypes = new ArrayList<>();
        //TODO query
        return  activityTypes;
    }

    public List<TypeMeal> getTypeMeals(){
        List<TypeMeal> typeMeals = new ArrayList<>();
        //TODO query
        return typeMeals;
    }

    public List<QMeasurement> getQMeasurement(){
        List<QMeasurement> qMeasurements =  new ArrayList<>();
        //TODO query
        return qMeasurements;
    }

    public List<SuscriptionType> getSuscriptionTypes(){
        List<SuscriptionType> suscriptionTypes = new ArrayList<>();
        //TODO query
        return suscriptionTypes;
    }
    //
    public List<Client> getPTrainerClients(PTrainer pTrainer){
        List<Client> clients = new ArrayList<>();
        //TODO query
        return clients;
    }

    public List<Goal> getClientGoals(Client client){
        List<Goal> goals = new ArrayList<>();
        //TODO query
        return goals;
    }

    //TODO interface interactions functions
}