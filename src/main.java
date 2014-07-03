import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.trolltech.qt.QThread;
import com.trolltech.qt.core.QSize;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class main extends QWidget implements Serializable
{
    private QFontDatabase f = new QFontDatabase();
    private QDesktopWidget escritorio = new QDesktopWidget();
    private int altura=escritorio.geometry().height();
    private int anchura=escritorio.geometry().width();
    private static boolean [] configuraciones = new boolean[10];
    private String [] preferencias = new String[4];
    public String [] portapapeles = new String [20];
    public int ppocupado;
    //Lista elementos de la aplicación
    private QGridLayout ventana = new QGridLayout(this); //Toda la ventana
    private QMenuBar menuprinc = new QMenuBar(); //Barra de menú
    //private boolean menuvisible = false; configuraciones [0]
    private QMenu archivo = new QMenu("Archivo"); //Menú archivo
    private QMenu editar = new QMenu("Editar"); //Menú editar
    private QMenu ver = new QMenu("Ver"); //Menú ver
    private QMenu ayuda = new QMenu("Ayuda"); //Menú ayuda
    private QToolBar herramientas = new QToolBar(); //Barra de herramientas
    //private boolean herramientasactivas = true; //Estado de la barra de erramientas configuraciones[1]
    private QAction switchcolor;
    private QAction coloresclaros;
    private QAction coloresoscuros;
    private QAction negrita;
    private QAction cursiva;
    private QAction subrayado;
    private QAction izquierda;
    private QAction centro;
    private QAction derecha;
    private QComboBox fuente = new QComboBox(this);
    private QComboBox size = new QComboBox(this);
    //private boolean esNegrita = false; configuraciones[2]
    //private boolean esCursiva = false; configuraciones [3]
    //private boolean esSubrayado = false; configuraciones [4]
    public QTextEdit texto = new QTextEdit(); //Caja de texto
    private QToolBar busqueda = new QToolBar(); //Barra de búsqueda
    private QLabel plb = new QLabel("Introduce palabra: ");
    private QLineEdit cont = new QLineEdit("");
    private QPushButton go = new QPushButton ("Buscar");
    //private boolean busquedactivada = false; //Estado barra de búsqueda configuraciones[5]
    private QToolBar busquedar = new QToolBar(); //Barra de búsqueda y reemplazo
    private QLabel plb2 = new QLabel("Introduce palabra: ");
    private QLineEdit cont2 = new QLineEdit("");
    private QLabel reem = new QLabel("Reemplazar por: ");
    private QLineEdit fin = new QLineEdit("");
    private QPushButton go2 = new QPushButton ("Buscar y reemplazar");
    //private boolean busquedaractivada = false; //Estado de la barra de búsqueda y reemplazo configuraciones[6]
    private QStatusBar estado = new QStatusBar(); //Barra de estado
    private QLabel fil = new QLabel("Fila: 0");
    private QLabel col = new QLabel("Columna: 0");
    private QLabel npal = new QLabel("Palabras: 0");
    //private boolean estadovisible = true; configuraciones[7]
    protected String nreg = null; //Nombre de usuario registro
    protected String sreg = null; //Appellidos del usuario registro
    protected String mreg = null; //Correo del usuario registro
    //private boolean pantallacompleta = false; //estado modo pantalla completa configuraciones[8]
    //private boolean esclaro = true; configuraciones[9]
    //Lista de iconos
    private String inir = "classpath:/ico/claro/";
    private String[] nombreiconosbarra = {"","nuevo.png", "abrir.png", "guardar.png", "", "deshacer.png", "rehacer.png", "", "negrita.png", "cursiva.png", "subrayado.png", "", "", "", "buscar.png",
    "buscareemplazar.png", "", "izquierda.png", "centro.png", "derecha.png", "", "menu.png", "estado.png", "pantallacompleta.png", "", "oscuro.png", "", "portapapeles.png", ""};
    private String[] nombreiconosarchivo = {"nuevo.png", "abrir.png", "", "guardar.png", "guardarcomo.png", "exportar.png", "", "imprimir.png", "", "salir.png"};
    private String[] nombreiconoseditar = {"copiar.png", "cortar.png", "pegar.png", "", "deshacer.png", "rehacer.png", "", "buscar.png", "buscareemplazar.png"};
    private String[] nombreiconosver = {"claro.png", "oscuro.png", "", "pantallacompleta.png", "", "menu.png", "herramientas.png", "estado.png"};
    private String[] nombreiconosayuda = {"acercade.png", "", "registro.png"};
    /*private QIcon iconovacio = new QIcon();
    private QIcon iconoactivado = new QIcon(inir+"seleccionado.png");
    private QIcon icononuevo = new QIcon(inir+"nuevo.png");
    private QIcon iconoabrir = new QIcon(inir+"abrir.png");
    private QIcon iconoguardar = new QIcon(inir+"guardar.png");
    private QIcon iconodeshacer = new QIcon(inir+"deshacer.png");
    private QIcon iconorehacer = new QIcon(inir+"rehacer.png");
    private QIcon icononegrita = new QIcon(inir+"negrita.png");
    private QIcon iconocursiva = new QIcon(inir+"cursiva.png");
    private QIcon iconoclaro = new QIcon(inir+"claro.png");
    private QIcon iconoscuro = new QIcon(inir+"oscuro.png");
    private QIcon iconosubrayado = new QIcon(inir+"subrayado.png");
    private QIcon iconoizquierda = new QIcon(inir+"izquierda.png");
    private QIcon iconocentro = new QIcon(inir+"centro.png");
    private QIcon iconoderecha = new QIcon(inir+"derecha.png");
    private QIcon iconobuscar = new QIcon(inir+"buscar.png");
    private QIcon iconomenu = new QIcon(inir+"menu.png");
    private QIcon iconoestado = new QIcon(inir+"estado.png");
    private QIcon iconopantallacompleta = new QIcon(inir+"pantallacompleta.png");
    private QIcon iconopreferencias = new QIcon(inir+"preferencias.png");
    private QIcon iconoprincipal = new QIcon(inir+"principal.png");
    private QIcon iconocopiar = new QIcon(inir+"copiar.png");
    private QIcon iconocortar = new QIcon(inir+"cortar.png");
    private QIcon iconopegar = new QIcon(inir+"pegar.png");
    private QIcon iconosalir = new QIcon(inir+"salir.png");
    private QIcon iconoacercade = new QIcon(inir+"acercade.png");
    private QIcon iconoimprimir = new QIcon(inir+"imprimir.png");
    private QIcon iconoregistro = new QIcon(inir+"registro.png");*/
    private boolean indicemovido = false;
    private QTextCursor actual;
    private QDialog confirmacion;
    private QDialog exito;
    private boolean dialogolanzado = false;
    private QWidget sp = new QWidget();

    private boolean archivoexiste = false;
    private boolean singuardar = false;
    private String archivoactual = "";

    private boolean licenciactiva;
    private String[] licencia = new String[3];

    public static void main (String [] args)
    {
        QApplication.setStyle(new QPlastiqueStyle());
        QApplication.initialize(args);
        QPixmap img;
        main app = new main(null);
        if(configuraciones[9])
            img = new QPixmap("classpath:/img/splashclaro.png");
        else
            img = new QPixmap("classpath:/img/splashoscuro.png");
        QSplashScreen inicio = new QSplashScreen(img);
        QProgressBar progreso = new QProgressBar(inicio);
        if(configuraciones[9])
            progreso.setStyleSheet("QProgressBar{background: #dedede; border: 1px solid #bbb; text-align: center; color: #2d2d2d;}" +
                    "QProgressBar::chunk{background: #d64937;}");
        else
            progreso.setStyleSheet("QProgressBar{background: #333; border: 1px solid #444; text-align: center; color: #f9f9f9;}" +
                    "QProgressBar::chunk{background: #d64937;}");
        QGridLayout spls = new QGridLayout(inicio);
        QLabel xd = new QLabel("");
        spls.addWidget(xd, 1,1);
        progreso.setMinimum(0);
        progreso.setMaximum(2000);
        spls.addWidget(progreso,2,1);
        inicio.show();
        avance(progreso);
        inicio.finish(app);
        app.config();
        System.out.println("Aplicación WriteMasta iniciada");
        QApplication.exec();
    }

    private static void avance(QProgressBar prg)
    {
        for(int i=0; i<2000; i++)
        {
            prg.setValue(i);
            try{QThread.sleep(1);}catch(Exception e){System.out.println(e);}
            prg.update();
        }
    }

    public main (QWidget parent)
    {
        super(parent);
        Scanner loader = null;
        try
        {
            loader = new Scanner(new File("./cfg"));
            cargaconfiguraciones(loader);
            System.out.println("Configuración cargada con éxito");
        }
        catch(IOException ioe)
        {
            System.out.println("Error, archivo de configuración inexistente. Cargando configuración por defecto");
            pordefecto();
        }
    }

    public void config()
    {
        sp.setSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Preferred);
        //Atributos ventana inicial
        if(configuraciones[9])
            inir="classpath:/ico/claro/";
        else
            inir="classpath:/ico/oscuro/";
        this.setWindowTitle("WriteMasta"); //Título de la aplicación
        this.resize(Integer.parseInt(preferencias[2]), Integer.parseInt(preferencias[3])); //Tamaño inicial de la ventana
        this.move(anchura / 2 - (Integer.parseInt(preferencias[2]))/2, altura / 2 - (Integer.parseInt(preferencias[3]))/2); //Posición inicial de la ventana
        //Menú archivo
        archivo.addAction("Nuevo documento", this, "nuevo()");
        archivo.addAction("Abrir documento", this, "abrir()");
        archivo.addSeparator();
        archivo.addAction("Guardar", this, "guardar()");
        archivo.addAction("Guardar como...", this, "guardarcomo()");
        archivo.addAction("Exportar a PDF", this, "exportar()");
        archivo.addSeparator();
        archivo.addAction("Imprimir", this, "imprimir()");
        archivo.addSeparator();
        archivo.addAction("Salir", this, "salir()");
        menuprinc.addMenu(archivo);
        //Menú editar
        editar.addAction("Copiar", this, "copiar()");
        editar.addAction("Cortar", this, "cortar()");
        editar.addAction("Pegar", this, "pegar()");
        editar.addSeparator();
        editar.addAction("Deshacer", this, "deshacer()");
        editar.addAction("Rehacer", this, "rehacer()");
        editar.addSeparator();
        editar.addAction("Buscar", this, "buscar()");
        editar.addAction("Buscar y reemplazar", this, "buscareemplaza()");
        menuprinc.addMenu(editar);
        //Menú ver
        coloresclaros = new QAction("Colores claros", this);
        ver.addAction(coloresclaros);
        coloresclaros.triggered.connect(this, "claro()");
        coloresoscuros = new QAction("Colores oscuros", this);
        ver.addAction(coloresoscuros);
        coloresoscuros.triggered.connect(this, "oscuro()");
        ver.addSeparator();
        ver.addAction("Pantalla completa", this, "pantallacompleta()");
        ver.addSeparator();
        ver.addAction("Barra de menú", this, "ocultamenu()");
        ver.addAction("Barra de herramientas", this, "ocultaherramientas()");
        ver.addAction("Barra de estado", this, "ocultaestado()");
        menuprinc.addMenu(ver);
        //Menú ayuda
        ayuda.addAction("Acerca de WriteMasta", this, "acercade()");
        ayuda.addSeparator();
        ayuda.addAction("Registrar producto", this, "registro()");
        menuprinc.addMenu(ayuda);
        //Todos los menús creados e inicializados en la barra de menú
        ventana.addWidget(menuprinc); //Añadimos la barra de menú a la ventana
        menuprinc.setVisible(configuraciones[0]);
        //Barra de herramientas
        //Elementos de la barra de herramientas
        herramientas.addWidget(sp);
        herramientas.addAction("Archivo nuevo", this, "nuevo()"); //Botón nuevo archivo
        herramientas.addAction("Abrir archivo", this, "abrir()"); //Botón abrir
        herramientas.addAction("Guardar archivo actual", this, "guardar()"); //Botón guardar
        herramientas.addSeparator();
        herramientas.addAction("Deshacer", this, "deshacer()"); //Botón deshacer
        herramientas.addAction("Rehacer", this, "rehacer()"); //Botón rehacer
        herramientas.addSeparator();
        negrita = new QAction("Negrita", this); //Cambiar negrita normal
        negrita.setCheckable(true);
        herramientas.addAction(negrita);
        negrita.triggered.connect(this, "negrita()");
        cursiva = new QAction("Cursiva", this); //Cambiar cursiva normal
        cursiva.setCheckable(true);
        herramientas.addAction(cursiva);
        cursiva.triggered.connect(this, "cursiva()");
        subrayado = new QAction("Subrayado", this); //Cambiar subrayado normal
        subrayado.setCheckable(true);
        herramientas.addAction(subrayado);
        subrayado.triggered.connect(this, "subrayado()");
        fuente = new QComboBox(herramientas);
        Object [] fonts = f.families().toArray();
        for(int i=0; i<fonts.length; i++)
        {
            fuente.addItem(fonts[i].toString());
        }
        texto.setFontPointSize(10);
        texto.setFontFamily("Arial");
        herramientas.addWidget(fuente);
        fuente.setCurrentIndex(fuente.findText(texto.fontFamily().toString()));
        fuente.currentIndexChanged.connect(this, "setFuente()");
        size = new QComboBox(herramientas);
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
        size.setCurrentIndex(size.findText(Integer.toString((int) texto.fontPointSize())));
        herramientas.addWidget(size);
        size.currentIndexChanged.connect(this, "setSize()");
        herramientas.addSeparator();
        herramientas.addAction("Buscar en el texto", this, "buscar()");
        herramientas.addAction("Buscar y reemplazar", this, "buscareemplaza()");
        herramientas.addSeparator();
        herramientas.addAction("Justificar a la izquierda", this, "justificaizquierda()"); //Justificar izquierda
        herramientas.addAction("Justificar al centro", this, "justificacentro()"); //Justificar centro
        herramientas.addAction("Justificar a la derecha", this, "justificaderecha()"); //Justificar derecha
        herramientas.addSeparator();
        herramientas.addAction("Ocultar menú superior", this, "ocultamenu()"); //Ocultar barra de menú
        herramientas.addAction("Ocultar barra de estado", this, "ocultaestado()"); //Ocultar barra de estado
        herramientas.addAction("Ver modo pantalla completa", this, "pantallacompleta()"); //Ver pantalla completa
        herramientas.addSeparator();
        switchcolor = new QAction("Cambiar color del fondo", this);
        herramientas.addAction(switchcolor); //Cambiar color oscuro claro
        switchcolor.triggered.connect(this, "cambiacolor()");
        herramientas.addSeparator();
        herramientas.addAction("Portapapeles", this, "clp()");
        QSize tam = new QSize(25,25);
        herramientas.setIconSize(tam);
        herramientas.setFixedHeight(25);
        herramientas.addWidget(sp);
        ventana.addWidget(herramientas);
        //Caja de texto
        if(configuraciones[9])
            claro();
        else
            oscuro();
        ventana.addWidget(texto);
        texto.cursorPositionChanged.connect(this, "filacolumna()");
        texto.textChanged.connect(this, "cuentapalabras()");
        texto.setContextMenuPolicy(Qt.ContextMenuPolicy.CustomContextMenu);
        texto.customContextMenuRequested.connect(this, "botonderecho()");
        texto.setTabStopWidth(4);
        //Barra de búsqueda
        //Añadimos los elementos de la barra
        busqueda.addWidget(plb);
        busqueda.addWidget(cont);
        busqueda.addWidget(go);
        busqueda.setFixedHeight(25); //Especificamos altura fija de la barra
        ventana.addWidget(busqueda); //Añadimos la barra a la ventana
        busqueda.setVisible(configuraciones[5]); //Ocultamos al barra
        go.clicked.connect(this, "buscapalabra()");
        //Barra de búsqueda y reemplazo
        //Elementos de la barra de búsqueda y reemplazo
        busquedar.addWidget(plb2);
        busquedar.addWidget(cont2);
        busquedar.addWidget(reem);
        busquedar.addWidget(fin);
        busquedar.addWidget(go2);
        busquedar.setFixedHeight(25); //Especificamos altura fija de la barra
        ventana.addWidget(busquedar); //Añadimos la barra a la ventana
        busquedar.setVisible(configuraciones[6]); //Ocultamos la barra
        go2.clicked.connect(this, "buscapalabrar()");
        //Barra de estado
        //Añadimos los elementos a la barra de estado
        estado.addWidget(fil);
        estado.addWidget(col);
        estado.addWidget(npal);
        estado.setFixedHeight(20);
        estado.setSizeGripEnabled(false);
        ventana.addWidget(estado); //Añadimos la barra a la ventana
        estado.setVisible(configuraciones[7]);
        //Atributos de la ventana
        ventana.setMargin(0);
        ventana.setVerticalSpacing(0);
        ventana.setHorizontalSpacing(0);
        if(configuraciones[8])
            this.showFullScreen();
        else
            this.showNormal();
        this.setWindowIcon(new QIcon("classpath:/ico/principal.png"));
        //Mostramos la aplicación
        texto.cursorPositionChanged.connect(this, "analizaposicion()");
        QApplication.clipboard().changed.connect(this, "alportas()");
        show();
    }

    private void clp()
    {
        clipboard pp = new clipboard(this, configuraciones[9]);
    }

    private void alportas()
    {
        portapapeles[(ppocupado++)%20]=(QApplication.clipboard().text().toString());
    }

    private void cambiatema()
    {
        List todos = herramientas.actions();
        for(int i=0; i<todos.size(); i++)
        {
            QAction tmp = (QAction) todos.get(i);
            tmp.setIcon(new QPixmap(inir+nombreiconosbarra[i]));
        }
        List ar = archivo.actions();
        for(int i=0; i<ar.size(); i++)
        {
            QAction tmp = (QAction) ar.get(i);
            tmp.setIcon(new QPixmap(inir+nombreiconosarchivo[i]));
        }
        List ed = editar.actions();
        for(int i=0; i<ed.size(); i++)
        {
            QAction tmp = (QAction) ed.get(i);
            tmp.setIcon(new QPixmap(inir+nombreiconoseditar[i]));
        }
        List ve = ver.actions();
        for(int i=2; i<ve.size(); i++)
        {
            QAction tmp = (QAction) ve.get(i);
            tmp.setIcon(new QPixmap(inir+nombreiconosver[i]));
        }
        List ay = ayuda.actions();
        for(int i=0; i<ay.size(); i++)
        {
            QAction tmp = (QAction) ay.get(i);
            tmp.setIcon(new QPixmap(inir+nombreiconosayuda[i]));
        }
        System.out.println("Tema de iconos actualizado");
    }

    private void analizaposicion()
    {
        cursiva.setChecked(texto.textCursor().charFormat().fontItalic());
        negrita.setChecked(texto.textCursor().charFormat().fontWeight()==75);
        subrayado.setChecked(texto.textCursor().charFormat().fontUnderline());
        fuente.setCurrentIndex(fuente.findText(texto.textCursor().charFormat().fontFamily().toString()));
        size.setCurrentIndex(size.findText(Integer.toString((int)texto.textCursor().charFormat().fontPointSize())));
        /*izquierda.setChecked(texto.alignment().toString() == Qt.AlignmentFlag.AlignLeft.toString());
        centro.setChecked(texto.alignment().toString() == Qt.AlignmentFlag.AlignCenter.toString());
        derecha.setChecked(texto.alignment().toString() == Qt.AlignmentFlag.AlignRight.toString());*/
    }

    protected void botonderecho()
    {
        QMenu btd = new QMenu();
        btd.addAction(new QIcon(inir+"copiar.png"), "Copiar", this, "copiar()");
        btd.addAction(new QIcon(inir+"cortar.png"), "Cortar", this, "cortar()");
        btd.addAction(new QIcon(inir+"pegar.png"), "Pegar", this, "pegar()");
        btd.addSeparator();
        btd.addAction(new QIcon(inir+"deshacer.png"), "Deshacer", this, "deshacer()");
        btd.addAction(new QIcon(inir+"rehacer.png"), "Rehacer", this, "rehacer()");
        if(configuraciones[9])
            btd.setStyleSheet("QMenu{background: #cecece ; border-top: 1px solid #bbb; border-bottom: 1px solid #bbb;}" +
                    "QMenu::icon{margin-left: 5px;}" +
                    "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#2d2d2d; border-left: 1px solid #bbb;border-right: 1px solid #bbb;}" +
                    "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                    "QMenu::separator{background: #bbb; height: 1px;}");
        else
            btd.setStyleSheet("QMenu{background: #2d2d2d ; border-top: 1px solid #222; border-bottom: 1px solid #222;}" +
                    "QMenu::icon{margin-left: 5px;}" +
                    "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#f9f9f9; border-left: 1px solid #222;border-right: 1px solid #222;}" +
                    "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                    "QMenu::separator{background: #222; height: 1px;}");
        btd.exec(QCursor.pos());
    }

    private void insertapp(String sel)
    {
        int i = ppocupado%20;
        portapapeles[20-i]=sel;
    }

    private void copiar(){texto.copy();}

    private void cortar()
    {
        texto.cut();
    }

    private void pegar()
    {
        texto.paste();
    }

    public void pordefecto()
    {
        configuraciones[0]=configuraciones[2]=configuraciones[3]=configuraciones[4]=configuraciones[5]=configuraciones[6]=configuraciones[8]=false;
        configuraciones[1]=configuraciones[7]=configuraciones[9]=true;
        preferencias[0] = "Arial";
        preferencias[1] = (10.0)+"";
        preferencias[2] = "1025";
        preferencias[3] = "600";
    }

    private void setFuente()
    {
        texto.setFontFamily(fuente.currentText());
        analizaposicion();
    }

    private void setSize()
    {
        texto.setFontPointSize(Double.parseDouble(size.currentText()));
        analizaposicion();
    }

    private void cargaconfiguraciones(Scanner s)
    {
        int i = 0;
        while(s.hasNextLine())
        {
            String actual = s.nextLine();
            if(i<10)
            {
                Boolean propiedad = Boolean.parseBoolean(actual);
                configuraciones[i] = propiedad;
            }
            else if(i<12)
            {
                preferencias[i-10+2]=actual;
            }
            else
            {
                licencia[i-12]=actual;
            }
            i++;
        }
        if(licencia[0].equalsIgnoreCase("null") && licencia[1].equalsIgnoreCase("null") && licencia[2].equalsIgnoreCase("null"))
            licenciactiva=false;
        else
            licenciactiva=true;
    }

    private void nuevo()
    {
        if(singuardar)
            dialogo(1);
        else
        {
            texto.setText("");
            singuardar = false;
        }
    }

    private void dialogo(int op)
    {
        dialogolanzado = true;
        confirmacion = new QDialog();
        QGridLayout cnf = new QGridLayout(confirmacion);
        QPushButton a = new QPushButton("Guardar");
        QPushButton b = new QPushButton("Continuar sin guardar");
        QLabel info = new QLabel("Antes de continuar, recuerde que tiene un archivo abierto sin guardar\ndesea guardarlo?");
        info.setAlignment(Qt.AlignmentFlag.AlignCenter);
        cnf.addWidget(info, 1, 1, 1, 2);
        cnf.addWidget(a,2,1);
        cnf.addWidget(b,2,2);
        confirmacion.setFixedSize(400, 130);
        confirmacion.setWindowFlags(Qt.WindowType.Widget);
        confirmacion.raise();
        confirmacion.setWindowTitle("Confirmación");
        if(configuraciones[9])
            confirmacion.setStyleSheet("background: #cecece; color: #2d2d2d;");
        else
            confirmacion.setStyleSheet("background: #2d2d2d; color: #f9f9f9;");
        a.clicked.connect(this, "guardar()");
        if(op==1)
            b.clicked.connect(this, "continuarnuevo()");
        else if(op==2)
            b.clicked.connect(this, "continuarabriendo()");
        else {
            info.setText("Antes de salir, recuerde que tiene un archivo abierto sin guardar\ndesea guardarlo?");
            b.setText("Salir sin guardar");
            b.clicked.connect(this, "continuarsaliendo()");
        }
        a.setStyleSheet("background: #37c4d6 ; color: #f9f9f9;");
        b.setStyleSheet("background: #d64937; color: #f9f9f9;");
        confirmacion.exec();
    }

    private void continuarnuevo()
    {
        confirmacion.close();
        texto.setText("");
        singuardar = false;
    }

    private void continuarabriendo()
    {
        confirmacion.close();
        QFileDialog a = new QFileDialog();
        String ruta = a.getOpenFileName(this);
        //Scanner loader = null;
        try {
            //loader = new Scanner(new File(ruta));
            String tmp = Files.toString(new File(ruta), Charsets.UTF_8);
            texto.setText(tmp);
            archivoactual = ruta;
            singuardar = false;
            archivoexiste = true;
        } catch (IOException ioe) {
            System.out.println(ioe);
            pordefecto();
        }
    }

    private void continuarsaliendo()
    {
        confirmacion.close();
        preferencias[2] = Integer.toString(this.size().width());
        preferencias[3] = Integer.toString(this.size().height());
        try
        {
            PrintWriter guarda = new PrintWriter(new File("./cfg"));
            for(int i=0; i<10; i++)
                guarda.println(configuraciones[i]);
            for(int i=0; i<2; i++)
                guarda.println(preferencias[i+2]);
            for(int i=0; i<3; i++)
                guarda.println(licencia[i]);
            guarda.close();
        }
        catch(IOException ioe)
        {
            System.out.print(ioe);
        }
        this.close();
    }

    private void abrir()
    {
        if(singuardar)
        {
            dialogo(2);
        }
        else
        {
            QFileDialog a = new QFileDialog();
            String ruta = a.getOpenFileName(this);
            //Scanner loader = null;
            try {
                //loader = new Scanner(new File(ruta));
                String tmp = Files.toString(new File(ruta), Charsets.UTF_8);
                texto.setText(tmp);
                archivoactual = ruta;
                singuardar = false;
                archivoexiste = true;
            } catch (IOException ioe) {
                System.out.println(ioe);
                pordefecto();
            }
        }
    }

    private void notifiguardado()
    {
        exito = new QDialog();
        exito.setFixedSize(320, 120);
        exito.move((anchura / 2) - (320 / 2), (altura / 2) - (120 / 2));
        exito.setWindowTitle("Información");
        exito.setWindowFlags(Qt.WindowType.Widget);
        exito.raise();
        QGridLayout tmp = new QGridLayout();
        exito.setLayout(tmp);
        QLabel msg = new QLabel("Archivo guardado con éxito");
        msg.setAlignment(Qt.AlignmentFlag.AlignCenter);
        QPushButton ok = new QPushButton("Aceptar");
        ok.setStyleSheet("background: #37c4d6 ; color: #f9f9f9;");
        tmp.addWidget(msg,1,1);
        tmp.addWidget(ok,2,1);
        if(configuraciones[9])
            exito.setStyleSheet("background: #cecece; color: #2d2d2d;");
        else
            exito.setStyleSheet("background: #2d2d2d; color: #f9f9f9;");
        ok.clicked.connect(this, "notificierra()");
        exito.exec();
    }

    private void notificierra()
    {
        exito.close();
    }

    private void guardar()
    {
        if(singuardar && !archivoexiste)
        {
            try
            {
                QFileDialog a = new QFileDialog();
                String ruta = a.getSaveFileName(this);
                Files.write(texto.toPlainText(), new File(ruta), Charsets.UTF_8);
                singuardar = false;
                archivoexiste = true;
                archivoactual = ruta;
                notifiguardado();
            }
            catch(IOException ioe)
            {
                System.out.print(ioe);
            }
        }
        else
        {
            try
            {
                Files.write(texto.toPlainText(), new File(archivoactual), Charsets.UTF_8);
                singuardar = false;
                archivoexiste = true;
                notifiguardado();
            }
            catch(IOException ioe)
            {
                System.out.print(ioe);
            }
        }
        if(dialogolanzado) {
            confirmacion.close();
            dialogolanzado = false;
        }
    }

    private void guardarcomo()
    {
        try
        {
            QFileDialog a = new QFileDialog();
            String ruta = a.getSaveFileName(this);
            Files.write(texto.toPlainText(), new File(ruta), Charsets.UTF_8);
            singuardar = false;
            archivoexiste = true;
            archivoactual = ruta;
            notifiguardado();
        }
        catch(IOException ioe)
        {
            System.out.print(ioe);
        }
    }

    private void exportar()
    {
        QPrinter pdf = new QPrinter(QPrinter.PrinterMode.HighResolution);
        pdf.setOutputFormat(QPrinter.OutputFormat.PdfFormat);
        QFileDialog a = new QFileDialog();
        String ruta = a.getSaveFileName(this);
        if(!ruta.endsWith(".pdf"))
            ruta += ".pdf";
        pdf.setOutputFileName(ruta);
        texto.print(pdf);
        notifiguardado();
    }

    private void imprimir()
    {
        System.out.println("imprimir");
    }

    protected void keyPressEvent(final QKeyEvent event) {
        int key = event.key();
        if(key == Qt.Key.Key_F10.value())
            ocultamenu();
        System.out.println("Tecla F10 presionada, se mostrará/ocultará el menú principal");
    }

    protected void closeEvent(final QCloseEvent event) {
        salir();
    }

    private void acercade()
    {
        about acd = new about(this, configuraciones[9]); //Lanza ventana de información
        System.out.println("Ventana de información lanzada");
    }
    private void registro()
    {
        register reg = new register(this, configuraciones[9]); //Lanza ventana de registro
        System.out.println("Ventana de registro lanzada");
    }

    private void buscar()
    {
        if(busquedar.isVisible())
        {
            busquedar.setVisible(false);
            configuraciones[6] = false;
        }
        configuraciones[5] = !configuraciones[5];
        busqueda.setVisible(configuraciones[5]);
    }

    private void buscareemplaza()
    {
        if(busqueda.isVisible())
        {
            busqueda.setVisible(false);
            configuraciones[5] = false;
        }
        configuraciones[6] = !configuraciones[6];
        busquedar.setVisible(configuraciones[6]);
    }

    private void buscapalabra()
    {
        if(!cont.text().equalsIgnoreCase(" "));
            busca(cont.text());
        texto.setFocus();
    }

    private void buscapalabrar()
    {
        if(!cont.text().equalsIgnoreCase(" ") && !fin.text().equalsIgnoreCase(" "));
            buscar(cont.text(), fin.text());
        texto.setFocus();
    }

    private void busca(String e)
    {
        if(!indicemovido) {
            texto.moveCursor(QTextCursor.MoveOperation.Start);
            actual = texto.textCursor();
            indicemovido = true;
        }
        actual = texto.textCursor();
        texto.find(e);
        if(texto.textCursor().position() == actual.position()) {
            texto.moveCursor(QTextCursor.MoveOperation.Start);
            texto.find(e);
        }
    }

    private void buscar(String e, String f)
    {
        String tmp = texto.toPlainText();
        tmp = tmp.replaceFirst(e, f);
        texto.setText(tmp);
    }

    private void cuentapalabras()
    {
        singuardar=true;
        String [] toltexto = texto.toPlainText().split(" |\n");
        int palabrejas = toltexto.length;
        npal.setText("Palabras: " + palabrejas);
    }

    private void filacolumna()
    {
        int f = texto.textCursor().blockNumber();
        int c = texto.textCursor().columnNumber();
        fil.setText("Fila: " + f);
        col.setText("Columna: "+ c);
    }

    private void pagado(String n, String a, String c)
    {
        nreg=n; sreg=a; mreg=c;
    }

    private void claro()
    {
        inir="classpath:/ico/claro/";
        coloresoscuros.setIcon(new QIcon(inir+"vacio.png"));
        menuprinc.setStyleSheet("background: #cecece; color: #2d2d2d;");
        archivo.setStyleSheet("QMenu{background: #cecece ; border-top: 1px solid #bbb; border-bottom: 1px solid #bbb;}" +
                "QMenu::icon{margin-left: 5px;}" +
                "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#2d2d2d; border-left: 1px solid #bbb;border-right: 1px solid #bbb;}" +
                "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                "QMenu::separator{background: #bbb; height: 1px;}");
        editar.setStyleSheet("QMenu{background: #cecece ; border-top: 1px solid #bbb; border-bottom: 1px solid #bbb;}" +
                "QMenu::icon{margin-left: 5px;}" +
                "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#2d2d2d; border-left: 1px solid #bbb;border-right: 1px solid #bbb;}" +
                "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                "QMenu::separator{background: #bbb; height: 1px;}");
        ver.setStyleSheet("QMenu{background: #cecece ; border-top: 1px solid #bbb; border-bottom: 1px solid #bbb;}" +
                "QMenu::icon{margin-left: 5px;}" +
                "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#2d2d2d; border-left: 1px solid #bbb;border-right: 1px solid #bbb;}" +
                "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                "QMenu::separator{background: #bbb; height: 1px;}");
        ayuda.setStyleSheet("QMenu{background: #cecece ; border-top: 1px solid #bbb; border-bottom: 1px solid #bbb;}" +
                "QMenu::icon{margin-left: 5px;}" +
                "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#2d2d2d; border-left: 1px solid #bbb;border-right: 1px solid #bbb;}" +
                "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                "QMenu::separator{background: #bbb; height: 1px;}");
        busqueda.setStyleSheet("QToolBar{background: #cecece; border-top: 1px solid #bbb;}" +
                "QLabel {color: #2d2d2d; margin-left: 5px;}" +
                "QLineEdit {border: 1px solid #bbb; background: #dedede; color: #2d2d2d; margin-top:1px;}" +
                "QPushButton {background: #37c4d6 ; color: #f9f9f9; margin: 2px; padding: 15px; width: 40px;}" +
                "QToolBar::separator{width: 1px; background: #dedede; border-top: 2px solid #bbb; border-bottom: 2px solid #bbb;}");
        busquedar.setStyleSheet("QToolBar{background: #cecece; border-top: 1px solid #bbb;}" +
                "QLabel {color: #2d2d2d; margin-left: 5px;}" +
                "QLineEdit {border: 1px solid #bbb; background: #dedede; color: #2d2d2d; margin-top:1px;}" +
                "QPushButton {background: #37c4d6 ; color: #f9f9f9; margin: 2px; padding: 15px; width: 105px;}" +
                "QToolBar::separator{width: 1px; background: #dedede; border-top: 2px solid #bbb; border-bottom: 2px solid #bbb;}");
        herramientas.setStyleSheet("QToolBar{background: #cecece; border-bottom: 1px solid #bbb; border-top: 1px solid #bbb;}" +
                "QToolButton{padding: 2px; margin: 2px 5px 2px 5px;}" +
                "QToolButton::icon{margin: 2px;}" +
                "QToolButton:hover{background: #ddd; border: 1px solid #bbb; padding: 1px;}" +
                "QToolButton:checked{background: #ddd; border: 1px solid #bbb; padding: 1px;}" +
                "QToolBar::separator{width: 1px; background: #bbb; border-top: 2px solid #cecece; border-bottom: 2px solid #cecece;}");
        texto.setStyleSheet("QTextEdit {border: 0px solid #000; background: #dedede; color: #2d2d2d;}" +
                "QScrollBar{background: #cecece;}" +
                "QScrollBar::handle{background: #d64937; border: 1px solid #bbb;}");
        estado.setStyleSheet("QStatusBar {border:none; border-top: 1px solid #bbb; background: #cecece;} " +
                "QStatusBar::item {border: none;} " +
                "QStatusBar QLabel{color: #2d2d2d;margin-left:2px;}");
        coloresclaros.setIcon(new QIcon(inir+"activado.png"));
        cambiatema();
    }

    private void oscuro()
    {
        inir="classpath:/ico/oscuro/";
        coloresclaros.setIcon(new QIcon(inir+"vacio.png"));
        menuprinc.setStyleSheet("background: #2d2d2d; color: #f9f9f9;");
        archivo.setStyleSheet("QMenu{background: #2d2d2d ; border-top: 1px solid #222; border-bottom: 1px solid #222;}" +
                "QMenu::icon{margin-left: 5px;}" +
                "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#f9f9f9; border-left: 1px solid #222;border-right: 1px solid #222;}" +
                "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                "QMenu::separator{background: #222; height: 1px;}");
        editar.setStyleSheet("QMenu{background: #2d2d2d ; border-top: 1px solid #222; border-bottom: 1px solid #222;}" +
                "QMenu::icon{margin-left: 5px;}" +
                "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#f9f9f9; border-left: 1px solid #222;border-right: 1px solid #222;}" +
                "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                "QMenu::separator{background: #222; height: 1px;}");
        ver.setStyleSheet("QMenu{background: #2d2d2d ; border-top: 1px solid #222; border-bottom: 1px solid #222;}" +
                "QMenu::icon{margin-left: 5px;}" +
                "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#f9f9f9; border-left: 1px solid #222;border-right: 1px solid #222;}" +
                "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                "QMenu::separator{background: #222; height: 1px;}");
        ayuda.setStyleSheet("QMenu{background: #2d2d2d ; border-top: 1px solid #222; border-bottom: 1px solid #222;}" +
                "QMenu::icon{margin-left: 5px;}" +
                "QMenu::item{padding: 2px 25px 2px 25px; border: 1px solid transparent; color:#f9f9f9; border-left: 1px solid #222;border-right: 1px solid #222;}" +
                "QMenu::item:selected{background: #d64937; border-right: 1px solid #d64937; border-left: 1px solid #d64937;}" +
                "QMenu::separator{background: #222; height: 1px;}");
        busqueda.setStyleSheet("QToolBar{background: #2d2d2d; border-top: 1px solid #222;}" +
                "QLabel {color: #ddd; margin-left: 5px;}" +
                "QLineEdit {border: 1px solid #222; background: #333; color: #ddd; margin-top:1px;}" +
                "QPushButton {background: #37c4d6 ; color: #f9f9f9; margin: 2px; padding: 15px; width: 40px;}" +
                "QToolBar::separator{width: 1px; background: #222; border-top: 2px solid #2d2d2d; border-bottom: 2px solid #2d2d2d;}");
        busquedar.setStyleSheet("QToolBar{background: #2d2d2d; border-top: 1px solid #222;}" +
                "QLabel {color: #ddd; margin-left: 5px;}" +
                "QLineEdit {border: 1px solid #222; background: #333; color: #ddd; margin-top:1px;}" +
                "QPushButton {background: #37c4d6 ; color: #f9f9f9; margin: 2px; padding: 15px; width: 105px;}" +
                "QToolBar::separator{width: 1px; background: #222; border-top: 2px solid #2d2d2d; border-bottom: 2px solid #2d2d2d;}");
        herramientas.setStyleSheet("QToolBar{background: #2d2d2d; border-bottom: 1px solid #222; border-top: 1px solid #222;}" +
                "QToolButton{padding: 2px; margin: 2px 5px 2px 5px;}" +
                "QToolButton::icon{margin: 2px;}" +
                "QToolButton:hover{background: #333; border: 1px solid #222; padding: 1px;}" +
                "QToolButton:checked{background: #333; border: 1px solid #222; padding: 1px;}" +
                "QToolBar::separator{width: 1px; background: #222; border-top: 2px solid #2d2d2d; border-bottom: 2px solid #2d2d2d;}");
        texto.setStyleSheet("QTextEdit {border: 0px solid #000; background: #333; color: #ddd;}" +
                "QScrollBar{background: #2d2d2d;}" +
                "QScrollBar::handle{background: #d64937; border: 1px solid #222;}");
        estado.setStyleSheet("QStatusBar {border:none; border-top: 1px solid #222; background: #2d2d2d;} " +
                "QStatusBar::item {border: none;} " +
                "QStatusBar QLabel{color: #dedede;margin-left:2px;}");
        coloresoscuros.setIcon(new QIcon(inir+"activado.png"));
        cambiatema();
    }

    private void cambiacolor()
    {
        if(configuraciones[9])
        {
            oscuro();
            switchcolor.setIcon(new QIcon(inir+"claro.png"));
        }
        else
        {
            claro();
            switchcolor.setIcon(new QIcon(inir+"oscuro.png"));
        }
        configuraciones[9] = !configuraciones[9];
    }

    private void negrita()
    {
        if(texto.fontWeight()==75)
            texto.setFontWeight(50);
        else
            texto.setFontWeight(75);
        analizaposicion();
    }

    private void cursiva()
    {
        if(texto.fontItalic())
            texto.setFontItalic(false);
        else
            texto.setFontItalic(true);
        analizaposicion();
        cambiatema();
    }

    private void subrayado()
    {
        configuraciones[4] = !configuraciones[4];
        texto.setFontUnderline(configuraciones[4]);
        if(texto.fontUnderline())
            texto.setFontUnderline(false);
        else
            texto.setFontUnderline(true);
        analizaposicion();
    }

    private void justificaizquierda()
    {
        texto.setAlignment(Qt.AlignmentFlag.AlignLeft);
    }

    private void justificacentro()
    {
        texto.setAlignment(Qt.AlignmentFlag.AlignCenter);
    }

    private void justificaderecha()
    {
        texto.setAlignment(Qt.AlignmentFlag.AlignRight);
    }

    private void deshacer()
    {
        texto.undo();
    }

    private void rehacer()
    {
        texto.redo();
    }

    private void pantallacompleta()
    {
        if(!configuraciones[8])
            this.showFullScreen();
        else
            this.showNormal();
        configuraciones[8] = !configuraciones[8];
    }
    private void ocultamenu()
    {
        configuraciones[0] = !configuraciones[0];
        menuprinc.setVisible(configuraciones[0]);
    }

    private void ocultaestado()
    {
        configuraciones[7] = !configuraciones[7];
        estado.setVisible(configuraciones[7]);
    }

    private void ocultaherramientas()
    {
        configuraciones[1] = !configuraciones[1];
        herramientas.setVisible(configuraciones[1]);
    }

    public void licencia(String[] lic)
    {
        licencia = lic;
        licenciactiva = true;
    }

    public boolean registrado()
    {
        return licenciactiva;
    }

    public String[] getLicencia()
    {
        return licencia;
    }

    private void salir()
    {
        if(singuardar)
            dialogo(3);
        preferencias[2] = Integer.toString(this.size().width());
        preferencias[3] = Integer.toString(this.size().height());
        try
        {
            PrintWriter guarda = new PrintWriter(new File("./cfg"));
            for(int i=0; i<10; i++)
                guarda.println(configuraciones[i]);
            for(int i=0; i<2; i++)
                guarda.println(preferencias[i+2]);
            for(int i=0; i<3; i++)
                guarda.println(licencia[i]);
            guarda.close();
        }
        catch(IOException ioe)
        {
            System.out.print(ioe);
        }
        this.close();
    }
}