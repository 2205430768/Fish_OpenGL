package com.bn.ld.Bubbles;



import javax.microedition.khronos.opengles.GL10;

import com.bn.ld.Constant.Constant;
import com.bn.ld.draw.Bubble;

public class SingleBubble  implements Comparable<SingleBubble>{
	// 当前位置：随即产生
	float cuerrentX;
	float cuerrentY;
	float cuerrentZ;
	// 气泡的最大高度：随机产生
	float border;
	// 纹理ID
	int texld;
	// 气泡对象
	Bubble bubbles;

	// 构造器
	public SingleBubble(int texld) {
		newposition();
		this.texld = texld;
		bubbles = new Bubble();
	}

	public void drawSelf(GL10 gl) {
		//绘制气泡
		gl.glPushMatrix();
		gl.glTranslatef(cuerrentX, cuerrentY, cuerrentZ);
		bubbles.drawSelf(gl,texld);
		gl.glPopMatrix();
	}
	public void bubbleMove() {
		//气泡移动
		this.cuerrentY += Constant.BubbleMoveDistance;
		if (this.cuerrentY > border) {
			newposition();
		}
	}

	public void newposition() {
		//随即改变气泡的位置
		
		cuerrentX = (float) ((Math.random() - 0.5) * 10);
		cuerrentY = (float) (Math.random() - 4);
		cuerrentZ = 9+(float) ((Math.random() - 1) * 12.5f);
		//气泡上升的高度
		border = (float) (5 * Math.random());
	}

	@Override
	public int compareTo(SingleBubble another) {
		//重写的比较两个仙人掌离摄像机距离的方法

		return ((this.cuerrentZ-another.cuerrentZ)==0)?0:((this.cuerrentZ-another.cuerrentZ)>0)?1:-1;  
	}
}
