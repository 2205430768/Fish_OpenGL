package com.bn.ld.Fishs;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.bn.ld.WorksThread.FishGoThread;
import com.bn.ld.wallpaper.TDRender;


public class FishControl {
	 //鱼群列表
	public ArrayList<SingleFish> fishAl;
	//鱼Go线程
	public FishGoThread  fgt;
	//渲染器
	public TDRender Tr;
	//构造器
	public FishControl(ArrayList<SingleFish> fishAl,TDRender tr)
	{
		this.fishAl = fishAl;
		this.Tr=tr;
		//启动鱼的移动线程
		fgt= new FishGoThread(this);
	    fgt.start();
	}
	public void drawSelf(GL10 gl)
   {
		try {
			 
			//循环绘制每一条鱼
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
