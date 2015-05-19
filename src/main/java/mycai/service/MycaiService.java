package mycai.service;

import mycai.pojo.message.req.TextMessage;
import mycai.util.MessageUtil;
import mycai.util.PropertyHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            String respContent = PropertyHolder.SERVER + "/mycai/index.html";
            textMessage.setContent(respContent);
            return MessageUtil.messageToXml(textMessage);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
