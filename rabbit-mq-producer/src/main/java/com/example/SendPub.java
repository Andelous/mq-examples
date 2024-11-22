package com.example;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendPub {
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		Scanner sc = new Scanner(System.in);

		String message;

		System.out.println("¡Escribe algunos mensajes!");

		while (sc.hasNextLine()) {
			message = sc.nextLine();

			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

			System.out.println(" [x] Sent '" + message + "'");
		}
	}
}
