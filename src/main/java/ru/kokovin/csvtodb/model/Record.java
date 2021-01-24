package ru.kokovin.csvtodb.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;


@SuppressWarnings({"PMD", "JpaAttributeTypeInspection"})
@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "calldate")
    private LocalDateTime calldate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "direction_id")
    private Direction direction;

}
