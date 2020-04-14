package ru.kokovin.csvtodb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;


@SuppressWarnings({"PMD", "JpaAttributeTypeInspection"})
@ToString
@Entity
@Table(name = "cdr", uniqueConstraints =
@UniqueConstraint(columnNames = {"calldate", "clid", "src", "dst", "billsec", "uniqueid"}, name = "cdr_unique_const"))
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "GET_RECORDS_BY_CLID",
                query = " select * from cdr c where c.clid like CONCAT('%', :clid, '%') " +
                        " and c.disposition = 'ANSWERED' and c.dst like '8%' " +
                        " and c.dst not like '8812%' " +
                        " and c.dst not like '8800%' " +
                        " and c.calldate between :d1 and :d2",
                resultClass = Record.class)
})
public class Record extends AbstractIdentifiableObject{

    @Getter
    @Setter
    @Column(name = "calldate")
    private LocalDateTime calldate;

    @Getter
    @Setter
    @Column(name = "clid")
    private String clid;

    @Getter
    @Setter
    @Column(name = "src")
    private String src;

    @Getter
    @Setter
    @Column(name = "dst")
    private String dst;

    @Getter
    @Setter
    @Column(name = "dcontext")
    private String dcontext;

    @Getter
    @Setter
    @Column(name = "channel")
    private String channel;

    @Getter
    @Setter
    @Column(name = "dstchannel")
    private String dstchannel;

    @Getter
    @Setter
    @Column(name = "lastapp")
    private String lastapp;

    @Getter
    @Setter
    @Column(name = "lastdata")
    private String lastdata;

    @Getter
    @Setter
    @Column(name = "duration")
    private Long duration;

    @Getter
    @Setter
    @Column(name = "billsec")
    private Long billsec;

    @Getter
    @Setter
    @Column(name = "disposition")
    private String disposition;

    @Getter
    @Setter
    @Column(name = "amaflags")
    private Integer amaflags;

    @Getter
    @Setter
    @Column(name = "accountcode")
    private String accountcode;

    @Getter
    @Setter
    @Column(name = "uniqueid")
    private String uniqueid;

    @Getter
    @Setter
    @Column(name = "userfield")
    private String userfield;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "direction_id", nullable = true)
    private Direction direction;

//    public Record() {
//    }
//
//    public Record(LocalDateTime calldate, String clid, String src, String dst
//            , String dcontext, String channel, String dstchannel
//            , String lastapp, String lastdata, Long duration, Long billsec
//            , String disposition, Integer amaflags, String accountcode
//            , String uniqueid, String userfield) {
//        this.calldate = calldate;
//        this.clid = clid;
//        this.src = src;
//        this.dst = dst;
//        this.dcontext = dcontext;
//        this.channel = channel;
//        this.dstchannel = dstchannel;
//        this.lastapp = lastapp;
//        this.lastdata = lastdata;
//        this.duration = duration;
//        this.billsec = billsec;
//        this.disposition = disposition;
//        this.amaflags = amaflags;
//        this.accountcode = accountcode;
//        this.uniqueid = uniqueid;
//        this.userfield = userfield;
//    }

//    @Override
//    public String toString() {
//        return "Record{" +
//                ", calldate=" + calldate +
//                ", clid='" + clid + '\'' +
//                ", src='" + src + '\'' +
//                ", dst='" + dst + '\'' +
//                ", dcontext='" + dcontext + '\'' +
//                ", channel='" + channel + '\'' +
//                ", dstchannel='" + dstchannel + '\'' +
//                ", lastapp='" + lastapp + '\'' +
//                ", lastdata='" + lastdata + '\'' +
//                ", duration=" + duration +
//                ", billsec=" + billsec +
//                ", disposition='" + disposition + '\'' +
//                ", amaflags=" + amaflags +
//                ", accountcode='" + accountcode + '\'' +
//                ", uniqueid='" + uniqueid + '\'' +
//                ", userfield='" + userfield + '\'' +
//                '}';
//    }
}
