package mycai.service;

import mycai.pojo.message.req.TextMessage;
import mycai.pojo.message.resp.Article;
import mycai.pojo.message.resp.NewsMessage;
import mycai.util.MessageUtil;
import mycai.util.PropertyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tangld on 2015/5/19.
 */
@Service
public class MycaiService {

    @Autowired
    private EventService eventService;

    @Autowired
    private TulingApiService tulingApiService;

    public String processRequest(HttpServletRequest request) {
        String fromUserName;
        String toUserName;
        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            fromUserName = requestMap.get("FromUserName");
            toUserName = requestMap.get("ToUserName");

            String msgType = requestMap.get("MsgType");

            String content = requestMap.get("Content").trim();

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                String eventType = requestMap.get("Event");
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    String respContent = "您好，欢迎关注送达！";
                    TextMessage textMessage = new TextMessage();
                    textMessage.setToUserName(fromUserName);
                    textMessage.setFromUserName(toUserName);
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                    textMessage.setContent(respContent);
                    return MessageUtil.messageToXml(textMessage);
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");
                    if (PropertyHolder.MENU_ABOUT_US.equals(eventKey)) {
                        return eventService.doAboutUs(fromUserName, toUserName);
                    } else if (PropertyHolder.MENU_NEW_PRODUCT.equals(eventKey)) {
                        return eventService.doProductInquiry(fromUserName, toUserName);
                    }
                }
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                String respContent = tulingApiService.getTulingResult(content);
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(fromUserName);
                textMessage.setFromUserName(toUserName);
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                textMessage.setContent(respContent);
                return MessageUtil.messageToXml(textMessage);
            }


            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);
            List<Article> articleList = new ArrayList<Article>();
            Article article = new Article();
            article.setTitle("送达");
            article.setDescription("上海三林地区最大的农产品移动电商平台̨");
            article.setPicUrl(PropertyHolder.SERVER + "/images/logo.jpg");
            article.setUrl(PropertyHolder.SERVER + "/index.html?wechat_id=" + fromUserName);
            articleList.add(article);
            newsMessage.setArticleCount(articleList.size());
            newsMessage.setArticles(articleList);
            return MessageUtil.messageToXml(newsMessage);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
