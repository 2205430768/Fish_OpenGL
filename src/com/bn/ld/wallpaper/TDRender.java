package com.bn.ld.wallpaper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import mybubble.MyBubbleControl;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;

import com.bn.ld.Bubbles.BubbleControl;
import com.bn.ld.Constant.Constant;
import com.bn.ld.Fishs.FishControl;
import com.bn.ld.Fishs.SingleFish;
import com.bn.ld.Tools.LoadUtil;
import com.bn.ld.UtilTools.MatrixUtil;
import com.bn.ld.Vecrors.Vector3f;
import com.bn.ld.draw.BackGround;
import com.bn.ld.draw.LoadedObjectVertexNormalTexture;
import com.bn.ld.gl.GLWallpaperService;
import com.example.yuershuizhongyou.MainActivity;
import com.example.yuershuizhongyou.TextureRect;

public class TDRender extends GLSurfaceView implements GLSurfaceView.Renderer,
		GLWallpaperService.Renderer {
	public MainActivity activity;
	public TDRender(MainActivity activity) {
		super(activity);
		this.activity=activity;
	}

	// 鱼类的纹理Id
	int fish1;
	int fish2;
	// 气泡的纹理Id
	int bubbles;
	// 水草的纹理Id
	int waterweeds;
	// 背景的纹理Id
	int background;
	// 食物的纹理IdSD
	//石头纹理Id
	int stone;
	
	public MyBubbleControl mMyBubbleControl;
	// 食物的X，Z坐标，通过ACTION_DOWN给出
	public static float Xposition;
	public static float Zposition;
	// 是否绘制食物的标志位
	public boolean Fooddraw = false;
	// 鱼类的集合
	public FishControl fishControl;
	// 鱼类的列表
	public ArrayList<SingleFish> fishAl = new ArrayList<SingleFish>();

	
	// 纹理矩形，背景图片
	BackGround backgrounds;
	// 水草类
	LoadedObjectVertexNormalTexture waterweeds1;
	//石头类
	LoadedObjectVertexNormalTexture stones;
	// 气泡
	BubbleControl bubbleControl;
	String[] texts={"stupid guy,boring","我想上去，晒太阳","我想留在这"};
	int[] colors=new int[]{Color.GREEN,Color.GREEN,Color.RED,0xff00ffff,Color.YELLOW,0xffffa500,0xff32cd32,Color.WHITE};
	String[] week={"钱","钱","小","宇","国","庆","愉","快"};
	int[] weektexId=new int[8];
	float[][] text_xyz=new float[][]{
			{2.3f,-0.7f,1f},
			{1.7f,0.6f,1f},
			{-2.8f,0.6f,1f}
	};
	ArrayList<TextureRect> list;
	public int TEXT_STATUS=-1;
	Paint paint;
	private float yAngle;// 绕y轴旋转的角度
	private float zAngle;// 绕z轴旋转的角度

	// 旋转角度的构造器
	public float getyAngle() {
		return yAngle;
	}

	public void setyAngle(float yAngle) {
		this.yAngle = yAngle;
	}

	public float getzAngle() {
		return zAngle;
	}

	public void setzAngle(float zAngle) {
		this.zAngle = zAngle;
	}

	public void onDrawFrame(GL10 gl) {
		// 设置为打开背面剪裁
		gl.glEnable(GL10.GL_CULL_FACE);
		// 设置着色模型为平滑着色
		gl.glShadeModel(GL10.GL_SMOOTH);
		// 设置为默认卷绕顺序——逆时针为正面
		gl.glFrontFace(GL10.GL_CCW);
		// 清除颜色缓存于深度缓存
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// 设置当前矩阵为模式矩阵
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// 设置当前矩阵为单位矩阵
		gl.glLoadIdentity();

		// 设置摄像机
		GLU.gluLookAt// 不太可能变形的视角——小视角
		(
				gl,
				Constant.CameraX, // 人眼位置的X
				Constant.CameraY, // 人眼位置的Y
				Constant.CameraZ, // 人眼位置 的Z
				Constant.TargetX, // 人眼球看的点X
				Constant.TargetY, // 人眼球看的点Y
				Constant.TargetZ, // 人眼球看的点Z
				Constant.UpX, //Up向量
				Constant.UpY, 
				Constant.UpZ
						);
		//绘制文字
		if(list!=null&&list.size()!=0&&TEXT_STATUS!=-1)
			list.get(TEXT_STATUS).drawSelf(gl);
		// 将要画的东西向-z轴移动
		// 绘制水草和背景，气泡
		gl.glPushMatrix();
		// 背景图
		if (backgrounds != null) {
			backgrounds.drawSelf(gl,background);
		}
		gl.glPopMatrix();
	
		
		gl.glPushMatrix();
		// 水草
		if (waterweeds1 != null&&stones!=null) {
			
			gl.glPushMatrix();
			gl.glTranslatef(-17, -5.5f,-14);
			stones.drawSelf(gl,stone);
			gl.glTranslatef(1.5f, 0, 1);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(2, 0, 1);
			gl.glPushMatrix();
			gl.glScalef(1.5f, 1.5f, 1.5f);
			stones.drawSelf(gl,stone);
			gl.glPopMatrix();
			gl.glTranslatef(4, 0, -2);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(1.5f, 0, 0);
			gl.glPushMatrix();
			gl.glRotatef(60, 0, 1, 0);
			gl.glScalef(1.5f, 1.5f, 1.5f);
			stones.drawSelf(gl,stone);
			gl.glPopMatrix();
			gl.glTranslatef(3, 0, 0);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(1.5f, 0, 1);
			stones.drawSelf(gl,stone);
			gl.glTranslatef(4f, 0, 0);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glPopMatrix();
			
			
			gl.glPushMatrix();
			gl.glTranslatef(2, -5.5f,-14);
			gl.glPushMatrix();
			gl.glScalef(2.5f, 1.5f, 1.5f);
			stones.drawSelf(gl,stone);
			gl.glPopMatrix();
			gl.glTranslatef(0, 0, 2);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(1.5f, 0, -1);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(2, 0, 1);
			stones.drawSelf(gl,stone);
			gl.glTranslatef(4, 0, -2);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(1.5f, 0, 0);
			gl.glPushMatrix();
			gl.glRotatef(60, 0, 1, 0);
			stones.drawSelf(gl,stone);
			gl.glPopMatrix();
			gl.glTranslatef(3, 0, 0);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(1.5f, 0, 1);
			stones.drawSelf(gl,stone);
			gl.glTranslatef(2.5f, 0, 0);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glPopMatrix();
			
			gl.glPushMatrix();
			gl.glTranslatef(2, -2,6);
			gl.glPushMatrix();
			gl.glScalef(2.5f, 1.5f, 1.5f);
			stones.drawSelf(gl,stone);
			gl.glPopMatrix();
			gl.glTranslatef(0, 0, 2);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(1.5f, 0, -1);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(2, 0, 1);
			gl.glTranslatef(0, 1, 0);
			stones.drawSelf(gl,stone);
			gl.glTranslatef(1, -1, 0);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glPopMatrix();
			
			gl.glPushMatrix();
			gl.glTranslatef(-2.5f, -3f,4);
			stones.drawSelf(gl,stone);
			gl.glTranslatef(-1.5f, 0, 1);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glTranslatef(-2, 0, 1);
			gl.glPushMatrix();
			gl.glScalef(1.5f, 2f, 1f);
			stones.drawSelf(gl,stone);
			gl.glPopMatrix();
			gl.glTranslatef(4, -1, -3);
			waterweeds1.drawSelf(gl,waterweeds);
			gl.glPopMatrix();
			
		}
		gl.glPopMatrix();
		// 绘制鱼
		//gl.glPushMatrix();
		//System.out.println("画"+this.fishAl.size());
		if (fishControl != null) {
			
			fishControl.drawSelf(gl);
		}
		//gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glEnable(GL10.GL_BLEND);
		// 设置源混合因子与目标混合因子
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glTranslatef(0, 0, 15f);
		if (bubbleControl != null) {
			bubbleControl.drawSelf(gl);
		}
		
		
	     if(mMyBubbleControl!=null)
		{
	    	// System.out.println("不为空");
			mMyBubbleControl.drawSelf(gl);
				
		}
		
		// 关闭混合
		gl.glDisable(GL10.GL_BLEND);
	    gl.glPopMatrix();
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// 设置视窗大小及位置
		gl.glViewport(0, 0, width, height);
		// 设置当前矩阵为投影矩阵
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// 设置当前矩阵为单位矩阵
		gl.glLoadIdentity();
		// 计算透视投影的比例
		float ratio = (float) width / height;
		Constant.SCREEN_HEGHT=height;
		Constant.SCREEN_WIDTH=width;
		Constant.leftABS=ratio*Constant.View_SCALE;
		Constant.topABS=1 * Constant.View_SCALE;
		Constant.SCREEN_SCALEX=Constant.View_SCALE*((ratio>1)?ratio:(1/ratio));
		// 调用此方法计算产生透视投影矩阵
		gl.glFrustumf(-Constant.leftABS, Constant.leftABS, -Constant.topABS, 
				Constant.topABS, Constant.nearABS,Constant.farABS);
		// 设置初始摄像机矩阵把摄像机存入到矩阵!
		MatrixUtil.setCamera(
				Constant.CameraX, // 人眼位置的X
				Constant.CameraY, // 人眼位置的Y
				Constant.CameraZ, // 人眼位置 的Z
				Constant.TargetX, // 人眼球看的点X
				Constant.TargetY, // 人眼球看的点Y	
				Constant.TargetZ, // 人眼球看的点Z
				Constant.UpX, 
				Constant.UpY, 
				Constant.UpZ
				);
		if (backgrounds == null) {
			backgrounds = new BackGround();
		}
	}
	public void onSurfaceCreated(final GL10 gl, EGLConfig config) {
		// 关闭抗抖动
		gl.glDisable(GL10.GL_DITHER);
		// 设置特定Hint项目的模式，这里为设置为使用快速模式
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		// 设置屏幕背景色黑色RGBA
		gl.glClearColor(0, 0, 0, 0);
		// 启用深度测试
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// 设置为打开背面剪裁
		gl.glEnable(GL10.GL_CULL_FACE);
		// 创建single鱼类
		if (fishAl.size() == 0) {
			// 气泡的纹理
			
			// 水草的纹理ID
			waterweeds = initTexture(gl, "waterweeds.png");
			// 背景的纹理ID
			background = initTexture(gl, "background.png");
			//石头的纹理
			stone=initTexture(gl, "stone.png");
			bubbles = initTexture(gl, "bubble.png");
			fish2 = initTexture(gl, "fish2.png");
			fish1 = initTexture(gl, "fish1.png");
			if (waterweeds1 == null) {
				waterweeds1 = LoadUtil.loadFromFileVertexOnly("waterweeds.obj",
						TDRender.this.getResources());
			}
			if(stones==null)
			{
				stones=LoadUtil.loadFromFileVertexOnly("stone.obj",
						TDRender.this.getResources());
			}
			paint=new Paint();
            paint.setAntiAlias(true);
            paint.setTextAlign(Align.CENTER);
            paint.setTextSize(70f);
            list=new ArrayList<TextureRect>();
            TextureRect  textRect;
            for(int i=0;i<texts.length;i++){
            	
            Bitmap bitmap=Bitmap.createBitmap(600, 160, Config.ARGB_8888);
            	 paint.setColor(colors[i]);
                 Canvas canvas=new Canvas(bitmap);
                 canvas.drawText(texts[i], 300, 93, paint);
                 textRect=new TextureRect(3f, 0.8f, 0.4f, initTexture_Bitmap(gl,bitmap));
                 textRect.x=text_xyz[i][0];
                 textRect.y=text_xyz[i][1];
                 textRect.z=text_xyz[i][2];
                 list.add(textRect);
            }
            Bitmap mybitmap=getBitmap("bubble.png");
           paint.setTextSize(35f);
    		for (int i = 0; i <week.length ; i++) {
    			paint.setColor(colors[i]);
                Bitmap bitmap=Bitmap.createBitmap(64, 64, Config.ARGB_8888);
                Canvas canvas=new Canvas(bitmap);
                canvas.drawBitmap(mybitmap, 0,0, paint);
                canvas.drawText(week[i], 32, 41, paint);
                weektexId[i]=initTexture_Bitmap(gl,bitmap);
    			
    		}
			new Thread(){
				public void run() {
					if (waterweeds1 == null) {
						waterweeds1 = LoadUtil.loadFromFileVertexOnly("waterweeds.obj",
								TDRender.this.getResources());
					}
					if(stones==null)
					{
						stones=LoadUtil.loadFromFileVertexOnly("stone.obj",
								TDRender.this.getResources());
					}	
					fishAl.add(new SingleFish(fish1, TDRender.this, "fish1.obj",
							new Vector3f(-1, 2, 0),//位置
							new Vector3f(-0.02f, -0.02f, 0.00f), new Vector3f(0, 0, 0),
							new Vector3f(0, 0, 0), 70));
					
					fishAl.add(new SingleFish(fish2, TDRender.this, "fish2.obj",
							new Vector3f(-0, 3, 0), new Vector3f(0.02f, 0.01f, -0.01f),//速度
							new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 70));
					if(mMyBubbleControl==null){
						mMyBubbleControl=new MyBubbleControl(weektexId);
					}
					// 创建气泡的Control对象!
					if (bubbleControl == null) {
						bubbleControl = new BubbleControl(bubbles);
					}
					// 创建对象鱼类的Control对象
					if (fishControl == null) {
						fishControl = new FishControl(fishAl, TDRender.this);
					}
					
				};
			}.start();
			
			// 将单个鱼加入鱼类列表
			
		}
		// 创建背景纹景纹理!

		// 创建水草类对象!
		
		
	}
  public void stopAllThread()
  {
	  if(mMyBubbleControl!=null){
		  mMyBubbleControl.Bgt.flag=false;
	  }
	  if(bubbleControl!=null){
		  bubbleControl.Bgt.flag=false;
	  }
  }
	// 初始化灯光
	//初始化纹理
	public int initTexture_Bitmap(GL10 gl,Bitmap bitmap){
		//生成纹理ID
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);//生成一个纹理id放在textures数组中的0位置
		int currTextureId=textures[0];   //获取生成的纹理id 
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);//绑定该纹理id，后面的操作都是针对该id
		//设置MIN_FILTER与MAG_FILTER为MIPMAP纹理过滤方式
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR_MIPMAP_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR_MIPMAP_LINEAR);
		//生成MIPMAP纹理
		((GL11)gl).glTexParameterf(GL10.GL_TEXTURE_2D,GL11.GL_GENERATE_MIPMAP, GL10.GL_TRUE);
		//设置纹理拉伸方式为REPEAT 
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);//自动设置图片的格式和类型
        bitmap.recycle();
        return currTextureId;
	}
	// 初始化纹理
	public int initTexture(GL10 gl, String pname)// textureId
	{
		// 生成纹理ID
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		int currTextureId = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_CLAMP_TO_EDGE);
		InputStream in = null;
		try {
			in = this.getResources().getAssets().open(pname);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Bitmap bitmapTmp;
		try {
			bitmapTmp = BitmapFactory.decodeStream(in);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
		bitmapTmp.recycle();

		return currTextureId;
	}
	public Bitmap getBitmap(String pname)
	{
		InputStream in = null;
		try {
			in = this.getResources().getAssets().open(pname);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Bitmap bitmapTmp;
		try {
			bitmapTmp = BitmapFactory.decodeStream(in);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bitmapTmp;
		
	}

}
