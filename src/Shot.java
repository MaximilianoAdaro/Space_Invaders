import javax.swing.ImageIcon;

class Shot extends Sprite {

    Shot(){}

    Shot(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        String shotImg = "src/images/shot.png";
        ImageIcon ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        int h_SPACE = 6;
        int v_SPACE = 1;

        setX(x + h_SPACE);
        setY(y - v_SPACE);
    }
    public void changeX(int difference){
        x+=difference;
    }


}
