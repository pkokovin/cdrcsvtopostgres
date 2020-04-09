package ru.kokovin.csvtodb.model;

public class RecordTO {
    private String calldate;
    private String source;
    private String destinadion;
    private String dcontext;
    private String lastdata;
    private Long billsec;

    public RecordTO(String calldate, String source, String destinadion, String dcontext, String lastdata, Long billsec) {
        this.calldate = calldate;
        this.source = source;
        this.destinadion = destinadion;
        this.dcontext = dcontext;
        this.lastdata = lastdata;
        this.billsec = billsec;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestinadion() {
        return destinadion;
    }

    public void setDestinadion(String destinadion) {
        this.destinadion = destinadion;
    }

    public Long getBillsec() {
        return billsec;
    }

    public void setBillsec(Long billsec) {
        this.billsec = billsec;
    }

    public String getCalldate() {
        return calldate;
    }

    public void setCalldate(String calldate) {
        this.calldate = calldate;
    }

    public String getDcontext() {
        return dcontext;
    }

    public void setDcontext(String dcontext) {
        this.dcontext = dcontext;
    }

    public String getLastdata() {
        return lastdata;
    }

    public void setLastdata(String lastdata) {
        this.lastdata = lastdata;
    }
}
