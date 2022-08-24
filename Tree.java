
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
     * @param id the id to search for
     * @return Content if it was found
     */
    private Content searchAt(Node node, int id) {
        if (node == null) return null;
        if (id > node.getContent().id) {
            return searchAt(node.right, id);
        } else if (id < node.getContent().id){
            return searchAt(node.left, id);
        } else {
            return node.getContent();
        }
    }
}
