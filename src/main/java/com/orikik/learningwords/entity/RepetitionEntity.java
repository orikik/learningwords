package com.orikik.learningwords.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = RepetitionEntity.TABLE)
public class RepetitionEntity {
    public static final double MAX_EASINESS = 2.5;
    public static final int REPEAT_NUMBER = 0;
    public static final int INTERVAL = 1;
    public static final boolean IS_LEARNED_DEFAULT = false;
    public static final String TABLE = "repetition";
    public static final String USER_ID = "user_id";
    public static final String WORD_ID = "word_id";

    @EmbeddedId
    RepetitionKey repetitionId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = USER_ID)
    UserEntity userEntity;

    @ManyToOne
    @MapsId("wordId")
    @JoinColumn(name = WORD_ID)
    WordEntity wordEntity;

    @Column(name = "e_factor")
    Double eFactor;

    Integer quality;

    @Column(name = "repeat_number")
    Integer repeatNumber;

    Integer interval;

    @Column(name = "next_repeat_day")
    LocalDate nextRepeatDate;

    @Column(name = "word_is_learned")
    Boolean wordIsLearned;

    @Column(name = "date_of_studying_the_word")
    LocalDate dateOfStudyingTheWord;

    public RepetitionEntity(UserEntity userEntity, WordEntity wordEntity) {
        this.userEntity = userEntity;
        this.wordEntity = wordEntity;
        this.repetitionId = new RepetitionKey(userEntity.getId(), wordEntity.getId());
        this.eFactor = MAX_EASINESS;
        this.repeatNumber = REPEAT_NUMBER;
        this.interval = INTERVAL;
        this.wordIsLearned = IS_LEARNED_DEFAULT;
    }

    public RepetitionEntity() {
    }

    public RepetitionKey getRepetitionId() {
        return repetitionId;
    }

    public void setRepetitionId(RepetitionKey id) {
        this.repetitionId = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public WordEntity getWordEntity() {
        return wordEntity;
    }

    public void setWordEntity(WordEntity wordEntity) {
        this.wordEntity = wordEntity;
    }

    public Double geteFactor() {
        return eFactor;
    }

    public void seteFactor(Double eFactor) {
        this.eFactor = eFactor;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getRepeatNumber() {
        return repeatNumber;
    }

    public void setRepeatNumber(Integer repeatNumber) {
        this.repeatNumber = repeatNumber;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public LocalDate getNextRepeatDate() {
        return nextRepeatDate;
    }

    public void setNextRepeatDate(LocalDate nextRepeatDate) {
        this.nextRepeatDate = nextRepeatDate;
    }

    public Boolean getWordIsLearned() {
        return wordIsLearned;
    }

    public void setWordIsLearned(Boolean wordIsLearned) {
        this.wordIsLearned = wordIsLearned;
    }

    public LocalDate getDateOfStudyingTheWord() {
        return dateOfStudyingTheWord;
    }

    public void setDateOfStudyingTheWord(LocalDate dateOfStudyingTheWord) {
        this.dateOfStudyingTheWord = dateOfStudyingTheWord;
    }
}
