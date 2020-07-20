package desafio.carla.steps;

import desafio.carla.elements.DiscourseElements;
import desafio.carla.questions.DiscourseQuestions;
import desafio.carla.tasks.ScrollDown;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.serenitybdd.screenplay.actors.OnStage.theActor;

public class DiscourseSteps {

    Actor actor;
    Logger LOGGER = LoggerFactory.getLogger(DiscourseSteps.class);

    @Before
    public void setup() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("that (.*) opened the discourse page")
    public void thatOpenedTheDiscoursePage(String actorName) {
        actor = theActor(actorName);
        actor.attemptsTo(Open.browserOn().thePageNamed("discourse"));
    }

    @When("she click on the Demo")
    public void heClickOnTheOptionDemo() {
        actor.attemptsTo(Click.on(DiscourseElements.DEMO));
        List<String> windows = new ArrayList<String>(BrowseTheWeb.as(actor).getDriver().getWindowHandles());
        actor.attemptsTo(Switch.toWindow(windows.get(1)));
    }

    @When("scroll down the page")
    public void scrollDownThePage() {
        actor.attemptsTo(ScrollDown.untilSeeElement(DiscourseElements.END_WARN));
    }

    @Then("she should see the description of all closed topics")
    public void heShouldSeeTheDescriptionOfAllClosedTopics() {
        List<String> titles = actor.asksFor(DiscourseQuestions.theClosedTopics());
        List<String> description = new ArrayList<String>();
        List<WebElementFacade> elements = DiscourseElements.LOCK_TITLE.resolveAllFor(actor);
        Integer index = 0;
        while(index <elements.size()) {
            WebElementFacade link = DiscourseElements.LOCK_TITLE.resolveAllFor(actor).get(index);
            actor.attemptsTo(Scroll.to(link).andAlignToBottom(),Click.on(link));
            description.add(actor.asksFor(DiscourseQuestions.theDescription()));
            actor.attemptsTo(Evaluate.javascript("window.history.go(-1)"));
            index++;
        }
        System.out.println(" ---------------------- ");
        System.out.println(" Closed topics description :");
        listToMap(titles,description).forEach((key,value) -> System.out.println(String.format(" - %s : %s %n", key,value)));
        System.out.println(" ---------------------- ");
    }

    @Then("she should see the quantity of topics for each category")
    public void heShouldSeeTheQualityOfTopicsForEachCategory() {
        List<String> categories = actor.asksFor(DiscourseQuestions.theCategories())
                .stream().map(values -> values.split("\\n")[0]).collect(Collectors.toList());
        Map<String,Long> catalog = categories.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );
        catalog.put("Uncategorized",catalog.get(""));
        catalog.remove("");
        System.out.println(" ---------------------- ");
        System.out.println("   | Category : Quantity |");
        catalog.forEach((key,value) -> System.out.printf("   | %s : %d |%n", key, value));
        System.out.println(" ---------------------- ");
    }

    @Then("she should see the title of the topic with most views")
    public void sheShouldSeeTheTitleOfTheTopicWithMostViews() {
        List<String> views = actor.asksFor(DiscourseQuestions.theViews());
        List<String> titles = actor.asksFor(DiscourseQuestions.theTitles());
        List<Long> viewLong = views.stream()
                .map(value -> value.endsWith("k")?(long)Double.parseDouble(value.replace("k",""))*1000:Long.parseLong(value))
                .collect(Collectors.toList());
        String titleMostViews = listToMap(titles,viewLong)
                .entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed()).findFirst().get().getKey();
        System.out.println(" ---------------------- ");
        System.out.println(" Topic with most views: ");
        System.out.printf(" %s%n", titleMostViews);
        System.out.println(" ---------------------- ");
    }

    private <K,V> Map<K,V> listToMap(List<K> keys, List<V> values) {
        return IntStream.range(0,keys.size()).boxed()
                .collect(Collectors.toMap(keys::get,values::get));
    }

}
