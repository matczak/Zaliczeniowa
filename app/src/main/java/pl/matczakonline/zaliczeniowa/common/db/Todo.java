package pl.matczakonline.zaliczeniowa.common.db;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by michnik on 03.01.2017.
 */

public class Todo {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String title;

    @DatabaseField
    String description;

    @DatabaseField
    int priority;

    public Todo() {

    }

    public Todo(String title, String description, int priority) {
        super();
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Todo: [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority;
    }
}
