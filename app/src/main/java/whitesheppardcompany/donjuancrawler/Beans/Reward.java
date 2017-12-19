package whitesheppardcompany.donjuancrawler.Beans;

import java.util.List;

/**
 * Created by saw on 19/12/17.
 */

class Reward {

    private int amountMoney;
    private List<Weapon> weapons;
    private List<Stuff> stuffs;
    private List<Consumable> conso;
    private int amountExp;



    /* Constructeur et je vais en avoir besoin de plusieurs*/
    public Reward(int amountExp) {
        this.amountExp = amountExp;
    }

    /* Getter & setter */

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) {
        this.amountMoney = amountMoney;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<Stuff> stuffs) {
        this.stuffs = stuffs;
    }

    public int getAmountExp() {
        return amountExp;
    }

    public void setAmountExp(int amountExp) {
        this.amountExp = amountExp;
    }


    public List<Consumable> getConso() {
        return conso;
    }

    public void setConso(List<Consumable> conso) {
        this.conso = conso;
    }


}
