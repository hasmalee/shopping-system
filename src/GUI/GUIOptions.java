package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

abstract class GUIOptions extends JFrame {

    protected void SetWindow(int width, int height, String title){
        setSize(width,height);
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
