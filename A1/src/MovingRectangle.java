/*
 *    ===============================================================================
 *    MovingEllipse.java : The subclass for rectangles.
 *    NAME: Shengqi "Lucas" Li
 *    UPI: sli396
 *    ===============================================================================
 */

import java.awt.*;

public class MovingRectangle extends MovingShape{

    public MovingRectangle(){
        super.x=0;
        super.y=0;
        super.width=100;
        super.height=50;
    }

    public MovingRectangle(int x){
        super.x=0;
        super.y=0;
        super.width=x;
        super.height=x;
    }

    public MovingRectangle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String m, int pathType){
        super.x=x;
        super.y=y;
        super.width=w;
        super.height=h;
        super.marginWidth=mw;
        super.marginHeight=mh;
        super.borderColor=bc;
        super.fillColor=fc;
        super.message=m;
        super.setPath(pathType);
    }

    public MovingRectangle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, int pathType){
        super.x=x;
        super.y=y;
        super.width=w;
        super.height=h;
        super.marginWidth=mw;
        super.marginHeight=mh;
        super.borderColor=bc;
        super.fillColor=fc;
        super.setPath(pathType);
    }

    public String toString(){
        return String.format("[MovingRectangle:(%d,%d) %d x %d, area=%.2f]", x, y, width, height, this.getArea());
    }

    @Override
    public boolean contains(Point p) {
        if(x <= p.getX() && p.getX() <= x+width && y <= p.getY()&& p.getY() <= x+height) return true;
        else return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(borderColor);
        g.fillRect(x, y, width, height);
        g.setColor(fillColor);
        g.fillRect(x+2, y+2, width-4, height-4);

        //set up message
        g.setFont(new Font("a", Font.BOLD, 15));
        FontMetrics metrics = g.getFontMetrics();
        int sx = x + (width - metrics.stringWidth(message)) / 2; // string center
        int sy = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setColor(Color.black);
        g.drawString(message, sx, sy);
        drawHandles(g);
    }

    @Override
    public double getArea() {
        return width*height;
    }

}
