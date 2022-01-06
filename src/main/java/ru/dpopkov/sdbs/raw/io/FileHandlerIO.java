package ru.dpopkov.sdbs.raw.io;

import ru.dpopkov.sdbs.raw.FileHandler;
import ru.dpopkov.sdbs.raw.Record;
import ru.dpopkov.sdbs.raw.RecordReader;
import ru.dpopkov.sdbs.raw.RecordWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileHandlerIO implements FileHandler {

    private static final int BOOLEAN_FLAG_LENGTH = 1;
    private static final int INT_LENGTH = Integer.BYTES;

    private final RandomAccessFile dbFile;
    private final RecordWriter recordWriter;
    private final RecordReader recordReader;

    public FileHandlerIO(final String dbFilename) throws FileNotFoundException {
        dbFile = new RandomAccessFile(dbFilename, "rw");
        recordWriter = new RecordWriterIO(dbFile);
        recordReader = new RecordReaderIO(dbFile);
    }

    public FileHandlerIO(RandomAccessFile dbFile, RecordWriter recordWriter, RecordReader recordReader) {
        this.dbFile = dbFile;
        this.recordWriter = recordWriter;
        this.recordReader = recordReader;
    }

    @Override
    public boolean add(Record record) throws IOException {
        dbFile.seek(dbFile.length());
        return recordWriter.write(record, false);
    }

    @Override
    public Record read(int rowNumber) throws IOException {
        int currentRow = 1;
        while (currentRow != rowNumber) {
            long start = dbFile.getFilePointer();
            boolean recordIsDeleted = dbFile.readBoolean();
            int recordLength = dbFile.readInt();
            long offsetToNext = start + BOOLEAN_FLAG_LENGTH + INT_LENGTH + recordLength;
            dbFile.seek(offsetToNext);
            if (!recordIsDeleted) {
                currentRow++;
            }
        }
        return recordReader.read()
                .orElseThrow(() -> new IllegalStateException("Cannot read row: " + rowNumber));
    }

    public void close() throws IOException {
        dbFile.close();
    }
}
