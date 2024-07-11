package ru.innopolis.java.homeworks.homework08.support;

public abstract class FileOutput {
    private final String defaultFileName = "output_data";
    protected String fileName;

    protected FileOutput() {
        this.fileName = getDefaultFileName() + getFileExtension();
    }

    protected FileOutput(final String fileName) {
        this.fileName = fileName + getFileExtension();
    }

    protected String getDefaultFileName() {
        return this.defaultFileName;
    }

    protected abstract String getFileExtension();
}
