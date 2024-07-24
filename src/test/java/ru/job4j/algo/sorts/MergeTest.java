package ru.job4j.algo.sorts;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenOddElements() {
        int[] array = {10, 4, 6};
        assertThat(Merge.mergesort(array)).containsExactly(4, 6, 10);
    }

    @Test
    void whenSortOneElement() {
        int[] array = {5};
        assertThat(Merge.mergesort(array)).containsExactly(5);
    }
}