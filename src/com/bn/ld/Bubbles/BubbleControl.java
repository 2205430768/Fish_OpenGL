package com.bn.ld.Bubbles;

import java.util.ArrayList;
import java.util.Collections;

import javax.microedition.khronos.opengles.GL10;

import com.bn.ld.Constant.Constant;
import com.bn.ld.WorksThread.BubbleThread;


public class BubbleControl {
	// ��������б�
	public ArrayList<SingleBubble> BubbleSingle = new ArrayList<SingleBubble>();
	// ���ݵ�����ID
	int texld;
	public BubbleThread Bgt;
	public BubbleControl(int texld ) {
		// �õ�ID
		this.texld = texld;

		// ����12������
		for (int i = 0; i < Constant.BUBBLE_NUM; i++) {
			BubbleSingle.add(new SingleBubble(this.texld));
		}
		// ���������������ƶ��߳�
		 Bgt = new BubbleThread(this);
		Bgt.start();
	}

	public void drawSelf(GL10 gl) {
		try {
			 Collections.sort(this.BubbleSingle);
			// ��������
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
