import java.awt.*;

public class Pixel {

    protected int x;
    protected int y;
    protected int colorId; //0-4 for the colors, 10 for black, 20 for empty. I prefer 10,20 over 5,6 simply for ease of distinction.
    private Color[] colorList = {Color.red,Color.green,Color.cyan,Color.yellow,Color.magenta};

    public Pixel(int x,int y){
        this.x = x;
        this.y = y;
        colorId = 20;
    }

    public void fill(){
        colorId = 10;
    }

    public boolean isEmpty(){
        if(colorId==20){
            return true;
        }
        return false;
    }

    public void color(int colorId){
        if(colorId<5) {
            this.colorId = colorId;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColorId() {
        return colorId;
    }

    public void draw(Graphics g){
        if(colorId==20){
            g.setColor(Color.black);
            g.drawRect(10*x,10*y,10,10);
        }else if(colorId==10){
            g.setColor(Color.black);
            g.fillRect(10*x,10*y,10,10);
        }else{
            g.setColor(colorList[colorId]);
            g.fillRect(10*x,10*y,10,10);
        }
    }
}
