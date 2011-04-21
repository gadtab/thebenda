package com.thebend.house;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
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
                mRenderer.setColor(event.getX() / getWidth(),event.getY() / getHeight(), 1.0f);
            }});
            return true;
        }

        ClearRenderer mRenderer;
}

class ClearRenderer implements GLSurfaceView.Renderer {
	
	public ClearRenderer () {
		square = new Square();
	}
	
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    	
    	// Set the background to black (rgba).
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
		// Enable smooth shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		// Enable depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
    	//square = new Square();
    }

    public void onSurfaceChanged(GL10 gl, int w, int h) {
        gl.glViewport(0, 0, w, h);
        
        // Select the projection matrix.
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix.
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window.
		GLU.gluPerspective(gl, 45.0f, (float)w/(float)h, 0.1f, 100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(mRed, mGreen, mBlue, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -12f);
        
        gl.glRotatef((float) rAngle, 1, 0, 0);
		gl.glRotatef((float) rAngle, 0, 1, 0);
		
		square.draw(gl);
        
//        //Back Square...
//        gl.glPushMatrix();
//        gl.glLoadIdentity();
//        gl.glTranslatef(0, 0, -2.1f);
////        gl.glRotatef((float) (90 + rAngle), 1, 1, 1);
//        square.draw(gl);
//        gl.glPopMatrix();
//        
//        
//        //Front Square...
//        gl.glPushMatrix();
//        gl.glLoadIdentity();
//        gl.glTranslatef(0, 0, -1.9f);
////        gl.glRotatef((float) (90 + rAngle), 1, 1, 1);
//        square.draw(gl);
//        gl.glPopMatrix();
//        
//        
//        //Bottom Square...        
//        gl.glPushMatrix();
//        gl.glLoadIdentity();        
//        gl.glTranslatef(0, -0.1f, -2f);
//        gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
////        gl.glRotatef((float) (90 + rAngle), 1, 1, 1);
//        square.draw(gl);
//        gl.glPopMatrix();
//
//        //Ceiling Square...
//        gl.glPushMatrix();
//        gl.glLoadIdentity();        
//        gl.glTranslatef(0, 0.1f, -3f);
//        gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
////        gl.glRotatef((float) (90 + rAngle), 0, 0, 1);
//        square.draw(gl);
//        gl.glPopMatrix();
//        
//        
//        //Left Square...        
//        gl.glPushMatrix();
//        gl.glLoadIdentity();        
//        gl.glTranslatef(-0.3f, 0, -3f);
//        gl.glRotatef(90, 0f, 1.0f, 0.0f);
////        gl.glRotatef((float) (90 + rAngle), 0, 0, 1);
//        square.draw(gl);
//        gl.glPopMatrix();
//
//        //Right Square...
//        gl.glPushMatrix();
//        gl.glLoadIdentity();        
//        gl.glTranslatef(0.3f, 0, -3f);
//        gl.glRotatef(90, 0f, -1.0f, 0.0f);
////        gl.glRotatef((float) (90 + rAngle), 0, 0, 1);
//        square.draw(gl);
//        gl.glPopMatrix();
//        
        //square.draw(gl);
        
        rAngle+=1;               
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
    
    private float mAngle; // this will change in the glTranslatef
    private double rAngle;

}