package bounce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NestingShape extends Shape {
    private ArrayList<Shape> nest = new ArrayList<Shape>();

    public NestingShape() {
        super();
    }

    public NestingShape(int x, int y) {
        super(x, y);
    }

    public NestingShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }

    public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
    }

    public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height, String str) {
        super(x, y, deltaX, deltaY, width, height, str);
    }

//        @Override
//    public void move(int width, int height) {
//        int nextX = _x + _deltaX;
//        int nextY = _y + _deltaY;
//
//        if (nextX <= 0) {
//            nextX = 0;
//            _deltaX = -_deltaX;
//        } else if (nextX + _width >= width) {
//            nextX = width - _width;
//            _deltaX = -_deltaX;
//        }
//
//        if (nextY <= 0) {
//            nextY = 0;
//            _deltaY = -_deltaY;
//        } else if (nextY + _height >= height) {
//            nextY = height - _height;
//            _deltaY = -_deltaY;
//        }
//
//        _x = nextX;
//        _y = nextY;
//
//        for (int i = 0; i < shapeCount(); i++) {
//            Shape S = shapeAt(i);
//            int SnextX = S._x + S._deltaX;
//            int SnextY = S._y + S._deltaY;
//
//            if (SnextX <= 0) {
//                SnextX = 0;
//                S._deltaX = -S._deltaX;
//            } else if (SnextX + S._width >= _width) {
//                SnextX = _width - S._width;
//                S._deltaX = -S._deltaX;
//            }
//
//            if (SnextY <= 0) {
//                SnextY = 0;
//                S._deltaY = -S._deltaY;
//            } else if (SnextY + S._height >= _height) {
//                SnextY = _height - S._height;
//                S._deltaY = -S._deltaY;
//            }
//
//            S._x = SnextX;
//            S._y = SnextY;
//        }
//
//    }

    public void move(int x, int y, int width, int height) {
        super.move(x, y, width, height);
        for (Shape s : nest) {
            s.move(x, y, _width, _height);
        }
    }

    @Override
    protected void doPaint(Painter painter) {
        painter.drawRect(_x, _y, _width, _height);
        painter.translate(_x, _y);
        for (Shape s : nest) {
            s.paint(painter);
        }
        painter.translate(-_x, -_y);
    }


    void add(Shape shape) throws IllegalArgumentException {
        if (nest.contains(shape) ||
                shape.parent() != null ||
                shape.width() > _width ||
                shape.height() > _height ||
                shape.x() + shape.width() > _x + _width ||
                shape.y() + shape.height() > _y + _height) {
            throw new IllegalArgumentException();
        } else {
            nest.add(shape);
            shape._parent = this;
        }
    }

    void remove(Shape shape) {
        if (indexOf(shape) < 0 || indexOf(shape) >= shapeCount()) throw new IndexOutOfBoundsException();
        else {
            nest.remove(indexOf(shape));
            shape._parent = null;
        }
    }

    public Shape shapeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= shapeCount()) throw new IndexOutOfBoundsException();
        else return nest.get(index);
    }

    public int shapeCount() {
        return nest.size();
    }

    public int indexOf(Shape shape) {
        return nest.indexOf(shape);
    }

    public boolean contains(Shape shape) {
        return nest.contains(shape);
    }


}
