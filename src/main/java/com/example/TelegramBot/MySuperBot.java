package com.example.TelegramBot;

import com.example.TelegramBot.repos.Chat2Repo;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.*;
import java.util.List;

@Component
public class MySuperBot extends TelegramLongPollingBot {
    private static final String TOKEN = "5368993802:AAHhzFsbtlJXGFHuoPGRpmUpQ-gXz0GoiZY";
    private static final String USERNAME = "clear_mind_by_tk_bot";
    private static final String ADMINID = "393965522";
    private com.example.TelegramBot.models.Chat chat;
    private com.example.TelegramBot.models.Chat2 chat2;
    private List<Integer> unswers = new ArrayList<>();
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
    private String[] questions2 = new String[]{"Мне порой не хватает выдержки.", "Если мои желания мешают мне, то я умею их подавлять.",
    "Родители, как более зрелые люди, должны устраивать семейную жизнь своих детей", "Я иногда преувеличиваю свою роль в каких-то событиях.",
    "Меня провести нелегко", "Мне бы понравилось быть воспитателем.", "Бывает, мне хочется подурачиться, как маленькому.",
    "Думаю, что я правильно понимаю все происходящие события.", "Каждый должен выполнять свой долг.", "Нередко я поступаю не как надо, а как хочется.",
    "Принимая решение, я стараюсь продумать его последствия.", "Младшее поколение должно учиться у старших, как ему следует жить.",
    "Я, как и многие люди, бываю обидчив.", "Мне удается видеть в людях больше, чем они говорят о себе.",
    "Дети должны безусловно следовать указаниям родителей.", "Я - увлекающийся человек.", "Мой основной критерий оценки человека — объективность.",
    "Мои взгляды непоколебимы.", "Бывает, что я не уступаю в споре лишь потому, что не хочу уступать.",
    "Правила оправданы лишь до тех пор, пока они полезны.", "Люди должны соблюдать все правила независимо от обстоятельств."};
    @Autowired
    UserRepo userRepo;
    @Autowired
    ChatRepo chatRepo;
    @Autowired
    Chat2Repo chat2Repo;


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

        if(update.hasMessage() && isErickBirn(update.getMessage())) {
            unswers = new ArrayList<>();
            execute(SendMessage.builder().chatId(update.getMessage().getChatId().toString()).text("Инструкция. Вам предлагается несколько утверждений, касающихся вашего поведения в повседневной жизни. Ответьте, как часто вы так поступаете или чувствуете, поставив в бланке «+» («да») против подходящего варианта. Здесь не может быть «плохих» и «хороших» ответов: это ваш собственный взгляд на то, каким вы являетесь на сегодняшний день.").build());
            com.example.TelegramBot.models.User newUser = new com.example.TelegramBot.models.User(update.getMessage().getFrom().getId(),
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getFrom().getLastName(),
                    update.getMessage().getFrom().getUserName());

            System.out.println(newUser);
            com.example.TelegramBot.models.Chat chat = new com.example.TelegramBot.models.Chat(update.getMessage().getChatId(),
                    update.getMessage().getChat().getFirstName(), update.getMessage().getChat().getLastName(),
                    update.getMessage().getChat().getUserName());
            this.chat = chat;

            com.example.TelegramBot.models.User userFromDb = userRepo.findById(newUser.getId());

            if(userFromDb == null) {
                userRepo.save(newUser);
            }
            if(chat.getNumQuestion() < 44) {
                setNextQuestion(chat, update.getMessage().getChatId().toString());
            }

        }

