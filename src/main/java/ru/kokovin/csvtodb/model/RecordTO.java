package ru.kokovin.csvtodb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class RecordTO {
    @Getter
    @Setter
    private String calldate;
    @Getter
    @Setter
    private String source;
    @Getter
    @Setter
    private String destination;
    @Getter
    @Setter
    private String dcontext;
    @Getter
    @Setter
    private String lastdata;
    @Getter
    @Setter
    private Long billsec;

    public RecordTO(String calldate, String source, String destination, String dcontext, String lastdata, Long billsec) {
        this.calldate = calldate;
        this.source = source;
        this.destination = destination;
        this.dcontext = dcontext;
        this.lastdata = lastdata;
        this.billsec = billsec;
    }

}
