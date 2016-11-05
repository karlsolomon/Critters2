package assignment5;

public class Point {
	private int x;
	private int y;
	public Point(){}
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Changes the location to match moving a given direction by one.
	 * If the critter moves off the side of the map it will reappear on the other side of the map.
	 * @param direction direction of movement in one of 8 directions: 2 vertical, 2 lateral, 4 diagonal
	 */
	public void update(int direction) {
		if(direction > 4) this.y = (this.y + 1) % Params.world_height;
		else if (direction < 4 && direction > 0 ) this.y--;
		if (direction == 7 || direction < 2) this.x = (this.x + 1) % Params.world_width;
		else if (direction < 6 && direction > 2) this.x --;
		if(this.x < 0){
			this.x += Params.world_width;
		}
		this.x %= Params.world_width;
		if(this.y < 0){
			this.y += Params.world_height;
		}
		this.y %= Params.world_height;
	}	
	/**
	 * Determines if two points hold the same location
	 * @param o the other point to compare location
	 * @return true if this and o hold the same location, false otherwise
	 */
	public boolean equals(Point o) {
		if(this.x == o.x && this.y == o.y){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static Point getrandomPoint(int min, int max){
		Point p = new Point(Critter.getRandomIntMinMax(min,max), Critter.getRandomIntMinMax(min,max));
		return p;
	}
}
