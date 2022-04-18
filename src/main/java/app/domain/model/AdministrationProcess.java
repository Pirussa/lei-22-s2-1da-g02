package app.domain.model;

import java.util.ArrayList;

/**
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 */

public class AdministrationProcess {

    private ArrayList<Integer> ageGroups;

    private ArrayList<Integer> numberOfDoses;

    private ArrayList<Integer> dosage;

    private ArrayList<Integer> timeIntervalBetweenVaccines;

    public AdministrationProcess(ArrayList<Integer> ageGroups, ArrayList<Integer> numberOfDoses, ArrayList<Integer> dosage, ArrayList<Integer> timeIntervalBetweenVaccines) {
        this.ageGroups = ageGroups;
        this.numberOfDoses = numberOfDoses;
        this.dosage = dosage;
        this.timeIntervalBetweenVaccines = timeIntervalBetweenVaccines;
    }
}
