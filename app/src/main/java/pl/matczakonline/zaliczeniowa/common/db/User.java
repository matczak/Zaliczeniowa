package pl.matczakonline.zaliczeniowa.common.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by michnik on 05.01.2017.
 */

@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField(columnName = "userName")
    private String userName;

    @DatabaseField
    private String password;

    public User() {

    }

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
