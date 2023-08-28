package yılanoyunu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JLabel;

public class Yem extends JLabel{
    
    public int Genişlik = 20;  
    
     Yem(){
                    
        Pozisyon(20,20);
        this.setLayout(null);
    }  
    
    @Override
    public void paint(Graphics g){
        
        Graphics2D g1 = (Graphics2D) g;
        
        Ellipse2D elp = new Ellipse2D.Double(1, 1, Genişlik-2, Genişlik-2);
        g1.setColor(Color.GREEN);   
        g1.setStroke(new BasicStroke(2));
        g1.draw(elp);
        g1.fill(elp);
    }
    
    public void Pozisyon(int PozX, int PozY){
        
        setBounds(PozX, PozY, Genişlik, Genişlik);
    }
}
