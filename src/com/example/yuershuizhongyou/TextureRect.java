package com.example.yuershuizhongyou;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class TextureRect {
private  FloatBuffer vertexBuffer;
private  FloatBuffer  textureBuffer;
public float x,y,z;
int vCount=0;
float size;
float xAngle=0,yAngle=0,zAngle=0;
int textureId;
public TextureRect(float scale,float a,float b,int textureId){
	this.textureId=textureId;
	size=scale*Constant.UNIT_SIZE;
	a*=size;
	b*=size;
	float xOffset=a/2;
	float yOffset=b/2;
	vCount=6;
	float[]  vertices=new float[]{
			-xOffset,-yOffset,0,
			xOffset,yOffset,0,
			-xOffset,yOffset,0,
			
			
			-xOffset,-yOffset,0,
			xOffset,-yOffset,0,
			xOffset,yOffset,0
	};
	ByteBuffer vbb=ByteBuffer.allocateDirect(vertices.length*4);
	vbb.order(ByteOrder.nativeOrder());
	vertexBuffer=vbb.asFloatBuffer();
	vertexBuffer.put(vertices);
	vertexBuffer.position(0);
	 float[]  textures=new float[]{
			0,1f,
			1f,0,
			0,0,
			0,1,
			1,1,
			1,0
			
	};
	
	ByteBuffer cbb=ByteBuffer.allocateDirect(textures.length*4);
	cbb.order(ByteOrder.nativeOrder());
	textureBuffer=cbb.asFloatBuffer();
	textureBuffer.put(textures);
	textureBuffer.position(0);
	
}
	

public void drawSelf(GL10 gl){
	
	gl.glPushMatrix();
	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	gl.glTranslatef(x, y, z);
	gl.glRotatef(xAngle, 1, 0, 0);
	gl.glRotatef(yAngle, 0, 1, 0);
	gl.glRotatef(zAngle, 0, 0, 1);
	
	gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
	gl.glEnable(GL10.GL_TEXTURE_2D);
	gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
	gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
	//»æÖÆ
	gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vCount);
	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	gl.glPopMatrix();
	
	
	
}












	
	
}