        if(update.hasMessage() && isErickBirn2(update.getMessage())) {
            unswers = new ArrayList<>();
            execute(SendMessage.builder().chatId(update.getMessage().getChatId().toString()).text("Инструкция. Вам предлагается несколько утверждений, касающихся вашего поведения в повседневной жизни. Ответьте по шкале от 0 до 10, где 0 - точно \"нет\", а 10 - точно \"да\", насколько точно данное утверждение относится к вам. Здесь не может быть «плохих» и «хороших» ответов: это ваш собственный взгляд на то, каким вы являетесь на сегодняшний день.").build());
            com.example.TelegramBot.models.User newUser = new com.example.TelegramBot.models.User(update.getMessage().getFrom().getId(),
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getFrom().getLastName(),
                    update.getMessage().getFrom().getUserName());


            com.example.TelegramBot.models.Chat2 chat2 = new com.example.TelegramBot.models.Chat2(update.getMessage().getChatId(),
                    update.getMessage().getChat().getFirstName(), update.getMessage().getChat().getLastName(),
                    update.getMessage().getChat().getUserName());
            this.chat2 = chat2;

            com.example.TelegramBot.models.User userFromDb = userRepo.findById(newUser.getId());

            if(userFromDb == null) {
                userRepo.save(newUser);
            }
            if(chat2.getNumQuestion() < 21) {
                setNextQuestion2(chat2, update.getMessage().getChatId().toString());
            }
        }
        if (update.hasCallbackQuery() && chat.getId() > 0) {
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            handleCallback(update.getCallbackQuery(), chat);
            execute(DeleteMessage.builder().chatId(update.getCallbackQuery().getMessage().getChatId().toString()).messageId(messageId).build());
        }
        if(update.hasMessage() && !update.getMessage().hasEntities()) {
            chat2.setNumQuestion(chat2.getNumQuestion() + 1);
            switch (update.getMessage().getText()) {
                case "0️⃣":
                    unswers.add(0);
                    break;
                case "1️⃣":
                    unswers.add(1);
                    break;
                case "2️⃣":
                    unswers.add(2);
                    break;
                case "3️⃣":
                    unswers.add(3);
                    break;
                case "4️⃣":
                    unswers.add(4);
                    break;
                case "5️⃣":
                    unswers.add(5);
                    break;
                case "6️⃣":
                    unswers.add(6);
                    break;
                case "7️⃣":
                    unswers.add(7);
                    break;
                case "8️⃣":
                    unswers.add(8);
                    break;
                case "9️⃣":
                    unswers.add(9);
                    break;
                case "\uD83D\uDD1F":
                    unswers.add(10);
                    break;
                default:
                    execute(SendMessage.builder().text("Я вас не понял.").chatId(update.getMessage().getChatId().toString()).build());
                    break;
            }
            if (chat2.getNumQuestion() < 21) {
                setNextQuestion2(chat2, update.getMessage().getChatId().toString());
            } else if (chat2.getNumQuestion() == 21) {
                chat2.setP1(unswers.get(0) + unswers.get(3) + unswers.get(6) + unswers.get(9) + unswers.get(12) + unswers.get(15) + unswers.get(18));
                chat2.setP2(unswers.get(1) + unswers.get(4) + unswers.get(7) + unswers.get(10) + unswers.get(13) + unswers.get(16) + unswers.get(19));
                chat2.setP3(unswers.get(2) + unswers.get(5) + unswers.get(8) + unswers.get(11) + unswers.get(14) + unswers.get(17) + unswers.get(20));
                chat2Repo.save(chat2);
                execute(SendMessage.builder().text("Ваш результат: " + "\nДитя: " + chat2.getP1() + "\nВзрослый: " + chat2.getP2() + "\nРодитель: " + chat2.getP3()).chatId(update.getMessage().getChatId().toString()).build());
                if(chat2.getP1() >= chat2.getP2() && chat2.getP2() >= chat2.getP3()) {
                    execute(SendMessage.builder().text("Формула ДВР говорит о непосредственности и эмоциональности как главных составляющих процесса общения. Но эти качества хороши до определенных пределов. Если они начинают мешать общению, то пора взять эмоции под контроль.\n" +
                            "\n" +
                            "«Д» на первом месте — это вполне приемлемый вариант, скажем, для научной работы. Эйнштейн шутливо объяснил причины своих научных успехов тем, что он развивался медленно, и над многими вопросами задумался лишь в том возрасте, когда люди обычно перестают о них думать. Но детская непосредственность хороша лишь до определенной степени. Если она начинает мешать делу, пора взять свои эмоции под контроль.").chatId(update.getMessage().getChatId().toString()).build());
                } else if (chat2.getP2() >= chat2.getP1() && chat2.getP1() >= chat2.getP3()) {
                    execute(SendMessage.builder().chatId(update.getMessage().getChatId().toString()).text("Вы обладаете развитым чувством ответственности, в меру импульсивны и не склонны назиданиям и поучениям. Вам можно пожелать лишь сохранять эти качества и впредь. Они помогут в любом деле, связанном с общением, коллективным трудом, творчеством. Хуже, если на первом месте стоит «Р». Категоричность и самоуверенность противопоказаны, например, педагогу, организатору — словом, всем тем, кто в основном имеет дело с людьми, а не с машинами.").build());
                } else if (chat2.getP3() >= chat2.getP1() && chat2.getP1() >= chat2.getP2()) {
                    execute(SendMessage.builder().chatId(update.getMessage().getChatId().toString()).text("Формула РДВ - могут возникнуть некоторые сложности, которые способны осложнить жизнь обладателю такой формулы. «Родитель» с детской непосредственностью режет «правду-матку», ни в чем не сомневаясь и не заботясь о последствиях. Но и тут нет поводов для уныния. Если вас не привлекает организаторская работа, шумные компании, и вы предпочитаете побыть наедине с книгой или этюдником, то все в порядке, Если же нет, и вы захотите передвинуть свое «Р» на второе и даже на третье место, то это вполне осуществимо.").build());
                }
                execute(SendMessage.builder().chatId(ADMINID).text("Опросник " + chat2.getFirstName() + " " + chat2.getLastName() + "\nUser name: " + chat2.getUserName() + "\nДитя: " + chat2.getP1() + "\nВзрослый: " + chat2.getP2() + "\nРодитель: " + chat2.getP3()).build());
                if(chat2.getP1() >= chat2.getP2() && chat2.getP2() >= chat2.getP3()) {
                    execute(SendMessage.builder().text("Формула ДВР говорит о непосредственности и эмоциональности как главных составляющих процесса общения. Но эти качества хороши до определенных пределов. Если они начинают мешать общению, то пора взять эмоции под контроль.\n" +
                            "\n" +
                            "«Д» на первом месте — это вполне приемлемый вариант, скажем, для научной работы. Эйнштейн шутливо объяснил причины своих научных успехов тем, что он развивался медленно, и над многими вопросами задумался лишь в том возрасте, когда люди обычно перестают о них думать. Но детская непосредственность хороша лишь до определенной степени. Если она начинает мешать делу, пора взять свои эмоции под контроль.").chatId(ADMINID).build());
                } else if (chat2.getP2() >= chat2.getP1() && chat2.getP1() >= chat2.getP3()) {
                    execute(SendMessage.builder().chatId(ADMINID).text("Вы обладаете развитым чувством ответственности, в меру импульсивны и не склонны назиданиям и поучениям. Вам можно пожелать лишь сохранять эти качества и впредь. Они помогут в любом деле, связанном с общением, коллективным трудом, творчеством. Хуже, если на первом месте стоит «Р». Категоричность и самоуверенность противопоказаны, например, педагогу, организатору — словом, всем тем, кто в основном имеет дело с людьми, а не с машинами.").build());
                } else if (chat2.getP3() >= chat2.getP1() && chat2.getP1() >= chat2.getP2()) {
                    execute(SendMessage.builder().chatId(ADMINID).text("Формула РДВ - могут возникнуть некоторые сложности, которые способны осложнить жизнь обладателю такой формулы. «Родитель» с детской непосредственностью режет «правду-матку», ни в чем не сомневаясь и не заботясь о последствиях. Но и тут нет поводов для уныния. Если вас не привлекает организаторская работа, шумные компании, и вы предпочитаете побыть наедине с книгой или этюдником, то все в порядке, Если же нет, и вы захотите передвинуть свое «Р» на второе и даже на третье место, то это вполне осуществимо.").build());
                }
                unswers = new ArrayList<>();
                this.chat2 = null;
            }
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery, com.example.TelegramBot.models.Chat chat) {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        unswers.add(Integer.valueOf(data));
        if(chat.getNumQuestion() < 43) {
            chat.setNumQuestion(chat.getNumQuestion() + 1);
            setNextQuestion(chat, callbackQuery.getMessage().getChatId().toString());
        } else if (chat.getNumQuestion() == 43) {
            chat.setP1(unswers.get(3) + unswers.get(12) + unswers.get(23) + unswers.get(28) + unswers.get(41) + unswers.get(42));
            chat.setP2(unswers.get(10) + unswers.get(20) + unswers.get(26) + unswers.get(32) + unswers.get(36) + unswers.get(37));
            chat.setP3(unswers.get(0) + unswers.get(4) + unswers.get(17) + unswers.get(19) + unswers.get(25) + unswers.get(33));
            chat.setP4(unswers.get(9) + unswers.get(11) + unswers.get(14) + unswers.get(21) + unswers.get(30) + unswers.get(34));
            chat.setP5(unswers.get(1) + unswers.get(5) + unswers.get(7) + unswers.get(27) + unswers.get(31) + unswers.get(38));
            chat.setP6(unswers.get(2) + unswers.get(6) + unswers.get(13) + unswers.get(40));
            chat.setP7(unswers.get(8) + unswers.get(15) + unswers.get(18) + unswers.get(24) + unswers.get(35) + unswers.get(39));
            chat.setP8(unswers.get(16) + unswers.get(22) + unswers.get(29) + unswers.get(43));

            execute(SendMessage.builder().chatId(callbackQuery.getMessage().getChatId().toString()).text("Спасибо! Результат уже у Clear Mind!").build());
            execute(SendMessage.builder().chatId(ADMINID).text("Результат " + callbackQuery.getMessage().getChat().getFirstName() +
                    " " + callbackQuery.getMessage().getChat().getLastName() + ":" + "\nРодитель Критикущий (Рк): " + chat.getP1() +
                    "\nРодитель Заботящийся (Рз): " + chat.getP2() +
                    "\nВзрослый (В): " + chat.getP3() +
                    "\nДитя Естественное (Де): " + chat.getP4() +
                    "\nДитя Адаптированное (Да): " + chat.getP5() +
                    "\nДитя Бунтующее (Дб): " + chat.getP6() +
                    "\nМаленький Профессор (Мп): " + chat.getP7() +
                    "\nШкала лжи: " + chat.getP8()).build());
            execute((SendMessage.builder().chatId(ADMINID).text("http://Telegrambotclearmind-env.eba-nmmnfdut.eu-central-1.elasticbeanstalk.com/" + callbackQuery.getMessage().getChatId()).build()));
            chatRepo.save(chat);
            unswers = new ArrayList<>();
            this.chat = null;
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
    private boolean isErickBirn2(Message message) {
        if(message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/erick_birn2":
                        return true;
                }
            }
        }
        return false;
    }

