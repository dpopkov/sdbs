package ru.dpopkov.sdbs.raw;

import java.io.IOException;

public interface FileHandler {

    boolean add(Record record) throws IOException;

    Record read(int rowNumber) throws IOException;

    void close() throws IOException;
}
