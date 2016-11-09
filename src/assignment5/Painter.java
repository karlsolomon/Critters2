/* Critter2 Painter.java
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
 */package assignment5;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Painter {

	/*
	 * Returns a square or a circle, according to shapeIndex
	 */
	static Shape getIcon(int shapeIndex) {
		Shape s = null;
		int size = 100;
		/**
		 * Cases:						| Shapes:	| Colors:
		 * Case 0: Algae				| circle	| green
		 * Case 1: Critter1				| square 	| blue
		 * Case 2: Critter2				| triangle	| red
		 * Case 3: Critter3				| star		| orange
		 * Case 4: Critter4				| diamond	| black
		 * Case 5: AlgaephobicCritter	| diamond	| salmon
		 */
		switch(shapeIndex) {
		case 0: s = new Rectangle(size, size); 
			s.setFill(javafx.scene.paint.Color.RED); break;
		case 1: s = new Circle(size/2); 
			s.setFill(javafx.scene.paint.Color.GREEN); break;
		}
		// set the outline of the shape
		s.setStroke(javafx.scene.paint.Color.BLUE); // outline
		return s;
	}
}
