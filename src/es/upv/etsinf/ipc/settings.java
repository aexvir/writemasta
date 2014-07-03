package es.upv.etsinf.ipc;
import com.trolltech.qt.core.QLocale;
import com.trolltech.qt.gui.*;

public class settings extends QWidget
{
    private QFontDatabase f = new QFontDatabase();
    private QGridLayout ventana = new QGridLayout(this);
    private QComboBox fuente = new QComboBox(this);
    private QComboBox size = new QComboBox(this);
    private QColorDialog color1 = new QColorDialog(this);
    private QColorDialog color2 = new QColorDialog(this);
    private QColorDialog color3 = new QColorDialog(this);
    private main upper = null;
    private boolean esClaro=false;

    private String[] preferencias = new String[5];

    private QIcon iconopreferencias = new QIcon("classpath:/ico/preferencias.png");

    public settings(main parent, boolean claro)
    {
        upper = parent;
        esClaro = claro;
        QApplication.setStyle(new QPlastiqueStyle());
        config();
    }

    private void config()
    {
        if(esClaro)
            this.setStyleSheet("background: #cecece; color: #2d2d2d;");
        else
            this.setStyleSheet("background: #2d2d2d; color: #f9f9f9;");
        this.setFixedSize(320, 240);
        setWindowTitle("Preferencias de WriteMasta");
        move(523,264);
        this.setWindowIcon(iconopreferencias);
        QLabel i1 = new QLabel("Fuente: ");
        ventana.addWidget(i1,1,1);
        Object [] fonts = f.families().toArray();
        for(int i=0; i<fonts.length; i++)
        {
            fuente.addItem(fonts[i].toString());
        }
        fuente.setCurrentIndex(fuente.findText(upper.texto.fontFamily().toString()));
        ventana.addWidget(fuente, 1, 2);
        fuente.currentIndexChanged.connect(this, "setFuente()");
        QLabel i2 = new QLabel("Tamaño de fuente: ");
        ventana.addWidget(i2, 2,1);
        ventana.addWidget(size,2,2);
        size.addItem("8");
        size.addItem("9");
        size.addItem("10");
        size.addItem("11");
        size.addItem("12");
        size.addItem("14");
        size.addItem("16");
        size.addItem("18");
        size.addItem("20");
        size.addItem("24");
        size.addItem("32");
        size.addItem("64");
        size.addItem("72");
        size.setCurrentIndex(size.findText(Integer.toString((int)upper.texto.fontPointSize())));
        size.currentIndexChanged.connect(this, "setSize()");
        QLabel i3 = new QLabel("Color de la fuente: ");
        ventana.addWidget(i3, 3,1);
        QPushButton cl = new QPushButton("Cambiar");
        ventana.addWidget(cl, 3,2);
        cl.clicked.connect(this, "selector1()");
        QLabel i4 = new QLabel("Color del fondo: ");
        ventana.addWidget(i4, 4,1);
        QPushButton cf = new QPushButton("Cambiar");
        cf.clicked.connect(this, "selector2()");
        ventana.addWidget(cf, 4,2);
        QLabel i5 = new QLabel("Color del editor: ");
        ventana.addWidget(i5, 5,1);
        QPushButton ce = new QPushButton("Cambiar");
        ventana.addWidget(ce, 5,2);
        ce.clicked.connect(this, "selector3()");
        QPushButton ok = new QPushButton("Guardar");
        QPushButton no = new QPushButton("Cancelar");
        no.setStyleSheet("background: #d64937; color: #f9f9f9;");
        ventana.addWidget(ok,6,1);
        ventana.addWidget(no,6,2);
        ok.clicked.connect(this, "guardar()");
        no.clicked.connect(this, "salir()");
        show();
    }

    private void selector1()
    {
        color1.setLocale(new QLocale(QLocale.Language.Spanish));
        color1.setVisible(true);
        color1.currentColorChanged.connect(this, "setCl()");
    }

    private void selector2()
    {
        color2.setLocale(new QLocale(QLocale.Language.Spanish));
        color2.setVisible(true);
        color2.currentColorChanged.connect(this, "setCf()");
    }

    private void selector3()
    {
        color3.setLocale(new QLocale(QLocale.Language.Spanish));
        color3.setVisible(true);
        color3.currentColorChanged.connect(this, "setCe()");
    }

    private void setCf()
    {
        preferencias[2] = color2.currentColor().toRgb().toString();
        System.out.println(preferencias[2]);
    }

    private void setCl()
    {
        preferencias[3] = color1.currentColor().toRgb().toString();
        System.out.println(preferencias[3]);
    }

    private void setCe()
    {
        preferencias[4] = color3.currentColor().toRgb().toString();
        System.out.println(preferencias[4]);
    }

    private void setFuente()
    {
        preferencias[0] = fuente.currentText();
    }

    private void setSize()
    {
        preferencias[1] = size.currentText();
    }

    private void guardar()
    {
        setFuente();
        setSize();
        setCf();
        setCl();
        setCe();
        this.close();
    }

    private void salir()
    {
        this.close();
    }
}
