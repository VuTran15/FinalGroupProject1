package final_group_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Transactions extends Bank{
    private JFrame f;
    private JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,err, err1;
    private LinkedList<String> transactionHistory;

    private JPanel p1, p2, p3;

    private JTextField j1, j2;



    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    
    
    
    public Transactions(double total, double USDollar, double austoralianDollar, double canadianDollar, double yen, double euro, double peso, double poundSterling, double dong, double interestRate) {
        super(total, USDollar, austoralianDollar, canadianDollar, yen, euro, peso, poundSterling, dong, interestRate);
        transactionHistory = new LinkedList<>();
        // create JFrame Object
        f = new JFrame("BANK");

        // create panels
        p1 = new JPanel();
        p2 = new JPanel();

        // add data to labels
        l1 = new JLabel("Total: " + total);
        l2 = new JLabel("USDollar: " + USDollar);
        l3 = new JLabel("Australian Dollar: " + austoralianDollar);
        l4 = new JLabel("Canadian Dollar: " + canadianDollar);
        l5 = new JLabel("Yen: " + yen);
        l6 = new JLabel("Euro: " + euro);
        l7 = new JLabel( "Peso: " + peso);
        l8 = new JLabel("Pound Sterling: " + poundSterling);
        l9 = new JLabel("Dong" + dong);
        l10 = new JLabel("Interest Rate: " + interestRate);
        err = new JLabel();

    }

    @Override
    public void calculateInterest(int year) {
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        double interest = this.getTotal() * (this.getInterestRate()/100) * (thisYear - year);
    }

    @Override
    public void deposit(double money) {
        // add money to total available in bank
        this.setTotal(this.getTotal() + money);
        // record transaction
        transactionHistory.add("Deposit of " + money + ". Current total: " + this.getTotal());

        // update labels
        updateBank();

        // show items
        showBank();
    }

    @Override
    public void withdraw(double money) {
        // subtract money from total available in bank
        this.setTotal(this.getTotal() - money);
        // record transaction
        transactionHistory.add("Withdrawal of " + money + ". Current total: " + this.getTotal());

        // update labels
        updateBank();

        // show items
        showBank();
    }

    @Override
    public void showTotal() {
        // show total on GUI
        l1.setText(String.valueOf(this.getTotal()));
        err.setText("Total: " + this.getTotal());
        err.setBounds(50, 600, 300, 30);
        f.revalidate();
        f.repaint();
    }

    @Override
    public void record() {
        // write transactions to file
        try {
            FileWriter myWriter = new FileWriter("transactions.txt");
            for (int i=0; i<transactionHistory.size(); i++)
                myWriter.write(transactionHistory.get(i) + "\n");
            myWriter.close();

            err.setText("File written successfully");
            err.setBounds(50, 600, 300, 30);
            f.revalidate();
            f.repaint();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void description() {
        // read from file
        try {
            File myObj = new File("transactions.txt");
            Scanner myReader = new Scanner(myObj);

            p3.removeAll();
            int count = 0;
            while (myReader.hasNextLine()) {
                p3.add(new JLabel(myReader.nextLine()));
                count++;
            }

            myReader.close();

            p3.setBounds(50, 620, 450, 40 * count);
            f.revalidate();
            f.repaint();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            err.setText("Error reading file");
            err.setBounds(50, 600, 300, 30);
            f.revalidate();
            f.repaint();
        }
    }

    @Override
    public void transitFromOtherBank(double money, double balance, int bank) {
        this.setTotal(money - balance);
    }

    @Override
    public void transitToOtherBank(double money, int bank, Bank bank2) {
        bank2.setTotal(money);
    }

    @Override
    public int compareTo(Bank bank2) {
        if(this.getTotal() == bank2.getTotal())
            return 0;
        else if(this.getTotal() > bank2.getTotal())
            return 1;
        else
            return -1;
    }

    @Override
    public void GUI() {
        // Default method for closing the frame
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        // button to show bank info
        JButton showBtn = new JButton("SHOW BANK");
        showBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show if panel is empty
//                p1 = new JPanel();
                showBank();
            }
        });

        showBtn.setBounds(50, 20, 150, 30);
        p2.setBounds(50, 50, 700, 500);





        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel1.setText("From");

        jLabel2.setText("To");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  "USDollar", "euro", "yen", "australianDollar", "peso", "dong", "canadianDollar", "poundSterling"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USDollar", "euro", "yen", "australianDollar", "peso", "dong", "canadianDollar", "poundSterling" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setText("Convert");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Enter Amount");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(f.getContentPane());
        f.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(71, 71, 71)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 26, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(89, Short.MAX_VALUE))
        );
        

        p2.add(jLabel1);
        p2.add(jLabel2);
        p2.add(jComboBox1);
        p2.add(jComboBox2);
        p2.add(jTextField1);
        p2.add(jTextField2);
        p2.add(jButton1);
        p2.add(jLabel3);



        
        
        // create withdrawal jtesxtfield and jbutton
        j1 = new JTextField("Enter amount");
        JButton withdrawBtn = new JButton("WITHDRAW");
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get amount to withdraw
                String s1 = j1.getText();

                // convert to double. catch exception if input is not a number
                double amount = 0;
                try {
                    amount = Double.parseDouble(s1);
                    withdraw(amount);
                } catch (Exception exception){
                    err.setText("Invalid input for amount");
                    err.setBounds(50, 600, 300, 30);
                    f.revalidate();
                    f.repaint();
                }
            }
        });
        p2.add(j1);
        p2.add(withdrawBtn);

        // create deposit jtesxtfield and jbutton
        j2 = new JTextField("Enter amount");
        JButton depositBtn = new JButton("DEPOSIT");
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get amount to withdraw
                String s1 = j2.getText();

                // convert to double. catch exception if input is not a number
                double amount = 0;
                try {
                    amount = Double.parseDouble(s1);
                    deposit(amount);
                } catch (Exception exception){
                    err.setText("Invalid input for amount");
                    err.setBounds(50, 600, 200, 30);
                    f.revalidate();
                    f.repaint();
                }
            }
        });
        p2.add(j2);
        p2.add(depositBtn);

        // add write to file button
        JButton fileWriter = new JButton("WRITE TRANSACTIONS");
        fileWriter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!transactionHistory.isEmpty())
                    record();
                else{
                    err.setText("No transactions yet");
                    err.setBounds(50, 600, 300, 30);
                    f.revalidate();
                    f.repaint();
                }
            }
        });
        p2.add(new JLabel("Write to file"));
        p2.add(fileWriter);

        // read from file button
        JButton fileReader = new JButton("READ TRANSACTIONS");
        fileReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                description();
            }
        });
        p2.add(new JLabel("Read from file"));
        p2.add(fileReader);

        // convert random btn
        JButton convertBtn = new JButton("CONVERT RANDOM");
        convertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertRandomToDong();
            }
        });

        p2.add(new JLabel("Convert Random Currency into VietNamDong"));
        p2.add(convertBtn);


