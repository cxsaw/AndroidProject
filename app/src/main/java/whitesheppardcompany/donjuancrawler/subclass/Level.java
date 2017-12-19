package whitesheppardcompany.donjuancrawler.subclass;

import java.util.List;
import java.util.Vector;

/**
 * Created by saw on 19/12/17.
 */

public class Level {


    String name;
    String LvlLore; // à persister?
    List <Room> rooms = new Vector<Room>();

    public Level(){};

    public Level(String name, String lvlLore, List<Room> rooms) {
        this.name = name;
        LvlLore = lvlLore;
        this.rooms = rooms;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLvlLore() {
        return LvlLore;
    }

    public void setLvlLore(String lvlLore) {
        LvlLore = lvlLore;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
