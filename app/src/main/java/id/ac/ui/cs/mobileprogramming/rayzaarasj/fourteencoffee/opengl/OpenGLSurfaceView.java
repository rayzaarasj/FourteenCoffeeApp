package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class OpenGLSurfaceView extends GLSurfaceView {
    public OpenGLSurfaceView(Context context) {
        super(context);
        init();
    }

    public OpenGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setRenderer(new OpenGLRenderer());
    }
}
