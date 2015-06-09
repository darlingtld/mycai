package mycai.other;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by darlingtld on 2015/5/30 0030.
 */
public class Test {

    @org.junit.Test
    public void testReplace() throws UnsupportedEncodingException {
        String text = "千克";
        System.out.println(URLEncoder.encode(text, "utf-8"));
    }
}
