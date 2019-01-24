package com.txtbravo.txtbravo.service;

import com.txtbravo.txtbravo.fcm.dto.Push;
import com.txtbravo.txtbravo.filters.HeaderRequestInterceptor;
import com.txtbravo.txtbravo.payload.FirebaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
public class PushNotificationService
{

	@Value("${my.fcm.key}")
	private String fcmKey;

	private static final String FCM_API = "https://fcm.googleapis.com/fcm/send";

	/**
	 * @param push
	 * @return
	 */
	public FirebaseResponse sendNotification(Push push) {

		HttpEntity<Push> request = new HttpEntity<>(push);

		CompletableFuture<FirebaseResponse> pushNotification = this.send(request);
		CompletableFuture.allOf(pushNotification).join();

		FirebaseResponse firebaseResponse = null;

		try
		{
			firebaseResponse = pushNotification.get();
		}

		catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}

		return firebaseResponse;
	}

	/**
	 * send push notification to API FCM
	 * 
	 * Using CompletableFuture with @Async to provide Asynchronous call.
	 *
	 * @param entity
	 * @return
	 */
	@Async
	private CompletableFuture<FirebaseResponse> send(HttpEntity<Push> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + fcmKey));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		FirebaseResponse firebaseResponse = restTemplate.postForObject(FCM_API, entity, FirebaseResponse.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}
}