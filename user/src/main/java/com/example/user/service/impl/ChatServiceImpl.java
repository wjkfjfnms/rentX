package com.example.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.user.service.ChatService;
import com.example.user.service.CommonService;
import com.example.user.util.StringUtil;
import com.example.user.vo.MyMessage;
import com.example.user.vo.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ChatServiceImpl implements ChatService {

    //onlineCount：在线连接数
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    //webSocketSet：用来存放每个客户端对应的MyWebSocket对象。
    public static List<ChatServiceImpl> webSocketSet = new ArrayList<>();

    //存放所有连接人信息
    public static List<String> userList  = new ArrayList<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //用户ID
    public String nickname = "";
//    public String userId = "";

    @Autowired
    CommonService commonService;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    @Override
    public RE onOpen(Session session) {
        this.session = session;
        this.nickname = commonService.getTokenNickname();;
        userList.add(nickname) ;
//        this.userId = userId;
//        this.userList.add(userId) ;
        //加入set中
        webSocketSet.add(this);
        //在线数加1
        onlineCount.incrementAndGet();
        System.out.println("有新连接加入！" + nickname + "当前在线用户数为" + onlineCount.get());
//        log.info("有新连接加入！" + userId + "当前在线用户数为" + onlineCount.get());
        JSONObject msg = new JSONObject();
        try {
            msg.put("msg", "连接成功");
            msg.put("status", "SUCCESS");
            msg.put("nickname", nickname);
            sendMessage(JSON.toJSONString(msg));
            return RE.ok().data("nickname",nickname);
        } catch (Exception e) {
            System.out.println("error");
            return RE.error();
//            log.debug("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @Override
    public RE onClose() {
        //从set中删除
        webSocketSet.remove(this);
        onlineCount.decrementAndGet(); // 在线数减1
        System.out.println("用户"+ nickname +"退出聊天！当前在线用户数为" + onlineCount.get());
        return RE.ok();
//        log.info("用户"+ userId +"退出聊天！当前在线用户数为" + onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @Override
    public RE onMessage(String message) {
        //客户端输入的消息message要经过处理后封装成新的message,后端拿到新的消息后进行数据解析,然后判断是群发还是单发,并调用对应的方法
//        logger.info("来自客户端" + userId + "的消息:" + message);
        try {
            MyMessage myMessage = JSON.parseObject(message, MyMessage.class);
            String messageContent = myMessage.getMessage();//messageContent：真正的消息内容
            String messageType = myMessage.getMessageType();
            if("1".equals(messageType)){ //单聊
                String recUser = myMessage.getUserId();//recUser：消息接收者
                sendInfo(messageContent,recUser,nickname);//messageContent：输入框实际内容 recUser：消息接收者  userId 消息发送者
                return RE.ok();
            }else{ //群聊
                sendGroupInfo(messageContent,nickname);//messageContent：输入框实际内容 userId 消息发送者
                return RE.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RE.error();
//            log.error("解析失败：{}", e);
        }
    }

    /**
     * 发生错误时调用的方法
     *
     * @OnError
     **/
    @Override
    public RE onError(Throwable error) {
//        log.debug("Websocket 发生错误");
        error.printStackTrace();
        return RE.error();
    }



    @Override
    public RE sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
        return RE.ok();
    }

    /**
     * 单聊
     * message ： 消息内容，输入的实际内容，不是拼接后的内容
     * recUser : 消息接收者
     * sendUser : 消息发送者
     */
    @Override
    public RE sendInfo(String message, String recUser, String sendUser) {
        JSONObject msgObject = new JSONObject();//msgObject 包含发送者信息的消息
        for (ChatServiceImpl item : webSocketSet) {
            if (StringUtil.equals(item.nickname, recUser)) {
//                log.info("给用户" + recUser + "传递消息:" + message);
                //拼接返回的消息，除了输入的实际内容，还要包含发送者信息
                msgObject.put("message",message);
                msgObject.put("sendUser",sendUser);
                item.sendMessage(JSON.toJSONString(msgObject));
            }
        }
        return RE.ok();
    }

    /**
     * 群聊
     * message ： 消息内容，输入的实际内容，不是拼接后的内容
     * sendUser : 消息发送者
     */
    @Override
    public RE sendGroupInfo(String message, String sendUser) {
        JSONObject msgObject = new JSONObject();//msgObject 包含发送者信息的消息
        if (StringUtil.isNotEmpty(webSocketSet)) {
            for (ChatServiceImpl item : webSocketSet) {
                if(!StringUtil.equals(item.nickname, sendUser)) { //排除给发送者自身回送消息,如果不是自己就回送
//                    log.info("回送消息:" + message);
                    //拼接返回的消息，除了输入的实际内容，还要包含发送者信息
                    msgObject.put("message",message);
                    msgObject.put("sendUser",sendUser);
                    item.sendMessage(JSON.toJSONString(msgObject));
                }
            }
        }
        return RE.ok();
    }

    /**
     * Map/Set的key为自定义对象时，必须重写hashCode和equals。
     * 关于hashCode和equals的处理，遵循如下规则：
     * 1）只要重写equals，就必须重写hashCode。
     * 2）因为Set存储的是不重复的对象，依据hashCode和equals进行判断，所以Set存储的对象必须重写这两个方法。
     * 3）如果自定义对象做为Map的键，那么必须重写hashCode和equals。
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChatServiceImpl that = (ChatServiceImpl) o;
        return Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
}
