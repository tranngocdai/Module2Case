package Model;

import java.io.Serializable;

public class Account implements Serializable {
    private String nameUser;
    private String password;

    public Account(){}

    public Account withNameUser(String nameUser) {
        this.nameUser = nameUser;
        return this;
    }

    public Account withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account anotherAcc = (Account) obj;
            if (this.nameUser.equals(anotherAcc.nameUser)&&this.password.equals(anotherAcc.password))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user='" + nameUser + '\'' +
                ", pass='" + password + '\'' +
                '}';
    }

}
