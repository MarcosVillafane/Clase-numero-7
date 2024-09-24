import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImagenGenerada {

    public static void main(String[] args) {
        try {
            // Dimensiones de la imagen
            int width = 800;
            int height = 600;

            // Crear una imagen en blanco
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // Obtener el objeto Graphics para dibujar en la imagen
            Graphics g = image.getGraphics();

            // Establecer el color de fondo y rellenar el fondo
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, width, height);

            // Establecer el color del texto y la fuente
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));

            // Dibujar el texto en el centro de la imagen
            String text = "Mi Imagen Generada";
            int textWidth = g.getFontMetrics().stringWidth(text);
            int textHeight = g.getFontMetrics().getHeight();
            g.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2);

            // Liberar los recursos gráficos
            g.dispose();

            // Guardar la imagen en un archivo
            String fileName = "imagen_generada.jpg";
            ImageIO.write(image, "jpg", new File(fileName));

            System.out.println("Imagen generada y guardada como '" + fileName + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}