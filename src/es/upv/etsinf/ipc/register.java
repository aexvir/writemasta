package es.upv.etsinf.ipc;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.*;

public class register extends QWidget
{
    private QDesktopWidget escritorio = new QDesktopWidget();
    private QGridLayout ventana = new QGridLayout(this);
    private QLineEdit nombre = new QLineEdit("Nombre");
    private QLineEdit apellido = new QLineEdit("Apellidos");
    private QLineEdit correo = new QLineEdit("Correo");
    private QPushButton otravez = new QPushButton("Licencia nueva");
    private QPushButton ok = new QPushButton("Enviar");
    private QPushButton no = new QPushButton("Cancelar");
    private QLabel info = new QLabel("Introduzca los datos para\nregistrar su copia de WriteMasta");
    private QLabel yareginfo = new QLabel("\n\nEsta copia del producto WriteMasta ya tiene una licencia activa\npero si lo desea puede introducir una nueva\nque anulará la anterior.");
    private QLabel a1;
    private QPushButton yaregno = new QPushButton("Cancelar");
    private main upper;
    private boolean esClaro=false;
    private boolean yamostrado=false;
    private boolean advertenciamostrada;

    public register (main parent, boolean claro)
    {
        QApplication.setStyle(new QPlastiqueStyle());
        upper = parent;
        esClaro = claro;
        if(upper.registrado())
            yareg();
        else
            config();
    }

    private void yareg()
    {
        this.setWindowIcon(new QIcon("classpath:/ico/registro.png"));
        if(esClaro)
            this.setStyleSheet("background: #cecece; color: #2d2d2d;");
        else
            this.setStyleSheet("background: #2d2d2d; color: #f9f9f9;");
        this.setFixedSize(320, 260);
        setWindowTitle("Registro de producto");
        int altura=escritorio.geometry().height();
        int anchura=escritorio.geometry().width();
        move((anchura/2)-160,(altura/2)-130);
        yareginfo.setAlignment(Qt.AlignmentFlag.AlignCenter);
        String [] tmp = upper.getLicencia();
        String licencia = "";
        licencia+="Licencia actual:\n\nNombre: "+tmp[0]+"\nApellidos: "+tmp[1]+"\nCorreo: "+tmp[2];
        a1 = new QLabel(licencia);
        a1.setAlignment(Qt.AlignmentFlag.AlignCenter);
        otravez.setStyleSheet("background: #37c4d6 ; color: #f9f9f9;");
        yaregno.setStyleSheet("background: #d64937; color: #f9f9f9;");
        ventana.addWidget(yareginfo, 1, 1, 1, 2);
        ventana.addWidget(a1, 2,1,1,2);
        ventana.addWidget(otravez,3,1);
        ventana.addWidget(yaregno,3,2);
        ventana.setMargin(5);
        yaregno.clicked.connect(this, "salir()");
        otravez.clicked.connect(this, "nlicense()");
        yamostrado = true;
        show();
    }

    private void nlicense()
    {
        yareginfo.setVisible(false);
        a1.setVisible(false);
        otravez.setVisible(false);
        yaregno.setVisible(false);
        ventana.removeWidget(yareginfo);
        ventana.removeWidget(a1);
        ventana.removeWidget(otravez);
        ventana.removeWidget(yaregno);
        config();
    }

    private void config()
    {
        this.setWindowIcon(new QIcon("classpath:/ico/registro.png"));
        if(esClaro)
            this.setStyleSheet("background: #cecece; color: #2d2d2d;");
        else
            this.setStyleSheet("background: #2d2d2d; color: #f9f9f9;");
        this.setFixedSize(320, 260);
        setWindowTitle("Registro de producto");
        int altura=escritorio.geometry().height();
        int anchura=escritorio.geometry().width();
        move((anchura/2)-160,(altura/2)-130);
        info.setAlignment(Qt.AlignmentFlag.AlignCenter);
        no.clicked.connect(this, "salir()");
        ok.clicked.connect(this, "enviar()");
        QLabel xd = new QLabel(" ");
        ventana.addWidget(info,1,1,1,2);
        ventana.addWidget(nombre,2,1,1,2);
        ventana.addWidget(apellido,3,1,1,2);
        ventana.addWidget(correo,4,1,1,2);
        ventana.addWidget(ok,6,1);
        ok.setStyleSheet("background: #37c4d6 ; color: #f9f9f9;");
        no.setStyleSheet("background: #d64937; color: #f9f9f9;");
        ventana.addWidget(no,6,2);
        ventana.setMargin(5);
        if(!yamostrado)
            show();
    }

    private void salir()
    {
        this.close();
    }

    private void enviar()
    {
        if(!nombre.text().equalsIgnoreCase("nombre")&&!apellido.text().equalsIgnoreCase("apellidos")&&!correo.text().equalsIgnoreCase("correo")
                &&!nombre.text().equalsIgnoreCase("null")&&!apellido.text().equalsIgnoreCase("null")&&!correo.text().equalsIgnoreCase("null"))
        {
            String[] licencia = new String[3];
            licencia[0] = nombre.text();
            licencia[1] = apellido.text();
            licencia[2] = correo.text();
            upper.licencia(licencia);
            QLabel mfinal = new QLabel("Información enviada correctamente\nEl producto está ahora registrado");
            nombre.setVisible(false);
            apellido.setVisible(false);
            correo.setVisible(false);
            info.setText("Información enviada correctamente\nEl producto está ahora registrado");
            ok.setVisible(false);
            no.setVisible(false);
        }
        else
        {
            if(!advertenciamostrada) {
                String txt = info.text();
                txt += "\n\nPor favor, rellene los campos presentes a continuación\ncon información válida";
                info.setText(txt);
                advertenciamostrada = true;
            }
        }
    }
}