package ru.dpopkov.sdbs.raw.io;

import ru.dpopkov.sdbs.raw.Record;
import ru.dpopkov.sdbs.raw.RecordImpl;
import ru.dpopkov.sdbs.raw.RecordReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;

public class RecordReaderIO implements RecordReader {

    private final RandomAccessFile file;

    public RecordReaderIO(RandomAccessFile file) {
        this.file = file;
    }

    @Override
    public Optional<Record> read() throws IOException {
        final boolean isDeleted = file.readBoolean();
        if (isDeleted) {
            return Optional.empty();
        }
        file.readInt(); // eat length of record
        Record record = RecordImpl.builder()
                .name(readStringField())
                .age(file.readInt())
                .address(readStringField())
                .cardPlateNumber(readStringField())
                .description(readStringField())
                .build();
        return Optional.of(record);
    }

    private String readStringField() throws IOException {
        int length = file.readInt();
        byte[] bytes = new byte[length];
        file.read(bytes);
        return new String(bytes);
    }
}
