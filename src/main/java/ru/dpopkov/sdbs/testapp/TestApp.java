package ru.dpopkov.sdbs.testapp;

import ru.dpopkov.sdbs.raw.FileHandler;
import ru.dpopkov.sdbs.raw.Record;
import ru.dpopkov.sdbs.raw.io.FileHandlerIO;

import java.io.IOException;

public class TestApp {
    public static void main(String[] args) {
        try {
            FileHandler handler = new FileHandlerIO("test.db");
            Record record = handler.read(2);
            System.out.println("record = " + record);
            /*Record record = RecordImpl.builder()
                    .name("Jane2")
                    .age(322)
                    .address("address2")
                    .cardPlateNumber("12345672")
                    .description("description2")
                    .build();
            handler.add(record);*/
            handler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
