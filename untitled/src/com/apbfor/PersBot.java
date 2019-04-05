package com.apbfor;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class PersBot extends TelegramLongPollingBot {




    //@Override
    public void onUpdateReceived1(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        ArrayList<String> photoBook = new ArrayList<String>();
        photoBook.add("AgADAgADA6oxG2SRKUkk3l_YwkKsnU93Uw8ABGM75FTN0Uh808MBAAEC");
        photoBook.add("AgADAgADBKoxG_PYKUk7JfTZNPiwuoBuXw8ABEEzqCyti5r3i1gDAAEC");
        photoBook.add("AgADAgADBaoxG_PYKUnHjkRpbKpcNSIC9Q4ABP1M3KTiTxpwqmIGAAEC");
        photoBook.add("AgADAgADBKoxG2SRKUmmjebwT-ycwHx5Xw8ABHtd4XrZf5hEBFgDAAEC");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("sosi");
        row.add("random photo");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);


        if(update.hasMessage()&&update.getMessage().hasText()){

            String text = update.getMessage().getText();
            switch (text) {
                case "sosi": {
                    String replyText = "sam sasi";
                    SendMessage message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setReplyMarkup(keyboardMarkup)
                            .setText(replyText);
                    try {
                        execute(message);
                    } catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                    break;
                }
                case "random photo":{
                    Random random = new Random();
                    SendPhoto photo = new SendPhoto()
                            .setPhoto(photoBook.get(random.nextInt(photoBook.size())))
                            .setCaption("Your photo!")
                            .setReplyMarkup(keyboardMarkup)
                            .setChatId(update.getMessage().getChatId());
                    try {
                        execute(photo);
                    } catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                    break;
                }
                default:{
                    String replyText = "hz che za command";
                    SendMessage message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setReplyMarkup(keyboardMarkup)
                            .setText(replyText);
                    try {
                        execute(message);
                    } catch (TelegramApiException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(update.hasMessage()&&update.getMessage().hasPhoto()){
            long chat_id = update.getMessage().getChatId();

            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();
            // Know file_id
            String nice_f_id="`";
            String f_id = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            // Know photo width
            nice_f_id+=f_id;
            nice_f_id+="`";

            int f_width = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getWidth();
            // Know photo height
            int f_height = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getHeight();
            // Set photo caption

            SendMessage message = new SendMessage()
                    .setChatId(chat_id)
                    .setText(nice_f_id)
                    .setReplyMarkup(keyboardMarkup);
            message.setParseMode("Markdown");


            try {
                execute(message); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "LimaBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "267611072:AAGax26y49m6rr2GC93ALMWIjB5XmgkxYA8";
    }
}