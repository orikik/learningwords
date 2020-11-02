package com.orikik.learningwords.dao;

import com.orikik.learningwords.entity.RepetitionEntity;
import com.orikik.learningwords.entity.WordEntity;
import com.orikik.learningwords.entity.WordEntity_;
import org.jooq.Record;
import org.jooq.SelectLimitPercentStep;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.jooq.impl.DSL.table;

@Repository
public class WordDao extends Dao<WordEntity> {
    @Autowired
    EntityManager entityManager;

    public WordDao() {
        super(WordEntity.class);
    }

    public List<WordEntity> getNewWordsForRepetitonByUserId(Long userId, Integer count) {
        SelectLimitPercentStep<Record> select = getDsl()
                .select()
                .from(WordEntity.TABLE)
                .leftOuterJoin(table(RepetitionEntity.TABLE))
                .on(columnName(WordEntity.TABLE, WordEntity_.ID) + "=" +
                        columnName(RepetitionEntity.TABLE, RepetitionEntity.WORD_ID) +
                        " and " +
                        columnName(RepetitionEntity.TABLE, RepetitionEntity.USER_ID) + "=" + userId)
                .where(getField(RepetitionEntity.TABLE, RepetitionEntity.WORD_ID).isNull())
                .orderBy(DSL.rand())
                .limit(count);
        return fetchInto(select);
    }

}
