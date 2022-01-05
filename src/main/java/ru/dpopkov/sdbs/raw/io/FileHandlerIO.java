package ru.dpopkov.sdbs.raw.io;

import ru.dpopkov.sdbs.raw.FileHandler;
import ru.dpopkov.sdbs.raw.Record;
import ru.dpopkov.sdbs.raw.RecordWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileHandlerIO implements FileHandler {

    private final RandomAccessFile dbFile;
    private final RecordWriter recordWriter;

    public FileHandlerIO(final String dbFilename) throws FileNotFoundException {
        dbFile = new RandomAccessFile(dbFilename, "rw");
        recordWriter = new RecordWriterIO(dbFile);
    }

    public FileHandlerIO(RandomAccessFile dbFile, RecordWriter recordWriter) {
        this.dbFile = dbFile;
        this.recordWriter = recordWriter;
    }

    public boolean add(Record record) throws IOException {
        dbFile.seek(dbFile.length());
        return recordWriter.write(record, false);
    }

    public void close() throws IOException {
        dbFile.close();
    }
}
