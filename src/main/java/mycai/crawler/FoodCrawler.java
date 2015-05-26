package mycai.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by tangld on 2015/5/26.
 */
public class FoodCrawler {

    public static void main(String[] args){
        try {
            Document doc = Jsoup.connect("http://www.21food.cn/quote/").get();
            System.out.println(doc.title());
            Elements eles = doc.select("div.artHead");
            System.out.println(eles.first().select("h3[class=artTitle]"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
