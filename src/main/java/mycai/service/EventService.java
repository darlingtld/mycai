package mycai.service;


import mycai.pojo.message.resp.Article;
import mycai.pojo.message.resp.NewsMessage;
import mycai.util.MessageUtil;
import mycai.util.PropertyHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by darlingtld on 2015/4/26.
 */
@Service
public class EventService {

    public String doAboutUs(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<>();
        Article article = new Article();
        article.setTitle("公司简介");
        article.setDescription("点击查看店铺地址、联系方式、营业时间");
        article.setPicUrl(PropertyHolder.SERVER);
        article.setUrl(PropertyHolder.SERVER);
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    public String doProductInquiry(String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setFuncFlag(0);
        List<Article> articleList = new ArrayList<>();
        Article article = new Article();
        article.setTitle("商品查询");
        article.setDescription("点击查询商品");
        article.setPicUrl(PropertyHolder.SERVER);
        article.setUrl(PropertyHolder.SERVER);
        articleList.add(article);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

}
