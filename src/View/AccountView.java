package View;

import Controller.AccountController;
import Controller.AlbumController;
import Model.Account;

import java.util.List;
import java.util.Scanner;

public class AccountView  {
    public static AccountController accountController = AccountController.getInstance();
    public static Validate validate = Validate.getInstance();

    public static void main(String[] args) {
        String chooseLogin;
        String regexChoseLogin = "^[1-3]+$";
        boolean checkoutLogin;
        Scanner scannerLogin = new Scanner(System.in);
        do {
            System.out.println("Menu:");
            System.out.println("1: Đăng nhập");
            System.out.println("2: Đăng xuất");
            System.out.println("4: Thoát");
            chooseLogin = scannerLogin.nextLine();
            checkoutLogin = validate.validate(chooseLogin, regexChoseLogin) && chooseLogin.equals("3");
            switch (chooseLogin) {
                case "1":
                    int index = login();
                    if (index == -1) {
                        System.out.println("Nhập sai tài khoản hoặc mật khẩu");
                    } else {
                        AlbumController albumController = accountController.getListAccount().get(index);
                        AlbumView.controller(albumController);
                    }
                    break;
                case "2":
                    if (signIn()) {
                        System.out.println("Đăng kí thành công.");
                    } else {
                        System.out.println("Tài khoản đã tồn tại, mời đăng ký lại");
                    }
                    break;
                case 3:

                    break;
                case "4":
                    System.exit(0);
                    break;
            }
        } while (!checkoutLogin);
        System.out.println("abc");
    }

    private static int login() {
        accountController = AccountController.getInstance();
        String user;
        String pass;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tài khoản:");
        user = scanner.nextLine();
        System.out.println("Mật khẩu");
        pass = scanner.nextLine();
        List<AlbumController> listAccount = accountController.getListAccount();
        for (int i = 0; i < listAccount.size(); i++) {
            boolean checkUser = listAccount.get(i).getAccount().getNameUser().equals(user);
            boolean checkPass = listAccount.get(i).getAccount().getPassword().equals(pass);
            if (checkUser && checkPass) {
                return i;
            }
        }
        return -1;
    }

    private static boolean signIn() {
        Scanner scanner = new Scanner(System.in);
        String nameUser;
        String password;
        String regexUser = "^[^\\s]{8,12}$";
        String regexPass = "^[0-9]{8,12}$";
        boolean checkData;
        do {
            System.out.println("UserName");
            nameUser = scanner.nextLine();
            System.out.println("Password");
            password = scanner.nextLine();
            checkData = validate.validate(nameUser, regexUser) && validate.validate(password, regexPass);
        } while (!checkData);
        Account account = new Account().withNameUser(nameUser).withPassword(password);
        boolean isIntance = accountController.checkNameAccountIsIntance(nameUser);//kiểm tra xem tài khoản có bị trùng tên ko?
        if (!isIntance) {
            AlbumController albumController = new AlbumController(account);
            accountController.addAccount(albumController);
            accountController = AccountController.getInstance();
            return true;
        } else {
            return false;
        }
    }
    private static int loginWithAdmin
}
