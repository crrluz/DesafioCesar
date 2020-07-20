package desafio.carla.questions;

import desafio.carla.elements.DiscourseElements;
import desafio.carla.elements.TrivagoElements;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.ArrayList;
import java.util.List;

public class TrivagoQuestions {

    public static Question<List<String>> theHotelNames(){
        return actor -> Text.of(TrivagoElements.HOTEL_NAME).viewedBy(actor).asList();
    }

}
