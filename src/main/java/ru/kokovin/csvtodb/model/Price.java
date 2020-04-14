package ru.kokovin.csvtodb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("PMD")
//@ToString
@Entity
@Table(name = "price")
public class Price extends AbstractIdentifiableObject {

    @Getter
    @Setter
    @Column(name = "zone_number")
    private Integer zoneNumber;

    @Getter
    @Setter
    @Column
    private String description;

    @Getter
    @Setter
    @Column
    private Double price;

    @Getter
    @Setter
    @OneToMany(mappedBy = "price", fetch = FetchType.LAZY)
    private List<Direction> directions;

//    public Price() {}
//
//    public Price(Integer zoneNumber, String description, Double price) {
//        this.zoneNumber = zoneNumber;
//        this.description = description;
//        this.price = price;
//    }

    @Override
    public String toString() {
        return "Price{" +
                ", zoneNumber=" + zoneNumber +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
