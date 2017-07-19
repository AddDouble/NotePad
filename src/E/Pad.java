package E;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


public class Pad extends Frame implements ActionListener{
    private MenuBar m;
    private Menu m1,m2;
    private MenuItem open,save,help;
    private TextArea text;
    private FileDialog openFD;
    private FileDialog saveFD;
    private Clipboard board;
    private BufferedReader Bread;
    private FileReader Fread;
    private BufferedWriter Bwrite;
    private FileWriter Fwrite;
    private Dialog Help;
    String filename;
    Pad(String name){
        super(name);
        m=new MenuBar();
        m1=new Menu("File");
        m2=new Menu("More");
        open=new MenuItem("Open");
        save=new MenuItem("Save");
        help=new MenuItem("Help");
        text=new TextArea();
        openFD=new FileDialog(this,"´ò¿ª",FileDialog.LOAD);
        saveFD=new FileDialog(this,"±£´æ",FileDialog.SAVE);
       // Help=new Dialog(null,"Tip",true);

        m1.add(open);
        m1.add(save);
        m2.add(help);
        m.add(m1);
        m.add(m2);

        board=new Clipboard("first");
        setMenuBar(m);
        setSize(1200,800);
        setVisible(true);

        open.addActionListener(this);
        save.addActionListener(this);
        help.addActionListener(this);
        add(text,"Center");

       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               super.windowClosing(e);
               System.exit(0);
           }
       });
    }
    public void openFile(){
        System.out.println("openFile()");
        openFD.setVisible(true);
        //openFD.show();
        filename=openFD.getDirectory()+openFD.getFile();
        if(filename!=null){
            File file=new File(filename);
            String s;
            try {
                Fread = new FileReader(file);
                Bread=new BufferedReader(Fread);
                while((s=Bread.readLine())!=null){
                    text.append(s+"\n");
                    System.out.println(s);
                }
                Bread.close();
                Fread.close();
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }
    public void isHelp(){

        JOptionPane.showMessageDialog(null,"There is nothing!\n");
        //setVisible(true);
    }

    public void saveFile(){
        //saveFD.show();
         saveFD.setVisible(true);
        filename=saveFD.getDirectory()+saveFD.getFile();
        if(filename!=null){

            try{
                 File file=new File(filename);
                 Fwrite=new FileWriter(file);
                 Bwrite=new BufferedWriter(Fwrite);
                 Bwrite.write(text.getText(),0,(text.getText()).length());
                 Bwrite.close();
                 Fwrite.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==open)
            openFile();
        else if(e.getSource()==save)
            saveFile();
        else if(e.getSource()==help)
            isHelp();

    }

}