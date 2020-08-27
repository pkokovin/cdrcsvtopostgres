package ru.kokovin.csvtodb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("PMD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "price")
public class Price extends AbstractIdentifiableObject {

    @Column(name = "zone_number")
    private Integer zoneNumber;

    @Column
    private String description;

    @Column
    private Double price;

    @OneToMany(mappedBy = "price", fetch = FetchType.LAZY)
    private List<Direction> directions;

    @Override
    public String toString() {
        return "Price{" +
                ", zoneNumber=" + zoneNumber +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
