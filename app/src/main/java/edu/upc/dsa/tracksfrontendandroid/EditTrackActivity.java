package edu.upc.dsa.tracksfrontendandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EditTrackActivity extends AppCompatActivity {
    private String id ="";
    private String title="";
    private String singer ="";
    private String speed = "1x";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_track_layout);

    }
    public void CompleteFieldsAndSaveParameters(String Id, String Title, String Singer, String Speed)
    {
        this.id = Id; this.title = Title; this.singer = Singer ; this.speed = Speed;
    }
    public void CompleteFieldsAndSaveParameters(String Id, String Title, String Singer)
    {
        this.id = Id; this.title = Title; this.singer = Singer ;
    }
    public String getid() {
        return this.id;
    }

    public void setid(String id) {
        this.id=id;
    }


    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getsinger() {
        return singer;
    }

    public void setsinger(String singer) {
        this.singer = singer;
    }

    public String getspeed() {  return this.speed;   }

    public void setspeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Track [id="+id+", title=" + title + ", singer=" + singer +", speed="+ speed +"]";
    }
    public void onButtonClickCancel(View view) {
        Toast toast= Toast.makeText(EditTrackActivity.this,"Button Pressed insideEdit Track!",Toast.LENGTH_SHORT);
        toast.show();
    }
    public void onButtonClickDelete(View view) {
        Toast toast= Toast.makeText(EditTrackActivity.this,"Button Pressed insideEdit Track!",Toast.LENGTH_SHORT);
        toast.show();
    }
    public void onButtonClickModify(View view) {
        Toast toast= Toast.makeText(EditTrackActivity.this,"Button Pressed insideEdit Track!",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void ShowToast(Context context){
        String Txt = "Id:"+id+",Singer="+singer+",speed="+speed;
        Toast toast= Toast.makeText(context,Txt,Toast.LENGTH_SHORT);
        toast.show();
    }
}
