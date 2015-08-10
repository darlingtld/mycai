package event.message.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by tangl9 on 2015-08-10.
 */
@Entity
@Table(name = "message")
public class Message {
    @Id
    private int id;
    @Column(name="openid")
    private String openid;
    @Column(name = "content")
    private String content;
    @Column(name = "ts")
    private Date ts;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", content='" + content + '\'' +
                ", ts=" + ts +
                '}';
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
