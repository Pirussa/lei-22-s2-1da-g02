package app.domain.shared;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenericClass<E> implements Serializable {

    E element;

    /**
     * Constructor of the Generics class.
     */
    public GenericClass() {
    }

    /**
     * Writes the registered information to a binary file.
     *
     * @param fileName: path of the file that is going to receive the information.
     * @param list:     list containing the information to be exported to the file.
     */

    public void binaryFileWrite(String fileName, List<E> list) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            try {
                out.writeObject(list);
            } finally {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<E> list = new ArrayList<>();


    /**
     * Reads information from a binary file.
     *
     * @param fileName:       path of the file containing the information.
     * @param listToBeFilled: list that is going to be filled with the file's information.
     * @throws EOFException
     */
    public void binaryFileRead(String fileName, List<E> listToBeFilled) throws EOFException {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            list = (List<E>) in.readObject();

            in.close();
            listToBeFilled.addAll(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<E> getList() {
        return list;
    }
}
