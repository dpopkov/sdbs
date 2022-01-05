package ru.dpopkov.sdbs.raw;

import java.io.IOException;

public interface FileHandler {

    boolean add(Record record) throws IOException;

    void close() throws IOException;
}
