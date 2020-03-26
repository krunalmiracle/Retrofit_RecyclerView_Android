package edu.upc.dsa.tracksfrontendandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EditTrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_track_layout);

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
}
