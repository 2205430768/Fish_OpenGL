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

	// ���������Id
	int fish1;
	int fish2;
	// ���ݵ�����Id
	int bubbles;
	// ˮ�ݵ�����Id
	int waterweeds;
	// ����������Id
	int background;
	// ʳ�������IdSD
	//ʯͷ����Id
	int stone;
	
	public MyBubbleControl mMyBubbleControl;
	// ʳ���X��Z���꣬ͨ��ACTION_DOWN����
	public static float Xposition;
	public static float Zposition;
	// �Ƿ����ʳ��ı�־λ
	public boolean Fooddraw = false;
	// ����ļ���
	public FishControl fishControl;
	// ������б�
	public ArrayList<SingleFish> fishAl = new ArrayList<SingleFish>();

	
	// ������Σ�����ͼƬ
	BackGround backgrounds;
	// ˮ����
	LoadedObjectVertexNormalTexture waterweeds1;
	//ʯͷ��
	LoadedObjectVertexNormalTexture stones;
	// ����
	BubbleControl bubbleControl;
	String[] texts={"stupid guy,boring","������ȥ��ɹ̫��","����������"};
	int[] colors=new int[]{Color.GREEN,Color.GREEN,Color.RED,0xff00ffff,Color.YELLOW,0xffffa500,0xff32cd32,Color.WHITE};
	String[] week={"Ǯ","Ǯ","С","��","��","��","��","��"};
	int[] weektexId=new int[8];
	float[][] text_xyz=new float[][]{
			{2.3f,-0.7f,1f},
			{1.7f,0.6f,1f},
			{-2.8f,0.6f,1f}
	};
	ArrayList<TextureRect> list;
	public int TEXT_STATUS=-1;
	Paint paint;
	private float yAngle;// ��y����ת�ĽǶ�
	private float zAngle;// ��z����ת�ĽǶ�

	// ��ת�ǶȵĹ�����
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
		// ����Ϊ�򿪱������
		gl.glEnable(GL10.GL_CULL_FACE);
		// ������ɫģ��Ϊƽ����ɫ
		gl.glShadeModel(GL10.GL_SMOOTH);
		// ����ΪĬ�Ͼ���˳�򡪡���ʱ��Ϊ����
		gl.glFrontFace(GL10.GL_CCW);
		// �����ɫ��������Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// ���õ�ǰ����Ϊģʽ����
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// ���õ�ǰ����Ϊ��λ����
		gl.glLoadIdentity();

		// ���������
		GLU.gluLookAt// ��̫���ܱ��ε��ӽǡ���С�ӽ�
		(
				gl,
				Constant.CameraX, // ����λ�õ�X
				Constant.CameraY, // ����λ�õ�Y
				Constant.CameraZ, // ����λ�� ��Z
				Constant.TargetX, // �����򿴵ĵ�X
				Constant.TargetY, // �����򿴵ĵ�Y
				Constant.TargetZ, // �����򿴵ĵ�Z
				Constant.UpX, //Up����
				Constant.UpY, 
				Constant.UpZ
						);
		//��������
		if(list!=null&&list.size()!=0&&TEXT_STATUS!=-1)
			list.get(TEXT_STATUS).drawSelf(gl);
		// ��Ҫ���Ķ�����-z���ƶ�
		// ����ˮ�ݺͱ���������
		gl.glPushMatrix();
		// ����ͼ
		if (backgrounds != null) {
			backgrounds.drawSelf(gl,background);
		}
		gl.glPopMatrix();
	
		
		gl.glPushMatrix();
		// ˮ��
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
		// ������
		//gl.glPushMatrix();
		//System.out.println("��"+this.fishAl.size());
		if (fishControl != null) {
			
			fishControl.drawSelf(gl);
		}
		//gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glEnable(GL10.GL_BLEND);
		// ����Դ���������Ŀ��������
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glTranslatef(0, 0, 15f);
		if (bubbleControl != null) {
			bubbleControl.drawSelf(gl);
		}
		
		
	     if(mMyBubbleControl!=null)
		{
	    	// System.out.println("��Ϊ��");
			mMyBubbleControl.drawSelf(gl);
				
		}
		
		// �رջ��
		gl.glDisable(GL10.GL_BLEND);
	    gl.glPopMatrix();
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// �����Ӵ���С��λ��
		gl.glViewport(0, 0, width, height);
		// ���õ�ǰ����ΪͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// ���õ�ǰ����Ϊ��λ����
		gl.glLoadIdentity();
		// ����͸��ͶӰ�ı���
		float ratio = (float) width / height;
		Constant.SCREEN_HEGHT=height;
		Constant.SCREEN_WIDTH=width;
		Constant.leftABS=ratio*Constant.View_SCALE;
		Constant.topABS=1 * Constant.View_SCALE;
		Constant.SCREEN_SCALEX=Constant.View_SCALE*((ratio>1)?ratio:(1/ratio));
		// ���ô˷����������͸��ͶӰ����
		gl.glFrustumf(-Constant.leftABS, Constant.leftABS, -Constant.topABS, 
				Constant.topABS, Constant.nearABS,Constant.farABS);
		// ���ó�ʼ������������������뵽����!
		MatrixUtil.setCamera(
				Constant.CameraX, // ����λ�õ�X
				Constant.CameraY, // ����λ�õ�Y
				Constant.CameraZ, // ����λ�� ��Z
				Constant.TargetX, // �����򿴵ĵ�X
				Constant.TargetY, // �����򿴵ĵ�Y	
				Constant.TargetZ, // �����򿴵ĵ�Z
				Constant.UpX, 
				Constant.UpY, 
				Constant.UpZ
				);
		if (backgrounds == null) {
			backgrounds = new BackGround();
		}
	}
	public void onSurfaceCreated(final GL10 gl, EGLConfig config) {
		// �رտ�����
		gl.glDisable(GL10.GL_DITHER);
		// �����ض�Hint��Ŀ��ģʽ������Ϊ����Ϊʹ�ÿ���ģʽ
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		// ������Ļ����ɫ��ɫRGBA
		gl.glClearColor(0, 0, 0, 0);
		// ������Ȳ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// ����Ϊ�򿪱������
		gl.glEnable(GL10.GL_CULL_FACE);
		// ����single����
		if (fishAl.size() == 0) {
			// ���ݵ�����
			
			// ˮ�ݵ�����ID
			waterweeds = initTexture(gl, "waterweeds.png");
			// ����������ID
			background = initTexture(gl, "background.png");
			//ʯͷ������
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
							new Vector3f(-1, 2, 0),//λ��
							new Vector3f(-0.02f, -0.02f, 0.00f), new Vector3f(0, 0, 0),
							new Vector3f(0, 0, 0), 70));
					
					fishAl.add(new SingleFish(fish2, TDRender.this, "fish2.obj",
							new Vector3f(-0, 3, 0), new Vector3f(0.02f, 0.01f, -0.01f),//�ٶ�
							new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 70));
					if(mMyBubbleControl==null){
						mMyBubbleControl=new MyBubbleControl(weektexId);
					}
					// �������ݵ�Control����!
					if (bubbleControl == null) {
						bubbleControl = new BubbleControl(bubbles);
					}
					// �������������Control����
					if (fishControl == null) {
						fishControl = new FishControl(fishAl, TDRender.this);
					}
					
				};
			}.start();
			
			// ����������������б�
			
		}
		// ���������ƾ�����!

		// ����ˮ�������!
		
		
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
	// ��ʼ���ƹ�
	//��ʼ������
	public int initTexture_Bitmap(GL10 gl,Bitmap bitmap){
		//��������ID
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);//����һ������id����textures�����е�0λ��
		int currTextureId=textures[0];   //��ȡ���ɵ�����id 
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);//�󶨸�����id������Ĳ���������Ը�id
		//����MIN_FILTER��MAG_FILTERΪMIPMAP������˷�ʽ
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR_MIPMAP_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR_MIPMAP_LINEAR);
		//����MIPMAP����
		((GL11)gl).glTexParameterf(GL10.GL_TEXTURE_2D,GL11.GL_GENERATE_MIPMAP, GL10.GL_TRUE);
		//�����������췽ʽΪREPEAT 
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);//�Զ�����ͼƬ�ĸ�ʽ������
        bitmap.recycle();
        return currTextureId;
	}
	// ��ʼ������
	public int initTexture(GL10 gl, String pname)// textureId
	{
		// ��������ID
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
