/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Collections;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
public class Billing extends javax.swing.JFrame {
    Connection conn;
    Statement stm;
    ResultSet rs;
    static double total;
    static int qnt;
    static String articleno;
    static String supplier;
    static String brandname;
    static String eancode;
    static String salespersonusername;
    private final Vector<String> v=new Vector<>();
    private final Vector<String> size=new Vector<>();
    private final Vector<String> color=new Vector<>();
    private JTextField tf;
    private JTextField tf1;
    private JTextField tf2;
    private boolean hide_flag=false;
    private boolean hide_flag1=false;
    private boolean hide_flag2=false;
    /**
     * Creates new form Billing
     */
    public Billing() {
        initComponents();
        connect();
        load();
        autoSuggest();
        //autoSuggest1();
    }
    
    void load(){
        String[] suggest={};
        try{
            String sql = "select articleno,brandname,eancode from stock ";
            rs=stm.executeQuery(sql);
            int cnt=0;
            while(rs.next()){
                v.addElement(rs.getString(1));
                v.addElement(rs.getString(2));
                v.addElement(rs.getString(3));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Something went wrong"+e);
        }
    }
    
    private void setModel(DefaultComboBoxModel mdl,String str){
        jComboBox1.setModel(mdl);
        jComboBox1.setSelectedIndex(-1);
        tf.setText(str);
    }
    
    private void setModel1(DefaultComboBoxModel mdl,String str){
        jComboBox2.setModel(mdl);
        jComboBox2.setSelectedIndex(-1);
        tf1.setText(str);
    }
    
    private void setModel2(DefaultComboBoxModel mdl,String str){
        jComboBox4.setModel(mdl);
        jComboBox4.setSelectedIndex(-1);
        tf2.setText(str);
    }
    
    private static DefaultComboBoxModel getSuggestedModel(List<String> list,String text){
        DefaultComboBoxModel m=new DefaultComboBoxModel();
        for(String s:list){
            if(s.startsWith(text)){
                m.addElement(s);
            }
        }
        return m;  
    }
    
    private static DefaultComboBoxModel getSuggestedModel1(List<String> list,String text){
        DefaultComboBoxModel m=new DefaultComboBoxModel();
        for(String s:list){
            if(s.startsWith(text)){
                m.addElement(s);
            }
        }
        return m;  
    }
    
    private static DefaultComboBoxModel getSuggestedModel2(List<String> list,String text){
        DefaultComboBoxModel m=new DefaultComboBoxModel();
        for(String s:list){
            if(s.startsWith(text)){
                m.addElement(s);
            }
        }
        return m;  
    }
    
    void autoSuggest(){
        jComboBox1.setEditable(true);
        tf=(JTextField) jComboBox1.getEditor().getEditorComponent();
        tf.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        String text=tf.getText();
                        if(text.length()==0){
                            jComboBox1.hidePopup();
                            setModel(new DefaultComboBoxModel(v),text);    
                        }
                        else{
                            DefaultComboBoxModel m=getSuggestedModel(v,text);
                            if(m.getSize()==0||hide_flag){
                                jComboBox1.hidePopup();
                                hide_flag=false;
                            }
                        else{
                                setModel(m,text);
                                jComboBox1.showPopup();
                            }
                        }
                    }
                    
                });
            }

            @Override
            public void keyPressed(KeyEvent e) {
                String text=tf.getText();
                int code=e.getKeyCode();
                if(code==KeyEvent.VK_ENTER){
                    if(v.contains(text)){
                        v.addElement(text);
                        Collections.sort(v);
                        setModel(getSuggestedModel(v,text),text);
                    }
                    hide_flag=true;
                }
                else if(code==KeyEvent.VK_ESCAPE){
                    hide_flag=true;
                }
                else if(code==KeyEvent.VK_RIGHT){
                    for(int i=0;i<v.size();i++){
                        String str=v.elementAt(i);
                        if(str.startsWith(text)){
                            jComboBox1.setSelectedIndex(-1);
                            tf.setText(str);
                            return;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
             
            }
            
        });
    }
    
    void autoSuggest1(){
        jComboBox2.setEditable(true);
        tf1=(JTextField) jComboBox2.getEditor().getEditorComponent();
        tf1.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        String text=tf1.getText();
                        if(text.length()==0){
                            jComboBox2.hidePopup();
                            setModel1(new DefaultComboBoxModel(size),text);    
                        }
                        else{
                            DefaultComboBoxModel m=getSuggestedModel1(size,text);
                            if(m.getSize()==0||hide_flag1){
                                jComboBox2.hidePopup();
                                hide_flag1=false;
                            }
                        else{
                                setModel1(m,text);
                                jComboBox2.showPopup();
                            }
                        }
                    }
                    
                });
            }

            @Override
            public void keyPressed(KeyEvent e) {
                String text=tf1.getText();
                int code=e.getKeyCode();
                if(code==KeyEvent.VK_ENTER){
                    if(size.contains(text)){
                        size.addElement(text);
                        Collections.sort(size);
                        setModel1(getSuggestedModel1(size,text),text);
                    }
                    hide_flag1=true;
                }
                else if(code==KeyEvent.VK_ESCAPE){
                    hide_flag1=true;
                }
                else if(code==KeyEvent.VK_RIGHT){
                    for(int i=0;i<size.size();i++){
                        String str=size.elementAt(i);
                        if(str.startsWith(text)){
                            jComboBox2.setSelectedIndex(-1);
                            tf1.setText(str);
                            return;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
             
            }
            
        });
    }
    
    void autoSuggest2(){
        jComboBox4.setEditable(true);
        tf2=(JTextField) jComboBox4.getEditor().getEditorComponent();
        tf2.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        String text=tf2.getText();
                        if(text.length()==0){
                            jComboBox4.hidePopup();
                            setModel2(new DefaultComboBoxModel(color),text);    
                        }
                        else{
                            DefaultComboBoxModel m=getSuggestedModel2(color,text);
                            if(m.getSize()==0||hide_flag2){
                                jComboBox4.hidePopup();
                                hide_flag2=false;
                            }
                        else{
                                setModel2(m,text);
                                jComboBox4.showPopup();
                            }
                        }
                    }
                    
                });
            }

            @Override
            public void keyPressed(KeyEvent e) {
                String text=tf2.getText();
                int code=e.getKeyCode();
                if(code==KeyEvent.VK_ENTER){
                    if(color.contains(text)){
                        color.addElement(text);
                        Collections.sort(color);
                        setModel2(getSuggestedModel2(color,text),text);
                    }
                    hide_flag2=true;
                }
                else if(code==KeyEvent.VK_ESCAPE){
                    hide_flag2=true;
                }
                else if(code==KeyEvent.VK_RIGHT){
                    for(int i=0;i<color.size();i++){
                        String str=color.elementAt(i);
                        if(str.startsWith(text)){
                            jComboBox4.setSelectedIndex(-1);
                            tf2.setText(str);
                            return;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
             
            }
            
        });
    }
    
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/billing","root","");
        stm=conn.createStatement();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(0, 102, 153));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText(" Billing");
        jLabel9.setOpaque(true);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Invoice Number:");

        jLabel14.setText("Color:");

        jLabel15.setText("Size:");

        jLabel17.setText("MRP:");

        jLabel18.setText("Selling Price:");

        jLabel11.setText("Discount% :");

        jLabel20.setText("Total Amount:");

        jLabel21.setText("Quantity :");

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 153));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save-16.png"))); // NOI18N
        jButton3.setText("SAVE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel24.setText("Salesperson Name :");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Invoice Date:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Article Number/Brand Name/EAN Code:");

        jLabel28.setText("Payment Mode :");

        jComboBox3.setBackground(new java.awt.Color(0, 102, 153));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Card", "Online" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Total Amount :");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(28, 122, 171));

        jLabel29.setBackground(new java.awt.Color(0, 102, 153));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/house-32.png"))); // NOI18N
        jLabel29.setAlignmentY(0.0F);
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel29.setOpaque(true);
        jLabel29.setPreferredSize(new java.awt.Dimension(90, 50));
        jLabel29.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel29);

        jLabel30.setBackground(new java.awt.Color(0, 102, 153));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill-24.png"))); // NOI18N
        jLabel30.setText("Billing");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel30.setOpaque(true);
        jLabel30.setPreferredSize(new java.awt.Dimension(80, 50));
        jLabel30.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel30);

        jLabel31.setBackground(new java.awt.Color(0, 102, 153));
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-user-3-24.png"))); // NOI18N
        jLabel31.setText("Masters");
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel31.setOpaque(true);
        jLabel31.setPreferredSize(new java.awt.Dimension(80, 50));
        jLabel31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel31);

        jLabel32.setBackground(new java.awt.Color(0, 102, 153));
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cart-43-24.png"))); // NOI18N
        jLabel32.setText("Stock Entry");
        jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel32.setOpaque(true);
        jLabel32.setPreferredSize(new java.awt.Dimension(80, 50));
        jLabel32.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel32);

        jLabel33.setBackground(new java.awt.Color(0, 102, 153));
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/return-24.png"))); // NOI18N
        jLabel33.setText("Stock Return");
        jLabel33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel33.setOpaque(true);
        jLabel33.setPreferredSize(new java.awt.Dimension(80, 50));
        jLabel33.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel33);

        jLabel34.setBackground(new java.awt.Color(0, 102, 153));
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/broken-link-24.png"))); // NOI18N
        jLabel34.setText("Damage Entry");
        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel34.setOpaque(true);
        jLabel34.setPreferredSize(new java.awt.Dimension(80, 50));
        jLabel34.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel34);

        jLabel35.setBackground(new java.awt.Color(0, 102, 153));
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/contacts-24.png"))); // NOI18N
        jLabel35.setText("<html> <body> <b>Salesperson<br> &emsp;Access</b> </body> </html>");
        jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel35.setOpaque(true);
        jLabel35.setPreferredSize(new java.awt.Dimension(80, 60));
        jLabel35.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel35);

        jLabel36.setBackground(new java.awt.Color(0, 102, 153));
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/report-2-24.png"))); // NOI18N
        jLabel36.setText("Reports");
        jLabel36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel36.setOpaque(true);
        jLabel36.setPreferredSize(new java.awt.Dimension(90, 50));
        jLabel36.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel36);

        jLabel37.setBackground(new java.awt.Color(0, 102, 153));
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/account-logout-24.png"))); // NOI18N
        jLabel37.setText("Logout");
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel37.setOpaque(true);
        jLabel37.setPreferredSize(new java.awt.Dimension(90, 50));
        jLabel37.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel37);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jLabel1.setText("Rs.");

        jLabel2.setText("Rs.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(61, 61, 61)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel11)
                                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(24, 24, 24)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(61, 61, 61)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel21))
                                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(43, 43, 43)
                .addComponent(jButton3)
                .addGap(45, 45, 45))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            connect();
            String sql1="select max(invoiceno) from bill";
            rs=stm.executeQuery(sql1);
            int max=0;
            while(rs.next()){
                max=rs.getInt(1);
            }
            max++;
            connect();
            String sql = "insert into bill values("+max+",'"+java.time.LocalDate.now()+"','"+articleno+"','"+brandname+"','"+eancode+"','"+tf1.getText()+"','"+tf2.getText()+"',"+Integer.parseInt(jTextField10.getText())+",'"+salespersonusername+"',"+Double.parseDouble(jTextField1.getText())+","+Double.parseDouble(jTextField6.getText())+","+Double.parseDouble(jTextField11.getText())+",'"+jComboBox3.getSelectedItem().toString()+"',"+Double.parseDouble(jLabel19.getText())+")";
            stm.executeUpdate(sql);
            if(Integer.parseInt(jTextField10.getText())<qnt)
                    JOptionPane.showMessageDialog(this,"Stock does not exist");
            else
            {
            connect();
            String sql2="update stock set quantity="+(qnt-Integer.parseInt(jTextField10.getText()))+" where articleno='"+articleno+"' and sz='"+tf1.getText()+"' and color='"+tf2.getText()+"'";
            stm.executeQuery(sql2);
            JOptionPane.showMessageDialog(this,"Bill Saved");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Something went wrong"+e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
        dispose();
        DashBoard db=new DashBoard();
        db.setVisible(true);
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        // TODO add your handling code here:
        dispose();
        Billing bill=new Billing();
        bill.setVisible(true);
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        // TODO add your handling code here:
        dispose();
        Masters ms=new Masters();
        ms.setVisible(true);
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        // TODO add your handling code here:
        dispose();
        StockEntry se=new StockEntry();
        se.setVisible(true);
    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        // TODO add your handling code here:
        dispose();
        StockReturn sr=new StockReturn();
        sr.setVisible(true);
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        // TODO add your handling code here:
        dispose();
        DamageEntry de=new DamageEntry();
        de.setVisible(true);
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        // TODO add your handling code here:
        dispose();
        SalespersonAccess sa=new SalespersonAccess();
        sa.setVisible(true);
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:
        dispose();
        Reports r=new Reports();
        r.setVisible(true);
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:
        dispose();
        LoginFrame lf=new LoginFrame();
        lf.setVisible(true);
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        String mode=(String) jComboBox3.getSelectedItem();
                if(mode.equals("Online")){
                    jLabel19.setText(""+total);
                }
                else if(mode.equals("Card")){
                    jLabel19.setText(""+total);
                }
                else if(mode.equals("Cash")){
                    jLabel19.setText(""+total);
                }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        connect();
        size.clear();
        String search=tf.getText().toString();
        try{
            connect();
            String sql="select sz from stock where articleno='"+search+"'or brandname='"+search+"'or eancode='"+search+"'";
            rs=stm.executeQuery(sql);
            while(rs.next()){
                //color.addElement(rs.getString(2));
                size.addElement(rs.getString(1));
                /*jTextField4.setText(""+rs.getString(3));
                jTextField3.setText(""+rs.getString(2));
                jTextField10.setText(""+rs.getInt(4));
                jTextField1.setText(""+rs.getBigDecimal(8));
                jTextField6.setText(""+rs.getBigDecimal(9));
                jTextField11.setText(""+rs.getBigDecimal(10));
                total=Double.parseDouble(""+rs.getBigDecimal(9));
                jLabel38.setText("Rs. "+total);
                supplier=rs.getString(11);
                connect();
                String sql2="select salespersonusername from masters where suppliername='"+supplier+"'";
                //JOptionPane.showMessageDialog(this,"salesperson");
                rs=stm.executeQuery(sql2);
                while(rs.next()){
                    salespersonusername=rs.getString(1);
                    jTextField13.setText(""+salespersonusername);
                    //JOptionPane.showMessageDialog(this,"salesperson");
                }
                */
            }
            //JOptionPane.showMessageDialog(this,size);
            autoSuggest1();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Something went wrong");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try{
            String sql1="select max(invoiceno) from bill";
            rs=stm.executeQuery(sql1);
            int max=0;
            while(rs.next()){
                max=rs.getInt(1);
            }
            max++;
            jLabel25.setText("B00"+max);
            jLabel27.setText(""+java.time.LocalDate.now());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Something went wrong");
        }
    }//GEN-LAST:event_formWindowOpened

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here
        connect();
        color.clear();
        String search=tf.getText().toString();
        try{
            connect();
            String sql="select color from stock where articleno='"+search+"'or brandname='"+search+"'or eancode='"+search+"' and sz='"+tf1.getText().toString()+"'";
            rs=stm.executeQuery(sql);
            while(rs.next()){
                //color.addElement(rs.getString(2));
                color.addElement(rs.getString(1));
            }
            //JOptionPane.showMessageDialog(this,size);
            autoSuggest2();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Something went wrong");
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        connect();
        String search=tf.getText().toString();
        try{
            connect();
            String sql="select * from stock where articleno='"+search+"'or brandname='"+search+"'or eancode='"+search+"' and sz='"+tf1.getText().toString()+"' and color='"+tf2.getText().toString()+"'";
            rs=stm.executeQuery(sql);
            while(rs.next()){
                //jTextField3.setText(""+rs.getString(2));
                jTextField10.setText(""+rs.getInt(4));
                qnt=Integer.parseInt(jTextField10.getText());
                if(Integer.parseInt(jTextField10.getText())<5)
                    jTextField10.setBorder(new LineBorder(Color.red,2));
                jTextField1.setText(""+rs.getBigDecimal(8));
                jTextField6.setText(""+rs.getBigDecimal(9));
                jTextField11.setText(""+rs.getBigDecimal(10));
                total=Double.parseDouble(""+rs.getBigDecimal(9));
                jLabel38.setText(""+total);
                eancode=rs.getString(5);
                articleno=rs.getString(3);
                brandname=rs.getString(4);
                supplier=rs.getString(11);
                connect();
                String sql2="select salespersonusername from masters where brandname='"+brandname+"' and color='"+tf2.getText()+"' and sz='"+tf1.getText()+"'";
                //JOptionPane.showMessageDialog(this,"salesperson");
                rs=stm.executeQuery(sql2);
                while(rs.next()){
                    salespersonusername=rs.getString(1);
                    jTextField13.setText(""+salespersonusername);
                    JOptionPane.showMessageDialog(this,"salesperson");
                }
            }
            //JOptionPane.showMessageDialog(this,size);
            autoSuggest1();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Something went wrong");
        }
    }//GEN-LAST:event_jComboBox4ItemStateChanged
   
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
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Billing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Billing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
