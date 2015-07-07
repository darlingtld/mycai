package mycai.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mycai.dao.ProductDao;
import mycai.pojo.Category;
import mycai.pojo.Order;
import mycai.pojo.Product;
import mycai.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by darlingtld on 2015/5/16.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderService orderService;

    @Transactional
    public int save(Product product) {
        return productDao.save(product);
    }

    @Transactional
    public List<Product> getList(String type, String category) {
        return productDao.getList(type, category);
    }

    @Transactional
    public List<Product> getList(String category) {
        return productDao.getList(category);
    }

    @Transactional
    public List<Product> getLatest(int limit) {
        return productDao.getLatest(limit);
    }

    @Transactional
    public List<Product> getListByFavourites(String category, String wechatId) {
        List<Product> productList = getList(category);

        List<Order> orderList = orderService.getLatestList(wechatId, 5);
        Map<String, AtomicInteger> boughtItemsMap = new HashMap<>();

        for (Order order : orderList) {
            JSONObject jsonObject = JSON.parseObject(order.getBill());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.size(); i++) {
                String productName = jsonArray.getJSONObject(i).getString("productName");
                if (boughtItemsMap.containsKey(productName)) {
                    boughtItemsMap.get(productName).incrementAndGet();
                } else {
                    boughtItemsMap.put(productName, new AtomicInteger(1));
                }
            }

        }
        List<Map.Entry<String, AtomicInteger>> sortedProductList = new ArrayList<>(boughtItemsMap.entrySet());
        Collections.sort(sortedProductList, new Comparator<Map.Entry<String, AtomicInteger>>() {

            @Override
            public int compare(Map.Entry<String, AtomicInteger> o1, Map.Entry<String, AtomicInteger> o2) {
                return o1.getValue().intValue() - o2.getValue().intValue();
            }
        });

        for (Map.Entry<String, AtomicInteger> entry : sortedProductList) {
            for (Product product : productList) {
                if (product.getName().equals(entry.getKey())) {
                    productList.set(0, product);
                    break;
                }
            }
        }
        return productList;
    }

    @Transactional
    public List<Product> getOnsaleList(int limit) {
        return productDao.getOnsaleList(limit);
    }

    @Transactional
    public void upsert(Product product) {
        product.setDataChangeLastTime(new Timestamp(System.currentTimeMillis()));
        productDao.saveOrUpdateByName(product);
    }

    @Transactional
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Transactional
    public Product getById(int id) {
        return productDao.getById(id);
    }

    @Transactional
    public void update(Product product) {
        Product productInDB = productDao.getById(product.getId());
        if (productInDB != null) {
            productInDB.setName(product.getName());
            productInDB.setDescription(product.getDescription());
            productInDB.setType(product.getType());
            productInDB.setCategory(product.getCategory());
            productInDB.setPrice(product.getPrice());
            productInDB.setUnit(product.getUnit());
            productInDB.setDataChangeLastTime(new Timestamp(System.currentTimeMillis()));
        }
        productDao.update(productInDB);
    }

    public Map<Type, List<Category>> getTypeMap() {
        Map<Type, List<Category>> typeListMap = new TreeMap<>();
        List<Category> shucaishuiguoList = new ArrayList<>();
        shucaishuiguoList.add(Category.YECAILEI);
        shucaishuiguoList.add(Category.GENJINGLEI);
        shucaishuiguoList.add(Category.QIEGUOLEI);
        shucaishuiguoList.add(Category.DOULEI);
        shucaishuiguoList.add(Category.CONGJIANGSUAN);
        shucaishuiguoList.add(Category.JUNLEI);
        shucaishuiguoList.add(Category.TECAI);
        typeListMap.put(Type.SHUCAISHUIGUO, shucaishuiguoList);
        List<Category> qinroudanleiList = new ArrayList<>();
        qinroudanleiList.add(Category.YIJIBAITIAO);
        qinroudanleiList.add(Category.ERJIBAITIAO);
        qinroudanleiList.add(Category.DONGZHUROU);
        qinroudanleiList.add(Category.YANGROU);
        qinroudanleiList.add(Category.NIUROU);
        qinroudanleiList.add(Category.XIANJIROU);
        qinroudanleiList.add(Category.DONGJIROU);
        qinroudanleiList.add(Category.YAROU);
        qinroudanleiList.add(Category.QINLEI);
        qinroudanleiList.add(Category.QINDANLEI);
        qinroudanleiList.add(Category.SHUSHIJIAGONG);
        typeListMap.put(Type.QINROUDANLEI, qinroudanleiList);
        List<Category> shuichandonghuoList = new ArrayList<>();
        shuichandonghuoList.add(Category.HAIXIANSHUICHAN);
        shuichandonghuoList.add(Category.YUWANHUOGUO);
        typeListMap.put(Type.SHUICHANDONGHUO, shuichandonghuoList);
        List<Category> mimianliangyouList = new ArrayList<>();
        mimianliangyouList.add(Category.DAMI);
        mimianliangyouList.add(Category.MIANFEIMIANTIAO);
        mimianliangyouList.add(Category.SHIYONGYOU);
        mimianliangyouList.add(Category.ZALIANG);
        mimianliangyouList.add(Category.MIANDIAN);
        mimianliangyouList.add(Category.HONGBEIZUOLIAO);
        typeListMap.put(Type.MIMIANLIANGYOU, mimianliangyouList);
        List<Category> tiaoliaoqitaList = new ArrayList<>();
        tiaoliaoqitaList.add(Category.TIAOWEIPIN);
        tiaoliaoqitaList.add(Category.FENSIFENTIAO);
        tiaoliaoqitaList.add(Category.DOUZHIPIN);
        tiaoliaoqitaList.add(Category.GANHUO);
        tiaoliaoqitaList.add(Category.TIAOWEIZHIJIANG);
        tiaoliaoqitaList.add(Category.JIANGYOUCU);
        tiaoliaoqitaList.add(Category.YANCAIGUANTOU);
        typeListMap.put(Type.TIAOLIAOQITA, tiaoliaoqitaList);
        List<Category> canchuyongpinList = new ArrayList<>();
        canchuyongpinList.add(Category.ZHIPINSHIJIN);
        canchuyongpinList.add(Category.CANYINYONGJU);
        typeListMap.put(Type.CANCHUYONGPIN, canchuyongpinList);
        List<Category> jiushuiyinliaoList = new ArrayList<>();
        jiushuiyinliaoList.add(Category.YINLIAO);
        jiushuiyinliaoList.add(Category.YINYONGSHUI);
        typeListMap.put(Type.JIUSHUIYINLIAO, jiushuiyinliaoList);
        return typeListMap;
    }
}
