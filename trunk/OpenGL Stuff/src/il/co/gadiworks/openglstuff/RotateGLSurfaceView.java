package il.co.gadiworks.openglstuff;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class RotateGLSurfaceView extends GLSurfaceView {
	private OpenGLRenderer2 renderer;
	
	public RotateGLSurfaceView(Context context) {
		super(context);
		renderer = new OpenGLRenderer2(context);
		setRenderer(renderer);
	}

	public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable(){
            public void run() {
                renderer.rx = event.getX();
                renderer.ry = event.getY();
            }});
            return true;
        }
}
