package ru.dpopkov.sdbs.raw;

import java.io.IOException;

public interface RecordWriter {

    boolean write(Record record, boolean deletedFlag) throws IOException;
}
