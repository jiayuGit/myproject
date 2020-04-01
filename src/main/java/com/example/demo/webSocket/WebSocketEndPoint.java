package com.example.demo.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.server.WsRemoteEndpointImplServer;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Slf4j
@Component
@ServerEndpoint("/websocket/{roomid}")//表明这是一个websocket服务的端点
public class WebSocketEndPoint {

    private static MeetingRoom meetingRoom = new MeetingRoom();


    @OnOpen
    public void onOpen(@PathParam("roomid") String roomid, @PathParam("name") String name, Session session) {
        log.info("有新的连接：{} sessionID={}", session, session.getId());
        session.setMaxIdleTimeout(360000);
    }

    @OnMessage
    public void onMessage(@PathParam("roomid") String roomid, Session session, String message) {
        log.info("有新消息： {}", message);
        try {
            JSONObject jsonObject = new JSONObject(message);
            if ("rtc".equals(jsonObject.get("group"))) {
                if ("join".equals(jsonObject.get("type"))) {

                    if (meetingRoom.getRoomUserCount(roomid) > 0) {
                        int count = meetingRoom.getRoomUserCount(roomid);
                        meetingRoom.getRoomSession(roomid).values().stream().forEach(v -> {
                            meetingRoom.sendUserMessage(v, "joined", count, session.getId(), null, null);
                            meetingRoom.sendUserMessage(session, "other_join", count, v.getId(), null, null);

                        });


                    }
                    meetingRoom.addUserToRoom(roomid, session);

                } else {
                    Session join_session = meetingRoom.getSessionById(roomid, jsonObject.getString("sessionid"));
                    meetingRoom.sendUserMessage(join_session, jsonObject.getString("type"), jsonObject.getString("data"), session.getId(),
                            jsonObject.getString("candidate"), jsonObject.getString("sdpMLineIndex"));

                }

            } else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(@PathParam("roomid") String roomid, @PathParam("name") String name, Session session) {
            log.info("连接关闭： {}", session);
            meetingRoom.sendUserToRoom(roomid, session, "bye", null, session.getId(), null, null);
            meetingRoom.sendUserMessage(session, "leaved", null, null, null, null);
            meetingRoom.removeUsertoRoom(roomid, session);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(@PathParam("roomid") String roomid, Session session, Throwable throwable) {
        try {
            meetingRoom.removeUsertoRoom(roomid, session);
            session.close();
        } catch (IOException e) {
            log.error("onError Exception: {}", e);
        }
        log.info("连接出现异常： {}", throwable);
    }

}
