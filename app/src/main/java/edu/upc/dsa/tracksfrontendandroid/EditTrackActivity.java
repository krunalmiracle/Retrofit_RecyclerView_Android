package edu.upc.dsa.tracksfrontendandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditTrackActivity extends AppCompatActivity {

    private String id ="";
    private String track_title ="";
    private String singer ="";
    private String speed = "1x";
    public TextView IdTextView ;
    public TextView TrackNameTextView ;
    public TextView AuthorTextView ;
    private static EditTrackActivity _instance;
    public EditTrackActivity() {
    }

    //Updates Edit TextView Fields with new values written
    public void updateEditFields()
    {
        AuthorTextView.setText(singer);
        TrackNameTextView.setText(track_title);
        IdTextView.setText(id);
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_track_layout);
        IdTextView = this.findViewById(R.id.editText6);
        TrackNameTextView = this.findViewById(R.id.editText3);
        AuthorTextView = this.findViewById(R.id.editText4);

        //Getting the Values passed from the Recycler View Adapter class
        Intent intent = getIntent();
        this.id = intent.getStringExtra("TRACK_ID");
        this.track_title = intent.getStringExtra("TRACK_TITLE");
        this.singer = intent.getStringExtra("TRACK_SINGER");
        updateEditFields();
    }

    public void mfillFields(String Id, String Title, String Singer, String Speed)
    {
        this.id = Id; this.track_title = Title; this.singer = Singer ; this.speed = Speed;
    }
    public void mfillFields(String Id, String Title, String Singer)
    {
        this.id = Id; this.track_title = Title; this.singer = Singer ;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTrack_title() {
        return track_title;
    }

    public void setTrack_title(String track_title) {
        this.track_title = track_title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Track [id="+id+", title=" + track_title + ", singer=" + singer +", speed="+ speed +"]";
    }
    public void onButtonClickCancel(View view) {
        Toast toast= Toast.makeText(EditTrackActivity.this,"Button Pressed Cancel inside Edit Track!",Toast.LENGTH_SHORT);
        toast.show();
        this.finish();
    }
    public void onButtonClickDelete(View view) {
        Toast toast= Toast.makeText(EditTrackActivity.this,"Button Pressed Delete inside Edit Track!",Toast.LENGTH_SHORT);
        toast.show();
    }
    public void onButtonClickModify(View view) {
        Toast toast= Toast.makeText(EditTrackActivity.this,"Button Pressed Modify inside Edit Track!",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void ShowToast(Context context){
        String Txt = "Id:"+id+",Singer="+singer+",speed="+speed;
        Toast toast= Toast.makeText(context,Txt,Toast.LENGTH_SHORT);
        toast.show();
    }
}
