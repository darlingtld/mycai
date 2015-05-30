package mycai.crawler;

import mycai.pojo.Product;
import mycai.pojo.Type;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by darlingtld on 2015/5/30 0030.
 */
public class VegFruitCrawler {

    private static final Logger logger = LoggerFactory.getLogger(ProductCrawler.class);

    private static final int CONNECTION_TIME_OUT = 30 * 1000;
    private static final double PIC_PROPORTION = 1.5;
    private static final String host = "http://www.vegnet.com.cn/";
    private static final String SHUCAISHUIGUO_IN_SH = host + "Price/list_ar310000.html?marketID=71";
    private ThreadPoolExecutor threadPoolExecutor;
    private ConcurrentHashMap<String, Product> productMap;

    public VegFruitCrawler(ThreadPoolExecutor threadPoolExecutor, ConcurrentHashMap<String, Product> productMap) {
        this.productMap = productMap;
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public void execute() {
        try {
            List<String> pageList = getPageListToCrawl();

            for (final String pageUrl : pageList) {
                threadPoolExecutor.execute(new Thread() {
                    @Override
                    public void run() {
                        Document doc;
                        try {
                            logger.info("{} is crawling {}...", this.getClass(), pageUrl);
                            doc = Jsoup.connect(pageUrl).timeout(CONNECTION_TIME_OUT).get();
                            Elements eles = doc.select("div.pri_K").select("p");
                            for (Element element : eles) {
                                Product product = extractFoodInfo(element.select("span"));
                                if (product == null) {
                                    logger.error("Error occurred!");
                                    continue;
                                }
                                productMap.putIfAbsent(product.getName(), product);
                                System.out.println(String.format("%s [%s]", Thread.currentThread(), product));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }

        } catch (IOException e1) {
            logger.error(e1.getMessage());
        }

    }

    private List<String> getPageListToCrawl() throws IOException {
        List<String> pageList = new ArrayList<>();
        Document doc = Jsoup.connect(SHUCAISHUIGUO_IN_SH).timeout(CONNECTION_TIME_OUT).get();
        Elements eles = doc.select("div.Pager>a");
        for (Element element : eles) {
            pageList.add(host + element.attr("href"));
        }
        return pageList;
    }

    private Product extractFoodInfo(Elements elements) {
        try {
            Product product = new Product();
//            String searchUrl = host + elements.select("span.k_2>a").attr("href");
            Elements eles = elements.select("span.k_2");
            product.setName(eles.get(0).text());
//            product.setPriceMin(Double.parseDouble(eles.get(1).text().substring(1)));
//            product.setPriceHign(Double.parseDouble(eles.get(2).text().substring(1)));
            product.setPrice(Double.parseDouble(eles.get(3).text().substring(1)));
            product.setUnit(eles.get(4).text().replace("元/", "").replace("(kg)", ""));
            product.setType(Type.SHUCAISHUIGUO);
            product.setOnsale(0);
            getProductImg(product);
            return product;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private void getProductImg(Product product) {
        String googleImgUrl = "https://image.glgoo.com/search?site=imghp&tbm=isch&q=%s&gws_rd=cr";
        Document doc;
        try {
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
