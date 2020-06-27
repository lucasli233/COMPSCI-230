/*
 *    ===============================================================================
 *    MovingEllipse.java : The subclass for squares.
 *    NAME: Shengqi "Lucas" Li
 *    UPI: sli396
 *    ===============================================================================
 */

import java.awt.*;

public class MovingSquare extends MovingRectangle{

    public MovingSquare(){
        super.x=0;
        super.y=0;
        super.width=50;
        super.height=50;
        super.borderColor=Color.yellow;
        super.fillColor=Color.green;
        super.setPath(0);
    }

    public MovingSquare(int x){
        super.x=0;
        super.y=0;
        super.width=x;
        super.height=x;
        super.borderColor=Color.yellow;
        super.fillColor=Color.green;
        super.setPath(0);
    }

    public MovingSquare(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String m, int pathType){
        super.x=x;
        super.y=y;
        if(w<=h){
            width = w;
            height = w;
        }else{
            width = h;
            height = h;
        }
        super.marginWidth=mw;
        super.marginHeight=mh;
        super.borderColor=bc;
        super.fillColor=fc;
        super.message=m;
        super.setPath(pathType);
    }

    public void setHeight(int h){
        width = h;
        height = h;
    }
    public void setWidth(int w){
        width = w;
        height = w;
    }

    public String toString(){
        return String.format("[MovingSquare:(%d,%d) %d x %d, area=%.2f]", x, y, width, width, this.getArea());
    }

}
