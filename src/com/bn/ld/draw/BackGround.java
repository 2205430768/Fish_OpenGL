package com.bn.ld.draw;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

import com.bn.ld.Constant.Constant;

public class BackGround {
	private FloatBuffer   mVertexBuffer;//顶点坐标数据缓冲
    private FloatBuffer   mTextureBuffer;//顶点纹理数据缓!
    int vCount;//顶点数量  
    public BackGround()
    {
    	//顶点坐标数据的初始化================begin============================
        vCount=12;//顶点的数量    	
        float vertices[]=new float[]//顶点坐标数据数组
        {
            	-23*Constant.SCREEN_SCALEX,20*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,
            	-23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,
            	23*Constant.SCREEN_SCALEX,20*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,
            	-23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,
            	23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,
            	23*Constant.SCREEN_SCALEX,20*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,
            	
            	-23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,
            	-23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,30*Constant.SCREEN_SCALEZ,
            	23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,
            	-23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,30*Constant.SCREEN_SCALEZ,
            	23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,30*Constant.SCREEN_SCALEZ,
            	23*Constant.SCREEN_SCALEX,-10*Constant.SCREEN_SCALEY,-30*Constant.SCREEN_SCALEZ,        	
        };
        //创建顶点坐标数据缓冲
        //vertices.length*4是因为一个整数四个字节
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
        mVertexBuffer = vbb.asFloatBuffer();//转换为int型缓冲
        mVertexBuffer.put(vertices);//向缓冲区中放入顶点坐标数据
        mVertexBuffer.position(0);//设置缓冲区起始位置
        //特别提示：由于不同平台字节顺序不同数据单元不是字节的一定要经过ByteBuffer
        //转换，关键是要通过ByteOrder设置nativeOrder()，否则有可能会出问题
        //顶点纹理数据的初始化================begin============================
        float textureCoors[]=new float[]//顶点纹理S、T坐标值数组
	    {
        		0,0,0,0.85f,1,0,
        		0,0.85f,1,0.85f,1,0,
        		0,0.9f,0,1,1,0.9f,
        		0,1,1,1,1,0.9f
	    };        
        //创建顶点纹理数据缓冲
        //textureCoors.length×4是因为一个float型整数四个字节
        ByteBuffer cbb = ByteBuffer.allocateDirect(textureCoors.length*4);
        cbb.order(ByteOrder.nativeOrder());//设置字节顺序
        mTextureBuffer = cbb.asFloatBuffer();//转换为int型缓冲
        mTextureBuffer.put(textureCoors);//向缓冲区中放入顶点着色数据
        mTextureBuffer.position(0);//设置缓冲区起始位置
        //特别提示：由于不同平台字节顺序不同数据单元不是字节的一定要经过ByteBuffer
        //转换，关键是要通过ByteOrder设置nativeOrder()，否则有可能会出问题
        //顶点纹理数据的初始化================end============================
    }
    public void drawSelf(GL10 gl,int texld)
    {    	
        //允许使用顶点数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//为画笔指定顶点坐标数据
        gl.glVertexPointer
        (
        		3,				//每个顶点的坐标数量为3  xyz 
        		GL10.GL_FLOAT,	//顶点坐标值的类型为 GL_FIXED
        		0, 				//连续顶点坐标数据之间的间隔
        		mVertexBuffer	//顶点坐标数据
        );
        //开启纹理
        gl.glEnable(GL10.GL_TEXTURE_2D);   
        //允许使用纹理数组
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //为画笔指定纹理uv坐标数据
        gl.glTexCoordPointer
        (
        		2, 					//每个顶点两个纹理坐标数据 S、T
        		GL10.GL_FLOAT, 		//数据类型
        		0, 					//连续纹理坐标数据之间的间隔
        		mTextureBuffer		//纹理坐标数据
        );
        //为画笔绑定指定名称ID纹理		
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texld);   
        //绘制图形
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, 
        		0, 
        		vCount
        );
    }
}
