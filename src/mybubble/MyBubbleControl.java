package mybubble;

import java.util.ArrayList;
import java.util.Collections;

import javax.microedition.khronos.opengles.GL10;

public class MyBubbleControl {
	public static int count=3;
	// 气泡类的列表
	public ArrayList<MySingleBubble> BubbleSingle = new ArrayList<MySingleBubble>();
	// 气泡的纹理ID
	int[] texld;
	public MyBubbleThread Bgt;
	public MyBubbleControl(int[] texld) {
		
		this.texld = texld;

		// 创建12个气泡
		for (int i = 0; i < texld.length; i++) {
			BubbleSingle.add(new MySingleBubble(this.texld[i],i));
		}
		// 创建并启动气泡移动线程
		 Bgt = new MyBubbleThread(this);
		Bgt.start();
	}

	public void drawSelf(GL10 gl) {
		
		try {
			 Collections.sort(this.BubbleSingle);
			
			 //System.out.println("绘制鱼泡  "+count);
			// 绘制气泡
			for (int i = 0; i < count; i++) {
				gl.glPushMatrix();
				BubbleSingle.get(i).drawSelf(gl);
				gl.glPopMatrix();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