    @SneakyThrows
    private void setNextQuestion (com.example.TelegramBot.models.Chat chat, String chatId) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("✅").callbackData("1").build(),
                InlineKeyboardButton.builder().text("❌").callbackData("0").build()
        ));
        execute(SendMessage.builder().text((chat.getNumQuestion() + 1) + ". " + questions[chat.getNumQuestion()])
                .chatId(chatId)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build());
    }

    @SneakyThrows
    private void setNextQuestion2 (com.example.TelegramBot.models.Chat2 chat2, String chatId) {
        List<KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("0️⃣");
        row.add("1️⃣");
        row.add("2️⃣");
        row.add("3️⃣");
        row.add("4️⃣");
        row.add("5️⃣");
        row.add("6️⃣");
        row.add("7️⃣");
        row.add("8️⃣");
        row.add("9️⃣");
        row.add("\uD83D\uDD1F");
        buttons.add(row);
        if (chat2.getNumQuestion() < 20) {
            execute(SendMessage.builder().text((chat2.getNumQuestion() + 1) + ". " + questions2[chat2.getNumQuestion()])
                    .chatId(chatId)
                    .replyMarkup(ReplyKeyboardMarkup.builder().resizeKeyboard(true).oneTimeKeyboard(false).keyboard(buttons).build())
                    .build());
        } else {
            execute(SendMessage.builder().text((chat2.getNumQuestion() + 1) + ". " + questions2[chat2.getNumQuestion()])
                    .chatId(chatId)
                    .replyMarkup(ReplyKeyboardMarkup.builder().resizeKeyboard(true).oneTimeKeyboard(true).keyboard(buttons).build())
                    .build());
        }
        
    }
}
