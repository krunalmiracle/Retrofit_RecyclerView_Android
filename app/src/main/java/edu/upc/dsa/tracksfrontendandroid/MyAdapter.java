package edu.upc.dsa.tracksfrontendandroid;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Repo> repoList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in our viewHolder
        public TextView txtRepoName;
        public TextView txtId;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtRepoName = v.findViewById(R.id.firstLine);
            txtId = v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, String item) {
        //values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        repoList.remove(position);
        notifyItemRemoved(position);
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Repo> myDataset)
    {
        repoList = myDataset;
    }

    // Create new views (invoked by the layout manager)

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from( parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, padding and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // final String name = values.get(position);
        holder.txtRepoName.setText(repoList.get(position).getFull_name());
        holder.txtRepoName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openTrackEditActivity(v,position);
                return true;
            }
        });

        holder.txtId.setText(repoList.get(position).getId());
    }
    private void openTrackEditActivity(View v,int position)
    {
        //New View to edit Current Track
        //Launches a new activity to edit the current view
        //View Already contains our Context of Main activity
        Context context = v.getContext();
        //TMP VARS
        //String tmp_id, tmp_full_name, tmp_name = ;
        //Creates a Edit Track Activity
        //EditTrackActivity editTrackActivity = new EditTrackActivity();
        //EDITS THE FIELD INSIDE THE EDIT TRACT ACTIVITY CLASS
        //editTrackActivity.mfillFields(repoList.get(position).getId().toString(),repoList.get(position).getFull_name().toString(),repoList.get(position).getName().toString());
        //CREATES A INTENT OF OUR EDIT TRACK ACTIVITY
        Intent intent = new Intent(context,EditTrackActivity.class);
        // Pass the data to our Activity as there exists no object instance of our EditTrackActivity class,
        // The only easy method to Pass data between activity is Intent,as singleton is not recommended
        intent.putExtra("TRACK_ID",repoList.get(position).getId());
        intent.putExtra("TRACK_SINGER",repoList.get(position).getName());
        intent.putExtra("TRACK_TITLE",repoList.get(position).getFull_name());
        //STARTS THE ACTIVITY OF OUR INTENT
        context.startActivity(intent);
        //sends a toast to the new activity saying it need to show the message
        new EditTrackActivity().ShowToast(context);
        //UPDATE THE TEXT FIELDS INSIDE THE NEW ACTIVITY

        //editTrackActivity.updateEditFields(context);
        // remove(repoList.indexOf(name));
    }
    @Override
    public int getItemCount() {
        return repoList.size();
    }
}