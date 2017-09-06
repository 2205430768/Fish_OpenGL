package mybubble;


//定时运动所有鱼类的线程
public class MyBubbleThread extends Thread {
	public boolean flag = true;
	MyBubbleControl Bcl;
	public MyBubbleThread(MyBubbleControl Bcl) {
		this.Bcl=Bcl;
}
	public void run()
	{
		// 循环定时移动气泡
		while (flag) {
			try {
				for(int i=0;i<Bcl.count;i++)
				{
					if(Bcl.BubbleSingle.get(i).scale<2)
					{
						Bcl.BubbleSingle.get(i).cuerrentY-=0.002f;
						Bcl.BubbleSingle.get(i).cuerrentX+=0.2*(Math.random()-0.5f);
						Bcl.BubbleSingle.get(i).scale+=0.2f;
					}
					else{
						Bcl.BubbleSingle.get(i).bubbleMove();	
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
