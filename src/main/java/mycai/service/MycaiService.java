package mycai.service;

import mycai.pojo.message.req.TextMessage;
import mycai.pojo.message.resp.Article;
import mycai.pojo.message.resp.NewsMessage;
import mycai.util.MessageUtil;
import mycai.util.PropertyHolder;
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

    public String processRequest(HttpServletRequest request) {
        String fromUserName = null;
        String toUserName = null;
        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            fromUserName = requestMap.get("FromUserName");
            toUserName = requestMap.get("ToUserName");

            String msgType = requestMap.get("MsgType");

            String content = requestMap.get("Content").trim();

            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);
            List<Article> articleList = new ArrayList<Article>();
            Article article = new Article();
            article.setTitle("买菜");
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
