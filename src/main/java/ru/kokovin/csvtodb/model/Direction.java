package ru.kokovin.csvtodb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@SuppressWarnings("PMD")
@Entity
@ToString
@Table(name = "direction")
public class Direction extends AbstractIdentifiableObject{

    @Getter
    @Setter
    @Column(name = "route_pattern")
    private String pattern;

    @Getter
    @Setter
    @Column(name = "route_description")
    private String description;

    @Getter
    @Setter
    @Column(name = "zone_number")
    private Integer zone;

    @Getter
    @Setter
    @OneToMany(mappedBy = "direction", fetch = FetchType.EAGER)
    private Collection<Record> records;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Price price;


//    public Direction() {}
//
//    public Direction(String pattern, String description, Integer zone) {
//        this.pattern = pattern;
//        this.description = description;
//        this.zone = zone;
//    }

//    @Override
//    public String toString() {
//        return "Direction{" +
//                ", pattern='" + pattern + '\'' +
//                ", description='" + description + '\'' +
//                ", zone=" + zone +
//                '}';
//    }
}
