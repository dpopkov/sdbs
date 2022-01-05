package ru.dpopkov.sdbs.raw;

public interface Record {
    String getName();
    int getAge();
    String getAddress();
    String getCardPlateNumber();
    String getDescription();
    int totalLength();
}
