package com.example.yuershuizhongyou;

import com.bn.ld.wallpaper.TDRender;
import android.opengl.GLSurfaceView;

public class MyGLView extends GLSurfaceView{
	public TDRender renderer;
	MainActivity activity;
	public MyGLView(MainActivity activity) {
		super(activity);
		this.activity=activity;
		renderer=new TDRender(activity);//������Ⱦ��
		this.setRenderer(renderer);//������Ⱦ��
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//������ȾģʽΪ������Ⱦ   
	}
	
	
}
