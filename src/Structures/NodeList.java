package Structures;

import Others.Folder;

/**
 *
 * @author JeffGeo
 */
public class NodeList {

    public Folder Folders;
    public AVLTree Files;
    public NodeList Next;
    public NodeList Parent;
    public LinkedList SubFolders;

    public NodeList(String Foldername, String path, NodeList n) {
        this.Folders = new Folder(Foldername, path);
        this.SubFolders = new LinkedList();
        this.Files = new AVLTree();
        this.Next = null;
        this.Parent = n;
    }
}
