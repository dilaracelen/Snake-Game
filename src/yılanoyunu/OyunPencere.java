package yılanoyunu;
import java.awt.*;
import javax.swing.*;

public class OyunPencere extends JFrame{ // JFrame inheritance
    
    private int Genişlik = 700;
    private int Yükseklik = 700;
    private static OyunPencere mPencere = null;
    
    private OyunPencere(){
        
        this.setTitle("Yılan Oyunum");
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        /*
        this.pack();
        this.frame.setLocationRelativeTo(null);
        */
        Ortala();
        
        // this.setLayout(null);
        
        Yılan Y = new Yılan();
        add(Y);   
    }   
    
    public void Ortala (){ 
    // Frame'i, bilgisayar fark etmeksizin tam ortaya getirmek için.
        
        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        // Kullanılan bilgisayarın ekran boyutunu alır.
        
        int PosX = 0, PosY = 0;
        
        if(Genişlik + 100 > dm.width)
            Genişlik = dm.width - 100;
        
        if (Yükseklik + 100 > dm.height)
            Yükseklik = dm.height - 100;     
        
        PosX = (dm.width - Genişlik) / 2;
        PosY = (dm.height - Yükseklik) / 2;
        
        setBounds(PosX, PosY, Genişlik, Yükseklik);
    }
    
    public static OyunPencere PencereGetir(){ //sadece 1 pencere oluşturmak için
        
        if(mPencere == null)
            mPencere = new OyunPencere();
        
        return new OyunPencere();
    }
    
}
