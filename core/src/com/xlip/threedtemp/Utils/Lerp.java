package com.xlip.threedtemp.Utils;

/**
 * Created by Arif on 20.07.2017.
 */

public class Lerp {
    private float orjStart;
    private float start;
    private float end;
    private float interpolation;
    private  boolean finished;
    private Lerp combined;



    public Lerp(float start, float end, float interpolation) {
        this.start = start;
        this.orjStart = start;
        this.end = end;
        this.interpolation = interpolation;
        finished = false;
    }


    public float getValue(float delta) {

        if(isFinished())
            onFinished();

        if(!this.finished) {

            if (Math.abs(end - start) < Math.abs(orjStart-end)/100 || end == start) {
                start = end;
                finished = true;

            }

            start += (end - start) * interpolation * delta;

            return start;
        }else {
            if(combined != null) {
                return combined.getValue(delta);
            }

            return end;
        }
    }

    public Lerp combineWith(float end){
        combineWith(end, interpolation);
        return this;
    }

    public Lerp combineWith(float end, float interpolation){
        if(combined == null) {
            combined = new Lerp(this.end,end,interpolation);
        }else {
            combined.combineWith(end,interpolation);
        }

        return this;
    }

    public void addToEnd(float difference) {
        this.end += difference;
        finished = false;
    }

    public Lerp go(float end) {
        this.end = end;
        finished = false;
        return this;
    }

    public boolean isFinished() {
        if(combined != null){
            return combined.isFinished();
        }

        return this.finished;
    }

    public float getEnd() {
        return end;
    }

    public void onFinished(){

    }

}
