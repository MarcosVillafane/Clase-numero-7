import calculadora.Calculadora;
import junit.framework.TestCase;

public class TestCalculadoraDivision extends TestCase {
    private Calculadora calculadora;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCalculadoraDivision.class);
    }
    protected void setUp() {
        calculadora = new Calculadora();
    }

    public void testDividir() {
        assertEquals(2, calculadora.dividir(6, 3));
    }
}

