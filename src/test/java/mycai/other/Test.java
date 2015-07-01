package mycai.other;

import mycai.util.PropertyHolder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by darlingtld on 2015/5/30 0030.
 */
public class Test {

    @org.junit.Test
    public void testReplace() throws UnsupportedEncodingException {
        String t = URLEncoder.encode(PropertyHolder.SERVER, "UTF-8");
        System.out.println(t);
    }
}
