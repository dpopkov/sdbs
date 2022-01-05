package ru.dpopkov.sdbs.raw.io;

import ru.dpopkov.sdbs.raw.Record;
import ru.dpopkov.sdbs.raw.RecordWriter;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RecordWriterIO implements RecordWriter {
    private static final byte[] EMPTY_BYTES = new byte[0];

    private final RandomAccessFile file;

    public RecordWriterIO(RandomAccessFile randomAccessFile) {
        this.file = randomAccessFile;
    }

    @Override
    public boolean write(Record record, boolean deletedFlag) throws IOException {
        // write isDeleted flag
        file.writeBoolean(deletedFlag);
        // write total record length
        file.writeInt(record.totalLength());
        // write name
        writeStringField(record.getName());
        // write age
        file.writeInt(record.getAge());
        // write address
        writeStringField(record.getAddress());
        // write card plate number
        writeStringField(record.getCardPlateNumber());
        // write description
        writeStringField(record.getDescription());
        return true;
    }

    private void writeStringField(String value) throws IOException {
        byte[] bytes = value != null ? value.getBytes() : EMPTY_BYTES;
        file.writeInt(bytes.length);
        file.write(bytes);
    }
}
