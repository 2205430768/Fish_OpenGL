package mybubble;

import java.util.ArrayList;
import java.util.Collections;

import javax.microedition.khronos.opengles.GL10;

public class MyBubbleControl {
	public static int count=3;
	// ��������б�
	public ArrayList<MySingleBubble> BubbleSingle = new ArrayList<MySingleBubble>();
	// ���ݵ�����ID
	int[] texld;
	public MyBubbleThread Bgt;
	public MyBubbleControl(int[] texld) {
		
		this.texld = texld;

		// ����12������
		for (int i = 0; i < texld.length; i++) {
			BubbleSingle.add(new MySingleBubble(this.texld[i],i));
		}
		// ���������������ƶ��߳�
		 Bgt = new MyBubbleThread(this);
		Bgt.start();
	}

	public void drawSelf(GL10 gl) {
		
		try {
			 Collections.sort(this.BubbleSingle);
			
			 //System.out.println("��������  "+count);
			// ��������
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
