package model;

public class Ice extends Water {

    public Ice(double milliliter,double temp) { //รับอุณหภูมิเพิ่ม
        super(milliliter,temp);
    }


    public int amountIce() {//จำนวนน้ำแข็งทั้งหมดจะได้กี่ก้อน โดยที่น้ำ 100 ml จะได้ 10 ก้อน ถ้าอุุณหภูมิมากกว่า50 องศา จำนวนน้ำแข็งจะลดลง
        if(getTemp() >= 50) {
            int amount = ((int) getMilliliter() * 8) / 100;
            return amount;
        }
        else {
            int amount = ((int) getMilliliter() * 10) / 100;
            return amount;
        }
    }


    public double timeTempIce() {       // คำนวณอุณหภูมิก่อนโดย 100 องศาใช้เวลาเป็นน้ำเย็น 60 นาที
        double time;
        time = (getTemp()*60)/100;
        return time;
    }

    @Override
    public double tempTo() {
        return (super.tempTo() + 459.67) / (1.8);
    }

    @Override
    public double timeColdWater() {      //เวลาน้ำแข็ง
        return  super.timeColdWater() + timeTempIce() ;
    }


}
