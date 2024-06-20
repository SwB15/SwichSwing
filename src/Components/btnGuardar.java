package Components;

import java.awt.BasicStroke;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.imageio.ImageIO;

public class btnGuardar extends JButton {

    private BufferedImage originalImage;
    private Color iconColor;
    private boolean hasBorder;
    private Color borderColor;
    private boolean hasBackgroundColor;
    private Color backgroundColor;
    private int borderRadius; // Radio de los bordes redondeados
    private int iconWidth; // Ancho del icono
    private int iconHeight; // Alto del icono
    private int borderSize; // Tamaño del borde

    private String buttonText;
    private Color textColor;
    private int textSize;

    public btnGuardar() {
        try {
            // Cargar la imagen desde el classpath
            originalImage = ImageIO.read(getClass().getResource("/Resources/Guardar32.png"));
            // Definir el tamaño inicial del icono (por defecto 32x32)
            iconWidth = 32;
            iconHeight = 32;
            // Redimensionar la imagen al tamaño deseado
            BufferedImage resizedImage = resizeImage(originalImage, iconWidth, iconHeight);
            setIcon(new ImageIcon(resizedImage));
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
        this.hasBackgroundColor = false;
        this.backgroundColor = Color.WHITE; // Sin color de fondo por defecto
        this.borderRadius = 10; // Radio de bordes por defecto
        this.borderSize = 1; // Tamaño de borde por defecto

        this.buttonText = ""; // Texto vacío por defecto
        this.textColor = Color.BLACK;
        this.textSize = 12; // Tamaño de texto por defecto
    }

    // Método para redimensionar una imagen al tamaño deseado
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Configuración inicial para el antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rellenar el fondo del botón si se ha especificado un color de fondo y hasBackgroundColor es true
        if (hasBackgroundColor && backgroundColor != null) {
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
        }

        // Calcular el espacio entre el texto y el icono
        int iconTextGap = 5; // Espacio entre el texto y el icono
        int iconX = (getWidth() - iconWidth - g2.getFontMetrics().stringWidth(buttonText) - iconTextGap) / 2;
        int iconY = (getHeight() - iconHeight) / 2;

        // Dibujar el icono si existe
        if (getIcon() != null) {
            getIcon().paintIcon(this, g2, iconX, iconY);
        }

        // Dibujar el texto al lado del icono si hay texto
        if (!buttonText.isEmpty()) {
            g2.setColor(textColor);
            g2.setFont(getFont().deriveFont((float) textSize));
            int textX = iconX + iconWidth + iconTextGap;
            int textY = (getHeight() + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;
            g2.drawString(buttonText, textX, textY);
        }

        // Dibujar el borde si está activado y el tamaño del borde es mayor a 0
        if (hasBorder && borderSize > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderSize));
            int offset = borderSize / 2; // Ajustar para el tamaño del borde
            g2.drawRoundRect(offset, offset, getWidth() - borderSize, getHeight() - borderSize, borderRadius, borderRadius);
        }

        // Liberar recursos gráficos
        g2.dispose();
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

    // Getter y Setter para hasBackgroundColor
    public boolean isHasBackgroundColor() {
        return hasBackgroundColor;
    }

    public void setHasBackgroundColor(boolean hasBackgroundColor) {
        this.hasBackgroundColor = hasBackgroundColor;
        repaint();
    }

    // Getter y Setter para backgroundColor
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
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

    // Getter y Setter para iconWidth
    public int getIconWidth() {
        return iconWidth;
    }

    public void setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
        setIconSize(iconWidth, this.iconHeight);
        repaint();
    }

    // Getter y Setter para iconHeight
    public int getIconHeight() {
        return iconHeight;
    }

    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
        setIconSize(this.iconWidth, iconHeight);
        repaint();
    }

    // Getter y Setter para borderSize
    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        repaint();
    }

    // Método para cambiar el tamaño del icono del botón
    public void setIconSize(int width, int height) {
        this.iconWidth = width;
        this.iconHeight = height;
        BufferedImage resizedImage = resizeImage(originalImage, width, height);
        setIcon(new ImageIcon(resizedImage));
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

        int[] newColorRGB = new int[]{newColor.getRed(), newColor.getGreen(), newColor.getBlue()};

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

    // Método para convertir un objeto Color a una cadena RGB
    private static String colorToString(Color color) {
        if (color == null) {
            return "";
        }
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }

    // Método para convertir una cadena RGB a un objeto Color
    private static Color stringToColor(String rgbString) {
        if (rgbString == null || rgbString.isEmpty()) {
            return null;
        }
        String[] rgb = rgbString.split(",");
        if (rgb.length != 3) {
            return null;
        }
        int r = Integer.parseInt(rgb[0].trim());
        int g = Integer.parseInt(rgb[1].trim());
        int b = Integer.parseInt(rgb[2].trim());
        return new Color(r, g, b);
    }
}
