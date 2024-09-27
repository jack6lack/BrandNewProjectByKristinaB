package ru.innopolis.java.attestation.attestation01.support;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TxtDataWriter {
    private final String defaultFileName = "output_data";
    private final String fileName;

    public TxtDataWriter() {
        this.fileName = defaultFileName + ".txt";
    }

    public TxtDataWriter(final String fileName) {
        this.fileName = fileName;
    }

    public void log(String message) {
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
