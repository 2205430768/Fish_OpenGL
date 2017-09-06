package com.bn.ld.UtilTools;

import com.bn.ld.Vecrors.Vector3f;

public class IntersectantUtil {	
	public static float[] mVMatrix = new float[16];
	public static Vector3f[] calculateABPosition
	(
		float x,
		float y,
		float w,
		float h,
		float left,
		float top,
		float near,
		float far
	)
	{
		float x0=x-w/2;
		float y0=h/2-y;		
		float xNear=2*x0*left/w;
		float yNear=2*y0*top/h;
		float ratio=far/near;
		float xFar=ratio*xNear;
		float yFar=ratio*yNear;
        float ax=xNear;
        float ay=yNear;
        float az=-near;
        float bx=xFar;
        float by=yFar;
        float bz=-far; 
		Vector3f start = MatrixUtil.fromGToO
		(
			new Vector3f( ax, ay, az ),
			MatrixUtil.mVMatrix
		);
		Vector3f end = MatrixUtil.fromGToO
		(
			new Vector3f( bx, by, bz ),
			MatrixUtil.mVMatrix
		);
		return new Vector3f[] {
			start,
			end
		};
	}
}