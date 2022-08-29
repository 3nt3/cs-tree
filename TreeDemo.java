
/**
 * Klasse TreeDemo
 * <p>
 * Roland Stiebel
 * version 1.0
 */

//Import

import basis.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TreeDemo extends Fenster implements KnopfLauscher, RollbalkenLauscher, ListAuswahlLauscher {

    //Deklaration
    private Knopf ende, knopf1, knopf2;
    private BeschriftungsFeld label1;
    private Rollbalken rollbalken;
    private ZahlenFeld zahlenfeld;
    private ListAuswahl liste;
    private Stift stift;
    private TextFeld text1, text2;

    private Tree tree;
    private Content content;

    // Konstruktor fuer Objekte der Klasse TischKlasse

    public TreeDemo() {
        this.initGui();
    }

    public void initGui() {
        this.setzeGroesse(600, 500);
        this.setzeTitel("TreeDemo");
        ende = new Knopf("Ende", 490, 460, 100, 30);
        ende.setzeKnopfLauscher(this);
        label1 = new BeschriftungsFeld("Wie heißt das Programmm?", 10, 10, 580, 30);
        text1 = new TextFeld(10, 170, 120, 30);
        text1.setzeText("Liste Klick");
        text2 = new TextFeld(10, 200, 120, 30);
        text2.setzeText("Liste Doppelklick");
        rollbalken = new Rollbalken(true, 10, 420, 120, 30);
        rollbalken.setzeWerte(1, 5, 3);    //MIN,MAX,Start
        rollbalken.setzeRollbalkenLauscher(this);
        zahlenfeld = new ZahlenFeld(10, 380, 120, 30);
        zahlenfeld.setzeZahl(rollbalken.wert());
        liste = new ListAuswahl(10, 230, 120, 140);
        liste.setzeListAuswahlLauscher(this);
        liste.fuegeAn("Auswahl1");
        liste.fuegeAn("Auswahl2");
        liste.fuegeAn("Auswahl3");
        liste.fuegeAn("Auswahl4");
        knopf2 = new Knopf("macht nix", 10, 460, 120, 30);
        knopf2.setzeKnopfLauscher(this);
        knopf1 = new Knopf("macht nix", 10, 130, 120, 30);
        knopf1.setzeKnopfLauscher(this);
        stift = new Stift();

        tree = new Tree();
//        content = new Content(10, "Joe");
//        tree.insert(content);
//        content = new Content(20, "Mama");
//        tree.insert(content);
//        content = new Content(5, "adsf");
//        tree.insert(content);
//        content = new Content(17, "ur mom");
//        tree.insert(content);
        try {
            tree.fromFile("tree.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tree.search(204).print();

//        tree.printAsc();
//        tree.printDesc();

        for (int i =  0; i < 5; i++) {
            tree.printLevel(i);
        }
//        System.out.format("level of %s: %d\n", content.toString(), tree.getLevel(17));


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson.toJson(tree));
    }

    @Override
    public void bearbeiteKnopfDruck(Knopf k) {
        if (k == ende) {
            this.gibFrei();
        } else if (k == knopf2) {
            //TODO
        } else if (k == knopf1) {
            //TODO
        }
    }

    @Override
    public void bearbeiteRollbalkenBewegung(Rollbalken k) {
        if (k == rollbalken) {
            zahlenfeld.setzeZahl(rollbalken.wert());
        }
    }

    @Override
    public void bearbeiteAuswahl(ListAuswahl k) {
        if (k == liste) {
            text1.setzeText(liste.gewaehlterText());
        }
    }

    @Override
    public void bearbeiteDoppelklick(ListAuswahl k) {
        if (k == liste) {
            text2.setzeText(liste.gewaehlterText());
        }
    }
}
