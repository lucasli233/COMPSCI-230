package bounce;

import java.awt.*;

public class RectangleShape extends Shape {

	public RectangleShape() {
		super();
	}

	public RectangleShape(int x, int y) {
		super(x, y);
	}
	public RectangleShape(int x, int y, String str) {
		super(x, y, str);
	}

	public RectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}

	public RectangleShape(int x, int y, int deltaX, int deltaY, String str) {
		super(x,y,deltaX,deltaY, str);
	}

	public RectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}

	public RectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String str) {
		super(x,y,deltaX,deltaY,width,height, str);
	}

	@Override
	protected void doPaint(Painter painter) {
		painter.drawRect(_x,_y,_width,_height);
	}

}
