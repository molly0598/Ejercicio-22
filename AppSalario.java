import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppSalario extends JFrame {
    private JTextField campoNombre;
    private JTextField campoSalarioHora;
    private JTextField campoHorasMes;
    private JLabel etiquetaResultado;

    public AppSalario() {
        setTitle("Calculadora de Salario Mensual");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(4, 2, 15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel etiquetaNombre = new JLabel("Nombre del empleado:");
        campoNombre = new JTextField();
        JLabel etiquetaSalarioHora = new JLabel("Salario por hora:");
        campoSalarioHora = new JTextField();
        JLabel etiquetaHorasMes = new JLabel("Horas trabajadas al mes:");
        campoHorasMes = new JTextField();
        JButton botonCalcular = new JButton("Calcular");
        etiquetaResultado = new JLabel("", SwingConstants.CENTER);

        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                try {
                    String nombre = campoNombre.getText();
                    double salarioHora = Double.parseDouble(campoSalarioHora.getText());
                    double horasTrabajadas = Double.parseDouble(campoHorasMes.getText());

                    double salarioMensual = calcularSalarioMensual(salarioHora, horasTrabajadas);
                    etiquetaResultado.setText("Nombre: " + nombre + " | Salario mensual: $" + salarioMensual);
                } catch (NumberFormatException excepcion) {
                    etiquetaResultado.setText("Por favor, ingresa valores válidos.");
                }
            }
        });

        botonCalcular.setPreferredSize(new Dimension(160, 50));
        botonCalcular.setBackground(Color.GREEN);
        botonCalcular.setForeground(Color.WHITE);

        panelPrincipal.add(etiquetaNombre);
        panelPrincipal.add(campoNombre);
        panelPrincipal.add(etiquetaSalarioHora);
        panelPrincipal.add(campoSalarioHora);
        panelPrincipal.add(etiquetaHorasMes);
        panelPrincipal.add(campoHorasMes);
        panelPrincipal.add(botonCalcular);
        panelPrincipal.add(etiquetaResultado);

        add(panelPrincipal);
        setVisible(true);
    }

    private double calcularSalarioMensual(double salarioHora, double horas) {
        double salarioBase = salarioHora * horas;

        // Aplicar un bono si el salario base excede cierto límite
        double bono = 0.0;
        if (salarioBase > 600000) {
            bono = 0.05 * salarioBase;
        }

        return salarioBase + bono;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
            new AppSalario();
        });
    }
}