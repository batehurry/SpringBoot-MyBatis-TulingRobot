package com.shawn.robot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shawn.robot.model.Interlocution;
import com.shawn.robot.service.RobotInterlocutionService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * created by sunliangliang
 * 图灵机器人问答
 */
@Service
public class TuLingInterlocution implements RobotInterlocutionService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${robot.tuling.interlocution-url:}")
    private String TULING__INTERLOCUTION_URL;
    @Value("${robot.tuling.APIKEY:}")
    private String API_KEY;

    @Override
    public String getAnswer(String question) {
        logger.info("--------------${question}:{}",question);
        StringBuffer sb = new StringBuffer();
        try {
            String INFO = URLEncoder.encode(question, "utf-8");
            String url = TULING__INTERLOCUTION_URL.replace("{apiKey}",API_KEY).replace("{info}",INFO);
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public Interlocution getInterlocation(String question) {
        String answer = this.getAnswer(question);
        JSONObject result = JSON.parseObject(answer);
        Interlocution interlocution = new Interlocution();
        interlocution.setContent(result.getString("text"));
        return interlocution;
    }

}
