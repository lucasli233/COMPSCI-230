package bounce;

import java.awt.*;

/*
 *    ===============================================================================
 *    Class to represent a dynamic rectangle. Depending on how it bounces, the dynamic
 *    rectangle alters its appearance.
 *    NAME: Shengqi "Lucas" Li
 *    UPI: sli396
 *    ===============================================================================
 */

public class DynamicRectangleShape extends RectangleShape{
    Color color = Color.red;


    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY,
                                 int width, int height){
        super(x,y,deltaX,deltaY,width,height);

    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY,
                                 int width, int height, String str){
        super(x,y,deltaX,deltaY,width,height, str);
    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY,
                                 int width, int height, Color color){
        super(x,y,deltaX,deltaY,width,height);
        this.color = color;
    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY,
                                 int width, int height, String str, Color color){
        super(x,y,deltaX,deltaY,width,height, str);
        this.color = color;
    }



    @Override
    public void doPaint(Painter painter) {
        Color ogColor = painter.getColor();
        if(!_topBot){
            painter.drawRect(_x,_y,_width,_height);
        }
        if(_topBot){
            painter.setColor(color);
            painter.fillRect(_x,_y,_width,_height);
            painter.setColor(ogColor);
        }
    }

}
