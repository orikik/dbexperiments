package com.orikik.dbexperiments.entity;

import javax.persistence.*;

@Entity
@Table(name = WordEntity.TABLE)
public class WordEntity {
    public static final String TABLE = "words";

    @Id
    @SequenceGenerator(name = "words_pk_sequence",
            sequenceName = "words_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "words_pk_sequence")
    private Long id;
    private String word;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
