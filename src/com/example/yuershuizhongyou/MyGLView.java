package com.example.yuershuizhongyou;

import com.bn.ld.wallpaper.TDRender;
import android.opengl.GLSurfaceView;

public class MyGLView extends GLSurfaceView{
	public TDRender renderer;
	MainActivity activity;
	public MyGLView(MainActivity activity) {
		super(activity);
		this.activity=activity;
		renderer=new TDRender(activity);//创建渲染器
		this.setRenderer(renderer);//设置渲染器
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染   
	}
	
	
}
