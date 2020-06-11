package bounce.views;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/** Task 1*/
public class TreeModelAdapter implements TreeModel{

    public ShapeModel _adaptee;
    public TreeModelAdapter(ShapeModel shape) {
        _adaptee = shape;
    }


    @Override
    public Object getRoot() {
        return _adaptee.root();
    }

    @Override
    public Object getChild(Object parent, int index) {
        Object result = null;
        if (parent instanceof NestingShape) {
            NestingShape res = (NestingShape) parent;
            if(index >= 0 && index < this.getChildCount(res)){
                result = res.shapeAt(index);
            }
        }
        return result;
    }

    @Override
    public int getChildCount(Object parent) {
        int result = 0;
        if(parent instanceof NestingShape){
            NestingShape res = (NestingShape) parent;
            result = res.shapeCount();
        }
        return result;
    }


    @Override
    public boolean isLeaf(Object node) {
        return !(node instanceof NestingShape);
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {}

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        int indexOfChild = -1;
        if (parent instanceof NestingShape && child instanceof Shape) {
            NestingShape res = (NestingShape) parent;
            indexOfChild = res.indexOf((Shape)child);
        }
        return indexOfChild;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }

}
