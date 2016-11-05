package assignment5;

import java.util.ArrayList;

//import com.google.common.reflect.*;

import assignment5.Critter.CritterShape;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class CritterView {
	public static int maxDimensionOfShape = 10;
	private CritterShape shape;
	private Color color;
	Point p;
	double[] xCoords;
	double[] yCoords;
	int size;
	
	
	public CritterView(CritterShape shape, Color color, Point point) {
		this.shape = shape;
		this.color = color;
		this.p = point;
		makeFxShape();		
	}
	
	
	private void makeFxShape() {
		if(shape.equals(CritterShape.CIRCLE)) {
			setXYCoords(equilateralPoints(360, 0, maxDimensionOfShape));
		}
		else if (shape.equals(CritterShape.SQUARE)) {
			setXYCoords(equilateralPoints(4, 90, maxDimensionOfShape));
		}
		else if (shape.equals(CritterShape.TRIANGLE)) {
			setXYCoords(equilateralPoints(3, 30, maxDimensionOfShape));			
		}
		else if (shape.equals(CritterShape.DIAMOND)) {
			setXYCoords(equilateralPoints(4, 45, maxDimensionOfShape));
		}
		else if (shape.equals(CritterShape.STAR)) {
			ArrayList<Double> points = new ArrayList<Double>();
			double[] longer = equilateralPoints(5,90-36,maxDimensionOfShape);
			double[] shorter = equilateralPoints(5,90,5);
			for(int i = 0; i < longer.length/2; i ++) {
				points.add(longer[2*i]);
				points.add(longer[2*i + 1]);
				points.add(shorter[2*i]);
				points.add(shorter[2*i + 1]);
			}
			double[] allPoints = new double[longer.length *2];
			for(int i = 0; i < points.size(); i++) {
				allPoints[i] = points.get(i);
			}
			setXYCoords(allPoints);
		}
	}	
	
	private double[] equilateralPoints(int numPoints, double deg, int distance) {
		double degreeStep = 360/numPoints;
		double[] points = new double[numPoints*2];
		for(int i = 0; i < numPoints; i++) {
			points[2*i] = p.getX() + Math.cos(Math.toRadians(deg))*distance;
			points[2*i+1] = p.getY() + Math.sin(Math.toRadians(deg))*distance;
			deg += degreeStep;
		}
		return points;
	}
	
	private void setXYCoords(double[] points){
		xCoords = new double[points.length/2];
		yCoords = new double[points.length/2];
		for(int i = 0; i < points.length/2; i++) {
			xCoords[i] = points[2*i];
			yCoords[i] = points[2*i+1];
		}
		size = xCoords.length + yCoords.length;
	}
	
	public double[] getXCoords(){
		return xCoords;
	}
	
	public double[] getYCoords() {
		return yCoords;
	}
	
	public Color getColor() {
		return this.color;
	}
	
}
