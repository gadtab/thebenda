package com.thebend.house;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

public class main extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new ClearGLSurfaceView(this);
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }

    private GLSurfaceView mGLView;
}

class ClearGLSurfaceView extends GLSurfaceView {
    public ClearGLSurfaceView(Context context) {
        super(context);
        mRenderer = new ClearRenderer();
        setRenderer(mRenderer);
    }

    public boolean onTouchEvent(final MotionEvent event) {
        queueEvent(new Runnable(){
            public void run() {
                mRenderer.setColor(event.getX() / getWidth(),
                        event.getY() / getHeight(), 1.0f);
            }});
            return true;
        }

        ClearRenderer mRenderer;
}

class ClearRenderer implements GLSurfaceView.Renderer {
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Do nothing special.
    	square = new Square();
    	//square1 = new Square();
    }

    public void onSurfaceChanged(GL10 gl, int w, int h) {
        gl.glViewport(0, 0, w, h);
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(mRed, mGreen, mBlue, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity();
//        gl.glTranslatef(0f, 0f, -2f);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -4f);
        square.draw(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glLoadIdentity();        
        gl.glTranslatef(-0.2f, -0.3f, -2f);
        gl.glRotatef(60, 1.0f, 0.0f, 0.0f);
        square.draw(gl);
        gl.glPopMatrix();
//        gl.glColor4x(1, 1, 1, 0);
//        square1.draw(gl);
//        
//        gl.glPushMatrix();
//        gl.glLoadIdentity();
//        gl.glRotatef(90, 0.3f, 0.5f, 0);
//        gl.glTranslatef(-0.4f, 0f, -10f);
//        
//        
//        square.draw(gl);
//        gl.glPopMatrix();
//        
//        gl.glPushMatrix();
//        gl.glLoadIdentity();
//        gl.glRotatef(90, 0.3f, 0.5f, 0);
//        gl.glTranslatef(0.4f, 0f, -20f);
//        
//        square.draw(gl);
//        gl.glPopMatrix();
//        
//        gl.glPushMatrix();
//        gl.glLoadIdentity();
//        gl.glRotatef(90, 0.3f, 0.5f, 0);
//        gl.glTranslatef(0f, 0.5f, -20f);
//        
//        square.draw(gl);
//        gl.glPopMatrix();
               
    }

    public void setColor(float r, float g, float b) {
        mRed = r;
        mGreen = g;
        mBlue = b;
    }

    private Square square;
    //private Square square1;
    private float mRed;
    private float mGreen;
    private float mBlue;

}