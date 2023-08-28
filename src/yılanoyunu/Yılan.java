package yılanoyunu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Yılan extends JLabel{

    public Kutu Baş = new Kutu();
    
    public Timer zaman = null;
    
    public ArrayList<Kutu> Liste = new ArrayList<Kutu>();
    
    public Yem ym = new Yem();
    
    public Random  rd = null;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody     
        Graphics2D g2 = (Graphics2D) g;
        
        Rectangle2D dikd = new Rectangle2D.Double(5, 5, getWidth()-10, getHeight()-10);
        g2.setColor(Color.GREEN);   
        g2.setStroke(new BasicStroke(10));
        g2.draw(dikd);
    }
 
    public int Genişlik = 20;
    
    Yılan(){
        
        rd = new Random(System.currentTimeMillis());
        
        this.addKeyListener(new KlavyeKontrol());
        this.setFocusable(true);
        
        zaman = new Timer(100,new ZamanKontrol());
        // 100 mili saniyede 1 çağrılcaktır.
        zaman.start();

        Liste.add(Baş);     
        
        for(int i=0; i<4; i++){
            
            KuyrukEkle();
        }

        this.add(ym);
        this.add(Baş);       
    }
    
    public void KuyrukEkle(){
        
        Kutu K = Liste.get(Liste.size()-1).KutuOluştur();
            
        Liste.add(K);
        add(K);
    }

    public void HepsiHareket()
    {
        for(int i=Liste.size()-1; i>0; i--){
            
            Kutu Önceki = Liste.get(i-1);
            Kutu Sonraki = Liste.get(i);
            
            Liste.get(i).Hareket(); 
            
            Sonraki.Yön = Önceki.Yön;
        }
        Baş.Hareket();
    }
    
    public boolean ÇarpışmaVarmı()
    {       
        int gnş = getWidth();
        int yks = getHeight();
        
        if(Baş.getX()<=10)
            return true;
        
        if(Baş.getX()+Baş.Genişlik>=gnş-10)
            return true;
        
        if(Baş.getY()<=10)
            return true;
        
        if(Baş.getY()+Baş.Genişlik>=yks-10)
            return true;
        
        for(int i=1; i<Liste.size(); i++){
            
            int x = Liste.get(i).getX();
            int y = Liste.get(i).getY();
            
            if(x == Baş.getX() && y == Baş.getY())
                return true;
        }
        
        if(ym.getX() == Baş.getX() && ym.getY() == Baş.getY()){
            
            KuyrukEkle();
            YemEkle();                                   
        }

        return false;
    }
    
    public void YemEkle(){
        
        int pGenişlik = getWidth() - 30 - ym.Genişlik;
        int pYükseklik = getHeight() - 30 - ym.Genişlik;
        
        int PosX = 10 + Math.abs(rd.nextInt()) % pGenişlik;
        int PosY = 10 + Math.abs(rd.nextInt()) % pYükseklik;
        
        PosX = PosX - PosX%20;
        PosY = PosY - PosY%20;
        
        ym.Pozisyon(PosX, PosY);
        
        for(int i=0; i<Liste.size(); i++){
            
            if(PosX == Liste.get(i).getX() && PosY == Liste.get(i).getY()){
                
                YemEkle();
                return;
            }
        }
    }
    
    
    class KlavyeKontrol implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
             
        }

        @Override
        public void keyPressed(KeyEvent e) { 
        // Tuşa basıldığında Key listener tarafından çağrılır.
           
            if(e.getKeyCode() == KeyEvent.VK_LEFT && Baş.Yön!=YÖN.SAĞ)
                Baş.Yön = YÖN.SOL;
            
            if(e.getKeyCode() == KeyEvent.VK_RIGHT && Baş.Yön!=YÖN.SOL)    
                Baş.Yön = YÖN.SAĞ;
            
            if(e.getKeyCode() == KeyEvent.VK_UP && Baş.Yön!=YÖN.AŞAĞI)
                Baş.Yön = YÖN.YUKARI;
            
            if(e.getKeyCode() == KeyEvent.VK_DOWN && Baş.Yön!=YÖN.YUKARI)
                Baş.Yön = YÖN.AŞAĞI;
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            
        }      
    }
        
    public void Mesaj(){
        
        this.setVisible(false);
        JOptionPane.showMessageDialog(null, "Game Over!");
    }
    
    class ZamanKontrol implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
             
            HepsiHareket(); 
            
            if(ÇarpışmaVarmı()){
                zaman.stop();   
                // Mesaj(); //SOR
            }
        }  
    }
}

class Kutu extends JLabel{
    
    public int Genişlik = 20;
    
    public int Yön = YÖN.SAĞ;
    
    Kutu(){
        
        this.setBounds(100, 100, Genişlik, Genişlik);
        this.setLayout(null);
    }  
    
    @Override
    public void paint(Graphics g){
        
        Graphics2D g1 = (Graphics2D) g;
        
        Rectangle2D dikd = new Rectangle2D.Double(0, 0, getWidth()-2, getHeight()-2);
        g1.setColor(Color.GREEN);   
        g1.setStroke(new BasicStroke(2));
        g1.draw(dikd);
        g1.fill(dikd);
    }
    
    public void SolaGit()
    {
        int PosX = getX();
        int PosY = getY();
        PosX -=Genişlik;
        this.setBounds(PosX, PosY, Genişlik, Genişlik);
    }
    
    public void SağaGit()
    {
        int PosX = getX();
        int PosY = getY();
        PosX +=Genişlik;
        this.setBounds(PosX, PosY, Genişlik, Genişlik);
    }
    
    public void YukarıGit()
    {
        int PosX = getX();
        int PosY = getY();
        PosY -=Genişlik;   //Buraya dikkat
        this.setBounds(PosX, PosY, Genişlik, Genişlik);
    }
    
    public void AşağıGit()
    {
        int PosX = getX();
        int PosY = getY();
        PosY +=Genişlik;
        this.setBounds(PosX, PosY, Genişlik, Genişlik);
    }
    
    public Kutu KutuOluştur()
    {
        Kutu K = new Kutu();
        
        int x = getX();
        int y = getY();
        
        K.setBounds(x, y, Genişlik, Genişlik);
  
        K.Yön = -Yön;
        
        K.Hareket();
        
        K.Yön = Yön;
                
        return K;
    }
    
    public void Hareket()
    {
        if(Yön == YÖN.SOL)
            SolaGit();
        
        else if(Yön == YÖN.SAĞ)
            SağaGit();
        
        else if(Yön == YÖN.AŞAĞI)
            AşağıGit();
        
        else if(Yön == YÖN.YUKARI)
            YukarıGit();
    }   
}