//        JButton convertBtn1 = new JButton("CONVERT RANDOM");
//        convertBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                convertRandomToEuro();
//            }
//        });
//
//        p2.add(new JLabel("Convert Random Currency into Euro"));
//        p2.add(convertBtn1);

        p2.setLayout(new GridLayout(10, 2));

        // Adding the created objects
        f.add(showBtn);
        f.add(p1);
        f.add(p2);
        p3 = new JPanel();
        f.add(p3);
        f.add(err);

        f.setLayout(null);
        f.setSize(600, 700);
        f.setVisible(true);
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MoneyConverter c = new MoneyConverter();
        String from_bank = String.valueOf(jComboBox1.getSelectedItem());
        String to_bank = String.valueOf(jComboBox2.getSelectedItem());
        String from_text = jTextField1.getText();
        double input_amount = Double.parseDouble(from_text);
        double output = c.transit(input_amount, from_bank, to_bank);
        String amount = String.valueOf(output);
        jTextField2.setText(amount);
    }//GEN-LAST:event_jButton1ActionPerformed
    private void showBank() {
        p2.setBounds(50, 250, 500, 300);
        p1.setBounds(50, 50, 300, 200);


        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        p1.add(l7);
        p1.add(l8);
        p1.add(l9);
        p1.add(l10);

        p1.setLayout(new GridLayout(5, 3));
        f.revalidate();
        f.repaint();
    }

    private void updateBank(){

        l1.setText("Total: " + this.getTotal());
        l2.setText("USDollar: " + this.getUSDollar());
        l3.setText("Australian Dollar: " + this.getAustoralianDollar());
        l4.setText("Canadian Dollar: " + this.getCanadianDollar());
        l5.setText("Yen: " + this.getYen());
        l6.setText("Euro: " + this.getEuro());
        l7.setText("Peso: " + this.getPeso());
        l8.setText("Pound Sterling: " + this.getPoundSterling());
        l9.setText("Dong" + this.getDong());
        l10.setText("Interest Rate: " + this.getInterestRate());
    }

    private void convertRandomToDong() {
        // put currencies in hashmap
        HashMap<String, Double> currencies = new HashMap<>();
        currencies.put("USDollar", this.getUSDollar());
        currencies.put("Australian Dollar", this.getAustoralianDollar());
        currencies.put("Canadian Dollar", this.getCanadianDollar());
        currencies.put("Yen", this.getYen());
        currencies.put("Euro", this.getEuro());
        currencies.put("Peso", this.getPeso());
        currencies.put("Pound Sterling", this.getPoundSterling());
        currencies.put("Dong", this.getDong());

        // get random number from 0 to 9
        int randomInt = new Random().nextInt(9);

        // convert random currency
        double interestRate = 0;
        int count = 0;
        for (String i : currencies.keySet()) {
            if (count == randomInt) {
                switch (i) {
                    case "USDollar":
                        interestRate += 24842.50;
                        break;
                    case "Australian Dollar":
                        interestRate += 16394.68;
                        break;
                    case "Canadian Dollar":
                        interestRate += 18479.47;
                        break;
                    case "Yen":
                        interestRate += 174.79;
                        break;
                    case "Euro":
                        interestRate += 25424.19;
                        break;
                    case "Peso":
                        interestRate += 432.82;
                        break;
                    case "Pound Sterling":
                        interestRate += 29355.02;
                        break;
                    case "Dong":
                        interestRate += 1;
                        break;
                }
                CurrencyConverter currencyConverter = new CurrencyConverter();
                double converted = currencyConverter.converter(currencies.get(i), interestRate);
                err.setBounds(50, 600, 300, 30);
                err.setText(i + ": " + currencies.get(i) + " Converted to Dong: " + converted);
                f.revalidate();
                f.repaint();
            }
            count++;
        }
    }
