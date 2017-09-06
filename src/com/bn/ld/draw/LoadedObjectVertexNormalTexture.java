package com.bn.ld.draw;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
//���غ�����塪��Я�����㡢��������������Ϣ
public class LoadedObjectVertexNormalTexture 
{
	private FloatBuffer   mVertexBuffer;//�����������ݻ���
	private FloatBuffer   mTexBuffer;//�����������ݻ���
    int vCount=0;  //��������

    public LoadedObjectVertexNormalTexture(float[] vertices,float[] normals,float texCoors[]) 
    {

    	//�����������ݵĳ�ʼ��================begin============================
        vCount=vertices.length/3;    
        //���������������ݻ���
        //vertices.length*4����Ϊһ�������ĸ��ֽ�
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�����ֽ�˳��
        mVertexBuffer = vbb.asFloatBuffer();//ת��ΪFloat�ͻ���
        mVertexBuffer.put(vertices);//�򻺳����з��붥����������
        mVertexBuffer.position(0);//���û�������ʼλ��
        //�ر���ʾ�����ڲ�ͬƽ̨�ֽ�˳��ͬ���ݵ�Ԫ�����ֽڵ�һ��Ҫ����ByteBuffer
        //ת�����ؼ���Ҫͨ��ByteOrder����nativeOrder()�������п��ܻ������
        //�����������ݵĳ�ʼ��================end============================
        //�������껺���ʼ��
        ByteBuffer vbt = ByteBuffer.allocateDirect(texCoors.length*4);
        vbt.order(ByteOrder.nativeOrder());//�����ֽ�˳��
        mTexBuffer = vbt.asFloatBuffer();//ת��ΪFloat�ͻ���
        mTexBuffer.put(texCoors);//�򻺳����з��붥����������
        mTexBuffer.position(0);//���û�������ʼλ�� 
    }
    public void drawSelf(GL10 gl,int texId)
    {        
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//���ö�����������     
        gl.glEnable(GL10.GL_TEXTURE_2D);    //��������      
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);//����ʹ������ST���껺��
		//Ϊ����ָ��������������
        gl.glVertexPointer
        (
        		3,				//ÿ���������������Ϊ3  xyz 
        		GL10.GL_FLOAT,	//��������ֵ������Ϊ GL_FIXED
        		0, 				//����������������֮��ļ��
        		mVertexBuffer	//������������
        ); 
        //Ϊ����ָ������ST���껺��
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTexBuffer);
        //�󶨵�ǰ����
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);
        //����ͼ��
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, 		//�������η�ʽ���
        		0, 			 			//��ʼ����
        		vCount					//���������
        );        
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);//���ö�����������
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);//����ʹ������ST���껺��
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
