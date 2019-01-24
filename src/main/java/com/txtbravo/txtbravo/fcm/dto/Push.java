package com.txtbravo.txtbravo.fcm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Push
{
	private String to;
	private String priority;

	private Notification notification;
	private DataPlayload data;

	@JsonProperty(value = "registration_ids")
	private List<String> registrationIds;

	/**
	 * @param priority
	 * @param notification
	 * @param registrationIds
	 */
	public Push(String priority, Notification notification, List<String> registrationIds)
	{
		this.priority = priority;
		this.notification = notification;
		this.registrationIds = registrationIds;
	}

	/**
	 * @param to
	 * @param priority
	 * @param notification
	 */
	public Push(String to, String priority, Notification notification)
	{
		this.to = to;
		this.priority = priority;
		this.notification = notification;
	}

	/**
	 * @param to
	 * @param priority
	 * @param data
	 */
	public Push(String to, String priority, DataPlayload data)
	{
		this.to = to;
		this.priority = priority;
		this.data = data;
	}

	/**
	 * @param priority
	 * @param data
	 * @param registrationIds
	 */
	public Push(String priority, DataPlayload data, List<String> registrationIds)
	{
		this.priority = priority;
		this.data = data;
		this.registrationIds = registrationIds;
	}


	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public DataPlayload getData() {
		return data;
	}

	public void setData(DataPlayload data) {
		this.data = data;
	}

	public List<String> getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(List<String> registrationIds) {
		this.registrationIds = registrationIds;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}