package snaige.smartfridge;

import javax.persistence.*;

/**
 * Created by Metr_yumora on 01.05.2017.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String name;

    @Column
    String hash;

    public User() {
    }

    public User(String name, String pass) {
        this.name = name;
        try {
            this.hash = PasswordStorage.createHash(pass);
        } catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace();
        }
    }

    public Boolean verifyPassword(char[] password) {
        try {
            return PasswordStorage.verifyPassword(password, this.getHash());
        } catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace();
        } catch (PasswordStorage.InvalidHashException hashE) {
            hashE.printStackTrace();
        }
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return getName();
    }
}
