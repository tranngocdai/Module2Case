package View;

import Controller.SongController;
import Model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SongView {
    public static SongController songController = SongController.getInstance();
    public static Validate validate = Validate.getInstance();

    public static void controllerSong() {
        String choose;
        String regexChoose = "^[1-5]+$";
        Scanner scanner = new Scanner(System.in);
        boolean checkout;
        do {
            System.out.println("Bảng chọn:");
            System.out.println("1: Thêm bài hát");
            System.out.println("2: Sửa bài hát");
            System.out.println("3: Xoá bài hát");
            System.out.println("4: Hiển thị toàn bộ bài hát");
            System.out.println("5: Thoát");
            choose = scanner.nextLine();
            checkout = validate.validate(choose, regexChoose) && choose.equals("5");
            switch (choose) {
                case "1":
                    if (addNewSong()) {
                        System.out.println("Thêm thành công");
                    } else
                        System.out.println("Bài hát đã có");
                    break;
                case "2":
                    if (editSong()) {
                        System.out.println("Sửa thành công");
                    } else
                        System.out.println("Trùng tên");
                    break;
                case "3":
                    if (deleteSong()) {
                        System.out.println("Xoá thành công");
                    } else
                        System.out.println("Không thấy");
                    break;
                case "4":
                    showAllSong();
                    break;
                case "5":
                    System.out.println("See you again!");
                    break;
            }
        } while (!checkout);
    }

    public static Song creatSong() {
        Song song = null;
        String nameSong;
        String author;
        Scanner scanner = new Scanner(System.in);

        String regexData = "\\b.*$";
        boolean checkdata;
        do {
            System.out.println("input name song");
            nameSong = scanner.nextLine();
            System.out.println("input author");
            author = scanner.nextLine();
            checkdata = validate.validate(nameSong, regexData) && validate.validate(author, regexData);
        } while (!checkdata);

        return song.withNameSong(nameSong).withAuthor(author);
    }

    public static boolean addNewSong() {
        Song newSong = creatSong();
        return songController.addSong(newSong);
    }

    public static boolean editSong() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input name song are you want to edit");
        String nameOfOldSong = scanner.nextLine();
        System.out.println("input info of song to edit");
        Song newSong = creatSong();
        int index = songController.search(nameOfOldSong);
        return songController.edit(index, newSong);
    }

    public static boolean deleteSong() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên bài hát để xoá bài hát");
        String nameOfOldSong = scanner.nextLine();
        int index = songController.search(nameOfOldSong);
        return songController.delete(index);
    }

    public static Song returSongInList(String name) {
        int index = songController.search(name);
        if (index != -1) {
            return songController.getSongList().get(index);
        } else
            return null;
    }

    public static void showAllSong() {
        for (Song song : songController.getSongList()) {
            System.out.println(song);
        }
    }
}
