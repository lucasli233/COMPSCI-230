package bounce.views;

import bounce.*;
import bounce.ShapeModelListener;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import java.util.ArrayList;
import java.util.List;

/** Task 2*/
public class TreeModelAdapterWithShapeModelListener extends TreeModelAdapter implements ShapeModelListener, TreeModelListener{
    JTree _jtree;
    public List<TreeModelListener> _treeModelListeners = new ArrayList<TreeModelListener>();

    public TreeModelAdapterWithShapeModelListener(ShapeModel model) {
        super(model);
        _jtree = new JTree(this);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        _treeModelListeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        _treeModelListeners.remove(l);
    }

    @Override
    public void update(ShapeModelEvent event) {
        ShapeModelEvent.EventType eventType = event.eventType();
        int[] childIndices =  {event.index()};
        Shape[] children =  {event.operand()};
        if(eventType.equals(ShapeModelEvent.EventType.ShapeAdded)) {
            Object[] path = event.parent().path().toArray();
            TreeModelEvent add = new TreeModelEvent(_adaptee,path,childIndices,children);
            for(TreeModelListener l : _treeModelListeners) {
                l.treeNodesInserted(add);
            }
        }else if(eventType.equals(ShapeModelEvent.EventType.ShapeRemoved)) {
            Object[] path = event.parent().path().toArray();
            TreeModelEvent remove = new TreeModelEvent(_adaptee,path,childIndices,children);
            for(TreeModelListener l : _treeModelListeners) {
                l.treeNodesRemoved(remove);
            }
        }

    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {

    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        for(TreeModelListener listener: _treeModelListeners) {
            listener.treeNodesInserted(e);
        }

    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        for(TreeModelListener listener: _treeModelListeners) {
            listener.treeNodesRemoved(e);
        }
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
    }

}