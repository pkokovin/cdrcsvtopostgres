package ru.kokovin.csvtodb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecordTO {

    private String calldate;

    private String source;

    private String destination;

    private String dcontext;

    private String lastdata;

    private Long billsec;

}
