package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class JHorizontalRotateLabel extends JPanel {
    private static final long serialVersionUID = 0L;
    
    private static Font _defaultFont;
    {
        if (_defaultFont == null) {
            _defaultFont = new Font("Lucida Grande", Font.PLAIN, 7);
            if (_defaultFont == null) _defaultFont = getFont();
        }
        setFont(_defaultFont);
        addPropertyChangeListener("font", e -> remeasure());
    }

    private boolean _faceLeft;
    private Dimension _size;
    private String _text;

    /**
     * Creates a {@link JHorizontalRotateLabel} with no initial text.
     */
    public JHorizontalRotateLabel() {
        setText(null);
    }

    /**
     * Creates a {@link JHorizontalRotateLabel} with the specified initial text.
     * @param text the text to display
     */
    public JHorizontalRotateLabel(String text) {
        setText(text);
    }

    /**
     * Creates a {@link JHorizontalRotateLabel} with the specified initial text and facing.
     * @param text the text to display
     * @param faceLeft {@code true} if the baseline of the {@link JHorizontalRotateLabel}
     *                 should face left, {@code false} if it should face right
     */
    public JHorizontalRotateLabel(String text, boolean faceLeft) {
        _faceLeft = faceLeft;
        setText(text);
    }

    /**
     * Indicates whether the {@link JHorizontalRotateLabel} is facing left.
     * The default is {@code false}.
     * @return {@code true} if the baseline of the {@link JHorizontalRotateLabel}
     *         is facing left, {@code false} if it is facing right
     */
    public boolean getFaceLeft() {
        return _faceLeft;
    }

    /**
     * Determines whether the {@link JHorizontalRotateLabel} is facing left.
     * @param faceLeft {@code true} if the baseline of the {@link JHorizontalRotateLabel}
     *                 should face left, {@code false} if it should face right
     */
    public void setFaceLeft(boolean faceLeft) {
        _faceLeft = faceLeft;
        repaint(); // same size, no remeasure needed
    }

    /**
     * Gets the text displayed in the {@link JHorizontalRotateLabel}.
     * @return the text displayed in the {@link JHorizontalRotateLabel}
     */
    public String getText() {
        return _text;
    }

    /**
     * Sets the text to display in the {@link JHorizontalRotateLabel}.
     * @param text the text to display in the {@link JHorizontalRotateLabel}
     */
    public void setText(String text) {
        _text = text;
        remeasure();
    }

    /**
     * Remeasures and repaints the {@link JHorizontalRotateLabel}.
     */
    private void remeasure() {
        if (_text == null || _text.isEmpty())
            _size = new Dimension();
        else {
            final FontMetrics metrics = getFontMetrics(getFont());
            _size = new Dimension(metrics.getHeight() + 1,
                    metrics.stringWidth(_text) + 1);
        }
        setMinimumSize(_size);
        setMaximumSize(_size);
        setPreferredSize(_size);

        repaint();
    }

    /**
     * Invoked by Swing to draw the content area of the {@link JHorizontalRotateLabel}.
     * @param g the {@link Graphics2D} context in which to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (_text == null || _text.isEmpty())
            return;

        final Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        final AffineTransform transform = g2.getTransform();

        if (_faceLeft) {
            g2.rotate(Math.toRadians(90));
            g2.drawString(_text, 1,
                    -1 - g2.getFontMetrics().getDescent());
        } else {
            g2.rotate(Math.toRadians(-90));
            g2.drawString(_text, 1 - _size.height,
                    g2.getFontMetrics().getAscent());
        }
        g2.setTransform(transform);
    }
}
