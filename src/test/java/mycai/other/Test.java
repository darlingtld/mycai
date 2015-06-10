package mycai.other;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by darlingtld on 2015/5/30 0030.
 */
public class Test {

    @org.junit.Test
    public void testReplace() throws UnsupportedEncodingException {
        String t = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("t---" + t);
    }
}
