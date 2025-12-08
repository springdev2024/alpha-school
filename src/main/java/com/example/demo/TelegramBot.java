package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class TelegramBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

	private final String botToken;

	private final TelegramClient client;

	public TelegramBot(@Value("${telegrambot.token}") String botToken) {
		this.botToken = botToken;
		client = new OkHttpTelegramClient(botToken);
		System.out.println("Bot initialized!");
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

	@Override
	public LongPollingUpdateConsumer getUpdatesConsumer() {
		return this;
	}

	@Override
	public void consume(Update update) {
		System.out.println("Update received!");

		if (update.hasMessage() && update.getMessage().hasText()) {
			String text = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();

			text = text.toLowerCase().strip();
			if (text.equals("/start")) {
				sendMsg(chatId, "Hello! I'm your Spring Boot Telegram bot 🚀");
			} else if (text.equals("/help")) {
				sendMsg(chatId, "Available commands:\n/start - greeting\n/help - this message");
			} else {
				sendMsg(chatId, "You said: " + text);
			}
		}
	}

	private void sendMsg(Long chatId, String text) {
		SendMessage message = SendMessage.builder().chatId(chatId.toString()).text(text).build();
		try {
			client.execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
