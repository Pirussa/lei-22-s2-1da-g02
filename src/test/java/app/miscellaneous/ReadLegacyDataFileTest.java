package app.miscellaneous;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.stores.VaccinationCentersStore;
import app.ui.console.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Read legacy data file test.
 */
class ReadLegacyDataFileTest {
    private VaccinationCenter center;
    private final ReadLegacyDataFile readLegacyDataFile;

    public ReadLegacyDataFileTest() {
        final Company company = App.getInstance().getCompany();
        final VaccinationCentersStore store = company.getVaccinationCentersStore();
        String id = Utils.getLoggedCoordinatorId();
        center = store.getVaccinationCenterAssociatedToCoordinator(id);
        readLegacyDataFile = new ReadLegacyDataFile(center);
    }

    List<String> list = new ArrayList<>();
    List<String> dates = new ArrayList<>();

    public void fillList() {
        list.add("161593120;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:24;5/30/2022 9:11;5/30/2022 9:43");
        list.add("161593120;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:24;5/30/2022 9:11;5/30/2022 9:43");
        list.add("161593120;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:24;5/30/2022 9:11;5/30/2022 9:43");
        list.add("161593120;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:24;5/30/2022 9:11;5/30/2022 9:43");
        list.add("161593121;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:17;5/30/2022 8:51");
        list.add("161593122;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:15;5/30/2022 8:46");
        list.add("161593123;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:11;5/30/2022 8:43");
        list.add("161593124;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:17;5/30/2022 8:49");
        list.add("161593125;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:19;5/30/2022 9:00;5/30/2022 9:34");
        list.add("161593126;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:01;5/30/2022 8:42;5/30/2022 9:19");
        list.add("161593127;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:18;5/30/2022 8:57");
        list.add("161593128;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:12;5/30/2022 8:46");
        list.add("161593129;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:13;5/30/2022 8:50");
        list.add("161593130;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:21;5/30/2022 9:09;5/30/2022 9:46");
        list.add("161593131;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:16;5/30/2022 8:54");
        list.add("161593132;Spikevax;Primeira;21C16-05;5/30/2022 8:00;5/30/2022 8:00;5/30/2022 8:10;5/30/2022 8:48");
    }

    public void fillListWithDates() {
        dates.add("5/30/2022 8:54");
        dates.add("5/30/2022 8:12");
        dates.add("5/30/2022 8:16");
        dates.add("5/30/2022 8:49");
        dates.add("5/30/2022 8:42");
        dates.add("5/30/2022 8:15");
        dates.add("5/30/2022 8:59");
        dates.add("5/30/2022 8:57");
        dates.add("5/30/2022 8:45");
        dates.add("5/30/2022 9:21");
        dates.add("5/30/2022 9:16");
        dates.add("5/30/2022 8:18");
        dates.add("5/30/2022 8:48");
        dates.add("5/30/2022 9:09");
        dates.add("5/30/2022 8:50");
        dates.add("5/30/2022 8:57");
        dates.add("5/30/2022 9:13");
        dates.add("5/30/2022 9:25");
        dates.add("5/30/2022 8:44");
        dates.add("5/30/2022 9:10");
        dates.add("5/30/2022 8:50");
        dates.add("5/30/2022 9:27");
        dates.add("5/30/2022 9:13");
        dates.add("5/30/2022 9:08");
        dates.add("5/30/2022 8:53");
        dates.add("5/30/2022 9:00");
        dates.add("5/30/2022 8:45");
        dates.add("5/30/2022 9:29");
        dates.add("5/30/2022 9:21");
        dates.add("5/30/2022 9:17");
        dates.add("5/30/2022 8:54");
        dates.add("5/30/2022 8:18");
        dates.add("5/30/2022 9:12");
        dates.add("5/30/2022 8:14");
        dates.add("5/30/2022 9:09");
        dates.add("5/30/2022 8:57");
        dates.add("5/30/2022 9:18");
        dates.add("5/30/2022 9:09");
        dates.add("5/30/2022 9:03");
        dates.add("5/30/2022 9:06");
        dates.add("5/30/2022 9:13");
        dates.add("5/30/2022 9:12");
        dates.add("5/30/2022 8:56");
        dates.add("5/30/2022 8:55");
        dates.add("5/30/2022 8:10");
        dates.add("5/30/2022 8:55");
        dates.add("5/30/2022 9:16");
        dates.add("5/30/2022 8:45");
        dates.add("5/30/2022 9:14");
        dates.add("5/30/2022 8:12");
        dates.add("5/30/2022 8:14");
        dates.add("5/30/2022 9:08");
        dates.add("5/30/2022 9:25");
        dates.add("5/30/2022 9:20");
        dates.add("5/30/2022 9:13");
        dates.add("5/30/2022 9:31");
        dates.add("5/30/2022 8:10");
        dates.add("5/30/2022 9:25");
        dates.add("5/30/2022 9:25");
        dates.add("5/30/2022 9:10");
        dates.add("5/30/2022 9:05");
        dates.add("5/30/2022 9:29");
        dates.add("5/30/2022 9:14");
        dates.add("5/30/2022 9:29");
        dates.add("5/30/2022 8:51");
        dates.add("5/30/2022 8:47");
        dates.add("5/30/2022 8:51");
        dates.add("5/30/2022 9:00");
        dates.add("5/30/2022 9:20");
        dates.add("5/30/2022 8:54");
        dates.add("5/30/2022 8:19");
        dates.add("5/30/2022 8:19");
        dates.add("5/30/2022 8:14");
        dates.add("5/30/2022 9:01");
        dates.add("5/30/2022 9:25");
        dates.add("5/30/2022 9:15");
        dates.add("5/30/2022 8:45");
        dates.add("5/30/2022 9:04");
        dates.add("5/30/2022 8:11");
        dates.add("5/30/2022 9:22");
        dates.add("5/30/2022 9:20");
        dates.add("5/30/2022 9:17");
        dates.add("5/30/2022 9:19");
        dates.add("5/30/2022 9:20");
        dates.add("5/30/2022 8:16");
        dates.add("5/30/2022 8:46");
        dates.add("5/30/2022 8:10");
        dates.add("5/30/2022 9:06");
        dates.add("5/30/2022 9:12");
        dates.add("5/30/2022 8:45");
        dates.add("5/30/2022 9:18");
        dates.add("5/30/2022 8:49");
        dates.add("5/30/2022 9:32");
        dates.add("5/30/2022 8:48");
        dates.add("5/30/2022 9:20");
        dates.add("5/30/2022 8:15");
        dates.add("5/30/2022 8:17");
        dates.add("5/30/2022 9:11");
        dates.add("5/30/2022 9:18");
        dates.add("5/30/2022 9:17");
        dates.add("5/30/2022 8:18");
        dates.add("5/30/2022 8:56");
        dates.add("5/30/2022 8:56");
        dates.add("5/30/2022 8:58");
        dates.add("5/30/2022 9:20");
        dates.add("5/30/2022 9:20");
        dates.add("5/30/2022 8:17");
        dates.add("5/30/2022 9:01");
        dates.add("5/30/2022 8:58");
        dates.add("5/30/2022 9:13");
        dates.add("5/30/2022 9:37");
        dates.add("5/30/2022 9:44");
        dates.add("5/30/2022 9:26");
        dates.add("5/30/2022 9:23");
        dates.add("5/30/2022 9:05");
    }

