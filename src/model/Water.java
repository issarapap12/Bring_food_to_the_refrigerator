package model;

public class Water {
    private double temp;
    private double milliliter;

    public Water(double milliliter,double temp) {
        this.milliliter = milliliter;
        this.temp = temp;
    }

    public double getTemp() {
        return temp;
    }

    public double getMilliliter() {
        return milliliter;
    }

    public double milliliterToLiter(){
        this.milliliter = this.milliliter/1000;
        return this.milliliter;
    }

    public double tempTo(){ //ต้องเพิ่มอีกกี่องศาน้ำจะร้อน
        this.temp = (1.8*temp) + 32;
        return this.temp;
    }

    public double timeColdWater() {
        double time;
        if (temp == 25) {
            time = (this.milliliter * 10) / 100;
            return time;
        } else if (temp > 25 && temp <100) {
            time = (this.milliliter * 20) / 100; // น้ำ 100 ml จะเย็นใน 20 นาที ที่น้ำอุณหภูมิมากกว่า 25 องศา
            return time;
        } else if (temp < 25 && temp > 10) {
            time = (this.milliliter * 5) / 100;
            return time;

        } else if (temp >=100) {
            time = (this.milliliter* 60) / 100;
            return time;
        } else {
            time = 0;
        }
        return time;
    }

    public double timeHotWater(){
        double time;
        if (temp == 25) {
            time = (this.milliliter * 15) / 100;
            return time;
        } else if (temp > 25 && temp < 100) {
            time = (this.milliliter * 10) / 100;
            return time;
        } else if (temp > 10 && temp < 25) {
            time = (this.milliliter * 30) / 100;
            return time;
        } else if (temp == 10) {
            time = (this.milliliter* 60) / 100;
            return time;
        } else if(temp < 10 && temp > 5){
            time = (this.milliliter * 40) / 100;
        }
        else  if(temp >= 100){
            time = 0 ;
        }
        else {
            time = (this.milliliter * 70) / 100;
        }
        return time;

    }


}
