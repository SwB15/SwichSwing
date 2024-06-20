package Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

public class InternalPanel extends JPanel {

    Panel2 panelSecundario = new Panel2();

    private int borderWidth = 7; // Grosor del borde por defecto
    private int borderRadius = 30; // Radio de las esquinas redondeadas por defecto
    private Color borderColor = new Color(0, 102, 255); // Color del borde por defecto
    private Color fillColor = new Color(0, 102, 255); // Color de relleno por defecto

    // Agregar JLabel
    JLabel leftLabel = new JLabel("Left Label");
    JLabel rightLabel = new JLabel();

    public InternalPanel() {
        this.setBorder(null);
        this.setOpaque(true);
        this.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.setForeground(Color.black);
        this.setBackground(new Color(0, 0, 0, 0));
        setLayout(null); // Usar null layout para control manual

        // Configurar el panel secundario
        panelSecundario.setOpaque(false);
        add(panelSecundario); // Añadir el panel secundario

        // Configurar los JLabel
        leftLabel.setForeground(Color.WHITE); // Color del texto para que sea visible
        leftLabel.setText("TITULO");
        leftLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        rightLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Cerrar32.png")));

        // Añadir los JLabel al panel principal
        add(leftLabel);
        add(rightLabel);

        // Añadir MouseListener al rightLabel
        rightLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // Obtener el JInternalFrame padre y cerrarlo
                    JInternalFrame internalFrame = (JInternalFrame) SwingUtilities.getAncestorOfClass(JInternalFrame.class, InternalPanel.this);
                    if (internalFrame != null) {
                        internalFrame.dispose();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // No se necesita implementación
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // No se necesita implementación
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // No se necesita implementación
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // No se necesita implementación
            }
        });

        // Escuchar los cambios de tamaño del panel principal
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                // Ajustar el tamaño del panel secundario proporcionalmente al tamaño del panel principal
                adjustSecondaryPanelSize();
                // Ajustar la posición de los JLabel
                adjustLabelPositions();
            }
        });

        // Inicializar el tamaño y la posición del panel secundario y de los JLabel
        adjustSecondaryPanelSize();
        adjustLabelPositions();
    }

    // Método para ajustar el tamaño del panel secundario en función del tamaño del panel principal
    private void adjustSecondaryPanelSize() {
        int mainWidth = getWidth();
        int mainHeight = getHeight();

        // Definir los márgenes según los requisitos
        int topMargin = 60;
        int sideBottomMargin = 7;

        // Calcular el nuevo tamaño del panel secundario
        int newWidth = mainWidth - (2 * sideBottomMargin);
        int newHeight = mainHeight - topMargin - sideBottomMargin;

        // Establecer el tamaño y la posición del panel secundario
        panelSecundario.setBounds(sideBottomMargin, topMargin, newWidth, newHeight);
        panelSecundario.revalidate();
        panelSecundario.repaint();
    }

    // Método para ajustar la posición de los JLabel
    private void adjustLabelPositions() {
        int mainWidth = getWidth();
        int topMargin = 30;

        // Calcular el ancho del texto del leftLabel
        FontMetrics fm = leftLabel.getFontMetrics(leftLabel.getFont());
        int textWidth = fm.stringWidth(leftLabel.getText());

        // Ajustar el tamaño y la posición del leftLabel para que se ajuste al texto
        leftLabel.setBounds(20, (topMargin / 2) - 2, textWidth + 10, 35); // Se añaden 10px de padding para un margen

        // Posicionar el rightLabel en la parte derecha del espacio de 30px, siempre a una distancia fija de 10px del borde derecho
        int rightLabelWidth = rightLabel.getIcon().getIconWidth();
        int rightLabelHeight = rightLabel.getIcon().getIconHeight();
        rightLabel.setBounds(mainWidth - rightLabelWidth - 10, (topMargin / 2) - 3, rightLabelWidth, rightLabelHeight); // Ajustar la posición y el tamaño del JLabel
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        repaint();
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Establece el color del borde
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

        // Establece el color del interior
        g2.setColor(fillColor);
        g2.fillRoundRect(borderWidth, borderWidth, getWidth() - (borderWidth * 2), getHeight() - (borderWidth * 2), borderRadius - borderWidth, borderRadius - borderWidth);

        g2.dispose();
    }
}

class Panel2 extends JPanel {

    private int borderWidth = 1; // Grosor del borde por defecto
    private int borderRadius = 23; // Radio de las esquinas redondeadas por defecto
    private Color borderColor = Color.WHITE; // Color del borde por defecto
    private Color fillColor = Color.WHITE; // Color de relleno por defecto

    public Panel2() {
        this.setBorder(null);
        this.setOpaque(false);
        this.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.setForeground(Color.black);
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        repaint();
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Establece el color del borde
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

        // Establece el color del interior
        g2.setColor(fillColor);
        g2.fillRoundRect(borderWidth, borderWidth, getWidth() - (borderWidth * 2), getHeight() - (borderWidth * 2), borderRadius - borderWidth, borderRadius - borderWidth);
        g2.dispose();
    }
}
