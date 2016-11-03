package assignment5;

import java.util.ArrayList;

import assignment5.Critter.CritterShape;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class CritterView {
	
	CritterShape shape;
	Color color;
	Shape fxShape;
	Point p;
	
	public CritterView(CritterShape shape, Color color, Point point) {
		this.shape = shape;
		this.color = color;
		this.p = point;
		makeFxShape();
	}
	
	
	private void makeFxShape() {
		if(shape.equals(CritterShape.CIRCLE)) {
			fxShape = new Circle(10, color);			
		}
		else if (shape.equals(CritterShape.SQUARE)) {
			fxShape = new Rectangle(10,10,color);
		}
		else if (shape.equals(CritterShape.TRIANGLE)) {
			
			fxShape = new Polygon(equilateralPoints(3, 90, 5));
			fxShape.setFill(color);
		}
		else if (shape.equals(CritterShape.DIAMOND)) {
			fxShape = new Polygon(equilateralPoints(4, 90, 5));
			fxShape.setFill(color);
		}
		else if (shape.equals(CritterShape.STAR)) {
			ArrayList<Double> points = new ArrayList<Double>();
			double[] longer = equilateralPoints(5,90,5);
			double[] shorter = equilateralPoints(5,90,2);
			for(int i = 0; i < longer.length; i ++) {
				points.add(longer[i]);
			}
			for(int i = 0; i < shorter.length; i ++) {
				points.add(shorter[i]);
			}
			double[] allPoints = new double[longer.length *2];
			for(int i = 0; i < points.size(); i++) {
				allPoints[i] = points.get(i);
			}
			fxShape = new Polygon(allPoints);
			fxShape.setFill(color);
			
			
			
		}
	}	
	
	private double[] equilateralPoints(int numPoints, double deg, int distance) {
		double degreeStep = 360/numPoints;
		double[] points = new double[numPoints*2];
		for(int i = 0; i < numPoints; i++) {
			points[2*i] = p.getX() + Math.cos(deg)*distance;
			points[2*i+1] = p.getY() + Math.sin(deg)*distance;
			deg += degreeStep;
		}
		return points;
	}
	
	
}
