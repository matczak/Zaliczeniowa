package pl.matczakonline.zaliczeniowa.common.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by michnik on 03.01.2017.
 */

@DatabaseTable(tableName = "todo")
public class Todo {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private int priority;

    @DatabaseField(columnName = "userID")
    private int userID;

    public Todo() {

    }

    public Todo(int userID, String title, String description, int priority) {
        super();
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return title;
    }
}
