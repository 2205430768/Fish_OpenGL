package mybubble;



import javax.microedition.khronos.opengles.GL10;
import com.bn.ld.Constant.Constant;
import com.bn.ld.draw.Bubble;

public class MySingleBubble  implements Comparable<MySingleBubble>{
	// 当前位置：随即产生
	float cuerrentX;
	float cuerrentY;
	float cuerrentZ;
	// 气泡的最大高度：随机产生
	float border;
	float[][] xy=new float[][]{
			{-0.67f,2.3f,9},
			{-0.15f,2.3f,9}
	};
	// 纹理ID
	int texld;
	// 气泡对象
	Bubble bubbles;
    float scale=0.3f;
    int id;
	// 构造器
	public MySingleBubble(int texld,int id) {
		this.texld = texld;
		this.id=id;
		newposition();
		bubbles = new Bubble();
	}

	public void drawSelf(GL10 gl) {
		//绘制气泡
		//gl.glPushMatrix();
		//System.out.println("绘制");
		gl.glTranslatef(cuerrentX, cuerrentY, cuerrentZ);
		gl.glScalef(scale, scale, scale);
		bubbles.drawSelf(gl,texld);
		//gl.glPopMatrix();
	}
	public void bubbleMove() {
		//气泡移动
		if (this.cuerrentY <border)
		this.cuerrentY += Constant.BubbleMoveDistance;
		/*if (this.cuerrentY > border) {
			newposition();*/
		//}
	}

	public void newposition() {
		if(id%2==0){
			cuerrentX = xy[0][0];
			cuerrentY = xy[0][1];
			cuerrentZ = xy[0][2];
		}
		else{
			cuerrentX = xy[1][0];
			cuerrentY = xy[1][1];
			cuerrentZ = xy[1][2];
			
		}
		border = 10;
	}

	@Override
	public int compareTo(MySingleBubble another) {
		//重写的比较两个仙人掌离摄像机距离的方法

		return ((this.cuerrentZ-another.cuerrentZ)==0)?0:((this.cuerrentZ-another.cuerrentZ)>0)?1:-1;  
	}
}
