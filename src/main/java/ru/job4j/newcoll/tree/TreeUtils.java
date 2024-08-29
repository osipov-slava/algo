package ru.job4j.newcoll.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Queue<Node<T>> queue = new LinkedList<>();
        int count = 0;

        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            count++;
            current.getChildren().forEach(n -> queue.add(n));
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     *
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Queue<Node<T>> queue = new LinkedList<>();
        List<T> list = new ArrayList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            list.add(current.getValue());
            current.getChildren().forEach(n -> queue.add(n));
        }
        return list;
    }

}
