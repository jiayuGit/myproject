package com.example.demo.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
public class MeetingRoom {

    private ConcurrentHashMap<String, Map<String, Session>> rooms = new ConcurrentHashMap<String, Map<String, Session>>();

    public void addUserToRoom(String roomid, Session session) {
        Map<String, Session> room = getRoom(roomid);
        room.put(session.getId(), session);
    }

    public Map<String, Session> getRoomSession(String roomid) {
        return getRoom(roomid);
    }

    public void removeUsertoRoom(String roomid, Session session) {
        Map<String, Session> room = getRoom(roomid);
        room.remove(session.getId());
        try {
            if (session.isOpen()){
                session.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRoomUserCount(String roomid) {
        return getRoom(roomid).size();
    }

    public void sendUserToRoom(String roomid, Session session, String type, Object data, String sessionid,
                               String candidate,String sdpMLineIndex) {
        String message = getDataString(type, data, sessionid,candidate,sdpMLineIndex);
        Map<String, Session> room = getRoom(roomid);
        room.values().stream().filter(v -> !session.equals(v)).forEach(v -> sendmessge(v, message));
    }


    public void sendUserMessage(Session session, String type, Object data,
                                String sessionid,String candidate,String sdpMLineIndex) {
        String message = getDataString(type, data, sessionid,candidate,sdpMLineIndex);
        sendmessge(session,message);
    }

    public void sendAll(String roomid, String message) {
        Map<String, Session> room = getRoom(roomid);
        room.values().stream().forEach(v -> sendmessge(v, message));
    }

    private String getDataString(String type, Object data, String sessionid,
                                 String candidate,String sdpMLineIndex) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", type);
            if (data != null) {
                jsonObject.put("data", data);
            }
            if (sessionid != null) {
                jsonObject.put("sessionid", sessionid);
            }
            if (candidate != null) {
                jsonObject.put("candidate", candidate);
            }
            if (sdpMLineIndex != null) {
                jsonObject.put("sdpMLineIndex", sdpMLineIndex);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private void sendmessge(Session v, String message) {
        try {
            if (v!=null&&v.isOpen()) {
                synchronized (v) {
                    v.getAsyncRemote().sendText(message).get();
                }
            }
        } catch (Exception e) {
            log.error("发送消息失败e={} ,sessionID={} ,消息={}", e, v.getId(), message);
        }

    }

    private Map<String, Session> getRoom(String roomid) {
        if (!rooms.containsKey(roomid)) {
            rooms.put(roomid, new ConcurrentHashMap<>());
        }
        return rooms.get(roomid);
    }

    public Session getSessionById(String roomid, String sessionid) {
        return getRoom(roomid).get(sessionid);
    }
}
