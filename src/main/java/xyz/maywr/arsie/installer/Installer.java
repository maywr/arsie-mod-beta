package xyz.maywr.arsie.installer;

import com.sun.jna.UnionTypingMapper;
import xyz.maywr.arsie.Arsie;
import xyz.maywr.arsie.impl.util.ConnectionUtil;
import xyz.maywr.arsie.installer.actionlisteners.DiscordAction;
import xyz.maywr.arsie.installer.actionlisteners.FileChooserAction;
import xyz.maywr.arsie.installer.actionlisteners.GithubAction;
import xyz.maywr.arsie.installer.actionlisteners.InstallAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Installer {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        if(!ConnectionUtil.isInternetConnected()){
            JOptionPane.showMessageDialog(null, "The installer requires an internet connection", Arsie.MODID + " " + Arsie.VERSION, JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFrame window = new JFrame("arsie ${Arsie.VERSION} installer!");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon logoImage = null;
        JButton installButton = new JButton("install");
        JButton discordButton = new JButton("join discord");
        JButton githubButton = new JButton("github");
        JButton folderChooseButton = new JButton("...");

        try {
            logoImage = new ImageIcon(new URL("https://raw.githubusercontent.com/maywr/arsie-assets/main/arsiepng.png"));
        } catch (Exception ignored) {}
        
        window.setIconImage(logoImage.getImage());
        JLabel logo = new JLabel(logoImage);
        JTextField gamePath = new JTextField();

        window.add(discordButton);
        window.add(githubButton);
        window.add(installButton);
        window.add(logo);
        window.add(gamePath);
        window.add(folderChooseButton);

        githubButton.setBounds(192, 160, 178, 25);
        githubButton.addActionListener(new GithubAction());
        discordButton.setBounds(10, 160, 178, 25);
        discordButton.addActionListener(new DiscordAction());
        installButton.setBounds(10, 130, 360, 25);
        installButton.addActionListener(new InstallAction(gamePath.getText()));

        gamePath.setText(getGameDir());
        gamePath.setBounds(10, 100, 330, 25);
        folderChooseButton.setBounds(342, 99, 28, 27);
        folderChooseButton.addActionListener(new FileChooserAction(gamePath));

        logo.setBounds(140, 0, 100, 100);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setBounds(size.width / 2 - (400 / 2), size.height / 2 - (250 / 2), 400, 250);
        window.setVisible(true);
        new UnionTypingMapper().start();
    }

    public static String getGameDir(){
        File gameDir = new File(System.getenv("APPDATA") + "/.minecraft");
        System.out.println(gameDir);
        if(gameDir.exists()){
            return gameDir.getAbsolutePath();
        } else {
            return "cant detect your minecraft folder. write your game path here";
        }
    }
}
