package app.domain.model;

import java.io.*;
import java.util.List;

public class GenericClass<E> {

    E element;

    public GenericClass(E element) {
        this.element = element;
    }

    public void binaryFileWrite(String fileName, List<E> list) {
        try {
            ObjectOutputStream os = new ObjectOutputStream((new FileOutputStream(fileName, true)));
            for (int listPosition = 0; listPosition < list.size(); listPosition++) {
                os.writeObject(list);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void binaryFileRead(String fileName) {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            E element = (E) is.readObject();
            System.out.println(element);
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
