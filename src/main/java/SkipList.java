import java.util.List;
import java.util.Random;

/**
 * Created by loxtank on 2017-03-02.
 */
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
        }

        Node<E> closestNode = findClosestElement(element, currentNode);

        int numberOfLevels = 0;
                /*while (new Random().nextBoolean()) {
                    numberOfLevels++;
                }*/

        if (closestNode.getConnections().size() <= numberOfLevels) {
            newNode.setConnections(closestNode.getConnections().subList(0, closestNode.getConnections().size()));

            if (closestNode.getConnections().size() == 0) {
                closestNode.getConnections().add(newNode);
            } else {
                for (int i = 0; i < closestNode.getConnections().size(); i++) {

                    closestNode.getConnections().set(i, newNode);
                }
            }

            if (closestNode.getConnections().size() > numberOfLevels) {
                newNode.setConnections(closestNode.getConnections().subList(0, numberOfLevels));

                for (int i = 0; i < numberOfLevels; i++) {
                    closestNode.getConnections().set(i, newNode);
                }
            }
        }
    }



    private Node<E> findClosestElement(E element, Node<E> currentNode) {
        if(currentNode.getConnections().size() == 0) {
            return currentNode;
        }

        for (Node<E> node : currentNode.getReversedConnections()) {
                if (node.data.compareTo(element) < 0) {
                    return findClosestElement(element, node);
            }
        }
        return currentNode;
    }

    public E getElement(E element) {
        return getElement(element, head);
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
        System.out.println("Could not find element");
        return null;
    }


}
