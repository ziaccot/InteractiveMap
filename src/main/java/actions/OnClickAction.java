package actions;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import javax.swing.*;

public class OnClickAction implements EventListener {
    private String country;
    private JFrame frame;

    public OnClickAction(String country, JFrame frame){
        this.country = country;
        this.frame = frame;
    }
    @Override
    public void handleEvent(Event evt) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/flags/"+country.toLowerCase().replaceAll(" ", "")+".png"));
        JOptionPane.showMessageDialog(frame, country, "About", 1, icon);
    }
}
