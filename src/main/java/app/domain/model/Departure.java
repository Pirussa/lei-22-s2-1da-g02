package app.domain.model;

import app.controller.App;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.stores.VaccinationCentersStore;

import java.io.NotSerializableException;
import java.time.LocalDateTime;

public class Departure {

    private final LocalDateTime departureTime;

    GenericClass<Arrival> generics = new GenericClass<>();
    VaccinationCentersStore vaccinationCentersStore = App.getInstance().getCompany().getVaccinationCentersStore();

    public Departure(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    /**
     * Exports the list of Users that arrived at a vaccination center to a binary file.
     * @throws NotSerializableException
     */
    public void exportDataToFile() throws NotSerializableException {
        for (VaccinationCenter vaccinationCenter : vaccinationCentersStore.getVaccinationCenters()) {
            generics.binaryFileWrite(Constants.FILE_PATH_ARRIVALS, vaccinationCenter.getArrivalsList());
        }
    }
}
