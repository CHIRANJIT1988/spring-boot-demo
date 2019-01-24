package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.fcm.dto.DataPlayload;
import com.txtbravo.txtbravo.fcm.dto.Notification;
import com.txtbravo.txtbravo.fcm.dto.Push;
import com.txtbravo.txtbravo.payload.FirebaseResponse;
import com.txtbravo.txtbravo.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/user/fcm")
public class FcmController
{

    private static final String fcm_token = "ci2b6LtO8tk:APA91bEAxIZcBuK5iLhV-1Gc1asYB9iUG7BfedSNat-d7qUnTDQoeVYvjO7XeUDGUko3PpXSboCO1btnmhwCi-bd1ibGuh-m8J0zUnnNe3AgGYeSELOESt8vnXc7JEiQ_XAhit1jan34";

    @Autowired
    private PushNotificationService pushNotification;

    /**
     * Send to singleton app
     * @return
     */
    @PostMapping("/push")
    public ResponseEntity<?> push()
    {
        /*personRepositoty.findFirstByOrderByName().ifPresent(p -> {

            Notification notification = new Notification("default", "My app", "Teste");
            Push push = new Push("ci2b6LtO8tk:APA91bEAxIZcBuK5iLhV-1Gc1asYB9iUG7BfedSNat-d7qUnTDQoeVYvjO7XeUDGUko3PpXSboCO1btnmhwCi-bd1ibGuh-m8J0zUnnNe3AgGYeSELOESt8vnXc7JEiQ_XAhit1jan34", "high", notification);
            pushNotification.sendNotification(push);
        });*/

        /**
         * Create Notification Playload Object
         * Pass it to push constructor as a parameter if notification playload require
         */

        Notification notification = new Notification("default", "My Notification", "Spring Boot Notification");

        /**
         * Create Data Playload Object
         * Pass it to push constructor as a parameter if data playload require
         */
        DataPlayload data = new DataPlayload("FCM", "Spring Boot FCM");

        Push push = new Push(fcm_token, "high", data);
        FirebaseResponse response = pushNotification.sendNotification(push);

        return ResponseEntity.ok().body(response);
    }



    /**
     * send notification to all
     *
     * @return
     */
    @PostMapping("/pushAll")
    public ResponseEntity<?> pushAll() {

        //List<Person> persons = personRepositoty.findAll();
        //persons.forEach(p -> tokens.add(p.getTokenFCM().getToken()));

        List<String> tokens = new ArrayList<>();
        tokens.add(fcm_token);

        /**
         * Create Data Playload Object
         * Pass it to push constructor as a parameter if data playload require
         */
        DataPlayload data = new DataPlayload("FCM", "Spring Boot FCM");

        Push push = new Push("high", data, tokens);
        FirebaseResponse response = pushNotification.sendNotification(push);

        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/send")
    public void send()
    {
        try
        {
            String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "key=" + fcm_token);
            httpHeaders.set("Content-Type", "application/json");

            JSONObject msg = new JSONObject();
            JSONObject json = new JSONObject();

            msg.put("title", "Title");
            msg.put("body", "Message");
            msg.put("notificationType", "Test");

            json.put("data", msg);
            json.put("to", fcm_token);

            HttpEntity<String> httpEntity = new HttpEntity<>(json.toString(),httpHeaders);
            String response = restTemplate.postForObject(androidFcmUrl, httpEntity, String.class);
            System.out.println(response);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}