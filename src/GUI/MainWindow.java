package GUI;

import Backend.MyImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class MainWindow extends JFrame {
    private File initialFile;
    private BufferedImage initialImage;
    private BufferedImage finalImage;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MainWindow() {
        setLocation(0, 0);
        setSize(screenSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2, 10, 10));
        createMunu();


        setVisible(true);
    }


    private void createMunu() {
        MenuBar topMenuBar = new MenuBar();
        setMenuBar(topMenuBar);
        Menu fileMenu = new Menu("файл");
        MenuItem open = new MenuItem("открыть");
        fileMenu.add(open);
        MenuItem save = new MenuItem("сохранить");
        fileMenu.add(save);
        topMenuBar.add(fileMenu);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileExplorer = new JFileChooser();
                String[] suffices = ImageIO.getReaderFileSuffixes();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("image files", suffices);
                fileExplorer.addChoosableFileFilter(filter);
                fileExplorer.setAcceptAllFileFilterUsed(true);
                int ret = fileExplorer.showOpenDialog(MainWindow.this);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    initialFile = fileExplorer.getSelectedFile();
                    MyImage initialImage = MyImage.getMyImage(initialFile);


                    ImagePanel initialImagePanel = new ImagePanel(initialImage);
                    add(initialImagePanel);
                    revalidate();

                    ImagePanel finalImagePanel=new ImagePanel(initialImage);
                    add(finalImagePanel);
                    revalidate();





                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileExplorer = new JFileChooser();
                int ret = fileExplorer.showSaveDialog(MainWindow.this);

            }
        });


    }
}
