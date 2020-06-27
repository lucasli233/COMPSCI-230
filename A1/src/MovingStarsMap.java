/*
 *    ===============================================================================
 *    MovingEllipse.java : The subclass for star maps.
 *    NAME: Shengqi "Lucas" Li
 *    UPI: sli396
 *    ===============================================================================
 */

import java.awt.*;

public class MovingStarsMap extends MovingRectangle{

    public MovingStarsMap (){
        super.x=0;
        super.y=0;
        super.width=100;
        super.height=50;
        super.borderColor=Color.yellow;
        super.fillColor=Color.green;
        super.setPath(0);
    }

    public MovingStarsMap (int x){
        super.x=0;
        super.y=0;
        super.width=x;
        super.height=x;
        super.borderColor=Color.yellow;
        super.fillColor=Color.green;
        super.setPath(0);
    }

    public MovingStarsMap (int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String m, int pathType){
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

        x1 = (int) (Math.random() * width);
        y1 = (int) (Math.random() * height);
        x2 = (int) (Math.random() * width);
        y2 = (int) (Math.random() * height);
        x3 = (int) (Math.random() * width);
        y3 = (int) (Math.random() * height);
    }

    public String toString(){
        return String.format("[MovingStarsMap:(%d,%d) %d x %d, area=%.2f]", x, y, width, height, this.getArea());
    }

    @Override
    public void draw(Graphics g) {

        // draw 3 random lines
        g.setColor(fillColor);
        g.drawLine(x, y, x+x1, y+y1);
        g.drawLine(x+x1, y+y1, x+x2, y+y2);
        g.drawLine(x+x2, y+y2, x+x3, y+y3);

        //set up message
        g.setFont(new Font("a", Font.BOLD, 15));
        FontMetrics metrics = g.getFontMetrics();
        int sx = x + (width - metrics.stringWidth(message)) / 2; // string center
        int sy = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setColor(Color.black);
        g.drawString(message, sx, sy);
        drawHandles(g);
    }

}
