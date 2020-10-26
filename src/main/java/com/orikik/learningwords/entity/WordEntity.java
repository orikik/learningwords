package com.orikik.learningwords.entity;

import javax.persistence.*;
import java.util.List;

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
    @ManyToMany(mappedBy = "wordList")
    private List<UserEntity> userList;
    @Column(name = "word")
    private String word;
    @Column(name = "description")
    private String description;
    @Column(name = "word_type")
    private String wordType;

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

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
