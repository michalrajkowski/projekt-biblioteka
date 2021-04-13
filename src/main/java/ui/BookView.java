package main.java.ui;

import main.java.model.Book;
import main.java.model.dto.BookDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class BookView extends JPanel implements ListCellRenderer<BookDTO> {

    private JLabel lbIcon = new JLabel();
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();

    public BookView() {
        setLayout(new BorderLayout(5, 5));

        JPanel panelText = new JPanel(new GridLayout(0, 1));
        panelText.add(lbName);
        panelText.add(lbAuthor);
        add(lbIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends BookDTO> list,
                                                  BookDTO book, int index, boolean isSelected, boolean cellHasFocus) {

        try {
            lbIcon.setIcon(new ImageIcon(getClass().getResource("/" + book.img)));
        }catch (Exception e){
            lbIcon.setIcon(new ImageIcon(getClass().getResource("/images/notFound.jpg")));
        }
            lbName.setText(book.title);
            lbAuthor.setText(book.authorName + " " + book.authorLastName);
            lbAuthor.setForeground(Color.blue);

            lbName.setOpaque(true);
            lbAuthor.setOpaque(true);
            lbIcon.setOpaque(true);

        // when select item
        if (isSelected) {
            lbName.setBackground(list.getSelectionBackground());
            lbAuthor.setBackground(list.getSelectionBackground());
            lbIcon.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else { // when don't select
            lbName.setBackground(list.getBackground());
            lbAuthor.setBackground(list.getBackground());
            lbIcon.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }

        return this;
    }

}