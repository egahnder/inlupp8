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
        int currentLevel = this.LEVELCAP;
        Node<E> currentNode = head;
        while(currentLevel >= 0 ){
            if (currentNode.hasNext(currentLevel)){
                boolean elementIsBigger = currentNode.next(currentLevel).getData().compareTo(element) < 0;
                if (!elementIsBigger){
                    if (newNode.height() >= currentLevel){
                        newNode.setNext(currentNode.next(currentLevel), currentLevel);
                        currentNode.setNext(newNode, currentLevel);
                    }
                    currentLevel--;
                }
                else if(elementIsBigger){
                    currentNode = currentNode.next(currentLevel);
                }

            }
            else{
                if (newNode.height() >= currentLevel){
                    newNode.setNext(currentNode.next(currentLevel), currentLevel);
                    currentNode.setNext(newNode, currentLevel);
                }
                currentLevel--;
            }
        }
    }


    private int decideLevel(){
        int level = 0;
        while(rand.nextBoolean() && level < this.LEVELCAP){
            level++;
        }
        return level;
    }

    public E getElement(E element) {
        int currentLevel = this.LEVELCAP;
        Node<E> currentNode = head;
        while (currentLevel >= 0) {
            if (currentNode.hasNext(currentLevel)) {
                int elementDifference = currentNode.next(currentLevel).getData().compareTo(element);
                if (elementDifference > 0) {
                    currentLevel--;
                } else if (elementDifference < 0) {
                    currentNode = currentNode.next(currentLevel);
                }
                else{
                    return currentNode.next(currentLevel).getData();
                }

            } else {
                currentLevel--;
            }
        }
        return null;
    }


    public E remove(E element){
        int currentLevel = this.LEVELCAP;
        Node<E> currentNode = head;
        while (currentLevel >= 0) {
            if (currentNode.hasNext(currentLevel)) {
                int elementDifference = currentNode.next(currentLevel).getData().compareTo(element);
                if (elementDifference > 0) {
                    currentLevel--;
                } else if (elementDifference < 0) {
                    currentNode = currentNode.next(currentLevel);
                }
                else{
                    if (currentLevel == 0){
                        E data = currentNode.next(currentLevel).getData();
                        currentNode.setNext(currentNode.next(currentLevel).next(currentLevel), currentLevel);
                        return data;
                    }
                    else{
                        currentNode.setNext(currentNode.next(currentLevel).next(currentLevel), currentLevel);
                    }
                    currentLevel--;
                }
            } else {
                currentLevel--;
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

    private class Node<E extends Comparable<E>> {
        private E data;
        private final int numOfLevels;
        private ArrayList<Node<E>> connections;

        private Node(E data, int numOfLevels) {
            this.data = data;
            this.numOfLevels = numOfLevels;
            this.connections = new ArrayList<>();
        }

        public E getData() {
            return data;
        }

        public void setNext(Node<E> newNext, int level) {
            connections.set(level, newNext);
        }


        private int height(){
            return numOfLevels;
        }

        private Node<E> next(int level){
            return connections.get(level);
        }

        private boolean hasNext(int level){
            return connections.get(level) != tail;
        }

    }
}

