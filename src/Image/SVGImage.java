package Image;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.function.Function;

public class SVGImage extends JLabel {

    private FlatSVGIcon svgIcon;

    public void setSVGImage(String image, int width, int height) {
        svgIcon = new FlatSVGIcon(image, width, height);
        setIcon(svgIcon);
    }

    // Hàm để thay đổi màu sắc của SVG
    public void setSVGColor(Color color) {
        if (svgIcon != null) {
            svgIcon.setColorFilter(new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
                @Override
                public Color apply(Color src) {
                    return color;
                }
            }));
            setIcon(svgIcon);
        }
    }

    public void setColor() {
        setSVGColor(Color.WHITE);
    }
    
    public void setYColor() {
        setSVGColor(Color.YELLOW);
    }
}
