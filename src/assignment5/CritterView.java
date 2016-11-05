package assignment5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import assignment5.Critter.CritterShape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Display World Class for Critter2. Represents the view component for each critter
 * @author KSolomon
 *
 */
public final class CritterView {
	/**
	 * Draws an individual critter
	 * @param gc
	 * @param c
	 * @param p
	 */
	private static void Draw(GraphicsContext gc, Critter c, Point p) {
	    CritterShape shape = c.viewShape();
	    double[] xPoints = getXCoords(p, getFxShape(shape)); 
	    double[] yPoints = getYCoords(p, getFxShape(shape));

	    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	    gc.setFill(c.viewColor());
    	gc.fillPolygon(xPoints, yPoints, xPoints.length);
	}
	
	
	/**
	 * Displays the Critter World
	 */
	public static void drawWorld(){
		if(Controller.world == null) {
			Controller.world = new Canvas();
			Controller.world.layoutXProperty().set(0);
			Controller.world.layoutYProperty().set(0);
			Controller.world.widthProperty().set(Params.canvas_width);
			Controller.world.heightProperty().set(Params.canvas_height);
		}
	    GraphicsContext gc = Controller.world.getGraphicsContext2D();
	    for(Critter c : CritterWorld.critterMap.keySet()) {
	    	Draw(gc, c, CritterWorld.critterMap.get(c));
	    }
	}
	
	private static Map<CritterShape, ArrayList<Double>> shapes = initShapes();;
	public static Map<CritterShape, ArrayList<Double>> initShapes() {
		Map<CritterShape, ArrayList<Double>> shapes = new HashMap<CritterShape, ArrayList<Double>>();
		
		for(CritterShape shape : Critter.CritterShape.values()) {
			shapes.put(shape, getFxShape(shape));
		}
		return shapes;
	}
	
	/**
	 * Returns array of points for shape
	 * @param shape
	 * @return
	 */
	private static ArrayList<Double> getFxShape(CritterShape shape) {
		if(shape.equals(CritterShape.CIRCLE)) {
			return equilateralPoints(360, 0);
		}
		else if (shape.equals(CritterShape.SQUARE)) {
			return equilateralPoints(4, 90);
		}
		else if (shape.equals(CritterShape.TRIANGLE)) {
			return equilateralPoints(3, 30);			
		}
		else if (shape.equals(CritterShape.DIAMOND)) {
			return equilateralPoints(4, 45);
		}
		else if (shape.equals(CritterShape.STAR)) {
			ArrayList<Double> longer = equilateralPoints(5,90-36);
			ArrayList<Double> shorter = equilateralPoints(5,90);
			ArrayList<Double> points = new ArrayList<Double>();
			for(int i = 0; i < longer.size()/2; i ++) {
				points.add(longer.get(2*i));
				points.add(longer.get(2*i + 1));
				points.add(shorter.get(2*i));
				points.add(shorter.get(2*i + 1));
			}
			return points;
		}
		else return null;
	}	
	
	/**
	 * Used to construct the original shape templates
	 * @param numPoints
	 * @param deg
	 * @return
	 */
	private static ArrayList<Double> equilateralPoints(int numPoints, double deg) {
		int distance = Params.bin_size/2;
		double degreeStep = 360/numPoints;
		ArrayList<Double> pts = new ArrayList<Double>();
		for(int i = 0; i < numPoints; i++) {
			pts.add(Math.cos(Math.toRadians(deg))*distance); // x value
			pts.add(Math.sin(Math.toRadians(deg))*distance); // y value
			deg += degreeStep;
		}
		return pts;
	}
	
	/**
	 * Returns xCoordinates of the shape of a particular critter at a given xPoint
	 * @param p
	 * @param allPoints
	 * @return
	 */
	public static double[] getXCoords(Point p, ArrayList<Double> allPoints) {
		double xLocation = (double) p.getX()*Params.bin_size + Params.bin_size/2;
		double[] xPoints = new double[allPoints.size()/2];
		for(int i = 0; i < allPoints.size()/2; i++) {
			xPoints[i] = allPoints.get(2*i) + xLocation;
		}
		return xPoints;
	}
	
	/**
	 * Returns yCoordinates of the shape of a particular critter at a given yPoint
	 * @param p
	 * @param allPoints
	 * @return
	 */
	public static double[] getYCoords(Point p, ArrayList<Double> allPoints) {
		double yLocation = (double) p.getY()*Params.bin_size + Params.bin_size/2;
		double[] yPoints = new double[allPoints.size()/2];
		for(int i = 0; i < allPoints.size()/2; i++) {
			yPoints[i] = allPoints.get(2*i+1) + yLocation;
		}
		return yPoints;
	}
	
}
