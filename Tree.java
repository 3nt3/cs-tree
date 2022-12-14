import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    private Node insertAt(Node node, Content content) {
        if (node == null) node = new Node(content);
        else {
            if (content.id < node.getContent().id) node.left = insertAt(node.left, content);
            if (content.id > node.getContent().id) node.right = insertAt(node.right, content);
            if (content.id == node.getContent().id) System.out.println("mach ich nicht");
        }
        return node;
    }

    private void printAscAt(Node node) {
        if (node == null) return;
        printAscAt(node.left);
        System.out.print(node.getContent().id + " ");
        printAscAt(node.right);
    }

    public void insert(Content content) {
        root = insertAt(root, content);
    }

    /**
     * Prints all elements of the tree in ascending order
     */
    public void printAsc() {
        printAscAt(root);
        System.out.println();
    }

    /**
     * @param id the id you want to search for
     * @return Content of the node if it was found
     */
    public Content search(int id) {
        return searchAt(this.root, id);

    }

    /**
     * @param node the parent node from which the function will recurse
     * @param id   the id to search for
     * @return Content if it was found
     */
    private Content searchAt(Node node, int id) {
        if (node == null) return null;
        if (id > node.getContent().id) {
            return searchAt(node.right, id);
        } else if (id < node.getContent().id) {
            return searchAt(node.left, id);
        } else {
            return node.getContent();
        }
    }

    private void printDescAt(Node node) {
        if (node == null) return;
        printDescAt(node.right);
        System.out.print(node.getContent().id + " ");
        printDescAt(node.left);
    }

    /**
     * Prints all elements of the tree in descending order
     */
    public void printDesc() {
        printDescAt(root);
        System.out.println();
    }

    public void printLevel(int level) {
        printLevelFrom(this.root, level - 1);
        System.out.println();
    }

    private Node printLevelFrom(Node node, int level) {
        if (node == null) {
            System.out.print("??? \t");
            return null;
        }
        if (level != 0) {
            printLevelFrom(node.left, level - 1);
            printLevelFrom(node.right, level - 1);
        } else {
            System.out.print(node.getContent().id + " \t");
        }

        return node;
    }

    // TODO: GetLevel(content) [root ist auf 1, R??ckgabe "0", falls nicht vorhanden]
    public int getLevel(int id) {
        return getLevelFrom(this.root, id, 1);
    }

    private int getLevelFrom(Node node, int id, int level) {
        if (node == null) return -1;
        if (node.getContent().id == id) {
            return level;
        }

        if (node.left != null && node.getContent().id > id) {
            return getLevelFrom(node.left, id, level + 1);
        }
        return getLevelFrom(node.right, id, level + 1);
    }

    public void fromFile(String fileName) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(fileName));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Content[] nodes = gson.fromJson(reader, Content[].class);
        for (Content node : nodes) {
            this.insert(node);
        }
    }

    public int depth() {
        return depthFrom(this.root, 1);
    }

    private int depthFrom(Node node, int depth) {
        if (node == null) return depth - 1;
        return Math.max(depthFrom(node.right, depth + 1),
                depthFrom(node.left, depth + 1));
    }
}
