package model;

import java.util.Date;

public class Food {

    private String name;
    private String type;
    private int amount;
    private Date expire;

    public Food(String name, int amount,String type, Date expire) {
        this.name = name;
        this.amount = amount;
        this.expire = expire;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }
    public void add(int amount){
        this.amount+=amount;
    }


    public void takeOut(int amount) {
        this.amount -= amount;

    }


    @Override
    public String toString() {
        return name + "," +
                amount + "," +
                type + ","+
                Utility.dateFormat.format(expire);
    }

}


