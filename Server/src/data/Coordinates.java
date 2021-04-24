package data;

import java.io.Serializable;

/**
 * Field coordinates for class {@link SpaceMarine}
 * @author NastyaBordun
 * @version 1.1
 */

public class Coordinates implements Serializable {
    /**
     * Coordinate x
     */
    private long x;
    /**
     * Coordinate y
     */
    private Double y;

    /**
     * Empty constructor for class {@link Coordinates} (for correct Json parsing)
     * @see Coordinates#Coordinates(long, Double)
     */
    public Coordinates(){

    }

    /**
     * Constructor for class {@link Coordinates}
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coordinates(long x, Double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Getting field value {@link Coordinates#x}
     * @return {@link Coordinates#x}
     */
    public long getX() {
        return x;
    }

    /**
     * Setting field value {@link Coordinates#x}
     * @param x new x coordinate
     */
    public void setX(long x){
        this.x = x;
    }

    /**
     * Getting field value {@link Coordinates#y}
     * @return {@link Coordinates#y}
     */
    public Double getY() {
        return y;
    }

    /**
     * Setting field value {@link Coordinates#y}
     * @param y new y coordinate
     */
    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Coordinates coordinates = (Coordinates) o;
        if (this.hashCode() != coordinates.hashCode()) return false;
        return (this.getX() == coordinates.getX() && this.getY() == coordinates.getY());
    }

    @Override
    public int hashCode(){
        int ans = this.getY().hashCode() + (int)this.getX();
        return ans;
    }

    @Override
    public String toString(){
        String res = "Координата X: " + this.getX() + "\n";
        res += "Координата Y: " + this.getY() + "\n";
        return res;
    }
}
