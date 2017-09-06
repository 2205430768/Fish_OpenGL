package com.bn.ld.Bubbles;



import javax.microedition.khronos.opengles.GL10;

import com.bn.ld.Constant.Constant;
import com.bn.ld.draw.Bubble;

public class SingleBubble  implements Comparable<SingleBubble>{
	// ��ǰλ�ã��漴����
	float cuerrentX;
	float cuerrentY;
	float cuerrentZ;
	// ���ݵ����߶ȣ��������
	float border;
	// ����ID
	int texld;
	// ���ݶ���
	Bubble bubbles;

	// ������
	public SingleBubble(int texld) {
		newposition();
		this.texld = texld;
		bubbles = new Bubble();
	}

	public void drawSelf(GL10 gl) {
		//��������
		gl.glPushMatrix();
		gl.glTranslatef(cuerrentX, cuerrentY, cuerrentZ);
		bubbles.drawSelf(gl,texld);
		gl.glPopMatrix();
	}
	public void bubbleMove() {
		//�����ƶ�
		this.cuerrentY += Constant.BubbleMoveDistance;
		if (this.cuerrentY > border) {
			newposition();
		}
	}

	public void newposition() {
		//�漴�ı����ݵ�λ��
		
		cuerrentX = (float) ((Math.random() - 0.5) * 10);
		cuerrentY = (float) (Math.random() - 4);
		cuerrentZ = 9+(float) ((Math.random() - 1) * 12.5f);
		//���������ĸ߶�
		border = (float) (5 * Math.random());
	}

	@Override
	public int compareTo(SingleBubble another) {
		//��д�ıȽ����������������������ķ���

		return ((this.cuerrentZ-another.cuerrentZ)==0)?0:((this.cuerrentZ-another.cuerrentZ)>0)?1:-1;  
	}
}
