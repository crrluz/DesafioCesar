package desafio.carla.steps;

import desafio.carla.elements.TrivagoElements;
import desafio.carla.tasks.GetAllHotels;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.questions.Presence;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.actors.OnStage.theActor;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

public class TrivagoSteps {

    Actor actor;

    @Given("that (.*) opened the trivago page")
    public void openTrivago(String actorName) {
        actor = theActor(actorName);
        actor.attemptsTo(Open.browserOn().thePageNamed("trivago"));
    }

    @When("she searches individual rooms in hotels that are located in Natal")
    public void searchIndividualRoomsInHotelsThatAreLocatedInNatal() {
        actor.attemptsTo(
                Enter.theValue("Natal").into(TrivagoElements.SEARCH_FIELD),
                Click.on(TrivagoElements.SEARCH_FIRST_SUGGESTION),
                Click.on(TrivagoElements.SEARCH_BUTTON),
                Click.on(TrivagoElements.ROOM_TYPE),
                Click.on(TrivagoElements.ROOM_TYPE_INDIVIDUAL),
                SelectFromOptions.byVisibleText("Somente distância").from(TrivagoElements.SORT_BY),
                MoveMouse.to(TrivagoElements.SEARCH_BUTTON),
                WaitUntil.the(TrivagoElements.PROCESSING,isNotVisible()).forNoMoreThan(20).seconds());
    }

    @Then("she should see all Deals for that place")
    public void sheShouldSeeAllDealsForThatPlace() {
        do {
            actor.attemptsTo(GetAllHotels.fromPage());
            if(Presence.of(TrivagoElements.NEXT_PAGE).viewedBy(actor).asBoolean()){
                actor.attemptsTo(Click.on(TrivagoElements.NEXT_PAGE),
                        MoveMouse.to(TrivagoElements.SEARCH_BUTTON),
                        WaitUntil.the(TrivagoElements.PROCESSING,isNotVisible()).forNoMoreThan(20).seconds()
                );
            }
        } while(Presence.of(TrivagoElements.NEXT_PAGE).viewedBy(actor).asBoolean());
        actor.attemptsTo(GetAllHotels.fromPage());
    }

}
