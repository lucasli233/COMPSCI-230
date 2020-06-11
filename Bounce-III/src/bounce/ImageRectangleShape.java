package bounce;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRectangleShape extends RectangleShape{
	Image image;
	int nwidth;
	int nheight;
	public ImageRectangleShape(int deltaX, int deltaY, Image image){
		super(DEFAULT_X_POS, DEFAULT_Y_POS, deltaX, deltaY,
				image.getWidth(null), image.getHeight(null));
		this.image = image;
		nwidth = image.getWidth(null);
		nheight = image.getHeight(null);
	}
	//
	@Override
	public void doPaint(Painter painter) {
		painter.drawImage(image, _x,_y,nwidth,nheight);
	}

	public static Image makeImage(String imageFileName, int shapeWidth){
		int sw = shapeWidth;
		double sf = 0.00;

		File f = new File(imageFileName);
		BufferedImage b = null;
		try {
			b = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		double w = b.getWidth();
		double h = b.getHeight();
		if(w > sw){
			sf = (double) (sw / w);
		}
		int sh = (int) (h * sf);
		BufferedImage nb = new BufferedImage(sw, sh, b.getType());
		Graphics2D g = nb.createGraphics();
		g.drawImage(b, 0, 0, sw, sh, null);
		return nb;
	}
}
