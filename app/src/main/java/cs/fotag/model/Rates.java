package cs.fotag.model;

/**
 * Created by Dexter on 4/4/2016.
 */
public class Rates {
    private static Rates ourInstance = new Rates();

    public static Rates getInstance() {
        return ourInstance;
    }

    private Integer[] picRates= {
            0,0,0,0,0,0,0,0,0,0,0,0
    };


    private Rates() {
    }

    public Integer[] getPicRates(){
        return picRates;
    }
}
