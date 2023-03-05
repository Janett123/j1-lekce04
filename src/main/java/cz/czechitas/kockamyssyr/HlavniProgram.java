package cz.czechitas.kockamyssyr;

import cz.czechitas.kockamyssyr.api.*;

import java.awt.*;
import java.util.Random;

/**
 * Hlaví třída pro hru Kočka–myš–sýr.
 */
public class HlavniProgram {
    private final Random random = new Random();

    private final int VELIKOST_PRVKU = 50;
    private final int SIRKA_OKNA = 1000 - VELIKOST_PRVKU;
    private final int VYSKA_OKNA = 600 - VELIKOST_PRVKU;

    private Cat tom;
    private Mouse jerry;


    /**
     * Spouštěcí metoda celé aplikace.
     *
     * @param args
     */
    public static void main(String[] args) {
        new HlavniProgram().run();
    }

    /**
     * Hlavní metoda obsahující výkonný kód.
     */
    public void run() {
        tom = vytvorKocku();
        // tom.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        jerry = vytvorMys();
        jerry.setBrain(new KeyboardBrain());

        vytvorVeci(4);
        chytMys();
    }

    public void chytMys() {
        // TODO: Sem vepište svůj program

        while (jerry.isAlive()) {
            jdiNaSouradnice(jerry.getX(), jerry.getY());
        }


    }

    public void jdiNaSouradnice(int x,int y) {
        int xRozdil = tom.getX() - jerry.getX();
        int yRozdil = tom.getY() - jerry.getY();

        if (xRozdil < 0) {
            otocSeDoprava();
            tom.moveForward(Math.abs(xRozdil));
        } else {
            otocSeDoleva();
            tom.moveForward(xRozdil);
        }

    }

    public void otocSeDoprava() {
        if(tom.getOrientation()==PlayerOrientation.UP) {
        tom.turnRight();
        } else if(tom.getOrientation()==PlayerOrientation.LEFT) {
        tom.turnRight();
        tom.turnRight();
        } else if(tom.getOrientation()==PlayerOrientation.DOWN) {
        tom.turnLeft();
        }

    }

    public void otocSeDoleva() {
        if (tom.getOrientation() == PlayerOrientation.LEFT) {
            return;
        }

        if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnLeft();
            return;
        }

        if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnRight();
            return;
        }

        tom.turnLeft();
        tom.turnLeft();
    }



    public void vytvorVeci(int pocetStromu) {
        for (int i = 0; i < pocetStromu; i++) {
            vytvorStrom();
        }
        vytvorSyr();
        vytvorJitrnici();
    }
    public Tree vytvorStrom() {
        return new Tree(vytvorNahodnyBod());
    }

    public Cat vytvorKocku() {
        return new Cat(vytvorNahodnyBod());
    }

    public Mouse vytvorMys() {
        return new Mouse(vytvorNahodnyBod());
    }

    public Cheese vytvorSyr() {
        return new Cheese(vytvorNahodnyBod());
    }

    public Meat vytvorJitrnici() {
        return new Meat(vytvorNahodnyBod());
    }

    private Point vytvorNahodnyBod() {
        return new Point(random.nextInt(SIRKA_OKNA), random.nextInt(VYSKA_OKNA));
    }

}
