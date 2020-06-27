/*
 *    ===============================================================================
 *    MovingEllipse.java : The subclass for spinning circles.
 *    NAME: Shengqi "Lucas" Li
 *    UPI: sli396
 *    ===============================================================================
 */

import java.awt.*;

public class MovingSpinningCircle  extends MovingEllipse{
    private int startAngle = 0;
    public int ROTATION_SPEED = 20;

    public MovingSpinningCircle(){
        x=0;
        y=0;
        width=50;
        height=50;
        borderColor=Color.yellow;
        fillColor=Color.green;
        setPath(0);
    }

    public MovingSpinningCircle (int x){
        super.x=0;
        super.y=0;
        width=x;
        height=x;
        borderColor=Color.yellow;
        fillColor=Color.green;
        setPath(0);
    }

    public MovingSpinningCircle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String m, int pathType){
        super.x=x;
        super.y=y;
        if(w<=h){
            width = w;
            height = w;
        }else{
            width = h;
            height = h;
        }
        marginWidth=mw;
        marginHeight=mh;
        borderColor=bc;
        fillColor=fc;
        message=m;
        setPath(pathType);
    }

    public void setHeight(int h){
        width = h;
        height = h;
    }
    public void setWidth(int w){
        width = w;
        height = w;
    }


    @Override
    public void draw(Graphics g) {
        // base white circle
        g.setColor(borderColor);
        g.fillOval(x, y, width, height);
        g.setColor(Color.white);
        g.fillOval(x+2, y+2, width-4, height-4);

        // arc
        startAngle = (startAngle - ROTATION_SPEED) % 360;
        g.setColor(fillColor);
        g.fillArc(getX(), getY(), width, height, startAngle, 90);
        g.fillArc(getX(), getY(), width, height, startAngle + 180, 90);

        //set up message
        g.setFont(new Font("a", Font.BOLD, 15));
        FontMetrics metrics = g.getFontMetrics();
        int sx = x + (width - metrics.stringWidth(message)) / 2; // string center
        int sy = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setColor(Color.black);
        g.drawString(message, sx, sy);
        drawHandles(g);
    }

    public String toString(){
        return String.format("[MovingSpinningCircle:(%d,%d) %d x %d, area=%.2f]", x, y, width, height, this.getArea());
    }
}
