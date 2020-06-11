package bounce.forms;

import bounce.ImageRectangleShape;
import bounce.NestingShape;
import bounce.ShapeModel;
import bounce.forms.util.Form;
import bounce.forms.util.FormHandler;
import bounce.forms.ImageShapeFormHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Task 3
 */
public class ImageShapeFormHandler implements FormHandler {

    private ShapeModel _model;
    private NestingShape _parentOfNewShape;
    private Form _form;
    private SwingWorker<ImageRectangleShape, Void> _worker;

    public ImageShapeFormHandler(ShapeModel model, NestingShape parent) {
        _model = model;
        _parentOfNewShape = parent;
    }

    @Override
    public void processForm(Form form) {

        _form = form;
        _worker = new ImageWorker();
        _worker.execute();
    }

    private class ImageWorker extends SwingWorker<ImageRectangleShape, Void> {
        @Override
        protected ImageRectangleShape doInBackground() throws Exception {
            long startTime = System.currentTimeMillis();

            // Read field values from the form.
            File imageFile = (File)_form.getFieldValue(File.class, ImageFormElement.IMAGE);
            int width = _form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
            int deltaX = _form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
            int deltaY = _form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);

            // Load the original image (ImageIO.read() is a blocking call).
            BufferedImage fullImage = null;
            try {
                fullImage = ImageIO.read(imageFile);
            } catch(IOException e) {
                System.out.println(e);
                System.out.println("Error loading image.");
            }

            int fullImageWidth = fullImage.getWidth();
            int fullImageHeight = fullImage.getHeight();

            BufferedImage scaledImage = fullImage;

            // Scale the image if necessary.
            if(fullImageWidth > width) {
                double scaleFactor = (double)width / (double)fullImageWidth;
                int height = (int)((double)fullImageHeight * scaleFactor);

                scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                Graphics2D g = scaledImage.createGraphics();

                // Method drawImage() scales an already loaded image. The
                // ImageObserver argument is null because we don't need to monitor
                // the scaling operation.
                g.drawImage(fullImage, 0, 0, width, height, null);
            }

            // Create the new Shape and add it to the model.
            ImageRectangleShape imageShape = new ImageRectangleShape(deltaX, deltaY, scaledImage);
            _model.add(imageShape, _parentOfNewShape);

            long elapsedTime = System.currentTimeMillis() - startTime;
            return imageShape;
        }

        @Override
        protected void done(){
            try {
                _model.add(get(), _parentOfNewShape);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
