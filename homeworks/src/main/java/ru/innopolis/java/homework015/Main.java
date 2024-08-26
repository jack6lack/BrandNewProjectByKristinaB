package ru.innopolis.java.homework015;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class Main {
    public static void main(String[] args) {
        System.setProperty("jansi.passthrough", "true");
        AnsiConsole.systemInstall();
        AnsiConsole.out().print(Ansi.ansi().reset().fg(Ansi.Color.GREEN));
        System.out.println("Hello world!");
        AnsiConsole.systemUninstall();
    }
}