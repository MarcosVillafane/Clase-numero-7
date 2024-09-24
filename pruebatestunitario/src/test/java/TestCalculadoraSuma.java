import calculadora.Calculadora;
import junit.framework.TestCase;

public class TestCalculadoraSuma extends TestCase {
    private Calculadora calculadora;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCalculadoraSuma.class);
    }
    protected void setUp() {
        calculadora = new Calculadora();
    }

    public void testSumar() {
        assertEquals(5, calculadora.sumar(2, 3));
    }
}
