package com.bn.ld.Fishs;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.bn.ld.WorksThread.FishGoThread;
import com.bn.ld.wallpaper.TDRender;


public class FishControl {
	 //��Ⱥ�б�
	public ArrayList<SingleFish> fishAl;
	//��Go�߳�
	public FishGoThread  fgt;
	//��Ⱦ��
	public TDRender Tr;
	//������
	public FishControl(ArrayList<SingleFish> fishAl,TDRender tr)
	{
		this.fishAl = fishAl;
		this.Tr=tr;
		//��������ƶ��߳�
		fgt= new FishGoThread(this);
	    fgt.start();
	}
	public void drawSelf(GL10 gl)
   {
		try {
			 
			//ѭ������ÿһ����
			 for(int i=0;i<this.fishAl.size();i++)
			 {
				// gl.glPushMatrix();
				
				 fishAl.get(i).drawSelf(gl);
				// gl.glPopMatrix();
			  }
		 } 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
