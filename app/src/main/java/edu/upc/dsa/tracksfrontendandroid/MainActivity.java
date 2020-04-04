package edu.upc.dsa.tracksfrontendandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    //List<Repo> Repo_List;
    List<Track> Track_List;
    private RecyclerView recyclerView;
    //As we added new methods inside our custom Adapter, we need to create our own type of adapter
    private MyAdapter mAdapter;
    private static int MODIFY_TRACK = 1;
    private boolean aBooleanServedAlready =false;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implementing RecyclerView
        recyclerView = findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(false);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
    //Inserts Item in List
    public void insertItem(Track track){
        if(Track_List!=null) {
            Track_List.add(track);
        }
        else
        {
            Track_List = new ArrayList<>();
            Track_List.add(track);
        }
        int pos = Track_List.size()-1;
        mAdapter.notifyDataSetChanged();
    }
    //Removes Item from List
    public void removeItem(int position)
    {
        Track_List.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
    //Changes Item in List
    public void changeItem(int position,Track track){
        Track_List.set(position,track);
        mAdapter.notifyItemChanged(position);
    }
    //Add Track New Activity
    public void onButtonAddTracksClick(View view){
        if(aBooleanServedAlready) {
            int tmp_uselessPosition = -1;
            LaunchEditActivity(tmp_uselessPosition, true);
        }
        else {
            //Notify User Update List from Server
            NotifyUser("Get tracks from server first!");
        }
    }
    //User Notifier Handler using Toast
    private void NotifyUser(String MSG){
        Toast toast = Toast.makeText(MainActivity.this,MSG,Toast.LENGTH_SHORT);
        toast.show();
    }
    //Launch New Activity to Edit-Add Track
    private void LaunchEditActivity(int position,boolean boolAddTrack){

        //New View to edit Current Track
        //Launches a new activity to edit the current selected Track Item
        //Creates a Edit Track Activity

        //TO LAUNCH A NEW ACTIVITY WE CREATE A INTENT OF OUR EDIT TRACK ACTIVITY
        Intent intent = new Intent(MainActivity.this ,EditTrackActivity.class);
        // Pass the data to our Activity as there exists no object instance of our EditTrackActivity class,
        // The only easy method to Pass data between activity is Intent,as singleton is not recommended
        if(boolAddTrack) { //Add a new Track
            intent.putExtra("TRACK_ID", "");
            intent.putExtra("TRACK_SINGER", "");
            intent.putExtra("TRACK_TITLE", "");
            intent.putExtra("ADD_TRACK", true);
            intent.putExtra("LIST_POSITION", position);
            //STARTS THE ACTIVITY FOR RESULT INTENT TO GET THE NEW VALUES
        }
        else{
            intent.putExtra("TRACK_ID", Track_List.get(position).getId());
            intent.putExtra("TRACK_SINGER", Track_List.get(position).getSinger());
            intent.putExtra("TRACK_TITLE", Track_List.get(position).getTitle());
            intent.putExtra("ADD_TRACK", false);
            intent.putExtra("LIST_POSITION", position);
            //STARTS THE ACTIVITY FOR RESULT INTENT TO GET THE NEW VALUES
        }
        startActivityForResult(intent,MODIFY_TRACK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                String tmp_id = data.getStringExtra("RETRIEVE_TRACK_ID");
                String tmp_singer = data.getStringExtra("RETRIEVE_TRACK_SINGER");
                String tmp_track_title = data.getStringExtra("RETRIEVE_TRACK_TITLE");
                int tmp_position = data.getIntExtra("RETRIEVE_LIST_POSITION",-1);
                boolean aBoolTmp_addTrack = data.getBooleanExtra("RETRIEVE_ADD_TRACK",false);
                Track track_tmp = new Track();
                track_tmp.setSinger(tmp_singer);
                track_tmp.setId(tmp_id);
                track_tmp.setTitle(tmp_track_title);
                if(aBoolTmp_addTrack){
                    //Repo repo_tmp = new Repo();
                    insertItem(track_tmp);
                }
                else{
                    changeItem(tmp_position,track_tmp);
                }
            }
        }

    }

    //Gets the List from GitHub
    public void onButtonGetTracksClick(View view) {
        //Retrofit Implementation on Button Press
        //Adding Interceptor
        try {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Attaching Interceptor to a client
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();

        // Running Retrofit to get result from Github tracks service Interface
            //Remember when using Local host on windows the IP is 10.0.2.2 for Android
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        TracksService tracksservice = retrofit.create(TracksService.class);
        Call<List<Track>> tracks = tracksservice.listTracks("tracks");
        /* Android Doesn't allow synchronous execution of Http Request and so we must put it in queue*/
            tracks.enqueue(new Callback<List<Track>>() {
                @Override
                public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {

                    //Retrieve the result containing in the body
                    if (!response.body().isEmpty()) {
                        // non empty response, Mapping Json via Gson...
                        NotifyUser("Server Response Ok");
                        MainActivity.this.Track_List = response.body();
                        buildRecyclerView();
                        //Server has served client so we can now edit the list of Tracks/Repo
                        aBooleanServedAlready = true;
                    } else {
                        // empty response...
                        NotifyUser("Request Failed!");
                    }

                }

                @Override
                public void onFailure(Call<List<Track>> call, Throwable t) {
                    NotifyUser("Error,could not retrieve data!");
                }
            });
       }
        catch(Exception e){
            NotifyUser("Exception: " + e.toString());
       }
    }
    //Builds the RecyclerView
    private void buildRecyclerView(){
        mAdapter = new MyAdapter(Track_List);
        recyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //NEED TO IMPLEMENT EDIT TRACK ACTIVITY
                LaunchEditActivity(position,false);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }
}
