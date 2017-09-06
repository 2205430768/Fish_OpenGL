package com.bn.ld.Bubbles;

import java.util.ArrayList;
import java.util.Collections;

import javax.microedition.khronos.opengles.GL10;

import com.bn.ld.Constant.Constant;
import com.bn.ld.WorksThread.BubbleThread;


public class BubbleControl {
	// 气泡类的列表
	public ArrayList<SingleBubble> BubbleSingle = new ArrayList<SingleBubble>();
	// 气泡的纹理ID
	int texld;
	public BubbleThread Bgt;
	public BubbleControl(int texld ) {
		// 拿到ID
		this.texld = texld;

		// 创建12个气泡
		for (int i = 0; i < Constant.BUBBLE_NUM; i++) {
			BubbleSingle.add(new SingleBubble(this.texld));
		}
		// 创建并启动气泡移动线程
		 Bgt = new BubbleThread(this);
		Bgt.start();
	}

	public void drawSelf(GL10 gl) {
		try {
			 Collections.sort(this.BubbleSingle);
			// 绘制气泡
			for (int i = 0; i < this.BubbleSingle.size(); i++) {
				gl.glPushMatrix();
				BubbleSingle.get(i).drawSelf(gl);
				gl.glPopMatrix();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
