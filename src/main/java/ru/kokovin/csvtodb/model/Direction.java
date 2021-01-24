package ru.kokovin.csvtodb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("PMD")
@Entity
@Table(name = "direction")
@Getter
@Setter
@ToString(of = {"pattern", "description", "zone"})
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "GET_DIRECTIONS_BY_DEST",
                query = " select * from direction d where d.route_pattern =:route_pattern ",
                resultClass = Direction.class)
})
public class Direction extends AbstractIdentifiableObject{

    @Column(name = "route_pattern")
    private String pattern;

    @Column(name = "route_description")
    private String description;

    @Column(name = "zone_number")
    private Integer zone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "direction")
    private List<Record> records;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Price price;

//    @Override
//    public String toString() {
//        return "Direction{" +
//                ", pattern='" + pattern + '\'' +
//                ", description='" + description + '\'' +
//                ", zone=" + zone +
//                '}';
//    }
}
