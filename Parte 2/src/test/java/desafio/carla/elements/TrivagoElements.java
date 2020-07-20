package desafio.carla.elements;

import net.serenitybdd.screenplay.targets.Target;

public class TrivagoElements {

    public static final Target SEARCH_FIELD = Target.the("Field to input search keys").locatedBy("#querytext");
    public static final Target SEARCH_FIRST_SUGGESTION = Target.the("Field to input search keys").locatedBy("//ul[@id='ssg-suggestions']/li[1]");
    public static final Target SEARCH_BUTTON = Target.the("Button to start search").locatedBy("//button[@data-qa='search-button']");
    public static final Target ROOM_TYPE = Target.the("Select to room type").locatedBy("//span[contains(@class,'icon-rtl')]/../..");
    public static final Target ROOM_TYPE_INDIVIDUAL = Target.the("Room type individual").locatedBy("//li[@class='roomtype-item' and contains(.,'Individual')]/button");
    public static final Target PROCESSING = Target.the("Processing label").locatedBy("//span[text()='Processando']");
    public static final Target MORE_DEALS = Target.the("Show more deals").locatedBy("//button[contains(@class,'deal-other__more')]");
    public static final Target HOTEL_NAME = Target.the("Hotel Name").locatedBy("//h3[@itemprop='name']");
    public static final Target HOTEL_STARS = Target.the("Hotel Stars").locatedBy("//h3[@itemprop='name']/../div|//h3[@itemprop='name']/../../div[2]//span[@data-qa='accommodation-type']");
    public static final Target SEE_MORE = Target.the("Hotel Stars").locatedBy("//button[contains(.,'Ver mais')]");
    public static final Target DEALS = Target.the("Hotel Deals").locatedBy("//ul[@class='slideout-deals']/li");
    public static final Target NEXT_PAGE = Target.the("Hotel Deals").locatedBy("//button[contains(@class,'btn--page-arrow btn--next')]");
    public static final Target SORT_BY = Target.the("Sort Hotels By").locatedBy("#mf-select-sortby");
    public static final Target STARS_FILTER = Target.the("Filter for stars").locatedBy("//li[@data-qa='stars-filter']");
    public static final Target HEADER = Target.the("Filter for stars").locatedBy("#js_navigation");


}
