import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener,KeyListener {
    private Pixel[][] grid; // this will be how I determine pixel behavior, interaction, color, etc.
    ArrayList<Integer> oozeCellsX = new ArrayList<Integer>(0);
    ArrayList<Integer> oozeCellsY = new ArrayList<Integer>(0); // these will hold all the currently oozing cells
    double spreadProbability = 1; // this number will determine each iteration if the ooze continues to spread, and will be decreased each iteration.
    boolean isOozing = false;

    public DrawingPanel(){
        grid = new Pixel[50][50];
        for(int i=0;i<50;i++){
            for(int j=0;j<50;j++){ // generate all pixels, then determine randomly if each pixel is empty or not.
                grid[i][j] = new Pixel(i,j);
                double p = Math.random();
                if(p<0.15){
                    grid[i][j].fill();
                }
            }
        }

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Pixel[] row: grid){
            for(Pixel cell: row){
                cell.draw(g);
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        // for every tick, give oozing pixels a chance to ooze:
        if(isOozing) {
            ArrayList<int[]> nextCells = new ArrayList<>(0); // this will replace the oozing cells array at the end of each tick.
            for (int i = 0; i < oozeCellsX.size(); i++) { // check every cell around the current cell, and ooze if probability dictates
                for (int a = -1; a < 2; a++) {
                    for (int b = -1; b < 2; b++) { // a is x displacement, b is y displacement
                        if(oozeCellsX.get(i)+a >= 0 && oozeCellsX.get(i)+a < 50 && oozeCellsY.get(i)+b >=0 && oozeCellsY.get(i)+b < 50) { // assure the cell in question is in bounds
                            int cellColor = grid[oozeCellsX.get(i) + a][oozeCellsY.get(i) + b].getColorId();
                            if (cellColor != 10 && cellColor != grid[oozeCellsX.get(i)][oozeCellsY.get(i)].getColorId()) {
                                if (Math.random() < spreadProbability) { // IF it successfully spreads, add those cells to the list of spreading ooze
                                    grid[oozeCellsX.get(i) + a][oozeCellsY.get(i) + b].color(grid[oozeCellsX.get(i)][oozeCellsY.get(i)].getColorId());
                                    nextCells.add(new int[]{oozeCellsX.get(i) + a, oozeCellsY.get(i) + b});
                                }
                            }
                        }
                    }
                }

            }
            spreadProbability*=0.75; // make oozing always possible, but diminishing probability
            oozeCellsX.clear();
            oozeCellsY.clear();
            if(nextCells.size()==0){
                isOozing=false;
            }else {
                for (int i = 0; i < nextCells.size(); i++) { //replace list with new generation of ooze cells
                    oozeCellsX.add(nextCells.get(i)[0]);
                    oozeCellsY.add(nextCells.get(i)[1]);
                }
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseCoordX = e.getX()/10;
        int mouseCoordY = e.getY()/10;
        int color = (int)(Math.random()*4.99);

        if(isOozing==false) { // this is to make the user wait for the last spread to complete.
            if (grid[mouseCoordX][mouseCoordY].getColorId() != 10) { // assure cell is ooze-able
                grid[mouseCoordX][mouseCoordY].color(color);
                oozeCellsX.add(mouseCoordX);
                oozeCellsY.add(mouseCoordY);
                isOozing = true;
                spreadProbability=1;

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
