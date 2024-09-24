import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class PromptGenerador {

    private static final Map<String, String> sinonimos = new HashMap<>();

    static {
        sinonimos.put("realiza", "ejecuta");
        sinonimos.put("siguiente", "próxima");
        sinonimos.put("instrucción", "directriz");
        sinonimos.put("responde", "contesta");
        sinonimos.put("pregunta", "cuestión");
        sinonimos.put("proporciona", "ofrece");
        sinonimos.put("descripción", "explicación");
        sinonimos.put("procesa", "maneja");
        sinonimos.put("texto", "mensaje");
    }

    public static void main(String[] args) {
        // Pedir al usuario que introduzca un texto
        String inputText = JOptionPane.showInputDialog(null, "Introduce el texto:");

        // Validar la entrada del usuario
        if (inputText == null || inputText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El texto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Opciones para el tipo de prompt
        String[] options = {"Instrucción", "Pregunta", "Descripción"};
        String promptType = (String) JOptionPane.showInputDialog(null, "Selecciona el tipo de prompt:",
                "Tipo de Prompt", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // Generar el prompt para la IA
        String prompt = generarPrompt(inputText, promptType);

        // Mostrar el prompt generado
        JOptionPane.showMessageDialog(null, "Prompt para la IA:\n" + prompt);
    }

    private static String generarPrompt(String inputText, String promptType) {
        String transformedText = transformarTexto(inputText);
        switch (promptType) {
            case "Instrucción":
                return "Por favor, ejecuta la próxima directriz: \"" + transformedText + "\"";
            case "Pregunta":
                return "Por favor, contesta a la siguiente cuestión: \"" + transformedText + "\"";
            case "Descripción":
                return "Por favor, ofrece una explicación detallada de lo siguiente: \"" + transformedText + "\"";
            default:
                return "Por favor, maneja el siguiente mensaje: \"" + transformedText + "\"";
        }
    }

    private static String transformarTexto(String inputText) {
        String[] words = inputText.split(" ");
        StringBuilder transformedText = new StringBuilder();

        for (String word : words) {
            String lowerCaseWord = word.toLowerCase();
            if (sinonimos.containsKey(lowerCaseWord)) {
                transformedText.append(sinonimos.get(lowerCaseWord));
            } else {
                transformedText.append(word);
            }
            transformedText.append(" ");
        }

        return transformedText.toString().trim();
    }
}