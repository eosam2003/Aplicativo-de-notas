package notas;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



public class AplicativoNotas extends JFrame implements ActionListener {

    private JTextArea areaTexto;

    private JButton botaoSalvar;



    public AplicativoNotas() {

        setTitle("Aplicativo de Notas");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());



        areaTexto = new JTextArea();

        add(new JScrollPane(areaTexto), BorderLayout.CENTER);



        botaoSalvar = new JButton("Salvar");

        botaoSalvar.addActionListener(this);

        add(botaoSalvar, BorderLayout.SOUTH);



        setVisible(true);

    }



    @Override

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botaoSalvar) {

            JFileChooser fileChooser = new JFileChooser();

            int resultado = fileChooser.showSaveDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {

                try {

                    String texto = areaTexto.getText();

                    String caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();

                    ArquivoUtil.salvarTexto(texto, caminhoArquivo);

                    JOptionPane.showMessageDialog(this, "Arquivo salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);

                }

            }

        }

    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override

            public void run() {

                new AplicativoNotas();

            }

        });

    }

}



class ArquivoUtil {

    public static void salvarTexto(String texto, String caminhoArquivo) throws Exception {

        java.io.FileWriter writer = new java.io.FileWriter(caminhoArquivo);

        writer.write(texto);

        writer.close();

    }

}