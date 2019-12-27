package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.opengl.OpenGLSurfaceView;

public class SplashScreenActivity extends AppCompatActivity {

    private OpenGLSurfaceView openGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalsh_screen_activity);
        openGLSurfaceView = (OpenGLSurfaceView) findViewById(R.id.open_gl_view);
        final SplashScreenActivity activity = this;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent historyIntent = new Intent(activity, HomeActivity.class);
                startActivity(historyIntent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        openGLSurfaceView.onPause();
    }
}
