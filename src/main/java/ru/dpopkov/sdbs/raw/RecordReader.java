package ru.dpopkov.sdbs.raw;

import java.io.IOException;
import java.util.Optional;

public interface RecordReader {

    Optional<Record> read() throws IOException;
}
