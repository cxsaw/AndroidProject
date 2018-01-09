package whitesheppardcompany.donjuancrawler.Beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by saw on 18/12/17.
 */

public class Player extends Entity implements Serializable {

    private static long serialVersionUID = 54641465524L;

    private List <Item> items = new Vector<Item>();
    private List <Weapon> weapons = new Vector<Weapon>();
    private int wallet ; //représente la somme en Or que le joueur possède
    private boolean alive;



    private int id; //sert à représenter l'avatar lors du changement d'intent!

    /*
    *
    *Setters & Getters
    *
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }


    //override méthode depuis la classe mère;
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getHp() {
        return super.getHp();
    }

    @Override
    public void setHp(int hp) {
        super.setHp(hp);
    }

    @Override
    public int getDef() {
        return super.getDef();
    }

    @Override
    public void setDef(int def) {
        super.setDef(def);
    }

    @Override
    public int getAttk() {
        return super.getAttk();
    }

    @Override
    public void setAttk(int attk) {
        super.setAttk(attk);
    }

    public Player() {
        super();
    }

    @Override
    public int getIntell() {
        return super.getIntell();
    }

    @Override
    public void setIntell(int intell) {
        super.setIntell(intell);
    }

    @Override
    public int getSagesse() {
        return super.getSagesse();
    }

    @Override
    public void setSagesse(int sagesse) {
        super.setSagesse(sagesse);
    }

}