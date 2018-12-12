package com.tomekl007.packt.reactive;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorTest {

    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    @Test
    public void givenStream_whenOneSubscriber_thenShouldReceiveAllEvents() {
        //given
        Flux<String> manyWords = Flux.fromIterable(words);
        List<String> result = new LinkedList<>();

        //when
        manyWords.subscribe(result::add);

        //then
        assertThat(result).hasSameElementsAs(words);
    }

    @Test
    public void givenStream_whenGetDistinctValues_shouldReturnNLetters() {
        //given
        List<String> result = new LinkedList<>();
        Flux<String> manyLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        //when
        manyLetters.subscribe(result::add);

        //then
        assertThat(result.size()).isEqualTo(25);
    }
}

