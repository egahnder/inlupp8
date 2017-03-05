import java.util.*;

public class SkipList<E extends Comparable<E>> {
    private final int LEVELCAP;
    private Node<E> head;
    private Node<E> tail;
    private Random rand = new Random();

    public SkipList() {
        this(31);
    }

    public SkipList(int levelCap){
        this.LEVELCAP = levelCap;
        tail = new Node<E>(null, 0);
        head = initNode(null, tail, levelCap);
    }

    public void add(E element) {
        Node<E> newNode = initNode(element, tail, decideLevel());
        Node<E> currentNode = head;
        for (int currentLevel = LEVELCAP; currentLevel >= 0; currentLevel--){
            currentNode = getClosest(currentNode, currentLevel, element);
            if (newNode.height >= currentLevel){
                addAfter(newNode, currentNode, currentLevel);
            }
        }
    }

    private void addAfter(Node<E> first, Node<E> second, int level){
        first.setNext(second.next(level), level);
        second.setNext(first, level);
    }

    private Node<E> getClosest(Node<E> startNode, int level, E element) {
        Node<E> currentNode = startNode;
        while (currentNode.hasNext(level)) {
            boolean elementIsBigger = currentNode.next(level).data.compareTo(element) <= 0;
            if (!elementIsBigger) {
                currentNode = currentNode.next(level);
            }
            else{
                break;
            }
        }
        return currentNode;
    }

    private int decideLevel(){
        int level = 0;
        while(rand.nextBoolean() && level < this.LEVELCAP){
            level++;
        }
        return level;
    }

    public E getElement(E element) {
        Node<E> currentNode = head;
        for (int currentLevel = LEVELCAP; currentLevel >= 0; currentLevel--){
            currentNode = getClosest(currentNode, currentLevel, element);
            if (currentNode.hasNext(currentLevel)){
                if (currentNode.next(currentLevel).data.compareTo(element) == 0){
                    return currentNode.next(currentLevel).data;
                }
            }
        }
        return null;
    }

    public E remove(E element){
        Node<E> currentNode = head;
        for (int currentLevel = LEVELCAP; currentLevel >= 0; currentLevel--) {
            currentNode = getClosest(currentNode, currentLevel, element);
            if (currentNode.hasNext(currentLevel)){
                if (currentNode.next(currentLevel).data.compareTo(element) == 0){
                    if (currentLevel == 0) {
                        E data = currentNode.next(currentLevel).data;
                        currentNode.setNext(currentNode.next(currentLevel).next(currentLevel), currentLevel);
                        return data;
                    } else {
                        currentNode.setNext(currentNode.next(currentLevel).next(currentLevel), currentLevel);
                    }
                }
            }
        }
        return null;
    }

    private Node<E> initNode(E data, Node<E> node, int level){
        Node<E> newNode = new Node<>(data, level);
        for(int i = 0; i <= level; i++){
            newNode.connections.add(node);
        }
        return newNode;
    }

    private class Node<N extends Comparable<N>> {
        private N data;
        private final int height;
        private ArrayList<Node<N>> connections;

        private Node(N data, int height) {
            this.data = data;
            this.height = height;
            this.connections = new ArrayList<>();
        }

        public void setNext(Node<N> newNext, int level) {
            connections.set(level, newNext);
        }

        private Node<N> next(int level){
            return connections.get(level);
        }

        private boolean hasNext(int level){
            return connections.get(level) != tail;
        }
    }
}

