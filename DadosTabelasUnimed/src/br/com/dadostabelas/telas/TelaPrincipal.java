/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dadostabelas.telas;

import br.com.dadostabelas.dal.*;
import java.awt.Font;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ilton
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private JTable tabela;
    private DefaultTableModel modelo = new DefaultTableModel();

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        conexao = ModuloConexao.conector();
        criaJtable();
        jspTabela.setViewportView(tabela);

        System.out.println(conexao);
        if (conexao != null) {
            lblIconeBd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/dadostabelas/imagens/MiniDbOk.png")));
            lblTextoBd.setText("Conexão efetuada com sucesso !");
        }

    }

    /* Monta as colunas da tabela */
    private void criaJtable() {

        tabela = new JTable(modelo);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setFont(new Font("Serif", Font.PLAIN, 12));
        //label.setFont(new Font("Serif", Font.PLAIN, 14));
        modelo.addColumn("Codigo-A");
        modelo.addColumn("Nome Comercial - B");
        modelo.addColumn("Descrição - C");
        modelo.addColumn("Especialidade - D");
        modelo.addColumn("Classificação - E");
        modelo.addColumn("Apresentação - F");
        modelo.addColumn("Unid-G");
        modelo.addColumn("CNPJ Fabric-H");
        modelo.addColumn("Detentor - I");
        modelo.addColumn("Reg. Anvisa-J");
        modelo.addColumn("Pr. Ún-K");
        modelo.addColumn("Pr Fáb-L");
        modelo.addColumn("Valor-M");
        modelo.addColumn("Observações-N");
        modelo.addColumn("Ref. Ant.-O");
        modelo.addColumn("Ref/Tam/Modelo-P");
        modelo.addColumn("Tipo Produto - Q");
        modelo.addColumn("Codif-R");
        modelo.addColumn("Dt Inicio-S");
        modelo.addColumn("Dt Fim - T");
        modelo.addColumn("Mot Ins-U");
        modelo.addColumn("Dt Fim Impl-V");
        modelo.addColumn("Cod Simpro - W");
        modelo.addColumn("Desc. Simpro-X");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(65); //A
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200); //B
        tabela.getColumnModel().getColumn(2).setPreferredWidth(500); //C
        tabela.getColumnModel().getColumn(3).setPreferredWidth(200); //D
        tabela.getColumnModel().getColumn(4).setPreferredWidth(150); //E
        tabela.getColumnModel().getColumn(5).setPreferredWidth(150); //F
        tabela.getColumnModel().getColumn(6).setPreferredWidth(50); //G
        tabela.getColumnModel().getColumn(7).setPreferredWidth(110); //H
        tabela.getColumnModel().getColumn(8).setPreferredWidth(120); //I
        tabela.getColumnModel().getColumn(9).setPreferredWidth(85); //J
        tabela.getColumnModel().getColumn(10).setPreferredWidth(60); //K
        tabela.getColumnModel().getColumn(11).setPreferredWidth(60); //L
        tabela.getColumnModel().getColumn(12).setPreferredWidth(55); //M
        tabela.getColumnModel().getColumn(13).setPreferredWidth(100); //N
        tabela.getColumnModel().getColumn(14).setPreferredWidth(75); //O
        tabela.getColumnModel().getColumn(15).setPreferredWidth(115); //P
        tabela.getColumnModel().getColumn(16).setPreferredWidth(110); //Q
        tabela.getColumnModel().getColumn(17).setPreferredWidth(60); //R
        tabela.getColumnModel().getColumn(18).setPreferredWidth(75); //S
        tabela.getColumnModel().getColumn(19).setPreferredWidth(70); //T
        tabela.getColumnModel().getColumn(20).setPreferredWidth(80); //U
        tabela.getColumnModel().getColumn(21).setPreferredWidth(95); //V
        tabela.getColumnModel().getColumn(22).setPreferredWidth(95); //W
        tabela.getColumnModel().getColumn(23).setPreferredWidth(120); //X
        // para definir tamanho da coluna use tabela.getColumnModel().getColumn(6).setPreferredWidth(300);
    }

    private void consultaCodigo() {
        System.out.println(cboTabela.getSelectedItem().toString());
        //define qual tabela vai consultar no bd
        String tabelaBd = cboTabela.getSelectedItem().toString();
        String tbNoBd = "a";
            
        if ("Vigente".equals(tabelaBd)) {
            tbNoBd = "tbmaterial";            
        } else if ("Anterior".equals(tabelaBd)) {
            tbNoBd = "tbmaterialvig2";
        } else {
            tbNoBd = "tbmaterialvig3";
        }
        System.out.println(tbNoBd);
        
        String sql = "Select * from "+tbNoBd+" where codigo like ? limit 30";
        System.out.println(sql);
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + txtCod.getText() + "%");
            rs = pst.executeQuery();

            /* Pega o resultado da consulta e preenche a tabela */
            rs.first();
            do {
                modelo.addRow(new Object[]{rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getObject(6), rs.getObject(7), rs.getObject(8), rs.getObject(9),
                    rs.getObject(10), rs.getObject(11), rs.getObject(12), rs.getObject(13), rs.getObject(14), rs.getObject(15), rs.getObject(16), rs.getObject(17), rs.getObject(18),
                    rs.getObject(19), rs.getObject(20), rs.getObject(21), rs.getObject(22), rs.getObject(23), rs.getObject(24)});
            } while (rs.next());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum Dado retornado !");
        }
    }

    private void consultaAnvisa () {

        String sql = "Select * from tbmaterial where registro like ? limit 30";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + txtAnvisa.getText() + "%");
            rs = pst.executeQuery();

            /* Pega o resultado da consulta e preenche a tabela */
            rs.first();
            do {
                modelo.addRow(new Object[]{rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getObject(6), rs.getObject(7), rs.getObject(8), rs.getObject(9),
                    rs.getObject(10), rs.getObject(11), rs.getObject(12), rs.getObject(13), rs.getObject(14), rs.getObject(15), rs.getObject(16), rs.getObject(17), rs.getObject(18),
                    rs.getObject(19), rs.getObject(20), rs.getObject(21), rs.getObject(22), rs.getObject(23), rs.getObject(24)});
            } while (rs.next());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum Dado retornado !");
        }
    }
    
    private void limparClienteJTable() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    private void consultaDescricao() {

        String sql = "Select * from tbmaterial where descricao like ? limit 30";
        try {
            pst = conexao.prepareStatement(sql);            
            pst.setString(PROPERTIES, sql);
            pst.setString(1, "%" + txtDesc.getText() + "%");
            rs = pst.executeQuery();

            rs.first();
            do {
                modelo.addRow(new Object[]{rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5), rs.getObject(6), rs.getObject(7), rs.getObject(8), rs.getObject(9),
                    rs.getObject(10), rs.getObject(11), rs.getObject(12), rs.getObject(13), rs.getObject(14), rs.getObject(15), rs.getObject(16), rs.getObject(17), rs.getObject(18),
                    rs.getObject(19), rs.getObject(20), rs.getObject(21), rs.getObject(22), rs.getObject(23), rs.getObject(24)});
            } while (rs.next());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum Dado retornado !");
        }
    }

    /**
     * * This method is called from within the constructor to initialize the
     * form.* WARNING: Do NOT modify this code. The content of this method is
     * always * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JTextField();
        jspTabela = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        btnCod = new javax.swing.JButton();
        btnDesc = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblIconeBd = new javax.swing.JLabel();
        lblTextoBd = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnUso = new javax.swing.JButton();
        btnSobre = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTabela = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtAnvisa = new javax.swing.JTextField();
        btnAnvisa = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ConDados - Unimed Resende - By Ilton .'.");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Código:");

        txtCod.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Descrição:");

        txtDesc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jspTabela.setViewportView(jScrollPane1);

        btnCod.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCod.setText("Código");
        btnCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodActionPerformed(evt);
            }
        });

        btnDesc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDesc.setText("Descrição");
        btnDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CONDADOS - Consulta Tabela MATERIAL Massa de Dados Total");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/dadostabelas/imagens/LogoTiUnimedResendeMenor.jpg"))); // NOI18N

        lblIconeBd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/dadostabelas/imagens/MiniDbError.png"))); // NOI18N

        lblTextoBd.setText("Falha de conexão com o Banco de Dados !");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("* Para uma consulta mais rápida os resultados estão limitados a 30 linhas, caso necessite de mais comunique ao setor de TI.");

        btnUso.setText("Licença de Uso");
        btnUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoActionPerformed(evt);
            }
        });

        btnSobre.setText("Sobre");
        btnSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSobreActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Tabela de Referência Vigente: TNUMM Março 2017");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Mês de Referência:");

        cboTabela.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vigente", "Anterior", "2xAntes" }));
        cboTabela.setToolTipText("");
        cboTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTabelaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Anvisa:");

        txtAnvisa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAnvisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnvisaActionPerformed(evt);
            }
        });

        btnAnvisa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAnvisa.setText("Anvisa");
        btnAnvisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnvisaActionPerformed(evt);
            }
        });

        jLabel9.setText("Vigente = 03/17");

        jLabel10.setText("Anterior = 01/17");

        jLabel11.setText("2xAntes = 09/16");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Tabela Referência:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Material", "Medicamento" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSobre))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jLabel3)
                                .addGap(34, 34, 34)
                                .addComponent(lblIconeBd)
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel11)))
                                            .addComponent(cboTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCod, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAnvisa, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnDesc)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(18, 18, 18)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnAnvisa)
                                                .addGap(32, 32, 32)
                                                .addComponent(lblTextoBd)))))))
                        .addGap(0, 86, Short.MAX_VALUE))
                    .addComponent(jspTabela))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblIconeBd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTextoBd))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAnvisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addComponent(btnAnvisa)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jspTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSobre)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnUso)
                        .addComponent(jLabel5)))
                .addGap(5, 5, 5))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodActionPerformed
        // TODO add your handling code here:              
        limparClienteJTable();
        consultaCodigo();
    }//GEN-LAST:event_btnCodActionPerformed

    private void btnUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoActionPerformed
        // TODO add your handling code here:
        this.dispose();
        TelaUso uso = new TelaUso();
        uso.setVisible(true);
    }//GEN-LAST:event_btnUsoActionPerformed

    private void btnSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSobreActionPerformed
        // TODO add your handling code here:
        this.dispose();
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_btnSobreActionPerformed

    private void btnDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescActionPerformed
        // TODO add your handling code here:
        limparClienteJTable();
        consultaDescricao();
    }//GEN-LAST:event_btnDescActionPerformed

    private void cboTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTabelaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTabelaActionPerformed

    private void txtAnvisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnvisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnvisaActionPerformed

    private void btnAnvisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnvisaActionPerformed
        // TODO add your handling code here:
        limparClienteJTable();
        consultaAnvisa();
    }//GEN-LAST:event_btnAnvisaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnvisa;
    private javax.swing.JButton btnCod;
    private javax.swing.JButton btnDesc;
    private javax.swing.JButton btnSobre;
    private javax.swing.JButton btnUso;
    private javax.swing.JComboBox<String> cboTabela;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jspTabela;
    private javax.swing.JLabel lblIconeBd;
    private javax.swing.JLabel lblTextoBd;
    private javax.swing.JTextField txtAnvisa;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDesc;
    // End of variables declaration//GEN-END:variables
}
