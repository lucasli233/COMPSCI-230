/*
 *    ===============================================================================
 *    MovingEllipse.java : The subclass for ellipses.
 *    NAME: Shengqi "Lucas" Li
 *    UPI: sli396
 *    ===============================================================================
 */

import java.awt.*;

public class MovingEllipse extends MovingShape{

    public MovingEllipse (){
        super.x=0;
        super.y=0;
        super.width=100;
        super.height=50;
        super.borderColor=Color.yellow;
        super.fillColor=Color.green;
        super.setPath(0);
    }

    public MovingEllipse(int x){
        super.x=0;
        super.y=0;
        super.width=x;
        super.height=x;
        super.borderColor=Color.yellow;
        super.fillColor=Color.green;
        super.setPath(0);
    }

    public MovingEllipse(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String m, int pathType){
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

    @Override
    public boolean contains(Point p) {
        double h = x+(0.5*width);//middle x
        double k = y+(0.5*height);//middle y
        double i = (Math.pow((p.getX() - h), 2)) / Math.pow(width/2, 2)
                + (Math.pow((p.getY() - k), 2)) / Math.pow(height/2, 2);
        if(i <= 1) return true;
        else return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(borderColor);
        g.fillOval(x, y, width, height);
        g.setColor(fillColor);
        g.fillOval(x+2, y+2, width-4, height-4);

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
        return Math.PI*(height/2)*(width/2);
    }

    public String toString(){
        return String.format("[MovingEllipse:(%d,%d) %d x %d, area=%.2f]", x, y, width, height, this.getArea());
    }

}
