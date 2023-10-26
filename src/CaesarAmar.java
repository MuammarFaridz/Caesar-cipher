import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CaesarAmar extends JFrame implements ActionListener {
    int shift;
    byte[] msgArray;
    byte[] encmsgArray;
    JLabel titleJLabel;
    JLabel JLabel1;
    JTextField msg;
    JLabel JLabel2;
    JTextField encmsg;
    JLabel shiftJLabel;
    JTextField entryShiftValue;
    JButton encipherButton;
    JButton decipherButton;
   int swapIndex; 
    

    public CaesarAmar() {
        this.shift = 0;
        this.msgArray = null;
        this.encmsgArray = null;
        this.titleJLabel = new JLabel("Amar nih bouz");
        this.titleJLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleJLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.JLabel1 = new JLabel("Plaintext");
        this.msg = new JTextField(40);

        this.JLabel2 = new JLabel("Ciphertext");
        this.encmsg = new JTextField(40);
        this.encmsg.setEditable(false);

        this.shiftJLabel = new JLabel("Shift key:");
        this.entryShiftValue = new JTextField(10);

        this.encipherButton = new JButton("Encipher");
        this.decipherButton = new JButton("Decipher");

        this.encipherButton.addActionListener(this);
        this.decipherButton.addActionListener(this);
        this.decipherButton.setEnabled(false);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final CaesarAmar app = new CaesarAmar();
            app.init();
        });
    }

    public void init() {
        this.setTitle("Caesar Cipher");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(this.titleJLabel, c);

        c.gridy++;
        c.gridwidth = 1;
        this.add(this.JLabel1, c);
        c.gridx++;
        this.add(this.msg, c);

        c.gridy++;
        c.gridx = 0;
        this.add(this.JLabel2, c);
        c.gridx++;
        this.add(this.encmsg, c);

        c.gridy++;
        c.gridx = 0;
        this.add(this.shiftJLabel, c);
        c.gridx++;
        this.add(this.entryShiftValue, c);

        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 2;
        this.add(this.encipherButton, c);
        c.gridy++;
        this.add(this.decipherButton, c);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.encipherButton) {
            this.shift = entryShiftValue.getText().length() + 1;
            this.msgArray = this.msg.getText().getBytes();
            this.encmsgArray = caesarEncipher(this.msgArray, this.shift);

            swapIndex = this.encmsgArray.length / 2;

            byte temp = this.encmsgArray[swapIndex];
            this.encmsgArray[swapIndex] = this.encmsgArray[swapIndex - 1];
            this.encmsgArray[swapIndex - 1] = temp;

            this.encmsg.setText(new String(this.encmsgArray));
            this.msg.setText("");
            this.encipherButton.setEnabled(false);
            this.decipherButton.setEnabled(true);
        } else if (e.getSource() == this.decipherButton) {
            this.shift = entryShiftValue.getText().length() + 1;

            byte temp = this.encmsgArray[swapIndex];
            this.encmsgArray[swapIndex] = this.encmsgArray[swapIndex - 1];
            this.encmsgArray[swapIndex - 1] = temp;

            this.msgArray = caesarDecipher(this.encmsgArray, this.shift);
            this.msg.setText(new String(this.msgArray));
            this.encmsg.setText("");
            this.decipherButton.setEnabled(false);
            this.encipherButton.setEnabled(true);
        }
    }



    private static byte[] caesarEncipher(final byte[] message, final int shift) {
        final byte[] m2 = new byte[message.length];
        for (int i = 0; i < message.length; ++i) {
            m2[i] = (byte) ((message[i] + shift) % 256);
        }
        return m2;
    }

    private static byte[] caesarDecipher(final byte[] message, final int shift) {
        final byte[] m2 = new byte[message.length];
        for (int i = 0; i < message.length; ++i) {
            m2[i] = (byte) ((message[i] - shift + 256) % 256);
        }
        return m2;
    }
}