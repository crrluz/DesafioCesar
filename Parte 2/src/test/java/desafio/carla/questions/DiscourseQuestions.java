package desafio.carla.questions;

import desafio.carla.elements.DiscourseElements;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

public class DiscourseQuestions {

    public static Question<List<String>> theClosedTopics(){
        return actor -> Text.of(DiscourseElements.LOCK_TITLE).viewedBy(actor).asList();
    }

    public static Question<List<String>> theCategories(){
        return actor -> Text.of(DiscourseElements.CATEGORY).viewedBy(actor).asList();
    }

    public static Question<List<String>> theViews(){
        return actor -> Text.of(DiscourseElements.VIEWS).viewedBy(actor).asList();
    }

    public static Question<List<String>> theTitles(){
        return actor -> Text.of(DiscourseElements.TITLES).viewedBy(actor).asList();
    }

    public static Question<String> theDescription() {
        return actor -> Text.of(DiscourseElements.TOPIC_DESCRIPTION).viewedBy(actor).asString();
    }
}
