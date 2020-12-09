package com.example.demo.client;

import java.util.Date;
import java.util.List;

public interface MedicationPlanInterfac {


    void hello(String s);

    List<String> getPlan(Integer id);
    List<String> getMedication(Integer id);
    void saveTakenNotTaken(String medicine, Date date, Boolean status);
}
