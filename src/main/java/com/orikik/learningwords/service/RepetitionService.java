package com.orikik.learningwords.service;

import com.orikik.learningwords.entity.RepetitionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RepetitionService {
    private static final Logger LOG = LoggerFactory.getLogger(RepetitionService.class);
    private static final double MAX_EASINESS = 2.5;
    private static final double MIN_EASINESS = 1.3;
    private static final int MAX_QUALITY = 5;
    private static final int MIN_QUALITY = 0;
    private static final int ZEROING_QUALITY = 3;
    private static final int INTERVAL_FOR_SECOND_REPEAT = 6;
    private static final double ALPHA = 0.1;
    private static final double BETA = 0.02;
    private static final double GAMMA = 0.08;

    public RepetitionEntity superMemo2Algorithm(RepetitionEntity repetitionEntity, Integer quality) {
        if (quality < MIN_QUALITY || quality > MAX_QUALITY) {
            LOG.error("Quality={} is wrong", quality);
            throw new IllegalArgumentException();
        }

        Integer repetitions = repetitionEntity.getRepeatNumber();
        Double easiness = repetitionEntity.geteFactor();
        Integer interval = repetitionEntity.getInterval();


        easiness = easiness + ALPHA - (MAX_QUALITY - quality) * (GAMMA + (MAX_QUALITY - quality) * BETA);
        easiness = Math.max(MIN_EASINESS, easiness);
        easiness = Math.min(MAX_EASINESS, easiness);

        repetitions = quality < ZEROING_QUALITY ? 0 : ++repetitions;

        if (repetitions <= 1) {
            interval = 1;
        } else if (repetitions == 2) {
            interval = INTERVAL_FOR_SECOND_REPEAT;
        } else {
            interval = Math.toIntExact(Math.round(interval * easiness));
        }

        LocalDate nextRepeatDate = LocalDate.now().plusDays(interval);

        repetitionEntity.seteFactor(easiness);
        repetitionEntity.setInterval(interval);
        repetitionEntity.setNextRepeatDate(nextRepeatDate);
        repetitionEntity.setQuality(quality);
        repetitionEntity.setRepeatNumber(repetitions);
        return repetitionEntity;
    }
}
