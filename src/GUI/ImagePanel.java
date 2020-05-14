package GUI;

import Backend.MyImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImagePanel extends JPanel {
    private BufferedImage image;
    public ImagePanel(MyImage image){
        super();
        this.image=image;
        setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(MyImage.getImage(),null,0,0);
        g.dispose();
    }
}
