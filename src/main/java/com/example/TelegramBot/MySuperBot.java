package com.example.TelegramBot;

import com.example.TelegramBot.repos.ChatRepo;
import com.example.TelegramBot.repos.UserRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

@Component
public class MySuperBot extends TelegramLongPollingBot {
    private static final String TOKEN = "5368993802:AAHhzFsbtlJXGFHuoPGRpmUpQ-gXz0GoiZY";
    private static final String USERNAME = "clear_mind_by_tk_bot";
    private int i = 0;
    private List<Integer> unswers = new ArrayList<>();
    private Integer p1, p2, p3, p4, p5, p6, p7, p8;
    Integer messageId;
    private String[] questions = new String[]{"Я разъясняю людям причины, по которым моя работа не сделана.",
            "Я переживаю по поводу чего-либо (из-за того, что кто-то сделал или сказал мне, или из-за того, что я сам сделал).",
            "Я опаздываю на работу или на встречи с кем-либо.", "Я требую, чтобы люди делали то, что я говорю.",
            "Я заполняю предложенные мне анкеты и отвечаю на вопросы информационного характера (например, в социологическом опросс).",
            "Я делаю то, что от меня требуется, даже если это трудно слелать.", "Я передаю слухи.",
            "Я чувствую вину по поводу чего-либо (не сделанной вовремя работы, опоздания, слишком жестоких требований. предъявляемых мною к другим и т.д.).",
            "Я пользуюсь тем, что подсказывает мне интуиция, не задерживаюсь на сборе фактов.",
            "Я радуюсь новому и необычному (непривычной еде, одежде, смене установившегося порядка, места ит.д.).",
            "Я советую больному человеку обратиться к врачу или отдохнуть день-другой.",
            "Я веду себя, так как мне нравится.", "Я чувствую, что мой долг — использовать свои знания и силу для руководства другими людьми.",
            "Я говорю о вещах, в которых совсем не разбираюсь.",
            "Я многократно переспрашиваю, не готово ли то, чего я жду, хотя знаю, что ещё рано.",
            "Я нахожу пути, чтобы сделать скучную работу интересной.",
            "Я сдерживаю обещания, даже если мне это не выгодно.", "Я чётко и ясно передаю другим суть того, что я хочу сообщить.",
            "Я заранее знаю, как поведут себя люди в определённых обстоятельствах.", "Я внимательно анализирую имеющиеся факты перед тем, как принять решение.",
            "Я говорю (или думаю) что-то типа: «Что они будут делать без меня?».",
            "Я открыто и непосредственно выражаю свои чувства и живо реагирую на происходящее.",
            "Я говорю только правду.", "Я привлекаю к порядку других людей, когда они не соблюдают установленные правила.",
            "Я ясно соображаю, как сделать так, чтобы люди действовали в моих интересах.",
            "Я сохраняю спокойствие, когда «накаляется атмосфера».", "Я прихожу на помощь людям, оказавшимся в затруднительном положении.",
            "Я ухожу, что побыть наедине с собой, чувствуя себя обиженным.", "Я непреклонно отстаиваю свои убеждения и принципы.",
            "У меня не бывает мыслей, которыми я не хотел бы делиться с другими.", "Я прерываю работу для физической разминки и чувствую истинное наслаждение, разминая мышцы и расслабляясь.",
            "Я чувствую бессилие, неспособность справиться с ситуацией.", "Я ободряю и утешаю людей, когда у них неприятности.",
            "Я апеллирую к фактам, обрисовываю реальное положение дел, когда нужно разрешить трудную ситуацию.",
            "Я без колебания беру последнее оставшееся пирожное или другую вкусную вещь из того, что принесли к чаю.",
            "Я знаю как себя повести, чтобы добиться своего.", "Я помогаю окружающим в случае необходимости, даже если они меня об этом не просят.",
            "Я настаиваю, чтобы люди заботились о себе, например, чтобы тепло одевались в холодную погоду или брали зонт, если ожидается дождь.",
            "Я чувствую, что должен добиваться совершенства в том, что я делаю.",
            "Я по выражению лица и другим особенностям поведения человека заранее знаю, что он скажет или сделает.",
            "Разозлившись, я раздражаюсь и выхожу из себя.", "Я прямо высказываю недовольство людям, не выполняющим работу должным образом.",
            "Я требую соблюдения обычаев и традиций.", "Я завершаю за день все, что сегодня предполагалось сделать."};
    @Autowired
    UserRepo userRepo;
    @Autowired
    ChatRepo chatRepo;


    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && isErickBirn(update.getMessage()) && i < 44) {
            i = 0;
            unswers = new ArrayList<>();
            execute(SendMessage.builder().chatId(update.getMessage().getChatId().toString()).text("Инструкция. Вам предлагается несколько утверждений, касающихся вашего поведения в повседневной жизни. Ответьте, как часто вы так поступаете или чувствуете, поставив в бланке «+» («да») против подходящего варианта. Здесь не может быть «плохих» и «хороших» ответов: это ваш собственный взгляд на то, каким вы являетесь на сегодняшний день.").build());
            com.example.TelegramBot.models.User newUser = new com.example.TelegramBot.models.User(update.getMessage().getFrom().getId(),
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getFrom().getLastName(),
                    update.getMessage().getFrom().getUserName());

            System.out.println(newUser);

            com.example.TelegramBot.models.User userFromDb = userRepo.findById(newUser.getId());

            if(userFromDb == null) {
                userRepo.save(newUser);
            }

            setNextQuestion(i, update.getMessage().getChatId().toString());
        }
        if (update.hasCallbackQuery() && i < 44) {
            messageId = update.getCallbackQuery().getMessage().getMessageId();
            handleCallback(update.getCallbackQuery());
            execute(DeleteMessage.builder().chatId(update.getCallbackQuery().getMessage().getChatId().toString()).messageId(messageId).build());
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        System.out.println(message);
        System.out.println(data);
        unswers.add(Integer.valueOf(data));
        System.out.println(unswers);
        if(i < 43) {
            i++;
            setNextQuestion(i, callbackQuery.getMessage().getChatId().toString());
        } else if (i == 43) {
            p1 = unswers.get(3) + unswers.get(12) + unswers.get(23) + unswers.get(28) + unswers.get(41) + unswers.get(42);
            p2 = unswers.get(10) + unswers.get(20) + unswers.get(26) + unswers.get(32) + unswers.get(36) + unswers.get(37);
            p3 = unswers.get(0) + unswers.get(4) + unswers.get(17) + unswers.get(19) + unswers.get(25) + unswers.get(33);
            p4 = unswers.get(9) + unswers.get(11) + unswers.get(14) + unswers.get(21) + unswers.get(30) + unswers.get(34);
            p5 = unswers.get(1) + unswers.get(5) + unswers.get(7) + unswers.get(27) + unswers.get(31) + unswers.get(38);
            p6 = unswers.get(2) + unswers.get(6) + unswers.get(13) + unswers.get(40);
            p7 = unswers.get(8) + unswers.get(15) + unswers.get(18) + unswers.get(24) + unswers.get(35) + unswers.get(39);
            p8 = unswers.get(16) + unswers.get(22) + unswers.get(29) + unswers.get(43);

            execute(SendMessage.builder().chatId(callbackQuery.getMessage().getChatId().toString()).text("Спасибо! Результат уже у Clear Mind!").build());
            execute(SendMessage.builder().chatId("393965522").text("Результат " + callbackQuery.getMessage().getChat().getFirstName() +
                    " " + callbackQuery.getMessage().getChat().getLastName() + ":" + "\nРодитель Критикущий (Рк): " + p1 +
                    "\nРодитель Заботящийся (Рз): " + p2 +
                    "\nВзрослый (В): " + p3 +
                    "\nДитя Естественное (Де): " + p4 +
                    "\nДитя Адаптированное (Да): " + p5 +
                    "\nДитя Бунтующее (Дб): " + p6 +
                    "\nМаленький Профессор (Мп): " + p7 +
                    "\nШкала лжи: " + p8).build());
            execute((SendMessage.builder().chatId("393965522").text("http://Telegrambotclearmind-env.eba-nmmnfdut.eu-central-1.elasticbeanstalk.com/" + callbackQuery.getMessage().getChatId()).build()));
            com.example.TelegramBot.models.Chat newChat = new com.example.TelegramBot.models.Chat(callbackQuery.getMessage().getChat().getId(),
                    callbackQuery.getMessage().getChat().getFirstName(),
                    callbackQuery.getMessage().getChat().getLastName(),
                    callbackQuery.getMessage().getChat().getUserName(),
                    p1, p2, p3, p4, p5, p6, p7, p8);
            chatRepo.save(newChat);
            unswers = new ArrayList<>();
            i = 0;
        }
    }

    @SneakyThrows
    private boolean isErickBirn(Message message) {
        if(message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/erick_birn":
                        return true;
                }
            }
        }
        return false;
    }

    @SneakyThrows
    private void setNextQuestion (int i, String chatId) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("✅").callbackData("1").build(),
                InlineKeyboardButton.builder().text("❌").callbackData("0").build()
        ));
        execute(SendMessage.builder().text((i +1) + ". " + questions[i])
                .chatId(chatId)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build());
    }
}
