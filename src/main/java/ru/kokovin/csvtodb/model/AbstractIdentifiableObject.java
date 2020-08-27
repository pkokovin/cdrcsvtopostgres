package ru.kokovin.csvtodb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Common 'id' part of all entities.
 */
@SuppressWarnings("PMD")
@Getter
@Setter
@MappedSuperclass
@ToString
public class AbstractIdentifiableObject {
    /**
     * Common id field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
