package View;

import Controller.AlbumController;
import Model.Album;
import Model.Song;

import java.util.Scanner;

public class AlbumView {
    public static Validate validate = Validate.getInstance();

    public static void controller(AlbumController albumController) {
        String choose;
        String regexChoose = "^[0-9]{1,2}$";
        Scanner scanner = new Scanner(System.in);
        boolean checkout;
        do {
            System.out.println("Menu:");
            System.out.println("1: Thêm album");
            System.out.println("2: Sửa album");
            System.out.println("3: Xoá album");
            System.out.println("4: Tìm kiếm album");
            System.out.println("5: Xoá bài hát trong album");
            System.out.println("6: Thêm bài hát vào album");
            System.out.println("7: Tìm tên bài hát");
            System.out.println("8: Hiển thị album");
            System.out.println("9: Lưu file");
            System.out.println("10: Quản lý bài hát");
            System.out.println("11: Hiển thị toàn bộ bài hát trong album");
            System.out.println("12: Sửa tên bài hát trong album");
            System.out.println("0: Đăng xuất");
            choose = scanner.nextLine();
            checkout = validate.validate(choose, regexChoose) && choose.equals("0");
            switch (choose) {
                case "1":
                    addAlbum(albumController);
                    break;
                case "2":
                    editNameAlbum(albumController);
                    break;
                case "3":
                    deleteAlbum(albumController);
                    break;
                case "4":
                    searchAlbum(albumController);
                    break;
                case "5":
                    deleteSongOfAlbum(albumController);
                    break;
                case "6":
                    addSongOnAlbum(albumController);
                    break;
                case "7":
                    searchSongByName(albumController);
                    break;
                case "8":
                    showAllAlbum(albumController);
                    break;
                case "9":
                    saveData(albumController);
                    System.out.println("Save data sucessful");
                    break;
                case "10":
                    SongView.controllerSong();
                    break;
                case "11":
                    showAllSongOfAlbum(albumController);
                    break;
                case "12":
                    editNameSongOfAlbum(albumController);
                    break;
                case "0":
                    break;
            }
        } while (!checkout);
    }

    private static void showAllSongOfAlbum(AlbumController albumController) {
        albumController.showAllAlbum();
        Scanner scanner = new Scanner(System.in);
        String nameAlbum;
        System.out.println("input name of Album to show all Song");
        nameAlbum = scanner.nextLine();
        int indexAlbum = albumController.searchByNameAlbum(nameAlbum);
        if (indexAlbum == -1) {
            System.out.println("wrong name of Album!");
        } else {
            albumController.getAlbumList().get(indexAlbum).showAllSong();
        }
    }

    public static Album creatNewAlbum(AlbumController albumController) {
        Scanner scanner = new Scanner(System.in);
        String nameAlbum;
        String regexNameAlbum = "^\\b.*$";
        boolean checkData;
        do {
            System.out.println("input name Album");
            nameAlbum = scanner.nextLine();
            int indexAlbum = albumController.searchByNameAlbum(nameAlbum);//kiểm tra xem tên album đã tồn tại chưa?
            checkData = validate.validate(nameAlbum, regexNameAlbum) && indexAlbum == -1;
            if (!checkData)
                System.out.println("Album name be duplicate. Try again");
        } while (!checkData);
        return new Album(nameAlbum);
    }

    public static void addAlbum(AlbumController albumController) {
        Album newAlbum = creatNewAlbum(albumController);
        albumController.addAlbum(newAlbum);
    }

    public static void editNameAlbum(AlbumController albumController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input name album edit");
        String nameAlbum = scanner.nextLine();
        String newName;
        String regexNewName = "^\\b.*$";
        boolean checkData;
        int index = albumController.searchByNameAlbum(nameAlbum);
        if (index == -1) {
            System.out.println("not fond");
        } else {
            do {
                System.out.println("Input new name");
                newName = scanner.nextLine();
                checkData = validate.validate(newName, regexNewName) && !newName.equals(nameAlbum);
                if (!checkData) {
                    System.out.println("String is emty or duplicate old name");
                    System.out.println("Try again");
                }
            } while (!checkData);
            albumController.getAlbumList().get(index).setNameAlbum(newName);
            albumController.showAllAlbum();
        }
    }

    public static void deleteAlbum(AlbumController albumController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên bài hát để xoá");
        String name = scanner.nextLine();
        int index = albumController.searchByNameAlbum(name);
        if (index == -1) {
            System.out.println("Không có");
        } else {
            boolean checkData;
            String chose;
            do {
                System.out.println("1. Xoá");
                System.out.println("2. Next");
                chose = scanner.nextLine();
                checkData = chose.equals("1") || chose.equals("2");
            } while (!checkData);
            if (chose.equals("1")) {
                albumController.deleteAlbum(index);
                albumController.showAllAlbum();
            }
        }
    }

