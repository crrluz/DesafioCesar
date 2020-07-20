package desafio.carla.tasks;

import desafio.carla.elements.TrivagoElements;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.questions.Presence;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

public class GetAllHotels implements Task{

    public static GetAllHotels fromPage() {
        return instrumented(GetAllHotels.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Scroll.to(TrivagoElements.HEADER));
        List<WebElementFacade> elements = TrivagoElements.MORE_DEALS.resolveAllFor(actor);
        List<String> hotels = Text.of(TrivagoElements.HOTEL_NAME.getCssOrXPathSelector()).viewedBy(actor).asList();
        List<WebElementFacade> starsElements = TrivagoElements.HOTEL_STARS.resolveAllFor(actor);
        while(hotels.size()!=starsElements.size() || hotels.size()!=elements.size()){
            elements = TrivagoElements.MORE_DEALS.resolveAllFor(actor);
            hotels = Text.of(TrivagoElements.HOTEL_NAME.getCssOrXPathSelector()).viewedBy(actor).asList();
            starsElements = TrivagoElements.HOTEL_STARS.resolveAllFor(actor);
        }
        List<String> stars = extractStars(starsElements);
        int allIndex = 0;
        while(allIndex<elements.size()){
            System.out.println(String.format("Nome: %s Estrelas: %s",hotels.get(allIndex),stars.get(allIndex)));
            WebElementFacade element = elements.get(allIndex);
            actor.attemptsTo(
                    MoveMouse.to(element),
                    Click.on(element));
            if(Presence.of(TrivagoElements.SEE_MORE).viewedBy(actor).asBoolean()){
                actor.attemptsTo(Click.on(TrivagoElements.SEE_MORE));
            }
            List<WebElementFacade> deals = TrivagoElements.DEALS.resolveAllFor(actor);
            for(WebElementFacade deal: deals){
                String company = deal.findElement(By.xpath(".//img[@class='slideout-deal__image']")).getAttribute("title");
                String description = deal.findElement(By.xpath(".//div/div/div")).getText();
                String price = deal.findElement(By.xpath(".//button/span[@class='slideout-deal__price']")).getText().trim().replace("\n","");
                System.out.println(String.format("Oferta da empresa: %s Acomodação: %s Preço: %s",company,description,price));
            }
            actor.attemptsTo(Click.on(element));
            allIndex++;
            System.out.println(" ---------------------------- ");
        }
    }

    private List<String> extractStars(List<WebElementFacade> starElements){
        List<String> stars = new ArrayList<>();
        for(WebElementFacade element: starElements) {
            List<WebElement> starsCount = element.findElements(By.xpath(".//span[@class='icon-ic star']"));
            String star = String.format("%s Estrela",starsCount.size());
            if(starsCount.size()>1){
                star = String.format("%ss",star);
            } else if(starsCount.size()<1){
                star = "Sem Estrelas";
            }
            stars.add(star);
        }
        return stars;
    }

}
