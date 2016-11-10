/* Critter2 CritterView.java
 * EE422C Project 5 submission by
 * Christopher Sickler
 * cbs2468
 * 16445
 * Karl Solomon
 * kws653
 * 16445
 * Slip days used: <0>
 * Git URL: https://github.com/karlsolomon/Critters2 
 * Fall 2016
 */
package assignment5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import assignment5.Critter.CritterShape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Display World Class for Critter2. Represents the view component for each critter
 * @author KSolomon
 *
 */
public final class CritterView {
	
	//Params stuff
		public static int bin_size;
		public static double leftMargin = 180;
		public static double rightMargin = 10;
		public static double bottomMargin = 25;
		public static double topMargin = 35;
		public static int animation_speed = 1;
		public static double canvas_width = 800;
		public static double canvas_height = 600;
		
		public static void setBin() {		
			Double sizeDouble = (Double) Math.min(canvas_width/Params.world_width, canvas_height/Params.world_height);
			int size = sizeDouble.intValue();
			if(size % 2 != 0) {
				size--;
			}
			bin_size = size;
		}
		
		public static void setCanvasWidth(double width) {
			canvas_width = width - (leftMargin + rightMargin);
			setBin();
			Controller.canvas.setWidth(canvas_width);
		}
		
		public static void setCanvasHeight(double height) {
			canvas_height = height - (topMargin + bottomMargin);
			setBin();
			Controller.canvas.setHeight(canvas_height);
		}
		
		public static void setWorldParams(double width, double height) {
			Params.world_width = toInt(width);
			Params.world_height = toInt(height);
			setBin();
		}
	
	/**
	 * Draws an individual critter
	 * @param gc
	 * @param c
	 * @param p
	 */
	private static void draw(GraphicsContext gc, Critter c, Point p) {
	    CritterShape shape = c.viewShape();
	    double[] xPoints = getXCoords(p, getFxShape(shape)); 
	    double[] yPoints = getYCoords(p, getFxShape(shape));
	    Color color = c.viewColor();
	    gc.setFill(color);
	    gc.setLineWidth(1);
	    gc.setStroke(c.viewOutlineColor());
	    gc.strokePolygon(xPoints, yPoints, xPoints.length);
    	gc.fillPolygon(xPoints, yPoints, xPoints.length);
	}
	
	private static void drawGrid(GraphicsContext gc) {
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(0.1);
		double x1 = 0;
		double y1 = 0;
		double y2 = Params.world_height*CritterView.bin_size;
		
		//Width
		for(int i = 0; i <= Params.world_width; i++) {
	        gc.strokeLine(x1, y1, x1, y2);
	        x1 += CritterView.bin_size;
		}
		
		x1 = 0;
		double x2 = Params.world_width*CritterView.bin_size+1;
		y1 = 0;
		
		//Height
		for(int i = 0; i <= Params.world_height; i++) {
	        gc.strokeLine(x1, y1, x2, y1);
	        y1 += CritterView.bin_size;
		}
	}
	
	
	/**
	 * Displays the Critter World
	 */
	public static void drawWorld(){
	    GraphicsContext gc = Controller.canvas.getGraphicsContext2D();
	    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	    drawGrid(gc);
	    for(Critter c : CritterWorld.critterMap.keySet()) {
	    	draw(gc, c, CritterWorld.critterMap.get(c));
	    }
	}
	
	//private static Map<CritterShape, ArrayList<Double>> shapes = initShapes();;
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
		int distance = CritterView.bin_size/2;
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
		double xLocation = (double) p.getX()*CritterView.bin_size + CritterView.bin_size/2;
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
		double yLocation = (double) p.getY()*CritterView.bin_size + CritterView.bin_size/2;
		double[] yPoints = new double[allPoints.size()/2];
		for(int i = 0; i < allPoints.size()/2; i++) {
			yPoints[i] = allPoints.get(2*i+1) + yLocation;
		}
		return yPoints;
	}
	
	public static Double toDouble(int i) {
		Double d = Integer.valueOf(i).doubleValue();
		return d;
	}
	
	public static Integer toInt(double d) {
		Integer i = Double.valueOf(d).intValue();
		return i;
	}
	
}
