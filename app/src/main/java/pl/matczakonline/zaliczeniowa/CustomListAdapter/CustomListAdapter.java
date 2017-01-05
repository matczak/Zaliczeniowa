package pl.matczakonline.zaliczeniowa.CustomListAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.matczakonline.zaliczeniowa.R;
import pl.matczakonline.zaliczeniowa.common.db.Todo;

/**
 * Created by michnik on 04.01.2017.
 */

public class CustomListAdapter extends ArrayAdapter<Todo> {

    private final List<Todo> todos;
    private final Context context;


    public CustomListAdapter(Context context, int resource, List<Todo> items) {
        super(context, resource, items);
        this.todos = items;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Todo todo = todos.get(position);
        ViewHolder holder = null;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        holder = new ViewHolder();

        switch (todo.getPriority()) {
            case 0:
                convertView = inflater.inflate(R.layout.list_view_0, parent, false);
                holder.textView = (TextView) convertView.findViewById(R.id.text);
                break;
            case 1:
                convertView = inflater.inflate(R.layout.list_view_1, parent, false);
                holder.textView = (TextView) convertView.findViewById(R.id.text);
                break;
            case 2:
            default:
                convertView = inflater.inflate(R.layout.list_view_2, parent, false);
                holder.textView = (TextView) convertView.findViewById(R.id.text);
                break;
        }

        convertView.setTag(holder);
        holder.textView.setText(todo.toString());

        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
    }
}
