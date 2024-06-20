package Components;

import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class btnGuardarBeanInfo extends SimpleBeanInfo {

    private static final Class<btnGuardar> beanClass = btnGuardar.class;

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor iconColor = new PropertyDescriptor("iconColor", beanClass);
            iconColor.setBound(true);
            iconColor.setDisplayName("Icon Color");
            iconColor.setShortDescription("The color of the button icon.");

            PropertyDescriptor hasBorder = new PropertyDescriptor("hasBorder", beanClass);
            hasBorder.setBound(true);
            hasBorder.setDisplayName("Has Border");
            hasBorder.setShortDescription("Specifies whether the button has a border.");

            PropertyDescriptor borderColor = new PropertyDescriptor("borderColor", beanClass);
            borderColor.setBound(true);
            borderColor.setDisplayName("Border Color");
            borderColor.setShortDescription("The color of the button border.");

            PropertyDescriptor hasBackgroundColor = new PropertyDescriptor("hasBackgroundColor", beanClass);
            hasBackgroundColor.setBound(true);
            hasBackgroundColor.setDisplayName("Has Background Color");
            hasBackgroundColor.setShortDescription("Specifies whether the button has a background color.");

            PropertyDescriptor backgroundColor = new PropertyDescriptor("backgroundColor", beanClass);
            backgroundColor.setBound(true);
            backgroundColor.setDisplayName("Background Color");
            backgroundColor.setShortDescription("The color of the button background.");

            PropertyDescriptor borderRadius = new PropertyDescriptor("borderRadius", beanClass);
            borderRadius.setBound(true);
            borderRadius.setDisplayName("Border Radius");
            borderRadius.setShortDescription("The radius of the button's rounded corners.");

            PropertyDescriptor buttonText = new PropertyDescriptor("buttonText", beanClass);
            buttonText.setBound(true);
            buttonText.setDisplayName("Button Text");
            buttonText.setShortDescription("The text displayed on the button.");

            PropertyDescriptor textColor = new PropertyDescriptor("textColor", beanClass);
            textColor.setBound(true);
            textColor.setDisplayName("Text Color");
            textColor.setShortDescription("The color of the button text.");

            PropertyDescriptor textSize = new PropertyDescriptor("textSize", beanClass);
            textSize.setBound(true);
            textSize.setDisplayName("Text Size");
            textSize.setShortDescription("The size of the button text.");

            PropertyDescriptor iconWidth = new PropertyDescriptor("iconWidth", beanClass);
            iconWidth.setBound(true);
            iconWidth.setDisplayName("Icon Width");
            iconWidth.setShortDescription("The width of the button icon.");

            PropertyDescriptor iconHeight = new PropertyDescriptor("iconHeight", beanClass);
            iconHeight.setBound(true);
            iconHeight.setDisplayName("Icon Height");
            iconHeight.setShortDescription("The height of the button icon.");

            PropertyDescriptor borderSize = new PropertyDescriptor("borderSize", beanClass);
            borderSize.setBound(true);
            borderSize.setDisplayName("Border Size");
            borderSize.setShortDescription("The size of the button border.");

            return new PropertyDescriptor[]{iconColor, hasBorder, borderColor, hasBackgroundColor,
                backgroundColor, borderRadius, buttonText, textColor, textSize, iconWidth, iconHeight, borderSize};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
