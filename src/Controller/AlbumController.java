package Controller;

import Model.Account;
import Model.Album;
import Model.Song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlbumController implements Serializable {
    private Obsever obsever = AccountController.getInstance();
    private List<Album> albumList = new ArrayList<>();
    private Account account;

    public AlbumController() {

    }

    public AlbumController(Account account) {
        this.account = account;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addAlbum(Album album) {
        albumList.add(album);
    }

    public void editAlbum(String name, Album album) {
        int index = searchByNameAlbum(name);
        if (index != -1) {
            albumList.remove(index);
            albumList.add(album);
        }

    }

    public void deleteAlbum(int index) {
        albumList.remove(index);
    }

    public int searchByNameAlbum(String name) {
        for (int i = 0; i < albumList.size(); i++) {
            if (albumList.get(i).getNameAlbum().equals(name)) {
                return i;
            }
        }
        return -1;
    }


    public boolean deleteSong(int indexSong, int indexAlbum) {
        return albumList.get(indexAlbum).delete(indexSong);
    }

    public boolean addSongs(Song song, int indexAlbum) {
        if (indexAlbum != -1) {
            albumList.get(indexAlbum).addSong(song);
            return true;
        }
        return false;
    }

    public boolean editSong(Song song, String nameAlbum, int indexSong) {
        int indexAlbum = searchByNameAlbum(nameAlbum);
        if (indexAlbum != -1) {
            return albumList.get(indexAlbum).editSong(indexSong, song);
        }
        return false;
    }

    public void showAllAlbum() {
        for (Album album : albumList) {
            System.out.print(album + ";\t");
        }
        System.out.println(" ");
    }


    public boolean saveInfor() {
        int index = obsever.searchByAccount(this.account);
        if (this.account == null || index == -1) {
            return false;
        } else
            obsever.updateAccount(index, this);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AlbumController) {
            AlbumController another = (AlbumController) obj;
            if (another.getAccount().equals(this.account))
                return true;
        }
        return false;
    }


}
