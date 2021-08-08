package Controller;

import Model.Account;

public interface Obsever {
    int searchByAccount(Account account);

    void updateAccount(int index, AlbumController albumController);

    boolean addAccount(AlbumController albumController);

    boolean checkInstance(AlbumController albumController);
    boolean checkNameAccountIsIntance(String name);
}
