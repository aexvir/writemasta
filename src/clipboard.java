import com.trolltech.qt.gui.*;

public class clipboard extends QWidget
{
    private QGridLayout ventana = new QGridLayout(this);
    private QTabWidget contenedor = new QTabWidget();
    private QGridLayout selector = new QGridLayout();
    private QTextEdit hist = new QTextEdit();
    private QPushButton limpiar = new QPushButton("Limpiar historial");
    private QPushButton insertar = new QPushButton("Insertar en archivo");
    private QPushButton no = new QPushButton("Salir");
    private QGridLayout preferencias = new QGridLayout();
    private main upper = null;
    private boolean esClaro;
    private QIcon iconoportapapeles = new QIcon("classpath:/ico/portapapeles.png");
    String [] todo;

    public clipboard(main parent, boolean claro)
    {
        upper = parent;
        esClaro = claro;
        QApplication.setStyle(new QPlastiqueStyle());
        config();
    }

    private void config() {
        todo = upper.portapapeles;
        if (esClaro) {
            this.setStyleSheet("background: #cecece; color: #2d2d2d;");
            hist.setStyleSheet("QTextEdit {border: 1px solid #bbb; background: #dedede; color: #2d2d2d;}" +
                    "QScrollBar{background: #cecece;}" +
                    "QScrollBar::handle{background: #d64937; border: 1px solid #bbb;}");
        }
        else {
            this.setStyleSheet("background: #2d2d2d; color: #f9f9f9;");
            hist.setStyleSheet("QTextEdit {border: 1px solid #222; background: #333; color: #ddd;}" +
                    "QScrollBar{background: #2d2d2d;}" +
                    "QScrollBar::handle{background: #d64937; border: 1px solid #222;}");
        }
        this.setBaseSize(480, 640);
        setWindowTitle("Portapapeles");
        move(523, 264);
        this.setWindowIcon(iconoportapapeles);
        ventana.addWidget(hist,1,1,1,2);
        ventana.addWidget(limpiar, 2,1,1,2);
        ventana.addWidget(insertar, 3,1);
        ventana.addWidget(no, 3,2);
        ventana.setMargin(2);
        ventana.setHorizontalSpacing(2);
        ventana.setVerticalSpacing(2);
        limpiar.setStyleSheet("background: #3EB019; color #f9f9f9;");
        insertar.setStyleSheet("background: #37c4d6 ; color: #f9f9f9;");
        no.setStyleSheet("background: #d64937; color: #f9f9f9;");
        limpiar.clicked.connect(this, "limpiar()");
        insertar.clicked.connect(this, "insertar()");
        no.clicked.connect(this, "salir()");
        for(int i=0; i<upper.portapapeles.length; i++)
        {
            if(todo[i]!=null) {
                String tmp = hist.toPlainText();
                tmp += todo[i] + "\n";
                hist.setText(tmp);
            }
        }
        show();
    }

    private void limpiar()
    {
        for(int i=0; i<upper.portapapeles.length; i++)
            upper.portapapeles[i]=null;
        hist.setText("");
    }

    private void insertar()
    {
        String tmp = upper.texto.toPlainText();
        tmp += hist.textCursor().selectedText()+"\n";
        upper.texto.setText(tmp);
    }

    private void salir()
    {
        this.close();
    }
}
