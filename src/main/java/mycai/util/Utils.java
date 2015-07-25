package mycai.util;

import java.math.BigDecimal;

/**
 * Created by darlingtld on 2015/7/25 0025.
 */
public class Utils {

    public static double formatDouble(double value) {
        BigDecimal b = new BigDecimal(value);
        double fValue = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return fValue;
    }
}
