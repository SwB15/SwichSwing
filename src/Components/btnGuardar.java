package Components;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageIO;

public class btnGuardar extends JButton {

    private BufferedImage originalImage;
    private Color iconColor;
    private boolean hasBorder;
    private Color borderColor;
    private Color fillColor;
    private int borderRadius; // Radio de los bordes redondeados

    private String buttonText;
    private Color textColor;
    private int textSize;

    public btnGuardar() {
        try {
            // Cargar la imagen desde el classpath
            originalImage = ImageIO.read(getClass().getResource("/Resources/Guardar32.png"));
            setIcon(new ImageIcon(originalImage));
        } catch (IOException e) {
            // Manejar la excepción y proporcionar una imagen de marcador de posición en el entorno de diseño
            if (isDesignTime()) {
                setIcon(new ImageIcon(getClass().getResource("/Resources/placeholder.png"))); // Asegúrate de tener una imagen de placeholder
            } else {
                e.printStackTrace();
            }
        }
        // Ajustes iniciales
        this.setText("");
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);

        // Valores por defecto para las propiedades
        this.iconColor = Color.BLACK;
        this.hasBorder = false;
        this.borderColor = Color.BLACK;
        this.fillColor = null; // Sin relleno por defecto
        this.borderRadius = 10; // Radio de bordes por defecto

        this.buttonText = ""; // Texto vacío por defecto
        this.textColor = Color.BLACK;
        this.textSize = 12; // Tamaño de texto por defecto
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el borde si está activado
        if (hasBorder) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(borderColor);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Crear una forma redondeada para el borde
            RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(
                    0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
            g2d.draw(roundRect);

            g2d.dispose();
        }

        // Rellenar el botón si se ha especificado un color de relleno
        if (fillColor != null) {
            g.setColor(fillColor);
            g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
        }

        // Dibujar el texto centrado en el botón
        if (!buttonText.isEmpty()) {
            g.setColor(textColor);
            g.setFont(getFont().deriveFont((float) textSize));
            int x = (getWidth() - g.getFontMetrics().stringWidth(buttonText)) / 2;
            int y = (getHeight() + g.getFontMetrics().getAscent() - g.getFontMetrics().getDescent()) / 2;
            g.drawString(buttonText, x, y);
        }
    }

    // Método para determinar si estamos en el entorno de diseño de NetBeans
    private boolean isDesignTime() {
        return java.beans.Beans.isDesignTime();
    }

    // Getter y Setter para iconColor
    public Color getIconColor() {
        return iconColor;
    }

    public void setIconColor(Color iconColor) {
        this.iconColor = iconColor;
        cambiarColorIcono(iconColor);
    }

    // Getter y Setter para hasBorder
    public boolean isHasBorder() {
        return hasBorder;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
        repaint();
    }

    // Getter y Setter para borderColor
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    // Getter y Setter para fillColor
    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        repaint();
    }

    // Getter y Setter para borderRadius
    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }

    // Getter y Setter para buttonText
    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        repaint();
    }

    // Getter y Setter para textColor
    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        repaint();
    }

    // Getter y Setter para textSize
    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        repaint();
    }

    // Método para cambiar el color del icono del botón
    private void cambiarColorIcono(Color nuevoColor) {
        if (originalImage != null) {
            BufferedImage imagenColoreada = colorImage(originalImage, nuevoColor);
            setIcon(new ImageIcon(imagenColoreada));
            repaint();
        } else {
            System.out.println("Imagen original no cargada.");
        }
    }

    private static BufferedImage colorImage(BufferedImage image, Color newColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        int[] newColorRGB = new int[] { newColor.getRed(), newColor.getGreen(), newColor.getBlue() };

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if (pixels[3] > 0) { // Si el píxel no es transparente
                    System.arraycopy(newColorRGB, 0, pixels, 0, 3);
                    raster.setPixel(xx, yy, pixels);
                }
            }
        }
        return image;
    }
}
