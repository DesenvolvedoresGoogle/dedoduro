package ro.dedodu.dedoduro.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ro.dedodu.dedoduro.mobile.R;
import ro.dedodu.dedoduro.mobile.model.Category;

public class CategoryAdapter extends ArrayAdapter<Category> {

    private List<Category> categories;
    private Context context;
    private OnRowClickListener rowClickListener;

    public CategoryAdapter(Context context, List<Category> categories, OnRowClickListener rowClickListener) {
        super(context, R.layout.category_list_row, categories);
        this.categories = categories;
        this.context = context;
        this.rowClickListener = rowClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View row = inflater.inflate(R.layout.category_list_row, parent, false);

        final Category category = categories.get(position);

        ImageView icon = (ImageView) row.findViewById(R.id.icon);
        TextView label = (TextView) row.findViewById(R.id.label);

        icon.setImageResource(R.drawable.ic_launcher);
        label.setText(category.getName());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowClickListener.onClick(category);
            }
        });

        return row;
    }

    public interface OnRowClickListener {
        public void onClick(Category category);
    }
}
