package com.bn.ld.WorksThread;

import com.bn.ld.Bubbles.BubbleControl;

//定时运动所有鱼类的线程
public class BubbleThread extends Thread {
	public boolean flag = true;
	BubbleControl Bcl;
	public BubbleThread(BubbleControl Bcl) {
		this.Bcl=Bcl;
}
	public void run()
	{
		// 循环定时移动气泡
		while (flag) {
			try {
				for(int i=0;i<Bcl.BubbleSingle.size();i++)
				{
					Bcl.BubbleSingle.get(i).bubbleMove();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
