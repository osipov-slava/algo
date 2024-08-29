package ru.job4j.newcoll.tree;

import java.util.*;

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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            if (current.getValue().equals(parent)) {
                List<T> listValues = current.getChildren().stream().map(Node::getValue).toList();
                if (!listValues.contains(child)) {
                    current.getChildren().add(new Node<>(child));
                    return true;
                }
            }
            current.getChildren().forEach(n -> queue.add(n));
        }
        return false;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            if (current.getValue().equals(key)) {
                return Optional.of(current);
            }
            current.getChildren().forEach(n -> stack.push(n));
        }
        return Optional.empty();
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (root.getValue().equals(key)) {
            return Optional.of(root);
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            for (Node<T> node : current.getChildren()) {
                if (node.getValue().equals(key)) {
                    current.getChildren().remove(node);
                    return Optional.of(node);
                }
            }
            current.getChildren().forEach(n -> queue.add(n));
        }
        return Optional.empty();
    }

}
