package GUI;

import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

public class SatoshiTURK extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    int startIndex;
    int endIndex;

    public SatoshiTURK() {
        initComponents();
        setupTable();
        setupListeners();
        setupThemeChangeComboBox();

        // İkon yükleme
        java.net.URL url = ClassLoader.getSystemResource("resources/images/satoshiturk.png");
        if (url != null) {
            Toolkit kit = Toolkit.getDefaultToolkit();
            Image img = kit.createImage(url);
            setIconImage(img);
        } else {
            System.out.println("İkon dosyası bulunamadı.");
        }

        if (isInternetAvailable()) {
            JOptionPane.showMessageDialog(null, "Internet connection detected! Please disconnect your internet connection for full security.");
        } else {
            bakiyeAktif.setEnabled(false);
        }

        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // Aktif sekmenin index'ini al
                int selectedIndex = jTabbedPane1.getSelectedIndex();

                // İndex'e göre işlem yap
                System.out.println("Seçilen sekme index'i: " + selectedIndex);

            }
        });

    }

    private void setupThemeChangeComboBox() {
        themechange.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) evt.getItem();
                    updateLookAndFeel(selectedItem);
                }
            }
        });
    }

    private void updateLookAndFeel(String theme) {
        try {
            switch (theme) {
                case "Theme1":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                    break;
                case "Theme2":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                    break;
                case "Theme3":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                    break;
                case "Theme4":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                    break;
                case "Theme5":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                    break;
                case "Theme6":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                    break;
                case "Theme7":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                    break;
                case "Theme8":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                    break;
                case "Theme9":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
                    break;
                case "Theme10":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                    break;
                case "Theme11":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                    break;
                case "Theme12":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                    break;
                case "Theme13":
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                    break;

            }
            SwingUtilities.updateComponentTreeUI(this);
            this.pack(); // Eğer boyut değişiklikleri olursa, formu yeniden boyutlandır
        } catch (Exception e) {
            e.printStackTrace();
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        bakiyeAktif = new javax.swing.JToggleButton();
        textField1 = new javax.swing.JTextField();
        rpcurl = new javax.swing.JTextField();
        startInd = new javax.swing.JTextField();
        endInd = new javax.swing.JTextField();
        derivePath = new javax.swing.JTextField();
        passphrase = new javax.swing.JTextField();
        generateButton = new javax.swing.JButton();
        seedButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        from = new javax.swing.JTextField();
        to = new javax.swing.JTextField();
        ether = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        sign = new javax.swing.JTextArea();
        generateSing = new javax.swing.JButton();
        network = new javax.swing.JTextField();
        privatekey = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        dec = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        aboutbilgi = new javax.swing.JTextArea();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        themechange = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        lchange = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/tr"); // NOI18N
        setTitle(bundle.getString("app.title")); // NOI18N
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        bakiyeAktif.setText(bundle.getString("bakiyegoster")); // NOI18N
        bakiyeAktif.setToolTipText(bundle.getString("bakiyegosterbilgi")); // NOI18N

        textField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textField1.setText(bundle.getString("seedword")); // NOI18N
        textField1.setToolTipText(bundle.getString("seedbilgi")); // NOI18N

        rpcurl.setText("https://bsc-dataseed.binance.org/");
        rpcurl.setToolTipText(bundle.getString("rpcbilgi")); // NOI18N

        startInd.setText(bundle.getString("startbilgi")); // NOI18N
        startInd.setToolTipText(bundle.getString("startbilgi")); // NOI18N

        endInd.setText("5");
        endInd.setToolTipText(bundle.getString("endbilgi")); // NOI18N

        derivePath.setText("m/44'/60'/0'/0");
        derivePath.setToolTipText(bundle.getString("derivepathbilgi")); // NOI18N

        passphrase.setToolTipText(bundle.getString("passwordbilgi")); // NOI18N

        generateButton.setText(bundle.getString("adresuret")); // NOI18N
        generateButton.setToolTipText(bundle.getString("adresuretbilgi")); // NOI18N
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        seedButton.setText(bundle.getString("seedcreatebutton")); // NOI18N
        seedButton.setToolTipText(bundle.getString("seedbuttonbilgi")); // NOI18N
        seedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seedButtonActionPerformed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(derivePath, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(startInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(endInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(passphrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 279, Short.MAX_VALUE))
                            .addComponent(rpcurl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(seedButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(bakiyeAktif, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bakiyeAktif, endInd, generateButton, passphrase, seedButton, startInd});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seedButton)
                    .addComponent(textField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rpcurl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generateButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endInd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(derivePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passphrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bakiyeAktif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jTabbedPane1.addTab(bundle.getString("wallet"), jPanel1); // NOI18N

        from.setText(bundle.getString("fromaddress")); // NOI18N
        from.setToolTipText(bundle.getString("fromadresbilgi")); // NOI18N

        to.setText(bundle.getString("toaddress")); // NOI18N
        to.setToolTipText(bundle.getString("toaddressbilgi")); // NOI18N

        ether.setText(bundle.getString("miktar")); // NOI18N
        ether.setToolTipText(bundle.getString("miktarbilgi")); // NOI18N

        sign.setColumns(20);
        sign.setLineWrap(true);
        sign.setRows(5);
        sign.setWrapStyleWord(true);
        jScrollPane3.setViewportView(sign);

        generateSing.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        generateSing.setText(bundle.getString("imzaolustur")); // NOI18N
        generateSing.setToolTipText(bundle.getString("imzaolusturbilgi")); // NOI18N
        generateSing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateSingActionPerformed(evt);
            }
        });

        network.setText("https://bsc-dataseed.binance.org/");
        network.setToolTipText(bundle.getString("imzarocurlbilgi")); // NOI18N

        privatekey.setText(bundle.getString("bualanapivgirin")); // NOI18N
        privatekey.setToolTipText(bundle.getString("privbilgi")); // NOI18N

        jLabel1.setText(bundle.getString("toaddresslabel")); // NOI18N

        jLabel2.setText(bundle.getString("fromaddresslabel")); // NOI18N

        jLabel3.setText(bundle.getString("miktarlabel")); // NOI18N

        dec.setEditable(false);
        dec.setColumns(20);
        dec.setLineWrap(true);
        dec.setRows(5);
        dec.setWrapStyleWord(true);
        jScrollPane4.setViewportView(dec);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(network)
                    .addComponent(privatekey)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ether, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(from, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(generateSing, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ether, from, to});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(network, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(privatekey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ether, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(generateSing, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("signcreated"), jPanel3); // NOI18N

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText(bundle.getString("imzagonderaciklamasi")); // NOI18N
        jTextArea2.setWrapStyleWord(true);
        jScrollPane5.setViewportView(jTextArea2);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setText(bundle.getString("imzagondermeuygulamasiac")); // NOI18N
        jButton1.setToolTipText("");
        jButton1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("borderdesc"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 51, 0))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(397, 397, 397))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );

        jTabbedPane1.addTab(bundle.getString("singsender"), jPanel4); // NOI18N

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(bundle.getString("onemliuyari")); // NOI18N
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("onemliborderuyari"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 0, 0))); // NOI18N
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(bundle.getString("warning"), jPanel2); // NOI18N

        aboutbilgi.setEditable(false);
        aboutbilgi.setColumns(20);
        aboutbilgi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        aboutbilgi.setLineWrap(true);
        aboutbilgi.setRows(5);
        aboutbilgi.setText(bundle.getString("aboutbilgi")); // NOI18N
        aboutbilgi.setWrapStyleWord(true);
        jScrollPane6.setViewportView(aboutbilgi);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(bundle.getString("about"), jPanel5); // NOI18N

        jButton7.setText("Sohbet");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Coin Earn");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        themechange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theme1", "Theme2", "Theme3", "Theme4", "Theme5", "Theme6", "Theme7", "Theme8", "Theme9", "Theme10", "Theme11", "Theme12", "Theme13" }));

        jButton9.setText("Forum");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        lchange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EN", "TR", "DE", "JP", "RU" }));
        lchange.setToolTipText(bundle.getString("applang")); // NOI18N
        lchange.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lchangeİtemStateChanged(evt);
            }
        });

        jButton2.setText("SatoshiTURK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Telegram");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Youtube");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Instagram");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Twitter");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(themechange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lchange, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lchange, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(themechange, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName(bundle.getString("wallet")); // NOI18N

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        generateAddresses();
    }//GEN-LAST:event_generateButtonActionPerformed

    private void seedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seedButtonActionPerformed
        generateAddresses();
    }//GEN-LAST:event_seedButtonActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        System.out.println("asdasd");
    }//GEN-LAST:event_jPanel4MouseClicked

    private void generateSingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateSingActionPerformed
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Web3j nesnesi ve kimlik bilgileri oluştur
                    Web3j web3j = Web3j.build(new HttpService(network.getText()));
                    Credentials credentials = Credentials.create(privatekey.getText());

                    // Anlık gas fiyatını ve kullanıcının nonce değerini al
                    EthGasPrice gasPriceResponse = web3j.ethGasPrice().send();
                    BigInteger gasPrice = gasPriceResponse.getGasPrice();
                    EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(
                            credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
                    BigInteger nonce = transactionCount.getTransactionCount();

                    // Gas limitini ve Ether değerini al
                    BigInteger gasLimit = BigInteger.valueOf(21000); // Standart gas limiti
                    BigInteger value = Convert.toWei(ether.getText(), Convert.Unit.ETHER).toBigInteger();

                    // Raw işlemi oluştur
                    RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
                            nonce, gasPrice, gasLimit, to.getText(), value);

                    // İşlemi imzala ve hex değerini al
                    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
                    String hexValue = Numeric.toHexString(signedMessage);
                    sign.setText(hexValue); // İmzalı işlemi göster

                    // Display transaction details
                    String transactionDetails = "Sender Address: " + credentials.getAddress()
                            + "\nRecipient Address: " + to.getText()
                            + "\nAmount Sent (Ether): " + ether.getText()
                            + "\nNonce: " + nonce.toString()
                            + "\nGas Limit: " + gasLimit.toString()
                            + "\nGas Price: " + gasPrice.toString();
                    dec.setText(transactionDetails);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error in numerical values: " + e.getMessage());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error during network connection or Web3j processing: " + e.getMessage());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
                }

            }
        });
        t.start();
    }//GEN-LAST:event_generateSingActionPerformed

    private void lchangeİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lchangeİtemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String selectedLanguage = (String) lchange.getSelectedItem();
            System.out.println(selectedLanguage);
            switch (selectedLanguage) {
                case "TR":
                    changeLanguage("en");
                    break;
                case "EN":
                    changeLanguage("tr");
                    break;
                case "DE":
                    changeLanguage("de");
                    break;
                case "JP":
                    changeLanguage("jp");
                    break;
                case "RU":
                    changeLanguage("ru");
                    break;
            }
        }
    }//GEN-LAST:event_lchangeİtemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new SenderSign().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        generateAddresses();
    }//GEN-LAST:event_formWindowOpened

    public void siteac(String urls) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI url = new URI(urls); // Açmak istediğiniz URL
            desktop.browse(url);
        } catch (IOException | URISyntaxException e) {
        }
    }


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        siteac("https://satoshiturk.com");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        siteac("https://t.me/satoshiturk");

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        siteac("https://www.youtube.com/@satoshiturk");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        siteac("https://www.twitter.com/satoshiturk_");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        siteac("https://www.instagram.com/satoshiturk");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        siteac("https://satoshiturk.com/kripto/bitcoin/sohbet");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        siteac("https://satoshiturk.com/koin-forumu/");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        siteac("https://satoshiturk.com/koin-forumu/threads/satoshiturk-yeni-kazanc-sistemi.21843/");
    }//GEN-LAST:event_jButton8ActionPerformed

    public void changeLanguage(String languageCode) {
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/" + languageCode);

        setTitle(bundle.getString("app.title"));

        bakiyeAktif.setText(bundle.getString("bakiyegoster"));
        aboutbilgi.setText(bundle.getString("aboutbilgi"));
        jLabel1.setText(bundle.getString("toaddresslabel"));
        jLabel2.setText(bundle.getString("fromaddresslabel"));
        jLabel3.setText(bundle.getString("miktarlabel"));
        textField1.setText(bundle.getString("seedword"));
        textField1.setToolTipText(bundle.getString("seedbilgi"));
        bakiyeAktif.setToolTipText(bundle.getString("bakiyegosterbilgi"));
        textField1.setToolTipText(bundle.getString("seedbilgi"));
        rpcurl.setToolTipText(bundle.getString("rpcbilgi"));
        startInd.setToolTipText(bundle.getString("startbilgi"));
        endInd.setToolTipText(bundle.getString("endbilgi"));
        derivePath.setToolTipText(bundle.getString("derivepathbilgi"));
        passphrase.setToolTipText(bundle.getString("passwordbilgi"));
        generateButton.setText(bundle.getString("adresuret"));
        generateButton.setToolTipText(bundle.getString("adresuretbilgi"));
        seedButton.setText(bundle.getString("seedcreatebutton"));
        seedButton.setToolTipText(bundle.getString("seedbuttonbilgi"));
        from.setText(bundle.getString("fromaddress"));
        from.setToolTipText(bundle.getString("fromadresbilgi"));
        to.setText(bundle.getString("toaddress"));
        to.setToolTipText(bundle.getString("toaddressbilgi"));
        ether.setText(bundle.getString("miktar"));
        ether.setToolTipText(bundle.getString("miktarbilgi"));
        generateSing.setText(bundle.getString("imzaolustur"));
        generateSing.setToolTipText(bundle.getString("imzaolusturbilgi"));
        network.setToolTipText(bundle.getString("imzarocurlbilgi"));
        privatekey.setText(bundle.getString("bualanapivgirin"));
        privatekey.setToolTipText(bundle.getString("privbilgi"));
        jTextArea2.setText(bundle.getString("imzagonderaciklamasi"));
        jButton1.setText(bundle.getString("imzagondermeuygulamasiac"));
        jButton1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("borderdesc"),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 0, 12),
                new java.awt.Color(255, 51, 0)));
        jTextArea1.setText(bundle.getString("onemliuyari"));
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("onemliborderuyari"),
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 0, 12),
                new java.awt.Color(255, 0, 0)));

        // Tab başlıklarını güncelle
        jTabbedPane1.setTitleAt(0, bundle.getString("wallet"));
        jTabbedPane1.setTitleAt(1, bundle.getString("signcreated"));
        jTabbedPane1.setTitleAt(2, bundle.getString("singsender"));
        jTabbedPane1.setTitleAt(3, bundle.getString("warning"));
        jTabbedPane1.setTitleAt(4, bundle.getString("about"));

    }

    private void setupTable() {
        tableModel = new DefaultTableModel(new String[]{"Index", "Address", "Balance", "Private Key"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hiçbir sütun düzenlenebilir olmamalı
            }
        };
        table1.setModel(tableModel);
        setupTableColumns();

        // MouseListener ekleme
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table1.rowAtPoint(evt.getPoint());
                int col = table1.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 3) { // 3. sütun (Private Key) kontrolü
                    String privateKey = (String) tableModel.getValueAt(row, col);
                    StringSelection selection = new StringSelection(privateKey);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
                    JOptionPane.showMessageDialog(null, "Privatekey copied!");
                }

                int rows = table1.rowAtPoint(evt.getPoint());
                int cols = table1.columnAtPoint(evt.getPoint());
                if (rows >= 0 && cols == 1) { // 1. sütun (Adres) kontrolü
                    String address = (String) tableModel.getValueAt(rows, cols);
                    StringSelection selection = new StringSelection(address);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
                    JOptionPane.showMessageDialog(null, "Address copied!");
                }
            }

        });
    }

    private void setupTableColumns() {
        TableColumn column;
        for (int i = 0; i < 4; i++) {
            column = table1.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(50); // Balance sütunu
                    column.setMinWidth(50); // Balance sütununun minimum genişliği
                    column.setMaxWidth(50); // Balance sütununun maksimum genişliği
                    break;
                case 1:
                    column.setPreferredWidth(100); // Address sütunu
                    break;
                case 2:
                    column.setPreferredWidth(50); // Balance sütunu
                    column.setMinWidth(50); // Balance sütununun minimum genişliği
                    column.setMaxWidth(50); // Balance sütununun maksimum genişliği
                    break;
                case 3:
                    column.setPreferredWidth(200); // Private Key sütunu
                    break;
                default:
                    break;
            }
        }
    }

    private void setupListeners() {
        generateButton.addActionListener(e -> generateAddresses());
        seedButton.addActionListener(e -> {
            try {
                generateSeed();
            } catch (MnemonicException.MnemonicLengthException ex) {
                throw new RuntimeException(ex);
            }
        });

        DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                generateAddresses();
            }

            public void removeUpdate(DocumentEvent e) {
                generateAddresses();
            }

            public void insertUpdate(DocumentEvent e) {
                generateAddresses();
            }
        };

        textField1.getDocument().addDocumentListener(documentListener);
        rpcurl.getDocument().addDocumentListener(documentListener);
        startInd.getDocument().addDocumentListener(documentListener);
        endInd.getDocument().addDocumentListener(documentListener);
        derivePath.getDocument().addDocumentListener(documentListener);
        passphrase.getDocument().addDocumentListener(documentListener);
    }

    private void generateAddresses() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    startIndex = Integer.parseInt(startInd.getText());
                    endIndex = Integer.parseInt(endInd.getText());

                    if (bakiyeAktif.isSelected()) {
                        // En fazla 25 adres kontrolü
                        if (endIndex - startIndex > 25) {
                            JOptionPane.showMessageDialog(SatoshiTURK.this,
                                    "You can create up to 25 addresses when Balance Inquiry is active.",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);

                            return;
                        }
                    }

                    String seedCode = textField1.getText().trim();
                    List<String> mnemonicCode;

                    if (seedCode.isEmpty()) {
                        SecureRandom random = new SecureRandom();
                        mnemonicCode = MnemonicCode.INSTANCE.toMnemonic(random.generateSeed(16));
                        seedCode = String.join(" ", mnemonicCode);
                        textField1.setText(seedCode);
                    } else {
                        mnemonicCode = Arrays.asList(seedCode.split(" "));
                    }

                    String customPath = derivePath.getText().trim();
                    if (customPath.isEmpty()) {
                        customPath = "m/44'/60'/0'/0"; // Varsayılan derive path
                    }
                    List<ChildNumber> path = createPath(customPath);

                    String customPassphrase = passphrase.getText();

                    long creationTimeSeconds = System.currentTimeMillis() / 1000;
                    DeterministicSeed seed = new DeterministicSeed(mnemonicCode, null, customPassphrase, creationTimeSeconds);
                    DeterministicKeyChain chain = DeterministicKeyChain.builder().seed(seed).build();

                    tableModel.setRowCount(0);

                    Web3j web3 = null;
                    if (bakiyeAktif.isSelected()) {
                        web3 = Web3j.build(new HttpService(rpcurl.getText()));
                    }

                    for (int i = startIndex; i <= endIndex; i++) {
                        DeterministicKey key = chain.getKeyByPath(HDUtils.append(path, new ChildNumber(i)), true);
                        byte[] privateKeyBytes = key.getPrivKeyBytes();
                        ECKeyPair keyPair = ECKeyPair.create(privateKeyBytes);
                        String address = "0x" + Keys.getAddress(keyPair);
                        String privateKey = "0x" + keyPair.getPrivateKey().toString(16);

                        BigDecimal balanceInEther = BigDecimal.ZERO;
                        if (web3 != null) {
                            BigInteger balance = getBalance(web3, address);
                            balanceInEther = weiToEther(balance);
                        }

                        // Private Key'i tabloya ekle
                        tableModel.addRow(new Object[]{i, address, balanceInEther.toString(), privateKey});
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(SatoshiTURK.this,
                            "Start and end indexes must be numeric.",
                            "Invalid Entry",
                            JOptionPane.ERROR_MESSAGE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void generateSeed() throws MnemonicException.MnemonicLengthException {
        SecureRandom random = new SecureRandom();
        List<String> mnemonicCode = MnemonicCode.INSTANCE.toMnemonic(random.generateSeed(16));
        String seedCode = String.join(" ", mnemonicCode);
        textField1.setText(seedCode);
    }

    private BigInteger getBalance(Web3j web3, String address) {
        try {
            EthGetBalance ethGetBalance = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get();
            return ethGetBalance.getBalance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Could not establish a connection with the RPC URL.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);

            return BigInteger.ZERO;
        }
    }

    private BigDecimal weiToEther(BigInteger wei) {
        BigDecimal etherValue = new BigDecimal(wei).divide(new BigDecimal("1000000000000000000"), 18, RoundingMode.DOWN);

        // Eğer sonuç sıfır ise, sadece "0" döndür
        if (etherValue.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        // Ondalık kısmı maksimum 8 basamakla sınırla
        return etherValue.setScale(8, RoundingMode.DOWN);
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("google.com");
            return address.isReachable(5000);  // 5000 milisaniye timeout
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<ChildNumber> createPath(String path) {
        List<ChildNumber> pathList = new ArrayList<>();
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.matches("m") || part.isEmpty()) {
                continue; // 'm' ve boş kısımları atla
            }
            boolean isHardened = part.endsWith("'");
            int index = Integer.parseInt(isHardened ? part.substring(0, part.length() - 1) : part);
            pathList.add(new ChildNumber(index, isHardened));
        }
        return pathList;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            // Varsayılan locale'yi İngilizce olarak ayarla
            Locale.setDefault(new Locale("en", "US"));

            // JTattoo Look and Feel tema ayarı
            UIManager.setLookAndFeel(new McWinLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SatoshiTURK.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SatoshiTURK().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextArea aboutbilgi;
    javax.swing.JToggleButton bakiyeAktif;
    javax.swing.JTextArea dec;
    javax.swing.JTextField derivePath;
    javax.swing.JTextField endInd;
    javax.swing.JTextField ether;
    javax.swing.JTextField from;
    javax.swing.JButton generateButton;
    javax.swing.JButton generateSing;
    javax.swing.JButton jButton1;
    javax.swing.JButton jButton2;
    javax.swing.JButton jButton3;
    javax.swing.JButton jButton4;
    javax.swing.JButton jButton5;
    javax.swing.JButton jButton6;
    javax.swing.JButton jButton7;
    javax.swing.JButton jButton8;
    javax.swing.JButton jButton9;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel2;
    javax.swing.JPanel jPanel3;
    javax.swing.JPanel jPanel4;
    javax.swing.JPanel jPanel5;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JScrollPane jScrollPane3;
    javax.swing.JScrollPane jScrollPane4;
    javax.swing.JScrollPane jScrollPane5;
    javax.swing.JScrollPane jScrollPane6;
    javax.swing.JTabbedPane jTabbedPane1;
    javax.swing.JTextArea jTextArea1;
    javax.swing.JTextArea jTextArea2;
    javax.swing.JComboBox<String> lchange;
    javax.swing.JTextField network;
    javax.swing.JTextField passphrase;
    javax.swing.JTextField privatekey;
    javax.swing.JTextField rpcurl;
    javax.swing.JButton seedButton;
    javax.swing.JTextArea sign;
    javax.swing.JTextField startInd;
    javax.swing.JTable table1;
    javax.swing.JTextField textField1;
    javax.swing.JComboBox<String> themechange;
    javax.swing.JTextField to;
    // End of variables declaration//GEN-END:variables
}
