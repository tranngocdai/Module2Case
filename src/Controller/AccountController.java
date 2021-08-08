package Controller;

import Model.Account;
import Storage.FileController;
import Storage.IFileController;

import java.io.Serializable;
import java.util.List;

public class AccountController implements Obsever, Serializable {
    private static AccountController instance;
    public static final String accPath = "account.txt";
    public static final IFileController<AlbumController> fileController = FileController.getIntance();
    private List<AlbumController> listAccount;

    private AccountController() {
    }

    public static AccountController getInstance() {
        if (instance == null) {
            instance = new AccountController();
            instance.listAccount = fileController.readFile(accPath);
        }
        return instance;
    }

    public List<AlbumController> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<AlbumController> listAccount) {
        this.listAccount = listAccount;
    }

    public void loginAdmin(){

    }

    @Override
    public int searchByAccount(Account account) {
        for (int i = 0; i < listAccount.size(); i++) {
            if (account.equals(listAccount.get(i).getAccount())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void updateAccount(int index, AlbumController albumController) {
        listAccount.remove(index);
        listAccount.add(index, albumController);
        fileController.writeFile(listAccount, accPath);
    }

    @Override
    public boolean addAccount(AlbumController albumController) {
        if (!checkInstance(albumController)) {
            listAccount.add(albumController);
            fileController.writeFile(listAccount, accPath);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkInstance(AlbumController albumController) {
        return listAccount.contains(albumController);
    }

    @Override
    public boolean checkNameAccountIsIntance(String name) {
        for (int i = 0; i < listAccount.size(); i++) {
            if (name.equals(listAccount.get(i).getAccount().getNameUser())) {
                return true;
            }
        }
        return false;
    }
}
