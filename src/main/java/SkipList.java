import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class SkipList<E extends Comparable<E>> {
    private Node<E> head;

    public SkipList() {
        head = new Node<>(null);
    }

    public void add(E element) {
        add(element, head);

    }

    private void add(E element, Node<E> currentNode) {
        Node<E> newNode = new Node<>(element);

        if (head.getConnections().size() == 0) {
            head.getConnections().add(newNode);
            return;
        }

        Node<E> closestNode = findClosestElement(element, currentNode);

        int closestNodeConnections = closestNode.getConnections().size();
        int numberOfLevels = 1;

        while (new Random().nextBoolean()) {
            numberOfLevels++;
        }

        // Här ska "&& numberOfNodes == 1" in i if satsen
        if (closestNodeConnections == 0 ) {
            closestNode.getConnections().add(newNode);
            return;
            
        } else {

        setNewNodeConnections(closestNode, newNode, closestNodeConnections, numberOfLevels);

        } if (closestNodeConnections <= numberOfLevels) {
            for (int i = 0; i < closestNodeConnections; i++) {
                closestNode.getConnections().set(i, newNode);
            }
        } else if (closestNodeConnections > numberOfLevels) {

            for (int i = 0; i < numberOfLevels - 1; i++) {
                closestNode.getConnections().set(i, newNode);
            }
        }
    }


    private void setNewNodeConnections(Node<E> closestNode, Node<E> newNode, int closestNodeConnections, int numberOfLevels) {
        if (closestNodeConnections <= numberOfLevels) {
            newNode.setConnections(closestNode.getConnections().subList(0, closestNodeConnections));

            // Här behöver man kalla en motod för att sätta de övre levelserna i den nya noden

        } else if (closestNodeConnections > numberOfLevels) {
            newNode.setConnections(closestNode.getConnections().subList(0, numberOfLevels - 1));
        }
    }





    private Node<E> findClosestElement(E element, Node<E> currentNode) {
        if(currentNode.getConnections().size() == 0) {
            return currentNode;
        }

        for (Node<E> node : currentNode.getReversedConnections()) {
            if (node.data.compareTo(element) < 0) {
                currentNode =  findClosestElement(element, node);
            }
        }
        return currentNode;
    }

    public E getElement(E element) {
        E target = getElement(element, head);
        if (target == null) {
            throw new NoSuchElementException("Could not find " +element);
        } else {
            return target;
        }

    }

    private E getElement(E element, Node<E> currentNode) {
        List<Node<E>> connections = currentNode.getReversedConnections();
        for (Node<E> node : connections) {
            if (node != null) {
                if (node.data.compareTo(element) < 0) {
                    return getElement(element, node);
                }

                if (node.data.compareTo(element) == 0) {
                    return node.data;

                }
            }
        }
        return null;
    }


}
