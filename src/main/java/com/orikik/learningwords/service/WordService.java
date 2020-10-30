package com.orikik.learningwords.service;

import com.orikik.learningwords.converter.WordConverter;
import com.orikik.learningwords.dto.WordDto;
import com.orikik.learningwords.entity.RepetitionEntity;
import com.orikik.learningwords.entity.RepetitionKey;
import com.orikik.learningwords.entity.UserEntity;
import com.orikik.learningwords.entity.WordEntity;
import com.orikik.learningwords.repository.RepetitionRepository;
import com.orikik.learningwords.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    private static final Logger LOG = LoggerFactory.getLogger(WordService.class);
    private static final int WORD_COUNT = 10;
    @Autowired
    private RepetitionRepository repetitionRepository;
    @Autowired
    private RepetitionService repetitionService;
    @Autowired
    private UserRepository userRepository;

    public List<WordDto> getWordsForRepeat(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            LOG.error("user={} not found", username);
            throw new IllegalArgumentException();
        }
        UserEntity userEntity = userOptional.get();
        List<WordEntity> wordEntities = getOldWordsForRepeat(userEntity);
        return WordConverter.convert(wordEntities);

    }

    public List<WordEntity> getOldWordsForRepeat(UserEntity userEntity) {
        List<WordEntity> wordEntities = new ArrayList<>();
        Optional<List<RepetitionEntity>> repetitionEntityList = repetitionRepository
                .findByUserEntityId(userEntity.getId());
        if (!repetitionEntityList.isPresent()) {
            return new ArrayList<>();
        }
        List<RepetitionEntity> repetitionEntities = repetitionEntityList.get();
        for (RepetitionEntity repetitionEntity : repetitionEntities) {
            if (repetitionEntity.getNextRepeatDate() == null ||
                    repetitionEntity.getNextRepeatDate().compareTo(LocalDate.now()) <= 0) {
                wordEntities.add(repetitionEntity.getWordEntity());
            }
            if (wordEntities.size() == WORD_COUNT) {
                break;
            }
        }
        return wordEntities;
    }

    public void setNewQuality(WordDto wordDto, String username, Integer quality) {
        WordEntity wordEntity = WordConverter.convert(wordDto);
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (!userEntityOptional.isPresent()) {
            LOG.error("user={} not found", username);
            throw new IllegalArgumentException();
        }
        UserEntity userEntity = userEntityOptional.get();

        RepetitionKey repetitionKey = new RepetitionKey(userEntity.getId(), wordEntity.getId());
        Optional<RepetitionEntity> repetitionEntity = repetitionRepository.findByRepetitionId(repetitionKey);
        if (!repetitionEntity.isPresent()) {
            throw new IllegalStateException();
        }
        repetitionRepository.save(repetitionService.superMemo2Algorithm(repetitionEntity.get(), quality));
    }

}
