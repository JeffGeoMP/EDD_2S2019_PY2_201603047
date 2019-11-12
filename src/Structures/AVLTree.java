package Structures;

/**
 *
 * @author JeffGeo
 */
public class AVLTree {

    public NodeAVL Root;
    public int count;

    public AVLTree() {
        this.Root = null;
        this.count = 0;
    }

    //Methods for AVL Tree -----------------------------------------------------
    private int Height(NodeAVL n) {
        if (n == null) {
            return -1;
        } else {
            return n.Height;
        }
    }

    private int Balance(NodeAVL n) {
        if (n == null) {
            return 0;
        } else {
            return this.Height(n.Right)-this.Height(n.Left);
        }
    }
    //--------------------------------------------------------------------------

    //Using Interface ----------------------------------------------------------
    public void add(int n) {
        NodeAVL new_node = new NodeAVL(n);
        if (this.Root == null) {
            Root = new_node;
        } else {
            Root = addAVL(new_node, Root);
        }
    }
    
    public void Remove(int key){
        Root = removeAVL(Root,key);
        System.out.println("Nodo Eliminado");
    }
    
    public void Search(){
        
    }
    
    public void Edit(){
        
    }
    //--------------------------------------------------------------------------
    
    //Operations in the AVL Tree -----------------------------------------------
    private NodeAVL addAVL(NodeAVL new_node, NodeAVL subtree) {
        NodeAVL sup = subtree;

        if (new_node.Key < subtree.Key) {
            if (subtree.Left == null) {
                subtree.Left = new_node;
            } else {
                subtree.Left = addAVL(new_node, subtree.Left);
                if ((this.Height(subtree.Left) - this.Height(subtree.Right)) == 2) {
                    if (new_node.Key < subtree.Left.Key) {
                        sup = RotationLeft(subtree);
                    } else {
                        sup = DRotationLeft(subtree);
                    }
                }
            }
        } else if (new_node.Key > subtree.Key) {
            if (subtree.Right == null) {
                subtree.Right = new_node;
            } else {
                subtree.Right = addAVL(new_node, subtree.Right);
                if ((this.Height(subtree.Right) - this.Height(subtree.Left)) == 2) {
                    if (new_node.Key > subtree.Right.Key) {
                        sup = RotationRight(subtree);
                    } else {
                        sup = DRotationRight(subtree);
                    }
                }
            }
        } else {
            System.out.println("Node is Duplicated");
        }
        if (subtree.Left == null && (subtree.Right != null)) {
            subtree.Height = subtree.Right.Height + 1;
        } else if (subtree.Right == null && (subtree.Left != null)) {
            subtree.Height = subtree.Left.Height + 1;
        } else {
            subtree.Height = Math.max(this.Height(subtree.Left), this.Height(subtree.Right)) + 1;
        }
        return sup;
    }

    private NodeAVL searchAVL(int d, NodeAVL root) {
        if (root == null) {
            return null;
        } else if (root.Key == d) {
            return root;
        } else if (root.Key < d) {
            return searchAVL(d, root.Right);
        } else {
            return searchAVL(d, root.Left);
        }
    }

