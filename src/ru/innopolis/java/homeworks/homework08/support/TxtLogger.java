package ru.innopolis.java.homeworks.homework08.support;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TxtLogger extends FileOutput {

    public TxtLogger() {
        super();
    }

    public TxtLogger(final String fileName) {
        super(fileName);
    }

    public void log(String message) {
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFileExtension() {
        return ".txt";
    }

    @Override
    public String toString() {
        return fileName + getFileExtension();
    }
}
