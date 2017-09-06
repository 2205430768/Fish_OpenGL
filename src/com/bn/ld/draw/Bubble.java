package com.bn.ld.draw;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Bubble {
	private FloatBuffer   mVertexBuffer;//�����������ݻ���
    private FloatBuffer   mTextureBuffer;//�����������ݻ�
    int vCount=0;//��������  

    public Bubble()
    {
    	
    	//�����������ݵĳ�ʼ��================begin============================
    	final float UNIT_SIZE=1.0f;    	
        vCount=6;//���������    	
        float vertices[]=new float[]//����������������
        {
        		-0.15f*UNIT_SIZE,0.15f*UNIT_SIZE,0,
            	-0.15f*UNIT_SIZE,-0.15f*UNIT_SIZE,0,
            	0.15f*UNIT_SIZE,0.15f*UNIT_SIZE,0,
            	-0.15f*UNIT_SIZE,-0.15f*UNIT_SIZE,0,
            	0.15f*UNIT_SIZE,-0.15f*UNIT_SIZE,0,
            	0.15f*UNIT_SIZE,0.15f*UNIT_SIZE,0,
        };
        //���������������ݻ���
        //vertices.length*4����Ϊһ�������ĸ��ֽ�
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�����ֽ�˳��
        mVertexBuffer = vbb.asFloatBuffer();//ת��Ϊint�ͻ���
        mVertexBuffer.put(vertices);//�򻺳����з��붥����������
        mVertexBuffer.position(0);//���û�������ʼλ��
        //�ر���ʾ�����ڲ�ͬƽ̨�ֽ�˳��ͬ���ݵ�Ԫ�����ֽڵ�һ��Ҫ����ByteBuffer
        //ת�����ؼ���Ҫͨ��ByteOrder����nativeOrder()�������п��ܻ������
        float textureCoors[]=new float[]//��������S��T����ֵ����
	    {
        		0,0,0,1,1,0,
        		0,1,1,1,1,0
	    };        
        //���������������ݻ���
        //textureCoors.length��4����Ϊһ��float�������ĸ��ֽ�
        ByteBuffer cbb = ByteBuffer.allocateDirect(textureCoors.length*4);
        cbb.order(ByteOrder.nativeOrder());//�����ֽ�˳��
        mTextureBuffer = cbb.asFloatBuffer();//ת��Ϊint�ͻ���
        mTextureBuffer.put(textureCoors);//�򻺳����з��붥����ɫ����
        mTextureBuffer.position(0);//���û�������ʼλ��
        //�ر���ʾ�����ڲ�ͬƽ̨�ֽ�˳��ͬ���ݵ�Ԫ�����ֽڵ�һ��Ҫ����ByteBuffer
        //ת�����ؼ���Ҫͨ��ByteOrder����nativeOrder()�������п��ܻ������
    }
    public void drawSelf(GL10 gl,int texld)
    {    	
        //����ʹ�ö�������
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//Ϊ����ָ��������������
        gl.glVertexPointer
        (
        		3,				//ÿ���������������Ϊ3  xyz 
        		GL10.GL_FLOAT,	//��������ֵ������Ϊ GL_FIXED
        		0, 				//����������������֮��ļ��
        		mVertexBuffer	//������������
        );
        //��������
        gl.glEnable(GL10.GL_TEXTURE_2D);   
        //����ʹ����������
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //Ϊ����ָ������uv��������
        gl.glTexCoordPointer
        (
        		2, 					//ÿ���������������������� S��T
        		GL10.GL_FLOAT, 		//��������
        		0, 					//����������������֮��ļ��
        		mTextureBuffer		//������������
        );
        //Ϊ���ʰ�ָ������ID����		
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texld);   
        //����===========end==================================================
        //����ͼ��
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, //�����η�ʽ���
        		0, 
        		vCount
        );
    }
}
