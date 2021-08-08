package Storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController <E> implements IFileController<E> {
    private static FileController instance;

    private FileController() {
    }

    public static FileController getIntance() {
        if (instance == null) {
            instance = new FileController();
        }
        return instance;
    }

    @Override
    public List<E> readFile(String path) {
        List<E> list = new ArrayList<>();
        try {
            File file = new File(path);
            if (file.length() > 0) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                list = (List<E>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found");
        }
        return list;
    }

    @Override
    public void writeFile(List<E> list, String path) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
