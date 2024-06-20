package Components;

import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class btnGuardarBeanInfo extends SimpleBeanInfo {

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor iconColor = new PropertyDescriptor("iconColor", btnGuardar.class);
            iconColor.setBound(true);
            iconColor.setDisplayName("Icon Color");
            iconColor.setShortDescription("The color of the button icon.");

            PropertyDescriptor hasBorder = new PropertyDescriptor("hasBorder", btnGuardar.class);
            hasBorder.setBound(true);
            hasBorder.setDisplayName("Has Border");
            hasBorder.setShortDescription("Specifies whether the button has a border.");

            PropertyDescriptor borderColor = new PropertyDescriptor("borderColor", btnGuardar.class);
            borderColor.setBound(true);
            borderColor.setDisplayName("Border Color (RGB)");
            borderColor.setShortDescription("The color of the button border.");

            PropertyDescriptor fillColor = new PropertyDescriptor("fillColor", btnGuardar.class);
            fillColor.setBound(true);
            fillColor.setDisplayName("Fill Color (RGB)");
            fillColor.setShortDescription("The color used to fill the button background.");

            PropertyDescriptor borderRadius = new PropertyDescriptor("borderRadius", btnGuardar.class);
            borderRadius.setBound(true);
            borderRadius.setDisplayName("Border Radius");
            borderRadius.setShortDescription("The radius of the button's rounded corners.");

            PropertyDescriptor buttonText = new PropertyDescriptor("buttonText", btnGuardar.class);
            buttonText.setBound(true);
            buttonText.setDisplayName("Button Text");
            buttonText.setShortDescription("The text displayed on the button.");

            PropertyDescriptor textColor = new PropertyDescriptor("textColor", btnGuardar.class);
            textColor.setBound(true);
            textColor.setDisplayName("Text Color");
            textColor.setShortDescription("The color of the button text.");

            PropertyDescriptor textSize = new PropertyDescriptor("textSize", btnGuardar.class);
            textSize.setBound(true);
            textSize.setDisplayName("Text Size");
            textSize.setShortDescription("The size of the button text.");

            return new PropertyDescriptor[]{iconColor, hasBorder, borderColor, fillColor,
                borderRadius, buttonText, textColor, textSize};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
