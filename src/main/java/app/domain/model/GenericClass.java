package app.domain.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenericClass<E> {

    E element;

    public GenericClass(/*E element*/) {
        // this.element = element;
    }

    public void binaryFileWrite(String fileName, List<E> list) {
        File file = new File(fileName);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            try {
                out.writeObject(list);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

List <E> list = new ArrayList<>();
    public void binaryFileRead(String fileName) {
        File file = new File(fileName);
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            list = (List<E>) in.readObject();
            for (int listPosition = 0; listPosition < list.size(); listPosition++) {
                System.out.println(list.get(listPosition));
            }

            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<E> getList(){
        return list;
    }
}
