package app.stores;

import app.domain.model.Departure;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;

import java.io.EOFException;
import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.List;

public class DepartureStore {

    private final ArrayList<Departure> departures = new ArrayList<>();
 //   private final GenericClass<Departure> genericsVaccineType = new GenericClass<>();


    /**
     * Gets the Departures that are stored in the Vaccination Center.
     *
     * @return A List of Departures.
     */
    public List<Departure> getDeparturesList() {
        return departures;
    }

    /**
     * Saves a new Departure:
     *
     * @param departure the departure
     */
    public void saveDeparture(Departure departure) {
        departures.add(departure);
    }

    /**
     * Read binary file departures.
     */
    /*
    public void readBinaryFileDepartures() throws NotSerializableException {
        try {
            genericsVaccineType.binaryFileRead(Constants.FILE_PATH_DEPARTURES, departures);
        } catch (EOFException e) {
            e.printStackTrace();
        }
    } */
}