    @Test
    void validateFileLegacy() {
        assertFalse(readLegacyDataFile.validateFileLegacy("110011001", "3/10/2000 8:00", "10/11/2011 8:00", "9/11/2001 8:00", "10/11/20111 8:00"));
        assertFalse(readLegacyDataFile.validateFileLegacy("11001101", "3/10/2000 8:00", "10/11/2011 8:00", "9/11/2001 8:00", "10/11/2011 8:00"));
        assertFalse(readLegacyDataFile.validateFileLegacy("", "3/10/2000 8:00", "10/11/2011 8:00", "9/11/2001 8:00", "10/11/2011 8:00"));
        assertFalse(readLegacyDataFile.validateFileLegacy("11001101", "3/10/2000 ", "10/11/2011 8:00", "9/11/2001 8:00", "10/11/2011 8:00"));
        assertFalse(readLegacyDataFile.validateFileLegacy("11001101", "3/10/2000 8:00", "40/11/2011 8:00", "9/11/2001 8:00", "10/11/2011 8:00"));
        assertFalse(readLegacyDataFile.validateFileLegacy("11001101", "0/10/2000 8:00", "10/11/2011 8:00", "9/11/2001 8:00", "10/11/2011 8:00"));
        assertFalse(readLegacyDataFile.validateFileLegacy("11001101", "3/0/2000 8:00", "10/11/2011 8:00", "9/11/2001 8:00", "10/11/2011 8:00"));
        assertFalse(readLegacyDataFile.validateFileLegacy("11001101", "3/10/2000 2", "10/11/2011 8:00", "9/11/2001 8:00", "10/11/2011 8:00"));
        fillList();
        String[] values;
        for (String s : list) {
            values = s.split(";");
            assertTrue(readLegacyDataFile.validateFileLegacy(values[0], values[4], values[5], values[6], values[7]));
        }
    }

    @Test
    void isValidDate() {
        assertFalse(readLegacyDataFile.isValidDate("3/110/2000 8:00"));
        assertFalse(readLegacyDataFile.isValidDate("3/10/2000 99:00"));
        assertFalse(readLegacyDataFile.isValidDate("3/10/2000 -1:00"));
        assertFalse(readLegacyDataFile.isValidDate("3/10/2000 00:"));
        fillListWithDates();
        int i = 0;
        for (String s : dates) {
            assertTrue(readLegacyDataFile.isValidDate(dates.get(i)));
            i++;
        }
    }

}