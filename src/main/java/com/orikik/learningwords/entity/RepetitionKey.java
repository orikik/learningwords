package com.orikik.learningwords.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RepetitionKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "word_id")
    Long wordId;

    public RepetitionKey() {
    }

    public RepetitionKey(Long userId, Long wordId) {
        this.userId = userId;
        this.wordId = wordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RepetitionKey that = (RepetitionKey) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(wordId, that.wordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, wordId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}