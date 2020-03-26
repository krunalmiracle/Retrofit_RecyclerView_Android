package edu.upc.dsa.tracksfrontendandroid;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, padding and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position);
        final int pos = holder.getLayoutPosition();
        holder.txtHeader.setText(name);
        holder.txtHeader.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //New View to edit Current Track
                //Launches a new activity to edit the current view
                Context context = v.getContext(); //View Already contains our Context of Main activity
                EditTrackActivity editTrackActivity = new EditTrackActivity();
                editTrackActivity.CompleteFieldsAndSaveParameters("123","Yellow Mellow","Kruskechi");
                Intent intent = new Intent(context,editTrackActivity.getClass() );
                context.startActivity(intent);
                editTrackActivity.ShowToast(context);
                remove(values.indexOf(name));
                return true;
            }
        });

        holder.txtFooter.setText(new StringBuilder().append("Footer: ").append(name).toString());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}