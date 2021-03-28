package actions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

public class OnMouseLeaveAction implements EventListener {
    private String country;
    private Document document;

    public OnMouseLeaveAction(String country, Document document){
        this.country = country;
        this.document = document;
    }

    @Override
    public void handleEvent(Event evt) {
        Element elt = document.getElementById(country);

        if (country == "Russia") { // in case of Kaliningrad part
            document.getElementById("Russia").setAttribute("style", "fill: #fff; stroke: #3899E6; stroke-width: 5; stroke-linejoin: round");
            document.getElementById("Kaliningrad").setAttribute("style", "fill: #fff; stroke: #3899E6; stroke-width: 5; stroke-linejoin: round");

        } else
            elt.setAttribute("style", "fill: #fff; stroke: #3899E6; stroke-width: 1; stroke-linejoin: round");
    }
}
