package Controller;

import Model.Song;
import Storage.FileController;
import Storage.IFileController;

import java.util.List;

public class SongController {

    private static SongController instance;
    private List<Song> songList;
    public static final String FILE_PATH_SONG = "src/storage/songs.txt";
    public static final IFileController<Song> fileController = FileController.getIntance();

    private SongController() {
        this.songList = fileController.readFile(FILE_PATH_SONG);
    }

    public static SongController getInstance() {
        if (instance == null) {
            instance = new SongController();
        }
        return instance;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public boolean addSong(Song song) {
        if (songList.contains(song)) {
            return false;
        } else {
            songList.add(song);
            fileController.writeFile(songList, FILE_PATH_SONG);
            return true;
        }
    }

    public int search(String name) {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getNameSong().equals(name))
                return i;
        }
        return -1;
    }

    public boolean edit(int index, Song song) {
        if (index >= 0 && index < songList.size()) {
            songList.remove(index);
            return songList.add(song);
        }
        return false;
    }

    public boolean delete(int index) {
        if (index >= -0 && index < songList.size()) {
            songList.remove(index);
            fileController.writeFile(songList, FILE_PATH_SONG);
            return true;
        }
        return false;
    }
}
