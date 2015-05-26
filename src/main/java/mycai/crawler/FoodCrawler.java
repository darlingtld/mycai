package mycai.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by tangld on 2015/5/26.
 */
public class FoodCrawler {

    public static void main(String[] args) {
        String host = "http://www.21food.cn/quote/";
        try {
            Document doc = Jsoup.connect(host).get();
            Elements eles = doc.select("div.price-bg").select("ul.pfl-ul>li>a");
            for (Element element : eles) {
                extractFoodInfo(element.text(), host + element.attr("href").toString());
                break;
            }
//            System.out.println(eles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractFoodInfo(String text, String href) {
        try {
            System.out.println(text + " - " + href);
            Document doc = Jsoup.connect(href).get();
            Elements elements = doc.select(".city-integrate table tr");
            for (int i = 1; i < elements.size(); i++) {
                Elements eles = elements.get(i).select("td");
                for (Element e : eles) {
                    System.out.println(e);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
