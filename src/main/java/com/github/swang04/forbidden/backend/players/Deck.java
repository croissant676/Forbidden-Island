/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Deck<T extends Card> implements Iterable<T> {

    protected ArrayDeque<T> deque = new ArrayDeque<>();

    public T getTopCard() {
        return deque.peek();
    }

    @SuppressWarnings("unchecked")
    public T[] getAllCards() {
        return (T[]) deque.toArray();
    }

    public void shuffle() {
        List<T> list = new ArrayList<>(deque);
        Collections.shuffle(list);
        deque = new ArrayDeque<>(list);
    }

    public void addCard(T card) {
        deque.add(card);
    }

    @SafeVarargs
    public final void addCards(T... cards) {
        deque.addAll(Arrays.asList(cards));
    }

    public void addCards(Collection<T> cards) {
        deque.addAll(cards);
    }

    public void removeCard(T card) {
        deque.remove(card);
    }

    public void removeCard(int placement) {
        T[] array = getAllCards();
        deque.remove(array[placement]);
    }

    public T popTopCard() {
        return deque.pop();
    }

    public ArrayDeque<T> getDeque() {
        return deque;
    }

    public List<T> listCards() {
        return new ArrayList<>(deque);
    }

    @Override
    public String toString() {
        return deque.toString();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return deque.iterator();
    }
}
