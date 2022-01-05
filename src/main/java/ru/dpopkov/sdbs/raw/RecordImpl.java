package ru.dpopkov.sdbs.raw;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RecordImpl implements Record {
    private String name;
    private int age;
    private String address;
    private String cardPlateNumber;
    private String description;

    @Override
    public int totalLength() {
        final int intLength = Integer.BYTES;
        return intLength                        // length of name
                + stringLength(name)            // name
                + intLength                     // age
                + intLength                     // length of address
                + stringLength(address)         // address
                + intLength                     // length of cardPlateNumber
                + stringLength(cardPlateNumber) // cardPlateNumber
                + intLength                     // length of description
                + stringLength(description);    // description
    }

    private int stringLength(String field) {
        return field != null ? field.length() : 0;
    }
}
