package bounce;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {

    // === Constants for default values. ===
    protected static final int DEFAULT_X_POS = 0;

    protected static final int DEFAULT_Y_POS = 0;

    protected static final int DEFAULT_DELTA_X = 5;

    protected static final int DEFAULT_DELTA_Y = 5;

    protected static final int DEFAULT_HEIGHT = 35;

    protected static final int DEFAULT_WIDTH = 25;

    // ===

    // === Instance variables, accessible by subclasses.
    protected int _x;

    protected int _y;

    protected int _deltaX;

    protected int _deltaY;

    protected int _width;

    protected int _height;

    protected String _str;

    protected NestingShape _parent;

    protected boolean _topBot = false;


    public Shape() {
        this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Shape(int x, int y) {
        this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Shape(int x, int y, String str) {
        this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT, str);
    }

    public Shape(int x, int y, int deltaX, int deltaY) {
        this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Shape(int x, int y, int deltaX, int deltaY, String str) {
        this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT, str);
    }

    public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String str) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
        _str = str;
    }

    public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
    }

    public void move(int x, int y, int width, int height) {
        int nextX = _x + _deltaX;
        int nextY = _y + _deltaY;

        if (nextX <= x) {
            nextX = x;
            _deltaX = -_deltaX;
            _topBot = false;
        } else if (nextX + _width >= width) {
            nextX = x + width - _width;
            _deltaX = -_deltaX;
            _topBot = true;
        }

        if (nextY <= y) {
            nextY = y;
            _deltaY = -_deltaY;
            _topBot = false;
        } else if (nextY + _height >= height) {
            nextY = y + height - _height;
            _deltaY = -_deltaY;
            _topBot = true;
        }
        _x = nextX;
        _y = nextY;
    }

    public void move(int width, int height) {
        this.move(0, 0, width, height);
    }

    public void paint(Painter painter) {
        this.doPaint(painter);
        doText(painter);
    }


    protected abstract void doPaint(Painter painter);

    public void doText(Painter painter) {
        if (_str != null) painter.drawCenteredText(_str, _x + _width / 2, _y + _height / 2);
    }

    public int x() {
        return _x;
    }

    public int y() {
        return _y;
    }

    public int deltaX() {
        return _deltaX;
    }

    public int deltaY() {
        return _deltaY;
    }

    public int width() {
        return _width;
    }

    public int height() {
        return _height;
    }

    public String text() {
        return _str;
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }

    public NestingShape parent() {
        return _parent;
    }

    protected void setParent(NestingShape parent) {
        _parent = parent;
    }

    /**
     * Returns an ordered list of Shape objects. The first item within the list is the root NestingShape
     * of the containment hierarchy. The last item within the list is the callee object (hence this method
     * always returns a list with at least one item). Any intermediate items are NestingShapes that
     * connect the root NestingShape to the callee Shape. E.g. given:
     * NestingShape root = new NestingShape ();
     * NestingShape intermediate = new NestingShape ();
     * Shape oval = new OvalShape ();
     * root.add(intermediate);
     * intermediate.add(oval);
     * a call to oval.path() yields: [root , intermediate , oval]
     */
    public List<Shape> path() {
        List<Shape> parents = new ArrayList<Shape>();
        parents.add(0, this);
        NestingShape parent = _parent;
        while (parent != null) {
            parents.add(0, parent);
            parent = parent.parent();
        }
        return parents;
    }
}
