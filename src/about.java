import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.*;

public class about extends QWidget
{
    private main upper;
    private QDesktopWidget escritorio = new QDesktopWidget();

    public about (main parent, boolean claro)
    {
        QApplication.setStyle(new QPlastiqueStyle());
        upper = parent;
        config(claro);
    }

    private void config(boolean claro)
    {
        this.setWindowIcon(new QIcon("classpath:/ico/acercade.png"));
        if(claro)
            this.setStyleSheet("background: #cecece; color: #2d2d2d;");
        else
            this.setStyleSheet("background: #2d2d2d; color: #f9f9f9;");
        this.setFixedSize(320, 260);
        setWindowTitle("Acerca de WriteMasta");
        int altura=escritorio.geometry().height();
        int anchura=escritorio.geometry().width();
        move((anchura/2)-160,(altura/2)-130);
        QGridLayout ventana = new QGridLayout(this);
        String os = System.getProperty("os.name");
        String arch = System.getProperty("os.arch");
        String about = "\nWriteMasta\nVersión 1.0 build 3025\n"+os+" "+arch+"\n\nAlexandru Constantin Viscreanu\nDavid Morales Cortés\n";
        //about += os +" "+ arch;
        QLabel info = new QLabel(about);
        info.setAlignment(Qt.AlignmentFlag.AlignCenter);
        QLabel a = new QLabel();
        if(claro)
            a.setPixmap(new QPixmap("classpath:/img/infoclaro.png"));
        else
            a.setPixmap(new QPixmap("classpath:/img/infoscuro.png"));
        a.setAlignment(Qt.AlignmentFlag.AlignCenter);
        QPushButton exit = new QPushButton("Salir");
        exit.setStyleSheet("background: #d64937; color: #f9f9f9;");
        exit.clicked.connect(this, "salir()");
        ventana.addWidget(a);
        ventana.addWidget(info);
        ventana.addWidget(exit);
        ventana.setMargin(5);
        ventana.setVerticalSpacing(0);
        ventana.setHorizontalSpacing(0);
        show();
    }
    private void salir()
    {
        this.close();
    }
}