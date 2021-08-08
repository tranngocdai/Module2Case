package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {
    String nameAlbum;
    private List<Song> listSong = new ArrayList<>();

    public Album(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public List<Song> getListSong() {
        return listSong;
    }

    public void setListSong(List<Song> list) {
        listSong = list;
    }

    public void addSong(Song song) {
        listSong.add(song);
    }

    public boolean editSong(int index, Song song) {
        try {
            listSong.remove(index);
            return listSong.add(song);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean delete(int index) {
        if (index != -1) {
            listSong.remove(index);
            return true;
        } else
            return false;
    }

    public int searchByNameSong(String name) {
        for (int i = 0; i < listSong.size(); i++) {
            if (listSong.get(i).getNameSong().equals(name))
                return i;
        }
        return -1;
    }

    public void showAllSong() {
        for (Song song : listSong) {
            System.out.print(song.toString()+";\t");
        }
        System.out.println(" ");
    }

    @Override
    public String toString() {
        return "Album{" +
                "nameAlbum='" + nameAlbum + '\'' +
                '}';
    }


}
