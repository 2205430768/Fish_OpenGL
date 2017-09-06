package com.bn.ld.WorksThread;

import mybubble.MyBubbleControl;

import com.bn.ld.Fishs.FishControl;
import com.bn.ld.Vecrors.Vector3f;

//定时运动所有鱼类的线程
public class FishGoThread extends Thread {
	boolean flag = true;
	FishControl fishControl;
    final int ONE=0;
    final int TWO=1;
    int changespan;
    float changespanX;
    float changespanY;
    float changespanZ;
    int changespan1;
    float changespanX1;
    float changespanY1;
    float changespanZ1;
	public FishGoThread(FishControl fishGroupforcontrol) {
		this.fishControl = fishGroupforcontrol;
	}
	public void run() {
		fishControl.fishAl.get(ONE).fish_only_change_speed_move();
		fishControl.fishAl.get(TWO).fish_only_change_speed_move();
		sleep(800);
		while(fishControl.fishAl.get(ONE).position.x>-2){
			fishControl.fishAl.get(ONE).fish_only_change_speed_move();
			sleep(80);
		}
		changespan=40;
		changespanX=(fishControl.fishAl.get(ONE).speed.x-fishControl.fishAl.get(TWO).speed.x)/changespan;
		changespanY=(fishControl.fishAl.get(ONE).speed.y-fishControl.fishAl.get(TWO).speed.y)/changespan;
		changespanZ=(fishControl.fishAl.get(ONE).speed.z-fishControl.fishAl.get(TWO).speed.z)/changespan;
		while(changespan-->0)
		{
			fishControl.fishAl.get(TWO).speed.x+=changespanX;
			fishControl.fishAl.get(TWO).speed.y+=changespanY;
			fishControl.fishAl.get(TWO).speed.z+=changespanZ;
			fishControl.fishAl.get(TWO).fish_only_change_speed_move();
			sleep(50);
		}
		fishControl.fishAl.get(TWO).speed=fishControl.fishAl.get(ONE).speed;
		while(fishControl.fishAl.get(ONE).position.x>-3.5){
			fishControl.fishAl.get(ONE).fish_only_change_speed_move();
			fishControl.fishAl.get(TWO).fish_only_change_speed_move();
			sleep(80);
		}
		oneMove();
		sleep(100);
		changespan=40;
		changespanX=(0.01f-fishControl.fishAl.get(TWO).speed.x)/changespan;
		changespanY=(-0.02f-fishControl.fishAl.get(TWO).speed.y)/changespan;
		changespanZ=(0-fishControl.fishAl.get(TWO).speed.z)/changespan;
		while(changespan-->0)
		{
			fishControl.fishAl.get(TWO).speed.x+=changespanX;
			fishControl.fishAl.get(TWO).speed.y+=changespanY;
			fishControl.fishAl.get(TWO).speed.z+=changespanZ;
			fishControl.fishAl.get(TWO).fish_only_change_speed_move();
			sleep(50);
		}
		fishControl.fishAl.get(TWO).speed=new Vector3f(0.01f, -0.02f, 0);
		while(fishControl.fishAl.get(TWO).position.x<0){
			fishControl.fishAl.get(TWO).fish_only_change_speed_move();
			sleep(60);
		}
		fishControl.Tr.TEXT_STATUS=0;
		sleep(2000);
		fishControl.Tr.TEXT_STATUS=-1;
		changespan=40;
		changespanX=(-0.005f-fishControl.fishAl.get(TWO).speed.x)/changespan;
		changespanY=(0.02f-fishControl.fishAl.get(TWO).speed.y)/changespan;
		changespanZ=(-0.015f-fishControl.fishAl.get(TWO).speed.z)/changespan;
		while(changespan-->0)
		{
			fishControl.fishAl.get(TWO).speed.x+=changespanX;
			fishControl.fishAl.get(TWO).speed.y+=changespanY;
			fishControl.fishAl.get(TWO).speed.z+=changespanZ;
			fishControl.fishAl.get(TWO).fish_only_change_speed_move();
			sleep(30);
		}
		fishControl.fishAl.get(TWO).speed=new Vector3f(-0.005f, 0.02f, -0.015f);
		while(fishControl.fishAl.get(TWO).position.y<4.5f){
			fishControl.fishAl.get(TWO).fish_only_change_speed_move();
			sleep(15);
		}
		 oneMove1();
		 sleep(12000);
		 oneMove2();
		  changespan=40;
			changespanX=(0.002f-fishControl.fishAl.get(TWO).speed.x)/changespan;
			changespanY=(-0.02f-fishControl.fishAl.get(TWO).speed.y)/changespan;
			changespanZ=(0.015f-fishControl.fishAl.get(TWO).speed.z)/changespan;
			while(changespan-->0)
			{
				fishControl.fishAl.get(TWO).speed.x+=changespanX;
				fishControl.fishAl.get(TWO).speed.y+=changespanY;
				fishControl.fishAl.get(TWO).speed.z+=changespanZ;
				fishControl.fishAl.get(TWO).fish_only_change_speed_move();
				sleep(50);
			}
		fishControl.fishAl.get(TWO).speed=new Vector3f(0.002f, -0.02f, 0.015f);
		while(fishControl.fishAl.get(TWO).position.y>0){
			fishControl.fishAl.get(TWO).fish_only_change_speed_move();
			sleep(50);
		}
	}
	void oneMove2(){
		new Thread(){
			public void run() {
				changespan1=60;
				changespanX1=(0.002f-fishControl.fishAl.get(ONE).speed.x)/changespan1;
				changespanY1=(-0.02f-fishControl.fishAl.get(ONE).speed.y)/changespan1;
				changespanZ1=(0.015f-fishControl.fishAl.get(ONE).speed.z)/changespan1;
				while(changespan1-->0)
				{
					fishControl.fishAl.get(ONE).speed.x+=changespanX1;
					fishControl.fishAl.get(ONE).speed.y+=changespanY1;
					fishControl.fishAl.get(ONE).speed.z+=changespanZ1;
					fishControl.fishAl.get(ONE).fish_only_change_speed_move();
					sleep(50);
				}
				fishControl.fishAl.get(ONE).speed=new Vector3f(0.002f, -0.02f, 0.015f);
				while(fishControl.fishAl.get(ONE).position.y>0){
					fishControl.fishAl.get(ONE).fish_only_change_speed_move();
					sleep(50);
				}
				fishControl.Tr.TEXT_STATUS=1;
				sleep(2000);
				fishControl.Tr.TEXT_STATUS=2;
				sleep(2000);
				fishControl.Tr.TEXT_STATUS=-1;
				changespan1=60;
				changespanX1=(0-fishControl.fishAl.get(ONE).speed.x)/changespan1;
				changespanY1=(0.02f-fishControl.fishAl.get(ONE).speed.y)/changespan1;
				changespanZ1=(0.01f-fishControl.fishAl.get(ONE).speed.z)/changespan1;
				while(changespan1-->0)
				{
					fishControl.fishAl.get(ONE).speed.x+=changespanX1;
					fishControl.fishAl.get(ONE).speed.y+=changespanY1;
					fishControl.fishAl.get(ONE).speed.z+=changespanZ1;
					fishControl.fishAl.get(ONE).fish_only_change_speed_move();
					sleep(40);
				}
			fishControl.fishAl.get(ONE).speed=new Vector3f(0, 0.02f, 0.01f);
			while(fishControl.fishAl.get(ONE).position.y<10){
				fishControl.fishAl.get(ONE).fish_only_change_speed_move();
				sleep(10);
			}
			sleep(5000);
			twoMove();
			changespan1=60;
			changespanX1=(0-fishControl.fishAl.get(ONE).speed.x)/changespan1;
			changespanY1=(-0.02f-fishControl.fishAl.get(ONE).speed.y)/changespan1;
			changespanZ1=(-0.01f-fishControl.fishAl.get(ONE).speed.z)/changespan1;
			while(changespan1-->0)
			{
				fishControl.fishAl.get(ONE).speed.x+=changespanX1;
				fishControl.fishAl.get(ONE).speed.y+=changespanY1;
				fishControl.fishAl.get(ONE).speed.z+=changespanZ1;
				fishControl.fishAl.get(ONE).fish_only_change_speed_move();
				sleep(40);
			}
		fishControl.fishAl.get(ONE).speed=new Vector3f(0, -0.02f, -0.01f);
		while(fishControl.fishAl.get(ONE).position.y>0){
			fishControl.fishAl.get(ONE).fish_only_change_speed_move();
			sleep(30);
		}//最后移动位置
		while(MyBubbleControl.count<8)
		{
			MyBubbleControl.count++;
			sleep(2000);
		}
		sleep(9500);
		FishGoThread.this.fishControl.Tr.activity.handler.sendEmptyMessage(0);
			};
			void sleep(int span){
				try {
					Thread.sleep(span);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
		
		
	}
	protected void twoMove() {
		new Thread(){
			public void run() {
				changespan=40;
				changespanX=(0f-fishControl.fishAl.get(TWO).speed.x)/changespan;
				changespanY=(0.02f-fishControl.fishAl.get(TWO).speed.y)/changespan;
				changespanZ=(0.01f-fishControl.fishAl.get(TWO).speed.z)/changespan;
				while(changespan-->0)
				{
					fishControl.fishAl.get(TWO).speed.x+=changespanX;
					fishControl.fishAl.get(TWO).speed.y+=changespanY;
					fishControl.fishAl.get(TWO).speed.z+=changespanZ;
					fishControl.fishAl.get(TWO).fish_only_change_speed_move();
					sleep(50);
				}
			fishControl.fishAl.get(TWO).speed=new Vector3f(0, 0.02f, 0.01f);
			while(fishControl.fishAl.get(TWO).position.y<5){
				fishControl.fishAl.get(TWO).fish_only_change_speed_move();
				sleep(18);
			}
			changespan=40;
			changespanX=(0f-fishControl.fishAl.get(TWO).speed.x)/changespan;
			changespanY=(-0.02f-fishControl.fishAl.get(TWO).speed.y)/changespan;
			changespanZ=(-0.01f-fishControl.fishAl.get(TWO).speed.z)/changespan;
			while(changespan-->0)
			{
				fishControl.fishAl.get(TWO).speed.x+=changespanX;
				fishControl.fishAl.get(TWO).speed.y+=changespanY;
				fishControl.fishAl.get(TWO).speed.z+=changespanZ;
				fishControl.fishAl.get(TWO).fish_only_change_speed_move();
				sleep(50);
			}
		fishControl.fishAl.get(TWO).speed=new Vector3f(0, -0.02f, -0.01f);
		while(fishControl.fishAl.get(TWO).position.y>0){
			fishControl.fishAl.get(TWO).fish_only_change_speed_move();
			sleep(18);
		}
			};
			void sleep(int span){
				try {
					Thread.sleep(span);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
		
		
	}
	void oneMove1(){
		new Thread(){
			public void run() {
				changespan1=60;
				changespanX1=(-0.01f-fishControl.fishAl.get(ONE).speed.x)/changespan1;
				changespanY1=(0.02f-fishControl.fishAl.get(ONE).speed.y)/changespan1;
				changespanZ1=(-0.015f-fishControl.fishAl.get(ONE).speed.z)/changespan1;
				while(changespan1-->0)
				{
					fishControl.fishAl.get(ONE).speed.x+=changespanX1;
					fishControl.fishAl.get(ONE).speed.y+=changespanY1;
					fishControl.fishAl.get(ONE).speed.z+=changespanZ1;
					fishControl.fishAl.get(ONE).fish_only_change_speed_move();
					sleep(50);
				}
				fishControl.fishAl.get(ONE).speed=new Vector3f(-0.01f, 0.02f, -0.015f);
				while(fishControl.fishAl.get(ONE).position.y<4f){
					fishControl.fishAl.get(ONE).fish_only_change_speed_move();
					sleep(20);
				}
				
				
			};
			void sleep(int span){
				try {
					Thread.sleep(span);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
		
		
	}
 
	
	void oneMove(){
		new Thread(){
			public void run() {
				changespan1=60;
				changespanX1=(0.02f-fishControl.fishAl.get(ONE).speed.x)/changespan1;
				changespanY1=(-0.02f-fishControl.fishAl.get(ONE).speed.y)/changespan1;
				changespanZ1=(0-fishControl.fishAl.get(ONE).speed.z)/changespan1;
				fishControl.fishAl.get(ONE).speed=new Vector3f(0.02f, -0.02f, 0);
				while(changespan1-->0)
				{
					fishControl.fishAl.get(ONE).speed.x+=changespanX1;
					fishControl.fishAl.get(ONE).speed.y+=changespanY1;
					fishControl.fishAl.get(ONE).speed.z+=changespanZ1;
					fishControl.fishAl.get(ONE).fish_only_change_speed_move();
					sleep(50);
				}
				while(fishControl.fishAl.get(ONE).position.x<1.5){
					fishControl.fishAl.get(ONE).fish_only_change_speed_move();
					sleep(80);
				}
				
				
			};
			void sleep(int span){
				try {
					Thread.sleep(span);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
		
		
	}
 
	void sleep(int span){
		try {
			Thread.sleep(span);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
