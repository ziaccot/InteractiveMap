package gui;

import actions.OnClickAction;
import actions.OnMouseEnterAction;
import actions.OnMouseLeaveAction;
import org.apache.batik.bridge.Window;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.JSVGScrollPane;
import org.apache.batik.swing.svg.JSVGComponent;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.EventTarget;
import properties.CountryProperty;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Paths;

public class SVGFrame {
    private JFrame frame;
    private JSVGCanvas canvas;
    Document document;
    Window window;

    public SVGFrame(){
        frame = new JFrame();
        canvas = new JSVGCanvas();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setDocumentState(JSVGComponent.ALWAYS_DYNAMIC);

        canvas.addSVGLoadEventDispatcherListener(new SVGLoadEventDispatcherAdapter(){
            public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent event){
                document = canvas.getSVGDocument();
                window = canvas.getUpdateManager()
                        .getScriptingEnvironment()
                        .getWindow();

                registerListeners();
                frame.pack();
                frame.setSize(800, 600);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                File file = Paths.get("src", "main", "resources", "europe.svg").toFile();
                //System.out.println(getClass().getResource("europe.svg").toString());
                canvas.setURI(file.toURI().toString());
            }
        });
        JSVGScrollPane pane = new JSVGScrollPane(canvas);
        frame.setContentPane(pane);
        //frame.getContentPane().add(canvas);
        frame.show();
    }

    private void registerListeners(){
        for (String currentCountry : CountryProperty.countryies){
            String country = currentCountry;
            Element elt = document.getElementById(country);
            EventTarget target = (EventTarget) elt;

            target.addEventListener("click", new OnClickAction(country, frame), false);
            target.addEventListener("mousemove", new OnMouseEnterAction(country, document), false);
            target.addEventListener("mouseout", new OnMouseLeaveAction(country, document), false);
        }
    }
}
