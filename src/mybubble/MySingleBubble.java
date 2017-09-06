package mybubble;



import javax.microedition.khronos.opengles.GL10;
import com.bn.ld.Constant.Constant;
import com.bn.ld.draw.Bubble;

public class MySingleBubble  implements Comparable<MySingleBubble>{
	// ��ǰλ�ã��漴����
	float cuerrentX;
	float cuerrentY;
	float cuerrentZ;
	// ���ݵ����߶ȣ��������
	float border;
	float[][] xy=new float[][]{
			{-0.67f,2.3f,9},
			{-0.15f,2.3f,9}
	};
	// ����ID
	int texld;
	// ���ݶ���
	Bubble bubbles;
    float scale=0.3f;
    int id;
	// ������
	public MySingleBubble(int texld,int id) {
		this.texld = texld;
		this.id=id;
		newposition();
		bubbles = new Bubble();
	}

	public void drawSelf(GL10 gl) {
		//��������
		//gl.glPushMatrix();
		//System.out.println("����");
		gl.glTranslatef(cuerrentX, cuerrentY, cuerrentZ);
		gl.glScalef(scale, scale, scale);
		bubbles.drawSelf(gl,texld);
		//gl.glPopMatrix();
	}
	public void bubbleMove() {
		//�����ƶ�
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
		//��д�ıȽ����������������������ķ���

		return ((this.cuerrentZ-another.cuerrentZ)==0)?0:((this.cuerrentZ-another.cuerrentZ)>0)?1:-1;  
	}
}
