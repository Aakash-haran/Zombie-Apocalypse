package main.java;

public class Position {
	int x;
	int y;
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
	public static Position setNewPosition(int x, int y) {
		Position position = new Position();
		position.x= x;
		position.y = y;
		return position;
	}
	
	@Override
	public int hashCode(){
	    return this.x+this.y;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Position)) return false;
	    Position o = (Position) obj;
	    return o.x == this.x && o.y == this.y;
	}
}
