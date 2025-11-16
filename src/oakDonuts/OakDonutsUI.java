package oakDonuts;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OakDonutsUI extends JFrame {

    // LEFT – menu & filters
    private JList<MenuItems> menuList;
    private DefaultListModel<MenuItems> menuListModel;
    private JComboBox<String> categoryCombo;
    private JTextField searchField;
    private JComboBox<String> icingCombo;
    private JComboBox<String> fillingCombo;
    private JSpinner qtySpinner;
    private JLabel unitPriceLabel;

    // RIGHT – order table
    private JTable orderTable;
    private DefaultTableModel orderModel;

    // totals
    private JLabel subtotalLabel;
    private JLabel taxLabel;
    private JLabel totalLabel;

    // cart data
    private List<CartItem> cartLines = new ArrayList<>();
    private static final double TAX_RATE = 0.06;

    public OakDonutsUI() {
        PastelTheme.apply();  // use your pastel UI
        initUI();
        loadMenuItems();
    }

    private void initUI() {
        setTitle("Oak Donuts – Ordering");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JLabel title = new JLabel("Oak Donuts");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 0));
        add(title, BorderLayout.NORTH);

        // ---------------- LEFT SIDE ----------------
        JPanel leftRoot = new JPanel(new BorderLayout(5, 5));
        leftRoot.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
        add(leftRoot, BorderLayout.WEST);

        JPanel topLeft = new JPanel();
        topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS));

        // Filters
        JPanel filtersPanel = new JPanel(new GridBagLayout());
        filtersPanel.setBorder(new TitledBorder("Filters"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0;
        filtersPanel.add(new JLabel("Category:"), c);

        c.gridx = 1;
        categoryCombo = new JComboBox<>(new String[]{"All"});
        categoryCombo.setPreferredSize(new Dimension(140, 24));
        filtersPanel.add(categoryCombo, c);

        c.gridx = 0; c.gridy = 1;
        filtersPanel.add(new JLabel("Search:"), c);

        c.gridx = 1;
        searchField = new JTextField(14);
        filtersPanel.add(searchField, c);

        topLeft.add(filtersPanel);

        // Options Panel
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBorder(new TitledBorder("Item Options"));
        GridBagConstraints o = new GridBagConstraints();
        o.insets = new Insets(3, 3, 3, 3);
        o.anchor = GridBagConstraints.WEST;

        o.gridx = 0; o.gridy = 0;
        optionsPanel.add(new JLabel("Icing:"), o);

        o.gridx = 1;
        icingCombo = new JComboBox<>(new String[]{"None", "Chocolate", "Vanilla", "Strawberry"});
        icingCombo.setPreferredSize(new Dimension(140, 24));
        optionsPanel.add(icingCombo, o);

        o.gridx = 0; o.gridy = 1;
        optionsPanel.add(new JLabel("Filling:"), o);

        o.gridx = 1;
        fillingCombo = new JComboBox<>(new String[]{"None", "Custard", "Jelly", "Cream"});
        fillingCombo.setPreferredSize(new Dimension(140, 24));
        optionsPanel.add(fillingCombo, o);

        topLeft.add(Box.createVerticalStrut(5));
        topLeft.add(optionsPanel);

        leftRoot.add(topLeft, BorderLayout.NORTH);

        // Menu List
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBorder(new TitledBorder("Menu"));
        menuListModel = new DefaultListModel<>();
        menuList = new JList<>(menuListModel);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.addListSelectionListener(e -> updateUnitPriceLabel());

        menuPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);
        leftRoot.add(menuPanel, BorderLayout.CENTER);

        // Bottom Left Controls
        JPanel bottomLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        bottomLeft.add(new JLabel("Qty:"));

        qtySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        qtySpinner.setPreferredSize(new Dimension(50, 24));
        bottomLeft.add(qtySpinner);

        unitPriceLabel = new JLabel("Unit: $0.00");
        bottomLeft.add(unitPriceLabel);

        PastelButton addBtn = new PastelButton("Add to Order");
        addBtn.addActionListener(e -> onAddToOrder());
        bottomLeft.add(addBtn);

        leftRoot.add(bottomLeft, BorderLayout.SOUTH);

        // ---------------- RIGHT SIDE ----------------
        JPanel rightRoot = new JPanel(new BorderLayout(5, 5));
        rightRoot.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
        add(rightRoot, BorderLayout.CENTER);

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBorder(new TitledBorder("Order"));

        String[] cols = {"Item", "Options", "Qty", "Price", "Total"};
        orderModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        orderTable = new JTable(orderModel);
        orderPanel.add(new JScrollPane(orderTable), BorderLayout.CENTER);

        rightRoot.add(orderPanel, BorderLayout.CENTER);

        // Totals + Buttons
        JPanel bottomRight = new JPanel(new BorderLayout());
        JPanel totalsPanel = new JPanel(new GridLayout(3, 2));

        totalsPanel.add(new JLabel("Subtotal:"));
        subtotalLabel = new JLabel("$0.00");
        totalsPanel.add(subtotalLabel);

        totalsPanel.add(new JLabel("Tax (6%):"));
        taxLabel = new JLabel("$0.00");
        totalsPanel.add(taxLabel);

        totalsPanel.add(new JLabel("Total:"));
        totalLabel = new JLabel("$0.00");
        totalsPanel.add(totalLabel);

        bottomRight.add(totalsPanel, BorderLayout.WEST);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        PastelButton clearBtn = new PastelButton("Clear");
        clearBtn.addActionListener(e -> clearOrder());
        PastelButton checkoutBtn = new PastelButton("Checkout");
        checkoutBtn.addActionListener(e -> checkout());
        PastelButton historyBtn = new PastelButton("History");
        historyBtn.addActionListener(e -> new OrderHistoryWindow().setVisible(true));

        buttonsPanel.add(clearBtn);
        buttonsPanel.add(historyBtn);
        buttonsPanel.add(checkoutBtn);

        bottomRight.add(buttonsPanel, BorderLayout.EAST);
        rightRoot.add(bottomRight, BorderLayout.SOUTH);
    }

    // Load menu items
    private void loadMenuItems() {
        try {
            MenuDAO dao = new MenuDAO();
            List<MenuItems> items = dao.getAllMenuItems();
            menuListModel.clear();
            menuListModel.addAll(items);
            if (!items.isEmpty()) menuList.setSelectedIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUnitPriceLabel() {
        MenuItems sel = menuList.getSelectedValue();
        if (sel != null) {
            unitPriceLabel.setText(String.format("Unit: $%.2f", sel.getPrice()));
        }
    }

    // Add item to order
    private void onAddToOrder() {
        MenuItems item = menuList.getSelectedValue();
        if (item == null) {
            JOptionPane.showMessageDialog(this, "Select a menu item first.");
            return;
        }

        int qty = (Integer) qtySpinner.getValue();
        if (qty <= 0) qty = 1;

        String icing = (String) icingCombo.getSelectedItem();
        String filling = (String) fillingCombo.getSelectedItem();

        String optionsText = ("None".equals(icing) && "None".equals(filling)) ?
                "-" : "Icing: " + icing + ", Filling: " + filling;

        double price = item.getPrice();
        double lineTotal = price * qty;

        // Create CartItem with itemId
        CartItem cartItem =
                new CartItem(item.getItemId(), item.getName(), item.getPrice());

        for (int i = 1; i < qty; i++) cartItem.increaseQty();
        cartLines.add(cartItem);

        orderModel.addRow(new Object[]{
                item.getName(),
                optionsText,
                qty,
                String.format("$%.2f", price),
                String.format("$%.2f", lineTotal)
        });

        updateTotals();
    }

    // Clear order
    private void clearOrder() {
        cartLines.clear();
        orderModel.setRowCount(0);
        updateTotals();
    }

    private void updateTotals() {
        double subtotal = 0;
        for (CartItem c : cartLines) subtotal += c.getTotal();

        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", total));
    }

    private void checkout() {
        if (cartLines.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your order is empty.");
            return;
        }

        double subtotal = 0;
        for (CartItem c : cartLines) subtotal += c.getTotal();
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        OrderDAO dao = new OrderDAO();
        int orderId = dao.saveOrder(cartLines, subtotal, tax, total);

        JOptionPane.showMessageDialog(this,
                "Order Complete!\nOrder ID: #" + orderId +
                        String.format("\nTotal: $%.2f", total),
                "Checkout Complete", JOptionPane.INFORMATION_MESSAGE);

        clearOrder();
    }
}