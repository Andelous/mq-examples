package com.example;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendQueue {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		Scanner sc = new Scanner(System.in);

		String message;

		System.out.println("¡Escribe algunos mensajes!");

		while (sc.hasNextLine()) {
			message = sc.nextLine();

			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

			System.out.println(" [x] Sent '" + message + "'");
		}
	}
}