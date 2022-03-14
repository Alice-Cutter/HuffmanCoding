
import java.util.PriorityQueue; 
import java.util.*;

/*
*/
class TreeMaker {
	/* Class to take in a string and encode it in a max heap binary tree. 
	*Each character will be stored as a prefix code. 
	*/
    private HashMap<Character, Integer> map; // stores the characters with their assigned frequencies
    private HashMap<Character, String> codes; //stores characters and their codes
    private PriorityQueue<Node> heap;
  
	private class Node {
  	  private char ch;
  	  private int freq;
  	  private Node right;
  	  private Node left;
        
      private Node(char c, int f, Node l, Node r) {
          ch = c;
          freq = f;
          left = l;
          right = r;
      }
        
      /**
        * Returns a Boolean that tells whether a Node is a leaf in the tree
        * @param Node
        * @return Boolean
        */  
        public boolean isLeaf() {
            return (left == null) && (right == null);
        }
	}

    private class NodeComparator implements Comparator<Node> {
		/* Compares to nodes to each other based on frequency returns the 			difference between x and y 
		@param: Node x one of the nodes being compared 
		@param: Node y the other node being compared */
		public int compare(Node x, Node y) {
            return x.freq - y.freq;
        }
    }

    public TreeMaker(HashMap<Character,Integer> frequencies){
        map = frequencies;
        heap = new PriorityQueue<Node>(map.size(), new NodeComparator());
        codes = new HashMap<Character,String>();
        Node treeRoot = buildTree();
        buildCodes(treeRoot, "");
    }

    /* 
    @param some table that stores frequencies and characers -- hashmap?
    @return the root node of the tree
    */
    public Node buildTree() {

        //make new nodes for each character and add to heap
        for (char character: map.keySet()){
            Node leaf = new Node(character, map.get(character), null, null); //make a new node with null right and left
            heap.add(leaf);
        }

        while (heap.size() > 1) {
            //remove first two
            Node leftChild = heap.poll();
            Node rightChild = heap.poll();
            //connect them
            Node internalNode = new Node('*', leftChild.freq + rightChild.freq, leftChild, rightChild);
            heap.add(internalNode);
        }
        //when only 1 left:
        Node root = heap.poll();
        return root;
        
    }

    public void buildCodes(Node T, String codeSoFar){
        if (T.isLeaf()){
            //set the codes for every leaf node
            codes.put(T.ch, codeSoFar);
        } else {
            //recursively look through left and right subtrees
            buildCodes(T.left, codeSoFar + "0");
            buildCodes(T.right, codeSoFar + "1");            
        }
    }

    public void printCodes() {
        for (char c: codes.keySet()) {
            System.out.println(c + ": " + codes.get(c));
        }
    }

    /* Encodes the given message using the generated huffman codes
    */
    public String encode(String message) {
        String encodedMessage = "";
        for (int i=0; i<message.length(); i++) {
            char curr = message.charAt(i);
            encodedMessage += codes.get(curr);
            
        }
        return encodedMessage;
    }

    /* Decodes the inputed message using the huffman codes */
    public String decode(String codedMessage) {
        String message = "";
        String curr = "";

        while (!codedMessage.isEmpty()) {
            while (!codes.containsValue(curr)) {
                //add one more character to the current code we are trying to decifer
                curr += codedMessage.substring(0,1);
                //remove that character from the coded message
                codedMessage = codedMessage.substring(1);
            }
            char currentChar = '*';
            //find current char
            for (char c: codes.keySet()) {
                if (codes.get(c).equals(curr)) {
                    currentChar = c;
                    break;
                }
            }
            //add current codes char to message
            message += Character.toString(currentChar);
            curr = ""; //reset curr
        }

        return message;
    }
}	