import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Imagenes extends JFrame {

	public Imagenes() {
		// Configurar la ventana
		setTitle("Mostrar Imagen");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(2, 2));

		// Pedir el nombre del archivo al usuario
		String fileName = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo de imagen:");

		// Cargar la imagen
		File file = new File(fileName);
		if (file.exists()) {
			try {
				BufferedImage image = ImageIO.read(file);

				// Crear las versiones de la imagen
				BufferedImage sepiaImage = applySepiaFilter(copyImage(image));
				BufferedImage bwImage = applyBlackAndWhiteFilter(copyImage(image));
				BufferedImage saturatedImage = applySaturationFilter(copyImage(image));

				// Crear ImageIcons a partir de las imágenes
				ImageIcon originalIcon = new ImageIcon(image.getScaledInstance(600, 400, Image.SCALE_SMOOTH));
				ImageIcon sepiaIcon = new ImageIcon(sepiaImage.getScaledInstance(600, 400, Image.SCALE_SMOOTH));
				ImageIcon bwIcon = new ImageIcon(bwImage.getScaledInstance(600, 400, Image.SCALE_SMOOTH));
				ImageIcon saturatedIcon = new ImageIcon(saturatedImage.getScaledInstance(600, 400, Image.SCALE_SMOOTH));

				// Crear JLabels para contener las imágenes
				JLabel originalLabel = new JLabel(originalIcon);
				JLabel sepiaLabel = new JLabel(sepiaIcon);
				JLabel bwLabel = new JLabel(bwIcon);
				JLabel saturatedLabel = new JLabel(saturatedIcon);

				// Agregar los JLabels al JFrame
				add(originalLabel);
				add(sepiaLabel);
				add(bwLabel);
				add(saturatedLabel);

				// Guardar la imagen resultante
				String newFileName = getNewFileName(fileName);
				ImageIO.write(sepiaImage, "jpg", new File(newFileName));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		// Hacer visible la ventana
		setVisible(true);
	}

	private BufferedImage applySepiaFilter(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int p = img.getRGB(x, y);

				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;

				// Calculate new red, green, blue values
				int tr = (int)(0.393 * r + 0.769 * g + 0.189 * b);
				int tg = (int)(0.349 * r + 0.686 * g + 0.168 * b);
				int tb = (int)(0.272 * r + 0.534 * g + 0.131 * b);

				// Check condition
				if (tr > 255) {
					r = 255;
				} else {
					r = tr;
				}

				if (tg > 255) {
					g = 255;
				} else {
					g = tg;
				}

				if (tb > 255) {
					b = 255;
				} else {
					b = tb;
				}

				// Set new RGB value
				p = (a << 24) | (r << 16) | (g << 8) | b;

				img.setRGB(x, y, p);
			}
		}

		return img;
	}

	private BufferedImage applyBlackAndWhiteFilter(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int p = img.getRGB(x, y);

				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;

				// Calculate average
				int avg = (r + g + b) / 3;

				// Replace RGB value with avg
				p = (a << 24) | (avg << 16) | (avg << 8) | avg;

				img.setRGB(x, y, p);
			}
		}

		return img;
	}

	private BufferedImage applySaturationFilter(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int p = img.getRGB(x, y);

				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;

				// Increase saturation
				r = Math.min(255, (int)(r * 1.2));
				g = Math.min(255, (int)(g * 1.2));
				b = Math.min(255, (int)(b * 1.2));

				// Set new RGB value
				p = (a << 24) | (r << 16) | (g << 8) | b;

				img.setRGB(x, y, p);
			}
		}

		return img;
	}

	private BufferedImage copyImage(BufferedImage img) {
		BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				copy.setRGB(x, y, img.getRGB(x, y));
			}
		}
		return copy;
	}

	private String getNewFileName(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex == -1) {
			return fileName + "2";
		} else {
			return fileName.substring(0, dotIndex) + "2" + fileName.substring(dotIndex);
		}
	}

	public static void main(String[] args) {
		// Crear y mostrar la ventana
		new Imagenes();
	}
}