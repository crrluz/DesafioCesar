package desafio.carla.elements;

import net.serenitybdd.screenplay.targets.Target;

public class DiscourseElements {

    public final static Target DEMO = Target.the("link for Demo").locatedBy("//a[text()='Demo']");
    public final static Target LOCK_TITLE = Target.the("Titles of Locked topics").locatedBy("//*[contains(concat(' ', normalize-space(@class), ' '),'locked')]/../../../a");
    public final static Target END_WARN = Target.the("Warn for end of page").locatedBy("//*[contains(concat(' ', normalize-space(@class), ' '),'locked')]/../../../a");
    public final static Target CATEGORY = Target.the("Category of the topics").locatedBy("//div[@class='link-bottom-line']");
    public final static Target VIEWS = Target.the("Views of each topic").locatedBy("//td[@class='main-link clearfix']/../td[4]");
    public final static Target TITLES = Target.the("Title of each topic").locatedBy("//span[@class='link-top-line']");
    public final static Target TOPIC_DESCRIPTION = Target.the("The first post in a topic").locatedBy("(//div[@class='cooked'])[1]");

}
