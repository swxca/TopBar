package com.zhangtao.topbar;



import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class DemoActivity extends Activity {

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        topBar= (TopBar) findViewById(R.id.topbar);

        topBar.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(getApplicationContext(),"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void midClick() {
                Toast.makeText(getApplicationContext(),"mid",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void rightClick() {
                Toast.makeText(getApplicationContext(),"right",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
