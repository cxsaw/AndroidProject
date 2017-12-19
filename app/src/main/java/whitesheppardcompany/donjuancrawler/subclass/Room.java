package whitesheppardcompany.donjuancrawler.subclass;

/**
 * Created by saw on 19/12/17.
 */

public class Room {


    /*  boolean pour set up les wayout   */
    private boolean east;
    private boolean west;
    private boolean north;



    /*
    *
    *           Setters & Getters
    *
    */


    public boolean isEast() {
        return east;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public boolean isWest() {
        return west;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public boolean isNorth() {
        return north;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }
}
