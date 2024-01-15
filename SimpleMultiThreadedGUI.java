import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleMultiThreadedGUI extends JFrame {
    private JLabel counterLabel = new JLabel("Counter: 0");
    private JButton startButton = new JButton("Start Thread");
    private int counter = 0;

    public SimpleMultiThreadedGUI() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            counter++;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    counterLabel.setText("Counter: " + counter);
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

        JPanel panel = new JPanel();
        panel.add(counterLabel);
        panel.add(startButton);

        add(panel);
        setTitle("Simple Multi-Threaded GUI");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleMultiThreadedGUI().setVisible(true);
            }
        });
    }
}
