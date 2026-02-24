package org.itson.banco.presentacion;

import java.math.BigDecimal;
import javax.swing.JOptionPane;
import org.itson.banco.dtos.NuevaTransferenciaDTO;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.entidades.Transferencia;
import org.itson.banco.negocio.IClientesBO;
import org.itson.banco.negocio.ICuentasBO;
import org.itson.banco.negocio.ITransferenciasBO;
import org.itson.banco.negocio.NegocioException;
import org.itson.banco.negocio.TransferenciasBO;
import org.itson.banco.persistencia.CuentasDAO;
import org.itson.banco.persistencia.TransferenciasDAO;

/**
 * Pantalla de confirmación para transferencias bancarias.
 * Esta interfaz muestra un resumen de la operación (emisor, destinatario y monto)
 * y solicita la autorización final del usuario. Al confirmar, delega la 
 * ejecución a la capa de negocio (BO), asegurando que se cumplan todas las 
 * reglas bancarias antes de persistir la transacción.
 * @author Dario
 */
public class ConfirmacionTransferenciaFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ConfirmacionTransferenciaFrame.class.getName());
    private Cliente clienteLogueado;
    private final ICuentasBO cuentasBO;
    private final IClientesBO clientesBO;
    private String numCuenta;
    private String numCuentaDestino;
    private BigDecimal monto;

   /**
     * Constructor que inicializa la vista con los datos de la transferencia pendiente.
     * Carga la información contextual de la operación y actualiza los componentes 
     * visuales (etiquetas) para que el usuario pueda validar los datos antes 
     * de proceder.
     * @param clienteLogueado El titular de la cuenta que mantiene la sesión activa.
     * @param cuentasBO Implementación de la lógica de negocio para ejecutar la transferencia.
     * @param numCuentaOrigen Identificador de la cuenta de cargo (emisor).
     * @param numCuentaDestino Identificador de la cuenta de abono (destinatario).
     * @param monto Cantidad económica sujeta a la transacción.
     * @param clientesBO Implementación de la lógica de negocio para la gestión de clientes.
     */
    public ConfirmacionTransferenciaFrame(Cliente clienteLogueado, ICuentasBO cuentasBO, String numCuentaOrigen, String numCuentaDestino, BigDecimal monto, IClientesBO clientesBO) {
        initComponents();
        this.clienteLogueado = clienteLogueado;
        this.cuentasBO = cuentasBO;
        this.numCuenta = numCuentaOrigen;
        this.numCuentaDestino = numCuentaDestino;
        this.monto = monto;
        this.clientesBO = clientesBO;
        
        // Actualización de etiquetas visuales para revisión del usuario
        this.lblEmisor.setText(numCuentaOrigen);
        this.lblDestinatario.setText(numCuentaDestino);
        this.lblMonto.setText("$ " + monto.toString());
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Procesa la confirmación de la transferencia.
     * Crea el DTO necesario y lo envía al objeto de negocio  TransferenciasBO.
     * Si la operación es exitosa, redirige al usuario a la pantalla de resumen (ticket).
     * En caso de error de negocio (saldo insuficiente, etc.), muestra un mensaje informativo.
     */
    public void confirmar() {
        try {
            // 1. Inyección de dependencias manual para el BO
            ITransferenciasBO transferenciaBO = new TransferenciasBO(new TransferenciasDAO(), new CuentasDAO());

            BigDecimal montoConvertido = new BigDecimal(String.valueOf(this.monto));
            
            // 2. Preparación del DTO (Data Transfer Object)
            NuevaTransferenciaDTO dto = new NuevaTransferenciaDTO(
                    this.numCuenta,
                    this.numCuentaDestino,
                    montoConvertido
            );

            // 3. Ejecución de la lógica de negocio (aquí se aplican validaciones y transacciones SQL)
            Transferencia transferenciaResultante = transferenciaBO.crearTransferencia(dto, this.clienteLogueado);

            JOptionPane.showMessageDialog(this, "Transferencia realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // 4. Transición al resumen final de la operación
            ResumenTransferenciaFrame resumen = new ResumenTransferenciaFrame(this.clienteLogueado, transferenciaResultante, this.cuentasBO, this.clientesBO);
            resumen.setVisible(true);
            this.dispose();

        } catch (NegocioException e) {
            // Manejo de errores controlados (reglas de negocio fallidas)
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Negocio", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Manejo de errores técnicos inesperados
            logger.severe("Error inesperado en confirmación: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error Técnico", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Cancela la confirmación actual y regresa a la pantalla de ingreso de monto.
     */
    public void regresar(){
        IngresarSaldoTransferenciaFrame anterior = new IngresarSaldoTransferenciaFrame(
        this.clienteLogueado, this.cuentasBO, this.numCuenta, this.numCuentaDestino, this.clientesBO);
        anterior.setVisible(true);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lblEmisor = new javax.swing.JLabel();
        lblDestinatario = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPrincipal.setBackground(new java.awt.Color(249, 244, 244));

        lbl1.setFont(new java.awt.Font("Ebrima", 1, 36)); // NOI18N
        lbl1.setText("Informacion de Transferencia");

        lbl3.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        lbl3.setText("Numero de cuenta Destinatario:");

        lbl4.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        lbl4.setText("Monto a transferir:");

        lbl2.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        lbl2.setText("Numero de cuenta Emisor: ");

        lblEmisor.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        lblEmisor.setText("Aqui se pondra el numero");

        lblDestinatario.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        lblDestinatario.setText("Aqui se pondra el numero");

        lblMonto.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        lblMonto.setText("Aqui se pondra el monto");

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoLayout.createSequentialGroup()
                        .addComponent(lbl4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMonto))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoLayout.createSequentialGroup()
                        .addComponent(lbl3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(lblDestinatario))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoLayout.createSequentialGroup()
                        .addComponent(lbl2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEmisor)))
                .addGap(32, 32, 32))
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmisor)
                    .addComponent(lbl2))
                .addGap(80, 80, 80)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3)
                    .addComponent(lblDestinatario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4)
                    .addComponent(lblMonto))
                .addGap(72, 72, 72))
        );

        btnRegresar.setBackground(new java.awt.Color(217, 217, 217));
        btnRegresar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        btnRegresar.setText("<-");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(204, 159, 243));
        btnConfirmar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnConfirmar.setText("Confirmar Transferencia");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnRegresar)
                        .addGap(61, 61, 61)
                        .addComponent(lbl1))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnConfirmar)
                .addGap(281, 281, 281))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(lbl1))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnRegresar)))
                .addGap(18, 18, 18)
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmar)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        regresar();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        confirmar();
    }//GEN-LAST:event_btnConfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lblDestinatario;
    private javax.swing.JLabel lblEmisor;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
