package Control;

import Model.BinaryTree;
import View.DrawingPanel;
import View.TreeView.TreeNode;
import View.TreeView.TreePath;

/**
 * Created by Jean-Pierre on 12.01.2017.
 */
public class MainController {

    private BinaryTree<String> binaryTree;

    public MainController(){
        binaryTree = new BinaryTree<>(""); // Ein Baum ohne Wurzel-Inhalt ist auf dauer ein leerer Baum - es lassen sich laut Dokumentation nichtmal Bäume anhängen. Also wird die Wurzel gefüllt.
        createMorseTree();
    }

    /**
     * Zur Präsentation des Programms wird der Morsecode im Baum dargestellt.
     */
    private void createMorseTree(){
        //TODO 02: Vervollständige den Morsebaum. Such bei google nach "morsecode as tree" als Vorlage. Das hilft, die Übersicht zu wahren.
        BinaryTree<String> left = new BinaryTree<>("E", new BinaryTree<>("I", new BinaryTree<>("S", new BinaryTree<>("H"), new BinaryTree<>("V")), new BinaryTree<>("U", new BinaryTree<>("F"), null)), new BinaryTree<>("A", new BinaryTree<>("R", new BinaryTree<>("L"), null), new BinaryTree<>("W", new BinaryTree<>("P"), new BinaryTree<>("J"))));
        BinaryTree<String> right = new BinaryTree<>("T", new BinaryTree<>("N", new BinaryTree<>("D", new BinaryTree<>("B"), new BinaryTree<>("X")), new BinaryTree<>("K", new BinaryTree<>("C"), new BinaryTree<>("Y"))), new BinaryTree<>("M", new BinaryTree<>("G", new BinaryTree<>("Z"), new BinaryTree<>("Q")), new BinaryTree<>("O")));

        binaryTree.setLeftTree(left);
        binaryTree.setRightTree(right);
    }

    /**
     * Der Baum wird im übergebenem Panel dargestellt.
     * Dazu wird zunächst die alte Zeichnung entfernt.
     * Im Anschluss wird eine eine internete Hilfsmethode aufgerufen.
     * @param panel Das DrawingPanel-Objekt, auf dem gezeichnet wird.
     */
    public void showTree(DrawingPanel panel){
        panel.removeAllObjects();
        //Der Baum wird in der Mitte des Panels beginnend gezeichnet: x = panel.getWidth()/2
        //Der linke und rechte Knoten in Tiefe 1 sind jeweils ein Viertel der Breite des Panels entfernt: spaceToTheSide = panel.getWidth()/4
        showTree(binaryTree, panel, panel.getWidth()/2, 50, panel.getWidth()/4);
		
		//Aufruf fordert das Panel zur Aktualisierung auf.
		panel.repaint();
    }

    /**
     * Hilfsmethode zum Zeichnen des Baums.
     * Für jeden Knoten wird ein neues TreeNode-Objekt dem panel hinzugefügt.
     * Für jede Kante wird ein neues TreePath-Objekt dem panel hinzugefügt.
     * @param tree Der zu zeichnende (Teil)Binärbaum.
     * @param panel Das DrawingPanel-Objekt, auf dem gezeichnet wird.
     * @param startX x-Koordinate seiner Wurzel
     * @param startY y-Koordinate seiner Wurzel
     * @param spaceToTheSide Gibt an, wie weit horizontal entfernt die folgenden Bäume gezeichnet werden soll.
     */
    private void showTree(BinaryTree tree, DrawingPanel panel, double startX, double startY, double spaceToTheSide) {
        //TODO 03: Vervollständige diese Methode. Aktuell wird nur der Wurzelknoten gezeichnet.
        int radius = 10;
        if (!tree.isEmpty()) {
            TreeNode node = new TreeNode(startX, startY, radius, tree.getContent().toString(), false);
            panel.addObject(node);
            if(!tree.getLeftTree().isEmpty()) {
                panel.addObject(new TreePath(startX, startY+2*radius, startX-spaceToTheSide, startY+100-2*radius, 0, false));
                showTree(tree.getLeftTree(), panel, startX - spaceToTheSide, startY + 100, spaceToTheSide / 2);
            }
            if(!tree.getRightTree().isEmpty()) {
                panel.addObject(new TreePath(startX, startY+2*radius, startX + spaceToTheSide, startY + 100-2*radius, 0, false));
                showTree(tree.getRightTree(), panel, startX + spaceToTheSide, startY + 100, spaceToTheSide / 2);
            }
        }
    }

    /**
     * Es wird das Ergebnis einer Traversierung bestimmt.
     * Ruft dazu eine interne Hilfsmethode auf.
     * @return Das Ergebnis der Traversierung als Zeichenkette.
     */
    public String traverse(){
        return traverse(binaryTree)+"\n Nodes: "+countNodes(binaryTree);
    }

    /**
     * Interne hilfsmethode zur Traversierung.
     * @param tree Der zu traversierende Binärbaum.
     * @return Das Ergebnis der Traversierung als Zeichenkette.
     */
    private String traverse(BinaryTree tree){
        //TODO 04: Nachdem wir geklärt haben, was eine Traversierung ist, muss diese Methode noch vervollständigt werden. Sollte ein Kinderspiel sein.
        if (!tree.isEmpty()) {
            String left = "";
            String right = "";
            if(!tree.getLeftTree().isEmpty()) {
                left = traverse(tree.getLeftTree());
            }
            if(!tree.getRightTree().isEmpty()) {
                right = traverse(tree.getRightTree());
            }
            return tree.getContent()+left+right;
        }
        return null;
    }

    /**
     * Interne Übungsmethode zur Traversierung.
     * @param tree Der zu traversierende Binärbaum.
     * @return Die Anzahl der Knoten in diesem Baum
     */
    private int countNodes(BinaryTree tree){
        //TODO 05: Übungsmethode
        if (!tree.isEmpty()) {
            return 1+countNodes(tree.getLeftTree())+countNodes(tree.getRightTree());
        }
        return 0;
    }
}
