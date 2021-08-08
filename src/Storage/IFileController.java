package Storage;

import java.util.List;

public interface IFileController<E> {
    public List<E> readFile(String path);
    public void writeFile(List<E> e, String path);
}
