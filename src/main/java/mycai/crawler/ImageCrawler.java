package mycai.crawler;

import mycai.pojo.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.TreeMap;

/**
 * Created by darlingtld on 2015/5/30 0030.
 */
public class ImageCrawler {

    private static final Logger logger = LoggerFactory.getLogger(ProductCrawler.class);

    private static final int CONNECTION_TIME_OUT = 30 * 1000;
    private static final double PIC_PROPORTION = 1.5;

    public static void getProductImg(Product product) {
        String googleImgUrl = "https://image.glgoo.com/search?site=imghp&tbm=isch&q=%s&gws_rd=cr";
        Document doc;
        try {
            System.out.println(String.format("fetching image for {}", product.getName()));
            String url = String.format(googleImgUrl, URLEncoder.encode(product.getName(), "utf-8"));
            doc = Jsoup.connect(url).userAgent("Mozilla").timeout(CONNECTION_TIME_OUT).get();
            Elements eles = doc.select("img");
            TreeMap<Double, String> sizeImgUrlMap = new TreeMap<>();
            for (Element element : eles) {
//                System.out.println(element);
                double height = Double.parseDouble(element.attr("height"));
                double width = Double.parseDouble(element.attr("width"));
                String src = element.attr("src");
                Double size = height / width < PIC_PROPORTION ? PIC_PROPORTION - height / width : height / width - PIC_PROPORTION;
                sizeImgUrlMap.put(size, src);
            }
//            System.out.println(sizeImgUrlMap);
            product.setPicurl(sizeImgUrlMap.firstEntry().getValue());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
