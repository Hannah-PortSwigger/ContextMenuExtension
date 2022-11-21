package burp;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BurpExtender implements IBurpExtender, IContextMenuFactory
{
    private IBurpExtenderCallbacks callbacks;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;

        callbacks.setExtensionName("Context menu extension");
        callbacks.registerContextMenuFactory(this);
    }

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        List<JMenuItem> menuItems = new ArrayList<>();

        JMenuItem logToolContext = new JMenuItem("Log tool context");
        logToolContext.addActionListener(e -> {
            try
            {
                int toolflag = invocation.getToolFlag();
                callbacks.printOutput("Toolflag is: " + toolflag);
            }
            catch(Exception err)
            {
                callbacks.printError("Something's gone wrong!\r\n" + err);
            }

        });

        menuItems.add(logToolContext);

        return menuItems;
    }
}