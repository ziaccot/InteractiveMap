package actions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

public class OnMouseEnterAction implements EventListener {
    private String country;
    private Document document;

    public OnMouseEnterAction(String country, Document document){
        this.country = country;
        this.document = document;
    }
    @Override
    public void handleEvent(Event evt) {
        Element elt = document.getElementById(country);

        if ((country == "Russia" ) || (country == "Kaliningrad")) { // in case of Kalinigrad part
            document.getElementById("Russia").setAttribute("style", "fill: #1669AD; stroke: #3899E6; stroke-width: 5; stroke-linejoin: round");
            document.getElementById("Kaliningrad").setAttribute("style", "fill: #1669AD; stroke: #3899E6; stroke-width: 5; stroke-linejoin: round");
        } else
            elt.setAttribute("style", "fill: #1669AD; stroke: #3899E6; stroke-width: 1; stroke-linejoin: round");
    }
}