    public static int searchAlbum(AlbumController albumController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input name album to search");
        String nameAlbum = scanner.nextLine();
        return albumController.searchByNameAlbum(nameAlbum);
    }

    public static void deleteSongOfAlbum(AlbumController albumController) {
        albumController.showAllAlbum();
        Scanner scanner = new Scanner(System.in);
        String nameAlbum;
        String nameSong;
        int indexAlbum;
        int indexSong;
        System.out.println("Tên album muốn xoá bài hát");
        nameAlbum = scanner.nextLine();
        indexAlbum = albumController.searchByNameAlbum(nameAlbum);
        if (indexAlbum == -1) {
            System.out.println("Không tìm thấy");
        } else {
            albumController.getAlbumList().get(indexAlbum).showAllSong();
            System.out.println("Nhập tên bài hát cần xoá");
            nameSong = scanner.nextLine();
            indexSong = albumController.getAlbumList().get(indexAlbum).searchByNameSong(nameSong);
            if (indexSong == -1) {
                System.out.println("Không thấy");
            } else {
                String confirm;
                boolean checkData;
                do {
                    System.out.println("Are you sure to delete?");
                    System.out.println("input yes to delete");
                    System.out.println("input no to skip");
                    confirm = scanner.nextLine();
                    checkData = confirm.equals("yes") || confirm.equals("no");
                } while (!checkData);
                if (confirm.equals("yes")) {
                    albumController.deleteSong(indexSong, indexAlbum);
                    System.out.println("Delete sucessful");
                }
            }
        }
    }

    public static void editNameSongOfAlbum(AlbumController albumController) {
        albumController.showAllAlbum();
        Scanner scanner = new Scanner(System.in);
        String nameAlbum;
        String nameSong;
        int indexAlbum;
        int indexSong;
        System.out.println("Nhập tên album để sửa bài hát");
        nameAlbum = scanner.nextLine();
        indexAlbum = albumController.searchByNameAlbum(nameAlbum);
        if (indexAlbum == -1) {
            System.out.println("Không tìm thấy");
        } else {
            albumController.getAlbumList().get(indexAlbum).showAllSong();
            System.out.println("Tên bài hát muốn sửa");
            nameSong = scanner.nextLine();
            indexSong = albumController.getAlbumList().get(indexAlbum).searchByNameSong(nameSong);
            if (indexSong == -1) {
                System.out.println("Không có bài hát này");
            } else {
                String newName = scanner.nextLine();
                String regexNewName = "^\\b.*$";
                boolean checkData;
                do {
                    System.out.println("Input new name, not emty and not duplicate old name");
                    newName = scanner.nextLine();
                    checkData = newName.equals(nameSong) || !validate.validate(regexNewName, newName);
                    if (checkData) {
                        System.out.println("try again");
                    }
                } while (checkData);
                albumController.getAlbumList().get(indexAlbum).getListSong().get(indexSong).setNameSong(newName);
                System.out.println("edit name sucessful");
            }
        }
    }

    public static void addSongOnAlbum(AlbumController albumController) {
        Scanner scanner = new Scanner(System.in);
        SongView.showAllSong();
        System.out.println("chose a Song to add on your Album");
        String nameSong = scanner.nextLine();
        String nameAlbum;
        Song newSong = SongView.returSongInList(nameSong);
        if (newSong == null) {
            System.out.println("try again");
            addSongOnAlbum(albumController);
        } else {
            albumController.showAllAlbum();
            System.out.println("chose a Album to add this Song");
            nameAlbum = scanner.nextLine();
            int index = albumController.searchByNameAlbum(nameAlbum);
            if (index == -1) {
                System.out.println("try again");
                addSongOnAlbum(albumController);
            } else {
                System.out.println("add sucessful");
                albumController.addSongs(newSong, index);
            }
        }
    }

    public static void searchSongByName(AlbumController albumController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input name Song are you want to search");
        String nameSong = scanner.nextLine();
        for (Album album : albumController.getAlbumList()) {
            for (Song song : album.getListSong()) {
                if (song.getNameSong().equals(nameSong)) {
                    System.out.println(album.getNameAlbum() + song);
                    return;
                }
            }
        }
        System.out.println("not found :))");
    }

    public static void showAllAlbum(AlbumController albumController) {
        albumController.showAllAlbum();
    }

    public static void saveData(AlbumController albumController) {
        albumController.saveInfor();
    }
}
