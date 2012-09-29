package Module.UI;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;

import Module.Config.ObjectData;
import Module.Logic.Tool.SysData;
import Module.Main.ConfigUI;

/**
 * @author 于风
 * @version 1.0
 * @created 13-四月-2011 10:58:55
 */
public class TipUI extends UserInterfaceBase {

    protected MenuItem mi, m2;
    protected Menu menu;
    protected JTextArea l;
    protected String name = "信息提示";
    protected ConfigUI configUI;
    protected String endString = "任务完成";

    /**
     *
     * @param s
     */
    public void showTip(String s) {
        l.setText(s);
        if (SysData.isExit()) {
            gui.setTitle(endString + "-" + SysData.getSoftName());
            gui.toFront();
        }
    }

    public void setEndString(String s) {
        endString = s;
    }

    public boolean isShow() {
        return gui.isShowing();
    }

    public void Reset() {
        gui.setTitle(SysData.getSoftName() + "-" + name);
    }

    public void setTitle(String s) {
        gui.setTitle(s);
    }

    @Override
    protected void initGui() {
        MenuBar mb = new MenuBar();
        ObjectData.SaveObject("MainUi", gui);
        gui.setTitle(SysData.getSoftName() + "-" + name);
        try {
            gui.setIconImage(SysData.getSelfImageIcon().getImage());
        } catch (Exception e) {
        }
        l = new JTextArea("....................");
        l.setEditable(false);
        menu = new Menu("菜单");
        mi = new MenuItem("配置");
        m2 = new MenuItem("退出");
        mb.add(menu);
        menu.add(mi);
        menu.add(m2);
        gui.setMenuBar(mb);
        gui.add(l);
        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                SysData.setExit();
                SysData.DoExit();// 唯一退出操作
            }
        });
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (configUI == null) {
                    configUI = new ConfigUI(gui);
                }
                configUI.show();
            }
        });
        gui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 整个程序的唯一退出点
                SysData.setExit();
                SysData.DoExit();// 唯一退出操作
            }

            public void windowIconified(WindowEvent arg0) {
                if (SysData.isTray()) {
                    gui.setVisible(false);
                }
            }
        });
    }

    @Override
    protected void initData() {
        gui.setBounds(200, 200, 200, 200);
    }

    public void setBounds(int x, int y, int width, int height) {
        gui.setBounds(x, y, width, height);
    }

    public void setName(String name) {
        gui.setTitle(name);
    }
}