//        private void convertRandomToEuro() {
//            // put currencies in hashmap
//            HashMap<String, Double> currencies1 = new HashMap<>();
//            currencies1.put("USDollar", this.getUSDollar());
//            currencies1.put("Australian Dollar", this.getAustoralianDollar());
//            currencies1.put("Canadian Dollar", this.getCanadianDollar());
//            currencies1.put("Yen", this.getYen());
//            currencies1.put("Euro", this.getEuro());
//            currencies1.put("Peso", this.getPeso());
//            currencies1.put("Pound Sterling", this.getPoundSterling());
//            currencies1.put("Dong", this.getDong());
//
//            // get random number from 0 to 9
//            int randomInt1 = new Random().nextInt(9);
//
//            // convert random currency
//            double interestRate1 = 0;
//            int count1 = 0;
//            for (String j:currencies1.keySet()){
//                if (count1 == randomInt1){
//                    switch(j)
//                    {
//                        case "USDollar":
//                            interestRate1 += 0.98;
//                            break;
//                        case "Australian Dollar":
//                            interestRate1 += 0.64;
//                            break;
//                        case "Canadian Dollar":
//                            interestRate1 += 0.73;
//                            break;
//                        case "Yen":
//                            interestRate1 += 0.0069;
//                            break;
//                        case "Euro":
//                            interestRate1 += 1;
//                            break;
//                        case "Peso":
//                            interestRate1 += 0.017;
//                            break;
//                        case "Pound Sterling":
//                            interestRate1 += 1.15;
//                            break;
//                        case "Dong":
//                            interestRate1 += 0.000039;
//                            break;
//                    }
//                    CurrencyConverter currencyConverter1 = new CurrencyConverter();
//                    double converted1 = currencyConverter1.converter(currencies1.get(j), interestRate1);
//                    err1.setBounds(55, 650, 350, 50);
//                    err1.setText( j + ": " + currencies1.get(j) + " Converted to Euro: " + converted1 );
//                    f.revalidate();
//                    f.repaint();
//                }
//                count1++;
//            }


    private class MoneyConverter {
        //    US
        public double US_Euro_converter(double money)
        {
            return money*0.98;
        }
        public double US_Yen_converter(double money)
        {
            return money*142.08;
        }
        public double US_AustralianDollar_converter(double money)
        {
            return money*1.51;
        }
        public double US_Peso_converter(double money)
        {
            return money*57.39;
        }
        public double US_Dong_converter(double money)
        {
            return money*24842.50;
        }
        public double US_CanadianDollar_converter(double money)
        {
            return money*1.34;
        }
        public double US_PoundSterling_converter(double money)
        {
            return money*0.85;
        }



        //    Euro
        public double Euro_US_converter(double money)
        {
            return money*1.02;
        }
        public double Euro_Yen_converter(double money)
        {
            return money*145.47;
        }
        public double Euro_AustralianDollar_converter(double money)
        {
            return money*1.55;
        }
        public double Euro_Peso_converter(double money)
        {
            return money*58.70;
        }
        public double Euro_Dong_converter(double money)
        {
            return money*25424.19;
        }
        public double Euro_CanadianDollar_converter(double money)
        {
            return money*1.38;
        }
        public double Euro_PoundSterling_converter(double money)
        {
            return money*0.87;
        }



        //    Yen
        public double Yen_US_converter(double money)
        {
            return money*0.0070;
        }
        public double Yen_Euro_converter(double money)
        {
            return money*0.0069;
        }
        public double Yen_AustralianDollar_converter(double money)
        {
            return money*0.011;
        }
        public double Yen_Peso_converter(double money)
        {
            return money*0.40;
        }
        public double Yen_Dong_converter(double money)
        {
            return money*174.79;
        }
        public double Yen_CanadianDollar_converter(double money)
        {
            return money*0.0095;
        }
        public double Yen_PoundSterling_converter(double money)
        {
            return money*0.0060;
        }


        //    Australian Dollar
        public double AustralianDollar_US_converter(double money)
        {
            return money*0.66;
        }
        public double AustralianDollar_Euro_converter(double money)
        {
            return money*0.64;
        }
        public double AustralianDollar_Yen_converter(double money)
        {
            return money*93.82;
        }
        public double AustralianDollar_Peso_converter(double money)
        {
            return money*37.89;
        }
        public double AustralianDollar_Dong_converter(double money)
        {
            return money*16394.68;
        }
        public double AustralianDollar_CanadianDollar_converter(double money)
        {
            return money*0.89;
        }
        public double AustralianDollar_PoundSterling_converter(double money)
        {
            return money*0.56;
        }


        //    Peso
        public double Peso_US_converter(double money)
        {
            return money*0.051;
        }
        public double Peso_Euro_converter(double money)
        {
            return money*0.017;
        }
        public double Peso_Yen_converter(double money)
        {
            return money*2.48;
        }
        public double Peso_AustralianDollar_converter(double money)
        {
            return money*0.026;
        }
        public double Peso_Dong_converter(double money)
        {
            return money*432.82;
        }
        public double Peso_CanadianDollar_converter(double money)
        {
            return money*0.023;
        }
        public double Peso_PoundSterling_converter(double money)
        {
            return money*0.015;
        }



        //    Dong
        public double Dong_US_converter(double money)
        {
            return money*0.000040;
        }
        public double Dong_Euro_converter(double money)
        {
            return money*0.000039;
        }
        public double Dong_Yen_converter(double money)
        {
            return money*0.0057;
        }
        public double Dong_AustralianDollar_converter(double money)
        {
            return money*0.000061;
        }
        public double Dong_Peso_converter(double money)
        {
            return money*0.0023;
        }
        public double Dong_CanadianDollar_converter(double money)
        {
            return money*0.000054;
        }
        public double Dong_PoundSterling_converter(double money)
        {
            return money*0.000034;
        }


        //    Canadian Dollar
        public double CanadianDollar_US_converter(double money)
        {
            return money*0.74;
        }
        public double CanadianDollar_Euro_converter(double money)
        {
            return money*0.73;
        }
        public double CanadianDollar_Yen_converter(double money)
        {
            return money*105.71;
        }
        public double CanadianDollar_AustralianDollar_converter(double money)
        {
            return money*1.13;
        }
        public double CanadianDollar_Peso_converter(double money)
        {
            return money*42.69;
        }
        public double CanadianDollar_Dong_converter(double money)
        {
            return money*18479.47;
        }
        public double CanadianDollar_PoundSterling_converter(double money)
        {
            return money*0.63;
        }


        //    Pound Sterling
        public double PoundSterling_US_converter(double money)
        {
            return money*1.18;
        }
        public double PoundSterling_Euro_converter(double money)
        {
            return money*1.15;
        }
        public double PoundSterling_Yen_converter(double money)
        {
            return money*167.95;
        }
        public double PoundSterling_AustralianDollar_converter(double money)
        {
            return money*1.79;
        }
        public double PoundSterling_Peso_converter(double money)
        {
            return money*67.83;
        }
        public double PoundSterling_Dong_converter(double money)
        {
            return money*29355.02;
        }
        public double PoundSterling_CanadianDollar_converter(double money)
        {
            return money*1.59;
        }




        public double transit(double money, String from_bank, String to_bank)
        {
            double converted_amount =  money;
            if(from_bank == "USDollar")
            {
                if(to_bank == "euro")
                {
                    converted_amount = US_Euro_converter(money);
                }
                else if(to_bank == "yen")
                {
                    converted_amount = US_Yen_converter(money);
                }
                else if(to_bank == "australianDollar")
                {
                    converted_amount = US_AustralianDollar_converter(money);
                }
                else if(to_bank == "peso")
                {
                    converted_amount = US_Peso_converter(money);
                }
                else if(to_bank == "dong")
                {
                    converted_amount = US_Dong_converter(money);
                }
                else if(to_bank == "canadianDollar")
                {
                    converted_amount = US_CanadianDollar_converter(money);
                }
                else if(to_bank == "poundSterling")
                {
                    converted_amount = US_PoundSterling_converter(money);
                }

            }
            else if(from_bank == "euro")
            {
                if(to_bank == "USDollar")
                {
                    converted_amount = Euro_US_converter(money);
                }
                else if(to_bank == "yen")
                {
                    converted_amount = Euro_Yen_converter(money);
                }
                else if(to_bank == "australianDollar")
                {
                    converted_amount = Euro_AustralianDollar_converter(money);
                }
                else if(to_bank == "peso")
                {
                    converted_amount = Euro_Peso_converter(money);
                }
                else if(to_bank == "dong")
                {
                    converted_amount = Euro_Dong_converter(money);
                }
                else if(to_bank == "canadianDollar")
                {
                    converted_amount = Euro_CanadianDollar_converter(money);
                }
                else if(to_bank == "poundSterling")
                {
                    converted_amount = Euro_PoundSterling_converter(money);
                }
            }
            else if(from_bank == "yen")
            {
                if(to_bank == "USDollar")
                {
                    converted_amount = Yen_US_converter(money);
                }
                else if(to_bank == "euro")
                {
                    converted_amount = Yen_Euro_converter(money);
                }
                else if(to_bank == "australianDollar")
                {
                    converted_amount = Yen_AustralianDollar_converter(money);
                }
                else if(to_bank == "peso")
                {
                    converted_amount = Yen_Peso_converter(money);
                }
                else if(to_bank == "dong")
                {
                    converted_amount = Yen_Dong_converter(money);
                }
                else if(to_bank == "canadianDollar")
                {
                    converted_amount = Yen_CanadianDollar_converter(money);
                }
                else if(to_bank == "poundSterling")
                {
                    converted_amount = Yen_PoundSterling_converter(money);
                }
            }
            else if(from_bank == "australianDollar")
            {
                if(to_bank == "USDollar")
                {
                    converted_amount = AustralianDollar_US_converter(money);
                }
                else if(to_bank == "euro")
                {
                    converted_amount = AustralianDollar_Euro_converter(money);
                }
                else if(to_bank == "yen")
                {
                    converted_amount = AustralianDollar_Yen_converter(money);
                }
                else if(to_bank == "peso")
                {
                    converted_amount = AustralianDollar_Peso_converter(money);
                }
                else if(to_bank == "dong")
                {
                    converted_amount = AustralianDollar_Dong_converter(money);
                }
                else if(to_bank == "canadianDollar")
                {
                    converted_amount = AustralianDollar_CanadianDollar_converter(money);
                }
                else if(to_bank == "poundSterling")
                {
                    converted_amount = AustralianDollar_PoundSterling_converter(money);
                }
            }
            else if(from_bank == "peso")
            {
                if(to_bank == "USDollar")
                {
                    converted_amount = Peso_US_converter(money);
                }
                else if(to_bank == "euro")
                {
                    converted_amount = Peso_Euro_converter(money);
                }
                else if(to_bank == "yen")
                {
                    converted_amount = Peso_Yen_converter(money);
                }
                else if(to_bank == "australianDollar")
                {
                    converted_amount = Peso_AustralianDollar_converter(money);
                }
                else if(to_bank == "dong")
                {
                    converted_amount = Peso_Dong_converter(money);
                }
                else if(to_bank == "canadianDollar")
                {
                    converted_amount = Peso_CanadianDollar_converter(money);
                }
                else if(to_bank == "poundSterling")
                {
                    converted_amount = Peso_PoundSterling_converter(money);
                }
            }
            else if(from_bank == "dong")
            {
                if(to_bank == "USDollar")
                {
                    converted_amount = Dong_US_converter(money);
                }
                else if(to_bank == "euro")
                {
                    converted_amount = Dong_Euro_converter(money);
                }
                else if(to_bank == "yen")
                {
                    converted_amount = Dong_Yen_converter(money);
                }
                else if(to_bank == "australianDollar")
                {
                    converted_amount = Dong_AustralianDollar_converter(money);
                }
                else if(to_bank == "peso")
                {
                    converted_amount = Dong_Peso_converter(money);
                }
                else if(to_bank == "canadianDollar")
                {
                    converted_amount = Dong_CanadianDollar_converter(money);
                }
                else if(to_bank == "poundSterling")
                {
                    converted_amount = Dong_PoundSterling_converter(money);
                }
            }
            else if(from_bank == "canadianDollar")
            {
                if(to_bank == "USDollar")
                {
                    converted_amount = CanadianDollar_US_converter(money);
                }
                else if(to_bank == "euro")
                {
                    converted_amount = CanadianDollar_Euro_converter(money);
                }
                else if(to_bank == "yen")
                {
                    converted_amount = CanadianDollar_Yen_converter(money);
                }
                else if(to_bank == "australianDollar")
                {
                    converted_amount = CanadianDollar_AustralianDollar_converter(money);
                }
                else if(to_bank == "peso")
                {
                    converted_amount = CanadianDollar_Peso_converter(money);
                }
                else if(to_bank == "dong")
                {
                    converted_amount = CanadianDollar_Dong_converter(money);
                }
                else if(to_bank == "poundSterling")
                {
                    converted_amount = CanadianDollar_PoundSterling_converter(money);
                }
            }
            else if(from_bank == "poundSterling")
            {
                if(to_bank == "USDollar")
                {
                    converted_amount = PoundSterling_US_converter(money);
                }
                else if(to_bank == "euro")
                {
                    converted_amount = PoundSterling_Euro_converter(money);
                }
                else if(to_bank == "yen")
                {
                    converted_amount = PoundSterling_Yen_converter(money);
                }
                else if(to_bank == "australianDollar")
                {
                    converted_amount = PoundSterling_AustralianDollar_converter(money);
                }
                else if(to_bank == "peso")
                {
                    converted_amount = PoundSterling_Peso_converter(money);
                }
                else if(to_bank == "dong")
                {
                    converted_amount = PoundSterling_Dong_converter(money);
                }
                else if(to_bank == "canadianDollar")
                {
                    converted_amount = PoundSterling_CanadianDollar_converter(money);
                }

            }
            return converted_amount;
        }
    }
}
