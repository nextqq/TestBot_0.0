

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;





//import org.telegram.telegrambots.api.methods.GetFile;





public class Bot extends TelegramLongPollingBot {

    public static Date upTime = new Date();

    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId()); отвечает на сообение
        sendMessage.setText(text);
        try {
            //setButtons(sendMessage);
            execute(sendMessage);
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message=update.getMessage();


        if (message != null && message.hasDocument()){




        }




        if (message != null && message.hasText()){

            if (message.getText().equals("/help")){
                sendMsg(message, "Чем могу помочь?");
            }else
            if (message.getText().equals("/start")){
                start(message);
            }

        }else if(update.hasCallbackQuery()){

            String call_data = update.getCallbackQuery().getData();
            if (call_data.equals("set_msg_text")) {
                setingMenu(update.getCallbackQuery());
            }
            if (call_data.equals("go_start")) {
                startres(update.getCallbackQuery());
            }
            if (call_data.equals("info_msg_text")) {
                infomenu(update.getCallbackQuery());
            }

        }
    }


    public void infomenu(CallbackQuery callbackQuery){

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        List<InlineKeyboardButton> rowLine = new ArrayList<InlineKeyboardButton>();
        rowLine.add(new InlineKeyboardButton().setText("Назад  <---").setCallbackData("go_start"));
        rowsInline.add(rowLine);
        markupInline.setKeyboard(rowsInline);
        EditMessageText new_Message = new EditMessageText()
                .setChatId(callbackQuery.getMessage().getChatId())
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setText("Данный бот предназначен хуй знает для чего!")
                .setReplyMarkup(markupInline);

        try {
            execute(new_Message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setingMenu(CallbackQuery callbackQuery){

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();
        rowInline.add(new InlineKeyboardButton().setText("Что-то ещё...").setCallbackData("...."));
        List<InlineKeyboardButton> rowInline1 = new ArrayList<InlineKeyboardButton>();
        rowInline1.add(new InlineKeyboardButton().setText("Выбрать язык").setCallbackData("set_lang_msg_text"));
        List<InlineKeyboardButton> rowInline2 = new ArrayList<InlineKeyboardButton>();
        rowInline2.add(new InlineKeyboardButton().setText("Назад  <---").setCallbackData("go_start"));
        rowsInline.add(rowInline);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);


        EditMessageText new_Message = new EditMessageText()
                .setChatId(callbackQuery.getMessage().getChatId())
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setText("Вы в настройках! ⚙⚙️⚙️")
                .setReplyMarkup(markupInline);

        try {
            execute(new_Message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }





    public void start(Message message){


        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText("НЕССАР!✋ " +
                        "ЭТА ХУЙНЯ РАБОТАЕТ!\uD83D\uDCC4");



        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<InlineKeyboardButton>();
        rowInline1.add(new InlineKeyboardButton().setText("Информация").setCallbackData("info_msg_text"));

        // Set the keyboard to the markup
        rowsInline.add(rowInline1);

        List<InlineKeyboardButton> rowInline2 = new ArrayList<InlineKeyboardButton>();
        rowInline2.add(new InlineKeyboardButton().setText("Последний запуск: " + new SimpleDateFormat("dd.MM.yyyy hh:mm:ss zzz").format(upTime)).setCallbackData("dae"));
//
        rowsInline.add(rowInline2);

        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);

        try {
            execute(sendMessage); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void startres(CallbackQuery callbackQuery){


        EditMessageText sendMessage = new EditMessageText()
                .setChatId(callbackQuery.getMessage().getChatId())
                .setMessageId(callbackQuery.getMessage().getMessageId())
                .setText("Привет!✋ ");



        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();
        rowInline.add(new InlineKeyboardButton().setText("Информация").setCallbackData("info_msg_text"));
        rowInline.add(new InlineKeyboardButton().setText("Настройки").setCallbackData("set_msg_text"));
        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
        try {
            execute(sendMessage); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }






    public String getBotUsername() {
        return "BOTSKIBOT";
    }

    public String getBotToken() {
        return "899611511:AAGjepHsn8Af4wi1LGCj3XhNlmmvwZP8yYU";
    }
}