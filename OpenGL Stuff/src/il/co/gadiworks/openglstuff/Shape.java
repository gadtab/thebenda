package il.co.gadiworks.openglstuff;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Shape {
	private float vertices[] = {
		0, 0, 0,
		3, 0, 0,
		0, 3, 0,
		0, 0, 3
	};
	
	private short[] indices = {0, 1, 0, 2, 0, 3};
	
	// Our vertex buffer.
	private FloatBuffer vertexBuffer;

	// Our index buffer.
	private ShortBuffer indexBuffer;
	
	// Our color buffer.
	private FloatBuffer colorBuffer;
	
	// The Colors
	private float[] colors = {
            0, 0, 0, 0.5f, // vertex 0 
            1, 0, 0, 1, // vertex 1 
            0, 1, 0, 1, // vertex 2 
            0, 0, 1, 1  // vertex 3 
    };
	
	public Shape() {
		// a float is 4 bytes, therefore we multiply the number if
		// vertices with 4.
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		// short is 2 bytes, therefore we multiply the number if
		// vertices with 2.
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		
		// float has 4 bytes, colors (RGBA) * 4 bytes
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
	}
	
	public void draw(GL10 gl) {
		// Counter-clockwize winding.
//		gl.glFrontFace(GL10.GL_CCW);
		
		// Enable face culling
//		gl.glEnable(GL10.GL_CULL_FACE);
		
		// What face to remove woth face culling.
//		gl.glCullFace(GL10.GL_BACK);
		
		// Enable the vertices buffer for writing and to be used during rendering
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		// Enable the color array buffer to be used during rendering.
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY); 
		// Point out the where the color buffer is.
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer); 
		
		gl.glDrawElements(GL10.GL_LINES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		
		// Disable the vertices buffer.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// Disable the color buffer.
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		// Disable face culling.
//		gl.glDisable(GL10.GL_CULL_FACE);
	}
}
