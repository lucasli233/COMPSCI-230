package bounce;

import java.awt.Dimension;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import bounce.views.TreeModelAdapterWithShapeModelListener;
import org.junit.Before;
import org.junit.Test;

import bounce.NestingShape;
import bounce.RectangleShape;
import bounce.Shape;
import bounce.ShapeModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class to test the event notification mechanism of class
 * Task3.
 *
 * @author Ian Warren
 *
 */
public class TestTask2 {

    private ShapeModel _model;
    private NestingShape _root;
    private NestingShape _emptyNest;
    private Shape _simpleShape;
    private Shape _newShape;
    private TreeModelAdapterWithShapeModelListener _adapter;
    private boolean _listenerMethodCalled;


    /**
     * Creates a CarrierShape structure as the fixture for each test case.
     */
    @Before
    public void setUpShapeModel() {
        // Create model.
        _model = new ShapeModel(new Dimension(500, 500));
        _root = _model.root();

        // Create shapes.
        _emptyNest = new NestingShape(0, 0, 1, 1, 100, 100);
        _simpleShape = new RectangleShape(0, 0, 1, 1, 20, 20);
        _newShape = new RectangleShape(0, 0, 1, 1, 20, 20);

        // Populate model.
        _model.add(_emptyNest, _root);
        _model.add(_simpleShape, _root);

        // Create the adapter.
        _adapter = new TreeModelAdapterWithShapeModelListener(_model);

        // Register adapter as a listener of the model.
        _model.addShapeModelListener(_adapter);

        _listenerMethodCalled = false;
    }

    /**
     * Checks that calling Task2's update() method with a ShapeModelEvent that
     * describes a Shape's removal from a ShapeModel results in a correctly
     * constructed TreeModelEvent being sent to a registered TreeModelListener.
     */
    @Test
    public void test_ShapeRemoval() {
        _adapter.addTreeModelListener( new TreeModelListener() {

            public void treeNodesChanged( TreeModelEvent e ) {
                // Wrong TreeModelListener method called.
                fail();
            }

            public void treeNodesInserted( TreeModelEvent e ) {
                // Wrong TreeModelListener method called.
                fail();
            }

            public void treeNodesRemoved( TreeModelEvent e ) {
                _listenerMethodCalled = true;

                /* Unpack event. */
                int[] indices = e.getChildIndices();
                Object[] children = e.getChildren();
                Object[] path = e.getPath();

                /*
                 * Check the indices array identifies the index position of the
                 * removed node BEFORE it was removed.
                 */
                assertNotNull( indices );
                assertEquals( 1, indices.length );
                assertEquals( 1, indices[ 0 ] );

                /* Check the children array contains the single removed Shape. */
                assertNotNull( children );
                assertEquals( 1, children.length );
                assertSame( _simpleShape, children[ 0 ] );

                /*
                 * Check the path to the former parent of the changed node is
                 * correct.
                 */
                assertEquals( 1, path.length );
                assertSame( _root, path[ 0 ] );
            }

            public void treeStructureChanged( TreeModelEvent e ) {
                fail();
            }
        } );

        /*
         * Cause the ShapeModel to fire a ShapeModelEvent describing a shape
         * addition.
         */
        _model.remove( _simpleShape );
        assertTrue( _listenerMethodCalled );
    }

    /**
     * Checks that calling Task2's update() method with a ShapeModelEvent that
     * describes a Shape's addition to a ShapeModel results in a correctly
     * constructed TreeModelEvent being sent to a registered TreeModelListener.
     */
    @Test
    public void test_shapeAdded() {
        _adapter.addTreeModelListener( new TreeModelListener() {

            public void treeNodesChanged( TreeModelEvent e ) {
                // Wrong TreeModelListener method called.
                fail();
            }

            public void treeNodesInserted( TreeModelEvent e ) {
                _listenerMethodCalled = true;

                /* Unpack event. */
                int[] indices = e.getChildIndices();
                Object[] children = e.getChildren();
                Object[] path = e.getPath();

                /*
                 * Check the indices array identifies the index position of the
                 * inserted node (i.e. after insertion).
                 */
                assertNotNull( indices );
                assertEquals( 1, indices.length );
                assertEquals( 0, indices[ 0 ] );

                /* Check the children array contains the single removed . */
                assertNotNull( children );
                assertEquals( 1, children.length );
                assertSame( _newShape, children[ 0 ] );

                /* Check the path to the inserted node's parent is correct. */
                assertEquals( 2, path.length );
                assertSame( _root, path[ 0 ] );
                assertSame( _emptyNest, path[ 1 ] ); // Now not empty!
            }

            public void treeNodesRemoved( TreeModelEvent e ) {
                // Wrong TreeModelListener method called.
                fail();
            }

            public void treeStructureChanged( TreeModelEvent e ) {
                // Wrong TreeModelListener method called.
                fail();
            }
        } );

        /*
         * Cause the ShapeModel to fire a ShapeModelEvent describing a shape
         * addition.
         */
        _model.add( _newShape, _emptyNest );
        assertTrue( _listenerMethodCalled );
    }
}