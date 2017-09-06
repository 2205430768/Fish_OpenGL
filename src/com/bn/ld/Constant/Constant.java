package com.bn.ld.Constant;

import com.bn.ld.Vecrors.Vector3f;

public class Constant {
	// 屏幕的长
	public static float SCREEN_HEGHT;
	// 屏幕的宽
	public static float SCREEN_WIDTH;
	// 屏幕的长宽比例
	public static float leftABS;
	public static float topABS;
	public static float nearABS =2.5f ;
	public static float farABS = 100.0f;
	//视角的缩放比
	public static float View_SCALE = 1f;
	//背景图的缩放比
	public static float SCREEN_SCALEX;
	public static float SCREEN_SCALEY = 1.0f * 0.6f;
	public static float SCREEN_SCALEZ = 1.0f * 0.6f;
	
	//摄像机的位置
	public static float CameraX = 0;
	public static float CameraY = 5.0f;
	public static float CameraZ = 20.0f;
	//摄像机的Up向量
	public static float UpX = 0.0f;
	public static float UpY = 0.9701f;//(float) 20/ (float) Math.sqrt(425)
	public static float UpZ = -0.2425f;//(float) 5/ (float) Math.sqrt(425)
	//摄像机移动的缩放比
	public static float CameraMove_SCALE = 120;
	//摄像机的最大移动距离
	public static float MaxCameraMove = 4;
	//摄像机观测的位置
	public static float TargetX = 0;
	public static float TargetY = 0;
	public static float TargetZ = 0;
	//地面的高度
	public static float Y_HEIGHT =  -6;
	//Z轴的触控范围
	public static float ZTouch_Max =  -1;
	public static float ZTouch_Min =  -9;
	//鱼食在X轴的晃动距离
	public static float FoodMove_X = 0.01f;
	//鱼食每次下落的距离
	public static float FoodSpeed = 0.1f; 
	//鱼吃到鱼食的判定范围
	public static float FoodFeedDistance = 0.5f;
	// 鱼食在Y轴方向上的最小距离
	public static float FoodPositionMin_Y =-5f;
	// 食物的初始Yposition  食物在Y轴的最大距离
	public static float FoodPositionMax_Y = 5f;
	// 气泡的数量
	public static int BUBBLE_NUM = 7;
	//气泡每次移动的距离
	public static float BubbleMoveDistance = 0.04f;
	
	// 鱼类速度变化最大值用它除以位移能得到没个方向上的力的变化 
	public static float MaxChangeSpeed = 0.01f;
	// 两条鱼之间的碰撞距离  小于这个距离之后将产生力的作用
	public static float MinDistances = 4.0f;

	// 单条鱼与鱼群之间的碰撞距离  小于这个距离之后产生力的作用
	public static float SMinDistaces = 3f;
	// 鱼类的初始位置向量
	public static Vector3f initialize = new Vector3f(-1, 0, 0);
	
	// 鱼群的半径
	public static float Radius = 1.5f;
	
	// 恒力的缩放比例
	public static float ConstantForceScals = 200;
	// 鱼群和单个鱼之间的缩放比
	public static float WeightScals = 11;
	//触控阈值
	public static float Thold=10;
	// 诱惑力的缩放比例
	public static float AttractForceScals = 56.1f;
	
	// 触控是否
	public static boolean isFeed = true;
	
	//速度的阈值
	public static float MaxSpeed=0.07f;
  
	//鱼群里面的鱼的最大游动速度
	public static float SchoolMaxSpeed=0.05f;
	//喂食
	public static boolean feeding=false;

}
