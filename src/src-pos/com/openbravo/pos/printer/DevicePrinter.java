


//

//




//




//



package com.openbravo.pos.printer;

import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public interface DevicePrinter {
    
    public static final int SIZE_0 = 0;
    public static final int SIZE_1 = 1;
    public static final int SIZE_2 = 2;
    public static final int SIZE_3 = 3;
    
    public static final int STYLE_PLAIN = 0;
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_UNDERLINE = 2; 
    
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 1;
    public static final int ALIGN_CENTER = 2;
    
    public static final String BARCODE_EAN13 = "EAN13";
    public static final String BARCODE_CODE128 = "CODE128";
    
    public static final String POSITION_BOTTOM = "bottom";
    public static final String POSITION_NONE = "none";
    
    // INTERFAZ DESCRIPCION
    public String getPrinterName();
    public String getPrinterDescription();
    public JComponent getPrinterComponent();
    public void reset();
    
    // INTERFAZ PRINTER   
    public void beginReceipt();
    public void printImage(BufferedImage image);
    public void printBarCode(String type, String position, String code);
    public void beginLine(int iTextSize);
    public void printText(int iStyle, String sText);
    public void endLine();
    public void endReceipt();   
    
    // INTERFAZ VARIOUS
    public void openDrawer();    
}
