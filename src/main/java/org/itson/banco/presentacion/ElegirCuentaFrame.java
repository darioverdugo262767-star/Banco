package org.itson.banco.presentacion;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.itson.banco.entidades.Cliente;
import org.itson.banco.entidades.Cuenta;
import org.itson.banco.negocio.IClientesBO;
import org.itson.banco.negocio.ICuentasBO;
import org.itson.banco.negocio.NegocioException;

/**
 * Ventana de selección de cuentas bancarias.
 * Permite al cliente visualizar en una tabla todas las cuentas asociadas a su 
 * perfil que se encuentran en estado 'ACTIVA'. Desde aquí se puede navegar 
 * hacia la gestión individual de una cuenta, dar de alta una nueva o 
 * realizar la desactivación (baja lógica) de una existente.
 * @author Dario
 */
public class ElegirCuentaFrame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuCuentaFrame.class.getName());
    private final Cliente clienteLogueado;
    private final ICuentasBO cuentasBO;
    private final IClientesBO clientesBO;

    /**
     * Inicializa la ventana cargando la información del cliente y 
     * poblando la tabla de cuentas desde la base de datos.
     * @param cliente Cliente que inició sesión.
     * @param cuentasBO Negocio de cuentas.
     * @param clientesBO Negocio de clientes.
     */
    public ElegirCuentaFrame(Cliente cliente, ICuentasBO cuentasBO, IClientesBO clientesBO) {
        this.clienteLogueado = cliente;
        this.cuentasBO = cuentasBO;
        this.clientesBO = clientesBO;
        initComponents();
        this.lblNombreCliente.setText(clienteLogueado.getNombres() + " "
                + clienteLogueado.getApellidoPaterno()+ " " + 
                clienteLogueado.getApellidoMaterno());
        llenarTablaCuentas();
    }
    
    /**
     * Consulta la base de datos y llena el JTable con los datos de las cuentas.
     * Utiliza un DefaultTableModel para gestionar las filas dinámicamente.
     */
    private void llenarTablaCuentas() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
            modelo.setRowCount(0); // Limpiar tabla antes de llenar

            List<Cuenta> listaCuentas = cuentasBO.consultarCuentasPorCliente(clienteLogueado.getId());

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");

            for (Cuenta cuenta : listaCuentas) {
                Object[] fila = {
                    cuenta.getNumeroCuenta(),
                    "$" + cuenta.getSaldo(),
                    sdf.format(cuenta.getFechaApertura().getTime())
                };
                modelo.addRow(fila);
            }
        } catch (NegocioException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al cargar las cuentas bancarias.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Obtiene la cuenta seleccionada en la tabla y navega hacia el menú 
     * detallado de dicha cuenta.
     */
    private void avanzar(){
        int filaSeleccionada = tbl.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione una cuenta de la tabla.");
            return;
        }

        // Obtener el ID/Número de cuenta de la primera columna
        String numCuenta = tbl.getValueAt(filaSeleccionada, 0).toString();

        MenuCuentaFrame siguientePantalla = new MenuCuentaFrame(this.clienteLogueado, this.cuentasBO, numCuenta, this.clientesBO);
        siguientePantalla.setVisible(true);
        this.dispose();
    }
    
    /**
     * Realiza una baja lógica de la cuenta seleccionada previa confirmación 
     * del usuario. Una vez desactivada, la cuenta ya no aparecerá en la tabla.
     */
    private void baja(){
        int filaSeleccionada = tbl.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, seleccione la cuenta que desea dar de baja.");
            return;
        }

        String numCuenta = tbl.getValueAt(filaSeleccionada, 0).toString();

        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea dar de baja la cuenta: " + numCuenta + "?\nEsta acción no se puede deshacer.", 
                "Confirmar Baja de Cuenta", 
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE);

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            try {
                this.cuentasBO.desactivarCuenta(numCuenta);
                javax.swing.JOptionPane.showMessageDialog(this, "La cuenta ha sido dada de baja con éxito.");
                this.llenarTablaCuentas(); // Refrescar vista
            } catch (NegocioException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al procesar la baja: " + ex.getMessage());
            }
        }
    }
    
    /**
     * Navega a la pantalla de creación de una nueva cuenta.
     */
    public void alta(){
        AltaCuentaFrame altaFrame = new AltaCuentaFrame(this.clienteLogueado, this.cuentasBO, this.clientesBO);
        altaFrame.setVisible(true);
        this.dispose();
    }
    
    /**
     * Cierra la sesión del cliente actual, libera los recursos de la ventana 
     * y redirige al usuario a la pantalla de inicio de sesión.
     */
    private void logout() {
        // 1. Confirmación opcional
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro que desea cerrar sesión?", 
            "Cerrar Sesión", 
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            // 2. Instanciar la lógica de negocio necesaria para el Login
            // Si ya tienes un objeto clientesBO en esta clase, úsalo. 
            // Si no, crea la instancia necesaria.

            LoginFrame login = new LoginFrame(this.clientesBO);
            login.setVisible(true);

            this.dispose();
            
            logger.info("El usuario ha cerrado sesión correctamente.");
        }
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
        pnlTable = new javax.swing.JPanel();
        scrTabla = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        btnLogout = new javax.swing.JButton();
        lblNombreCliente = new javax.swing.JLabel();
        btnAvanzar = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPrincipal.setBackground(new java.awt.Color(249, 244, 244));

        lbl1.setFont(new java.awt.Font("Ebrima", 1, 36)); // NOI18N
        lbl1.setText("Selector de Cuentas Bancarias");

        pnlTable.setBackground(new java.awt.Color(0, 0, 0));

        scrTabla.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        tbl.setFont(new java.awt.Font("Ebrima", 0, 24)); // NOI18N
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Numero de Cuenta", "Saldo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.setRowHeight(40);
        scrTabla.setViewportView(tbl);

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrTabla)
                .addContainerGap())
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnLogout.setBackground(new java.awt.Color(255, 51, 51));
        btnLogout.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        lblNombreCliente.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        lblNombreCliente.setText("Nombre del Cliente");

        btnAvanzar.setBackground(new java.awt.Color(204, 159, 243));
        btnAvanzar.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        btnAvanzar.setText("Avanzar");
        btnAvanzar.addActionListener(this::btnAvanzarActionPerformed);

        btnBaja.setBackground(new java.awt.Color(204, 159, 243));
        btnBaja.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBaja.setText("Dar de baja cuenta");
        btnBaja.addActionListener(this::btnBajaActionPerformed);

        btnAlta.setBackground(new java.awt.Color(204, 159, 243));
        btnAlta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAlta.setText("Dar de alta cuenta");
        btnAlta.addActionListener(this::btnAltaActionPerformed);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(lblNombreCliente))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnLogout)
                                .addGap(72, 72, 72)
                                .addComponent(lbl1)))
                        .addGap(0, 145, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(btnAvanzar)
                        .addGap(343, 343, 343))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAlta)
                            .addComponent(btnBaja))
                        .addGap(327, 327, 327))))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl1)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnLogout)))
                .addGap(33, 33, 33)
                .addComponent(lblNombreCliente)
                .addGap(18, 18, 18)
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAvanzar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnBaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnAlta)
                .addContainerGap())
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

    private void btnAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarActionPerformed
        avanzar();
    }//GEN-LAST:event_btnAvanzarActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        baja();
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        alta();
    }//GEN-LAST:event_btnAltaActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        logout();
    }//GEN-LAST:event_btnLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnAvanzar;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JScrollPane scrTabla;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
