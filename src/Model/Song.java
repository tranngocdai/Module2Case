package Model;

import java.io.Serializable;
import java.util.Scanner;

public class Song implements Serializable {
    private int idSong;
    private String nameSong, author, singer;

    public Song(){

    }

    public Song withNameSong(String nameSong) {
        this.nameSong = nameSong;
        return this;
    }

    public Song withAuthor(String author) {
        this.author = author;
        return this;
    }

    public Song(int idSong, String nameSong, String author, String singer) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.author = author;
        this.singer = singer;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Song{" +
                "idSong=" + idSong +
                ", name='" + nameSong + '\'' +
                ", author='" + author + '\'' +
                ", singer='" + singer + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Song) {
            return ((Song) obj).getNameSong().equals(this.getNameSong());
        }
        return false;
    }

}
