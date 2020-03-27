package edu.upc.dsa.tracksfrontendandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditTrackActivity extends AppCompatActivity {

    private String id ="";
    private int position;
    private String track_title ="";
    private String singer ="";
    private String speed = "1x";
    public TextView IdTextView ;
    public TextView TrackNameTextView ;
    public TextView AuthorTextView ;
    public Button AddButton;
    public Button ModifyButton;
    private boolean aBooleanAddTrack= false;
    public EditTrackActivity() {
    }

    //Updates Edit TextView Fields with new values written
    public void updateEditFields()
    {
        AuthorTextView.setText(this.singer);
        TrackNameTextView.setText(this.track_title);
        IdTextView.setText(this.id);
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
        AddButton = this.findViewById(R.id.button3);
        ModifyButton = this.findViewById(R.id.button2);
        //Getting the Values passed from the Recycler View Adapter class
        Intent intent = getIntent();
        this.id = intent.getStringExtra("TRACK_ID");
        this.position = intent.getIntExtra("LIST_POSITION",0);
        this.track_title = intent.getStringExtra("TRACK_TITLE");
        this.singer = intent.getStringExtra("TRACK_SINGER");
        aBooleanAddTrack = intent.getBooleanExtra("ADD_TRACK",false);
        //IF ADD TRACK,THAN MODIFIED BUTTON INVISIBLE OTHERWISE ADD BUTTON INVISIBLE
        if(aBooleanAddTrack){ ModifyButton.setVisibility(View.INVISIBLE);}
        else{AddButton.setVisibility(View.INVISIBLE);}
        updateEditFields();
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
        Intent resultIntent = new Intent();
        setResult(RESULT_CANCELED,resultIntent);
        finish();
    }
    public void onButtonClickModify(View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("RETRIEVE_TRACK_ID",EditTrackActivity.this.IdTextView.getText().toString());
        resultIntent.putExtra("RETRIEVE_TRACK_SINGER",EditTrackActivity.this.AuthorTextView.getText().toString());
        resultIntent.putExtra("RETRIEVE_TRACK_TITLE",EditTrackActivity.this.TrackNameTextView.getText().toString());
        resultIntent.putExtra("RETRIEVE_LIST_POSITION",EditTrackActivity.this.position);
        resultIntent.putExtra("RETRIEVE_ADD_TRACK",EditTrackActivity.this.aBooleanAddTrack);
        setResult(RESULT_OK,resultIntent);
        finish();
    }
    public void onButtonClickAdd(View view){
        //Add new Track
        Intent resultIntent = new Intent();
        resultIntent.putExtra("RETRIEVE_TRACK_ID",EditTrackActivity.this.IdTextView.getText().toString());
        resultIntent.putExtra("RETRIEVE_TRACK_SINGER",EditTrackActivity.this.AuthorTextView.getText().toString());
        resultIntent.putExtra("RETRIEVE_TRACK_TITLE",EditTrackActivity.this.TrackNameTextView.getText().toString());
        resultIntent.putExtra("RETRIEVE_LIST_POSITION",EditTrackActivity.this.position);
        resultIntent.putExtra("RETRIEVE_ADD_TRACK",EditTrackActivity.this.aBooleanAddTrack);
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}
