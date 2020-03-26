package edu.upc.dsa.tracksfrontendandroid;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.ExtractEditText;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    //Repo List
    private List<Repo> repoList;
    //Intent Request ID
    public static final int TRACK_REQUEST = 1;
    // Adding Listener to call it from Main Activity
    private OnItemClickListener mListener;
    //Through this we get the click and the position to our main activity
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }
    //for When we call the OnClick Method from main
    public void SetOnItemClickListener(OnItemClickListener listener){
        mListener =listener ;
    }
    // Provide a reference to the views for each data item, Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in our viewHolder
        public TextView txtRepoName;
        public TextView txtId;
        public ImageView imageDelete;
        public View layout;

        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            layout = itemView;
            txtRepoName = itemView.findViewById(R.id.firstLine);
            txtId = itemView.findViewById(R.id.secondLine);
            imageDelete = itemView.findViewById(R.id.imageView);

            //Click Handler for the whole Item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                        public void onClick(View v){
                            if( listener !=null){
                                int position = getAdapterPosition();
                                if(position != RecyclerView.NO_POSITION){
                                    listener.onItemClick(position);
                                }
                            }
                        }
                });
            //Click handler for imageDelete to call from Main Activity
            imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if( listener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
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
        ViewHolder vh = new ViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // final String name = values.get(position);
        holder.txtId.setText(repoList.get(position).getId());
        holder.txtRepoName.setText(repoList.get(position).getFull_name());
    }
    @Override
    public int getItemCount() {
        return repoList.size();
    }
}