package com.bn.ld.Fishs;

import javax.microedition.khronos.opengles.GL10;

import com.bn.ld.Constant.Constant;
import com.bn.ld.Tools.LoadUtil;
import com.bn.ld.Vecrors.Vector3f;
import com.bn.ld.draw.LoadedObjectVertexNormalTexture;
import com.bn.ld.wallpaper.TDRender;

public class SingleFish {
	// ��ʼ���������λ�ã��ٶȣ��������
	public Vector3f position;
	public Vector3f speed;
	public Vector3f force;
	// �����ʳ֮֮���������
	public Vector3f attractforce;
	// ����������㱾���ܵ���������������ɷ���
	public float weight;
	// ����������ת���Ƕ�Z���Y��!
	float yAngle;
	float zAngle;
	float tempY;
	float tempZ;
	// ��ʼ���������
	LoadedObjectVertexNormalTexture lovn;
	int texld;
	/**
	 * 
	 * @param fish  ����id
	 * @param TDRender 
	 * @param name  ��ʼ���������
	 * @param Position �������λ��
	 * @param Speed  �ٶ�
	 * @param force  �������
	 * @param attractforce  �����ʳ֮֮���������
	 * @param weight ����������㱾���ܵ���������������ɷ���
	 */
	public SingleFish(int fish, TDRender TDRender, String name,
			Vector3f Position, Vector3f Speed, Vector3f force,
			Vector3f attractforce, float weight) {
		this.texld=fish;
		this.position = Position;
		this.speed = Speed;
		this.force = force;
		this.attractforce = attractforce;
		this.weight = weight;
		lovn = LoadUtil.loadFromFileVertexOnly(name, TDRender.getResources());
	}

	// drawSelf����,
	public void drawSelf(GL10 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(this.position.x, this.position.y, this.position.z);
		gl.glRotatef(yAngle, 0, 1, 0);
		gl.glRotatef(zAngle, 0, 0, 1);
		if (lovn != null) {
			lovn.drawSelf(gl,this.texld);
		}
		gl.glPopMatrix();
	}

	public void fish_only_change_speed_move(){
		// zAngle�ļ���
				float fz = (speed.x * speed.x + speed.y * 0 + speed.z * speed.z);
				// ��ĸ
				float fm = (float) (Math.sqrt(speed.x * speed.x + speed.y * speed.y
						+ speed.z * speed.z) * Math.sqrt(speed.x * speed.x + speed.z
						* speed.z));
				// cosֵ
				float angle = fz / fm;
				// ��Z�����ת�Ƕ�
				tempZ = (float) (180f / Math.PI) * (float) Math.acos(angle);
				// yAngle�ļ���
				fz = (speed.x * Constant.initialize.x + speed.z * Constant.initialize.z);
				// ��ĸ
				fm = (float) (Math.sqrt(Constant.initialize.x * Constant.initialize.x
						+ Constant.initialize.z * Constant.initialize.z) * Math
						.sqrt(speed.x * speed.x + speed.z * speed.z));
				// cosֵ
				angle = fz / fm;
				// ��Y�����ת�Ƕ�
				tempY = (float) (180f / Math.PI) * (float) Math.acos(angle);
				// �õ��нǸ���Speed.y����������ȷ���нǵ������ԣ���������ĳ��ĽǶȾ�Ϊ��ֵ��
				if (speed.y <= 0) {
					zAngle = tempZ;
				} else {
					zAngle = -tempZ;
				}
				// �õ��нǸ���Speed.z����������ȷ���нǵ������ԣ���������ĳ��ĽǶȾ�Ϊ��ֵ��
				if (speed.z > 0) {
					yAngle = tempY;
				} else {
					yAngle = -tempY;
				}
				position.plus(speed);
	}
	// ������ζ��ķ��������ݵ�ǰ������λ���Լ��ٶ������������һ��λ��!
	public void fishMove() {
		/**
		 * һ��ע���ж�x��z�ٶ�ͬʱΪ0 �����
		 */
		// zAngle�ļ���
		float fz = (speed.x * speed.x + speed.y * 0 + speed.z * speed.z);
		// ��ĸ
		float fm = (float) (Math.sqrt(speed.x * speed.x + speed.y * speed.y
				+ speed.z * speed.z) * Math.sqrt(speed.x * speed.x + speed.z
				* speed.z));
		// cosֵ
		float angle = fz / fm;
		// ��Z�����ת�Ƕ�
		tempZ = (float) (180f / Math.PI) * (float) Math.acos(angle);
		// yAngle�ļ���
		fz = (speed.x * Constant.initialize.x + speed.z * Constant.initialize.z);
		// ��ĸ
		fm = (float) (Math.sqrt(Constant.initialize.x * Constant.initialize.x
				+ Constant.initialize.z * Constant.initialize.z) * Math
				.sqrt(speed.x * speed.x + speed.z * speed.z));
		// cosֵ
		angle = fz / fm;
		// ��Y�����ת�Ƕ�
		tempY = (float) (180f / Math.PI) * (float) Math.acos(angle);
		// �õ��нǸ���Speed.y����������ȷ���нǵ������ԣ���������ĳ��ĽǶȾ�Ϊ��ֵ��
		if (speed.y <= 0) {
			zAngle = tempZ;
		} else {
			zAngle = -tempZ;
		}
		// �õ��нǸ���Speed.z����������ȷ���нǵ������ԣ���������ĳ��ĽǶȾ�Ϊ��ֵ��
		if (speed.z > 0) {
			yAngle = tempY;
		} else {
			yAngle = -tempY;
		}
		// ��̬���޸�����ٶȣ���̽�Եļ������ٶȣ���������涨�ķ�Χ������ٶȲ�������
		// �������
		if (Math.abs(speed.x + force.x) < Constant.MaxSpeed) 
		{
			speed.x += force.x;
		}
		if (Math.abs(speed.y + force.y) < Constant.MaxSpeed)
		{
			speed.y += force.y;
		}
		if (Math.abs(speed.z + force.z) < Constant.MaxSpeed) 
		{
			speed.z += force.z;
		}

		// ��̬���޸�����ٶ�
		// ���������ʳ֮���������
		if (Math.abs(speed.x + attractforce.x) < Constant.MaxSpeed) 
		{
			speed.x += attractforce.x;
		}
		if (Math.abs(speed.y + attractforce.y) < Constant.MaxSpeed) 
		{
			speed.y += attractforce.y;
		}
		if (Math.abs(speed.z + attractforce.z) < Constant.MaxSpeed) 
		{
			speed.z += attractforce.z;
		}
		// �ı����λ��
		position.plus(speed);
		//��ֹ�㴩������

		// ÿ�μ���ÿ���������֮�󣬰����ܵ�������
		// ����
		this.force.x = 0;
		this.force.y = 0;
		this.force.z = 0;
		// ��ʳ�����������
		attractforce.x = 0;
		attractforce.y = 0;
		attractforce.z = 0;

	}
}
