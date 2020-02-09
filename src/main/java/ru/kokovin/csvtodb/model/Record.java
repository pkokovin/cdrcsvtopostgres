package ru.kokovin.csvtodb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cdr")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "calldate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calldate;

    @Column(name = "clid")
    private String clid;

    @Column(name = "src")
    private String src;

    @Column(name = "dst")
    private String dst;

    @Column(name = "dcontext")
    private String dcontext;

    @Column(name = "channel")
    private String channel;

    @Column(name = "dstchannel")
    private String dstchannel;

    @Column(name = "lastapp")
    private String lastapp;

    @Column(name = "lastdata")
    private String lastdata;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "billsec")
    private Long billsec;

    @Column(name = "disposition")
    private String disposition;

    @Column(name = "amaflags")
    private Integer amaflags;

    @Column(name = "accountcode")
    private String accountcode;

    @Column(name = "uniqueid")
    private String uniqueid;

    @Column(name = "userfield")
    private String userfield;

    public Record() {}

    public Record(Date calldate, String clid, String src, String dst
            , String dcontext, String channel, String dstchannel
            , String lastapp, String lastdata, Long duration, Long billsec
            , String disposition, Integer amaflags, String accountcode
            , String uniqueid, String userfield) {
        this.calldate = calldate;
        this.clid = clid;
        this.src = src;
        this.dst = dst;
        this.dcontext = dcontext;
        this.channel = channel;
        this.dstchannel = dstchannel;
        this.lastapp = lastapp;
        this.lastdata = lastdata;
        this.duration = duration;
        this.billsec = billsec;
        this.disposition = disposition;
        this.amaflags = amaflags;
        this.accountcode = accountcode;
        this.uniqueid = uniqueid;
        this.userfield = userfield;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCalldate() {
        return calldate;
    }

    public void setCalldate(Date calldate) {
        this.calldate = calldate;
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getDcontext() {
        return dcontext;
    }

    public void setDcontext(String dcontext) {
        this.dcontext = dcontext;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDstchannel() {
        return dstchannel;
    }

    public void setDstchannel(String dstchannel) {
        this.dstchannel = dstchannel;
    }

    public String getLastapp() {
        return lastapp;
    }

    public void setLastapp(String lastapp) {
        this.lastapp = lastapp;
    }

    public String getLastdata() {
        return lastdata;
    }

    public void setLastdata(String lastdata) {
        this.lastdata = lastdata;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getBillsec() {
        return billsec;
    }

    public void setBillsec(Long billsec) {
        this.billsec = billsec;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public Integer getAmaflags() {
        return amaflags;
    }

    public void setAmaflags(Integer amaflags) {
        this.amaflags = amaflags;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getUserfield() {
        return userfield;
    }

    public void setUserfield(String userfield) {
        this.userfield = userfield;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", calldate=" + calldate +
                ", clid='" + clid + '\'' +
                ", src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                ", dcontext='" + dcontext + '\'' +
                ", channel='" + channel + '\'' +
                ", dstchannel='" + dstchannel + '\'' +
                ", lastapp='" + lastapp + '\'' +
                ", lastdata='" + lastdata + '\'' +
                ", duration=" + duration +
                ", billsec=" + billsec +
                ", disposition='" + disposition + '\'' +
                ", amaflags=" + amaflags +
                ", accountcode='" + accountcode + '\'' +
                ", uniqueid='" + uniqueid + '\'' +
                ", userfield='" + userfield + '\'' +
                '}';
    }
}
