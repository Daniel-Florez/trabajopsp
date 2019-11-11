package psp;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Daniel Florez
 */
public class Ventana extends JFrame implements ActionListener {

    private long tiempo;

    private GridBagConstraints gbc = new GridBagConstraints();

    private JPanel panel = new JPanel();
    private JPanel panelCentro = new JPanel();
    private JPanel panelSur = new JPanel();

    private Thread tRobot;
    private Thread tComando;

    private JSpinner txt_tiempo;
    private JLabel lb_tiempo = new JLabel("tiempo para apagado (seg)");
    private JButton btnApagar = new JButton("Apagar PC");
    private JButton btnApagarEntrada = new JButton("Apagar PC con robot");

    public void confiConstraint(Integer posX, Integer posY, Integer tX, Integer tY, Double weightX, Double weightY, Integer anchor, Integer fill, Component componente, JPanel panel) {

        gbc.gridx = posX;
        gbc.gridy = posY;
        gbc.gridwidth = tX;
        gbc.gridheight = tY;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.anchor = anchor;
        gbc.fill = fill;
        panel.add(componente, gbc);

    }

    public Ventana() {

        txt_tiempo = new JSpinner();
        txt_tiempo.setValue(0);
        txt_tiempo.setSize(500, 50);
        btnApagar.setActionCommand("apagarComandos");
        btnApagar.addActionListener(this);
        btnApagarEntrada.setActionCommand("apagarRobot");
        btnApagarEntrada.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(new Dimension(300, 400));

        panel.setLayout(new BorderLayout());

        panelCentro.setLayout(new GridBagLayout());
        confiConstraint(0, 0, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.NONE, lb_tiempo, panelCentro);
        confiConstraint(0, 1, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, txt_tiempo, panelCentro);

        panelSur.setLayout(new GridBagLayout());
        confiConstraint(0, 0, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.NONE, btnApagar, panelSur);
        confiConstraint(1, 0, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.NONE, btnApagarEntrada, panelSur);

        panel.add(panelCentro, BorderLayout.CENTER);
        panel.add(panelSur, BorderLayout.SOUTH);
        add(panel);

        tRobot = new Thread(apagadoRobot());
        tComando = new Thread(apagadoComandos());

    }

    public Runnable apagadoRobot() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Robot rob = new Robot();

                    rob.keyPress(KeyEvent.VK_WINDOWS);
                    rob.keyPress(KeyEvent.VK_D);
                    rob.keyRelease(KeyEvent.VK_WINDOWS);
                    rob.keyRelease(KeyEvent.VK_D);
                    rob.delay(200);
                    rob.keyPress(KeyEvent.VK_ALT);
                    rob.keyPress(KeyEvent.VK_F4);
                    rob.keyRelease(KeyEvent.VK_ALT);
                    rob.keyRelease(KeyEvent.VK_F4);
                    rob.delay(200);
                    rob.keyPress(KeyEvent.VK_ENTER);
                    rob.keyRelease(KeyEvent.VK_ENTER);
                } catch (AWTException ex) {

                }
            }
        };
        return r;
    }

    public Runnable apagadoComandos() {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "shutdown /p");
                try {
                    //System.out.println("SE TE APAGO EL PC");
                    pb.start();
                } catch (IOException ex) {
                }
            }
        };
        return r;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "apagarComandos":
                try {
                    tiempo = ((int) txt_tiempo.getValue()) * 1000;
                    tComando.sleep(tiempo);
                    tComando.start();
                    Thread.sleep(200);
                    System.exit(0);
                } catch (InterruptedException ex) {

                }

                break;
            case "apagarRobot": {
                try {
                    tiempo = ((int) txt_tiempo.getValue()) * 1000;
                    tRobot.sleep(tiempo);
                    tRobot.start();
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException ex) {
                }
            }

            break;
        }
    }
}
