package com.orikik.learningwords.converter;

import com.orikik.learningwords.dto.WordDto;
import com.orikik.learningwords.entity.WordEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordConverter {
    public static WordDto convert(WordEntity wordEntity) {
        if (wordEntity == null) {
            return null;
        }
        WordDto wordDto = new WordDto();
        wordDto.setId(wordEntity.getId());
        wordDto.setWord(wordEntity.getWord());
        wordDto.setDescription(wordEntity.getDescription());
        wordDto.setWordType(wordEntity.getWordType());
        return wordDto;
    }

    public static WordEntity convert(WordDto wordDto) {
        if (wordDto == null) {
            return null;
        }
        WordEntity wordEntity = new WordEntity();
        wordEntity.setId(wordDto.getId());
        wordEntity.setWord(wordDto.getWord());
        wordEntity.setDescription(wordDto.getDescription());
        wordEntity.setWordType(wordDto.getWordType());
        return wordEntity;
    }

    public static List<WordDto> convert(List<WordEntity> wordEntities) {
        if (CollectionUtils.isEmpty(wordEntities)) {
            return Collections.emptyList();
        }
        return wordEntities.stream().map(WordConverter::convert).collect(Collectors.toList());
    }
}
