import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by loxtank on 2017-03-02.
 */
public class Node<E extends Comparable<E>> {
    E data;
    List<Node<E>> connections;

    public Node(E data) {
        this.data = data;
        this.connections = new ArrayList<>();
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public List<Node<E>> getReversedConnections() {
        ArrayList<Node<E>> reversed = new ArrayList<>(connections);
        Collections.reverse(reversed);
        return reversed;
    }

    public List<Node<E>> getConnections() {
        return connections;
    }

    public void setConnections(List<Node<E>> connections) {
        //OBS! riktigt kinkigt. Eftersom en array är pass by reference så går det inte att sätta den till inkommande
        //variabel. Måste göra en kopia med new!!!
        this.connections = new ArrayList<>(connections);
    }
}