    private NodeAVL removeAVL(NodeAVL node, int elem) {
        if (node == null) {
            return null;
        }
        if (elem < node.Key) {
            node.Left = removeAVL(node.Left, elem);
        } else if (elem > node.Key) {
            node.Right = removeAVL(node.Right, elem);
        } else {

            if (node.Left == null) {
                return node.Right;

            } else if (node.Right == null) {
                return node.Left;
            } else {
                if (node.Left.Height > node.Right.Height) {
                    int successorValue = findMax(node.Left);
                    node.Key = successorValue;
                    node.Left = removeAVL(node.Left, successorValue);
                } else {
                    int successorValue = findMin(node.Right);
                    node.Key = successorValue;
                    node.Right = removeAVL(node.Right, successorValue);
                }
            }
        }

        // Update balance factor and height values.
        int LeftNodeHeight = 0;
        int RightNodeHeight = 0;
        if (node.Left == null) {
            LeftNodeHeight = -1;
        } else {
            LeftNodeHeight = node.Left.Height;
        }

        if (node.Right == null) {
            RightNodeHeight = -1;
        } else {
            RightNodeHeight = node.Right.Height;
        }
        node.Height = Math.max(LeftNodeHeight, RightNodeHeight) + 1;

        // Re-balance tree.
        if (Balance(node) == -2) {
            if (Balance(node.Left) <= 0) {
                return RotationLeft(node);
            } else {
                return DRotationLeft(node);
            }
        } else if (Balance(node) == +2) {
            if (Balance(node.Right) >= 0) {
                return RotationRight(node);
            } else {
                return DRotationRight(node);
            }
        }
        return node;
    }
    //--------------------------------------------------------------------------
    
    //Others Methods------------------------------------------------------------
    private int findMin(NodeAVL node) {
        while (node.Left != null) {     //Helper Method to find
            node = node.Left;           //the LeftMost Node
        }       
        return node.Key;                //Which has the smallest 
    }

    private int findMax(NodeAVL node) {
        while (node.Right != null) {    //Helper Method to find
            node = node.Right;          //thr RightMost node
        }
        return node.Key;                //Which has te largest value
    }
    //--------------------------------------------------------------------------
    
    //Orders for print the AVL Tree ---------------------------------------------
    public void InOrden(NodeAVL n) {
        if (n != null) {
            InOrden(n.Left);
            System.out.println(n.Key + "," +n.Height+",BF: "+Balance(n));
            InOrden(n.Right);
        }
    }

    public void PreOrden(NodeAVL n) {
        if (n != null) {
            System.out.print(n.Key + ",");
            PreOrden(n.Left);
            PreOrden(n.Right);
        }
    }

    public void PostOrden(NodeAVL n) {
        if (n != null) {
            PostOrden(n.Left);
            PostOrden(n.Right);
            System.out.print(n.Key + ",");
        }
    }
    //--------------------------------------------------------------------------

    //Rotations for AVL Tree----------------------------------------------------
    //Simple left Rotation
    private NodeAVL RotationLeft(NodeAVL n) { //Right Rotation
        NodeAVL aux = n.Left;  //equals aux to subtree of n
            n.Left = aux.Right;     //The subtree of n in the branch left
        //will equals to the branch left of aux
        aux.Right = n;
        n.Height = Math.max(this.Height(n.Left), this.Height(n.Right)) + 1;
        aux.Height = Math.max(this.Height(aux.Left), this.Height(aux.Right)) + 1;

        return aux;
    }

    //Simple Right Rotation
    private NodeAVL RotationRight(NodeAVL n) { //Left Rotation
        NodeAVL aux = n.Right;  //equals aux to subtree of n
        n.Right = aux.Left;     //The subtree of n in the branch right
        //will equals to the branch left of aux
        aux.Left = n;
        n.Height = Math.max(this.Height(n.Left), this.Height(n.Right)) + 1;
        aux.Height = Math.max(this.Height(aux.Left), this.Height(aux.Right)) + 1;

        return aux;
    }

    //Double Left Rotation
    private NodeAVL DRotationLeft(NodeAVL n) {
        NodeAVL aux;
        n.Left = this.RotationRight(n.Left);
        aux = RotationLeft(n);
        return aux;
    }

    //Double Right Rotation
    private NodeAVL DRotationRight(NodeAVL n) {
        NodeAVL aux;
        n.Right = this.RotationLeft(n.Right);
        aux = RotationRight(n);
        return aux;
    }
    //--------------------------------------------------------------------------
}
class NodeAVL {
    public int Key; //Change for class node tree;
    public int Height;
    public NodeAVL Left;
    public NodeAVL Right;

    public NodeAVL(int Key) {
        this.Key = Key;
        this.Height = 0;
        this.Left = null;
        this.Right = null;
    }
}
