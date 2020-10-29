package model;

public class HotWater extends Water {
    public HotWater(double milliliter,double temp) {
        super(milliliter,temp);
    }
    public double timeTempHot() {
        double time;
        if(getTemp() >= 100){
            time = 0;
            return time;
        }
        else {
            time = (getTemp()) / 10 ;
        }
        return time;
    }

    @Override
    public double tempTo() {
        return (((super.tempTo() - 32) * 5)/9)/1.25;
    }

    @Override
    public double timeHotWater() {
        return  super.timeHotWater()-timeTempHot();
    }
    @Override
    public double milliliterToLiter() {
        return (super.milliliterToLiter()*98) / 100 ;
    }


    }
