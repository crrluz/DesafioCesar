package desafio.carla.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Evaluate;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.questions.Presence;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ScrollDown implements Task {

    Target element;

    public ScrollDown(Target element){
        this.element=element;
    }

    public static ScrollDown untilSeeElement(Target element) {
        return instrumented(ScrollDown.class,element);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        while(!Presence.of(element).viewedBy(actor).asBoolean()){
            actor.attemptsTo(Evaluate.javascript("window.scrollTo(0, document.body.scrollHeight)"));
        }
        actor.attemptsTo(Scroll.to(element));
    }
}
