package com.ingenieria.proyecto;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.mongodb.repository.Query;

import javax.swing.*;
import javax.xml.soap.Text;
import java.util.*;

@SpringUI
@Theme("valo")
public class MiUI extends UI {

    public static String cId;
    @Autowired
    RepositorioProfesor repoProfe;
    @Autowired
    RepositorioAlumno repoAlumno;

    //Profesor
    //Grid para mostrar todos los profesores
    Grid<Profesor> profesoresMostrar = new Grid<>();
    //Grid para mostrar profesor por Id
    Grid<Profesor> profesoresMostrarId = new Grid<>();
    //Grid para eliminar profesor
    Grid<Profesor> profesoresEliminar = new Grid<>();
    //Lista para guardar todos profesores obtenidos
    List<Profesor> profesoresTodos = new ArrayList<>();
    //Lista para guardar id de todos profesores obtenidos
    List<String> lsProfesorId = new ArrayList<>();

    //Lista para mostrar los cursos disponibles a inscribir
    List<Profesor> lsCursoId = new ArrayList<>();

    //Combobox para mostrar id de profesor para buscar
    ComboBox<Profesor> cboProfesorMostrarId = new ComboBox<>();
    //Combobox para mostrar id de profesor para actualizar
    ComboBox<Profesor> cboProfesorActualizar = new ComboBox<>();
    //Combobox para mostrar id de profesor para eliminar
    ComboBox<Profesor> cboProfesorEliminar = new ComboBox<>();

    //Combobox para mostrar tipo de alumno al inscribirse
    ComboBox cboAlumnoTipo = new ComboBox();
    List<String> lsAlumnoTipo = new ArrayList<>();


    //Combobox para mostrar curso a inscribir para alumno
    ComboBox cboAlumnoCurso = new ComboBox();
    List<String> lsAlumnoCurso = new ArrayList<>();

    Profesor profe;

    List<Alumno> alumnosTodosCurso1 = new ArrayList<>();
    List<Alumno> alumnosTodosCurso2 = new ArrayList<>();


    @Override
    protected void init(VaadinRequest request) {

        profesoresTodos = repoProfe.findAll();
        alumnosTodosCurso1 = repoAlumno.find1();
        alumnosTodosCurso2 = repoAlumno.find2();

        if (profesoresTodos.size() > 0) {
            for (Profesor p : profesoresTodos) {
                lsProfesorId.add(p.getId());
                lsAlumnoCurso.add(p.getCurso().getIdCurso());
            }
            cboProfesorMostrarId.setItems((List) lsProfesorId);
            cboProfesorActualizar.setItems((List) lsProfesorId);
            cboProfesorEliminar.setItems((List) lsProfesorId);
            cboAlumnoCurso.setItems((List) lsAlumnoCurso);
        }

        lsAlumnoTipo.add("Interno");
        lsAlumnoTipo.add("Externo");
        cboAlumnoTipo.setItems(lsAlumnoTipo);


        //Layout principal, aquí pondré todos los otros layouts
        VerticalLayout layout = new VerticalLayout();

        //Layout del título y menú
        VerticalLayout layoutPrincipal = new VerticalLayout();
        Label etiqueta = new Label("Aplicación de Cursos");
        etiqueta.addStyleName(ValoTheme.LABEL_H1);


        //Inicia layout para guardar profesores
        VerticalLayout layoutProfesorGuardar = new VerticalLayout();
        TextField txtProfesorId = new TextField();
        txtProfesorId.setPlaceholder("Id");
        TextField txtProfesorNombre = new TextField();
        txtProfesorNombre.setPlaceholder("Nombre");
        TextField txtProfesorTipo = new TextField();
        txtProfesorTipo.setPlaceholder("Tipo");
        TextField txtProfesorDepartamento = new TextField();
        txtProfesorDepartamento.setPlaceholder("Departamento");
        Button btnProfesorGuardar = new Button("Guardar");
        btnProfesorGuardar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layoutProfesorGuardar.addComponents(txtProfesorId, txtProfesorNombre, txtProfesorTipo, txtProfesorDepartamento, btnProfesorGuardar);
        //Termina layout para guardar profesores


        //Layout para mostrar todos los profesores
        VerticalLayout layoutProfesorMostrarTodos = new VerticalLayout();
        profesoresMostrar.setItems(profesoresTodos);
        profesoresMostrar.addColumn(Profesor::getId).setCaption("Id");
        profesoresMostrar.addColumn(Profesor::getNombre).setCaption("Nombre");
        profesoresMostrar.addColumn(Profesor::getTipo).setCaption("Tipo");
        profesoresMostrar.addColumn(Profesor::getDepartamento).setCaption("Departamento");
        layoutProfesorMostrarTodos.addComponent(profesoresMostrar);
        //Termina layout para mostrar todos los profesores


        //Inicia layout para actualizar profesor por Id
        VerticalLayout layoutProfesorActualizar = new VerticalLayout();
        List<String> idProfes = new ArrayList<>();
        cboProfesorActualizar.clear();
        cboProfesorActualizar.setPlaceholder("Id");
        for (Profesor p : profesoresTodos) {
            idProfes.add(p.getId());
        }
        cboProfesorActualizar.setItems((List) idProfes);
        cboProfesorActualizar.setScrollToSelectedItem(true);
        TextField txtProfesorActualizarNombre = new TextField();
        txtProfesorActualizarNombre.setPlaceholder("Nombre");
        TextField txtProfesorActualizarTipo = new TextField();
        txtProfesorActualizarTipo.setPlaceholder("Tipo");
        TextField txtProfesorActualizarDepartamento = new TextField();
        txtProfesorActualizarDepartamento.setPlaceholder("Departamento");
        TextField txtProfesorActualizarCurso = new TextField();
        txtProfesorActualizarCurso.setPlaceholder("Curso");
        Button btnProfesorActualizar = new Button("Actualizar");
        btnProfesorActualizar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layoutProfesorActualizar.addComponents(cboProfesorActualizar, txtProfesorActualizarNombre, txtProfesorActualizarTipo, txtProfesorActualizarDepartamento, txtProfesorActualizarCurso, btnProfesorActualizar);
        //Termina layout para actualizar profesor por Id


        //Layout para mostrar profesor por Id
        VerticalLayout layoutProfesorMostrarId = new VerticalLayout();
        cboProfesorMostrarId.setPlaceholder("Id");
        cboProfesorMostrarId.setItems((List) lsProfesorId);
        layoutProfesorMostrarId.addComponents(cboProfesorMostrarId, profesoresMostrarId);
        //Termina layout para mostrar profesor por Id


        //Layout para eliminar profesor por Id
        VerticalLayout layoutProfesorEliminar = new VerticalLayout();
        List<String> lsProfesorEliminar = new ArrayList<>();
        cboProfesorEliminar.setPlaceholder("Id");
        for (Profesor p : profesoresTodos) {
            lsProfesorEliminar.add(p.getId());
        }
        cboProfesorEliminar.setItems((List) lsProfesorEliminar);
        Button btnEliminarProfesor = new Button("Eliminar");
        btnEliminarProfesor.addStyleName(ValoTheme.BUTTON_DANGER);
        layoutProfesorEliminar.addComponents(cboProfesorEliminar, profesoresEliminar, btnEliminarProfesor);
        //Termina layout para eliminar profesor por Id


        //Inicia layout para guardar Alumnos
        FormLayout lGuardarAlumno = new FormLayout();
        TextField txtNombreAlumno = new TextField();
        txtNombreAlumno.setPlaceholder("Nombre");
        txtNombreAlumno.setIcon(VaadinIcons.USER);
        txtNombreAlumno.setRequiredIndicatorVisible(true);
        cboAlumnoTipo.setIcon(VaadinIcons.USERS);
        cboAlumnoTipo.setRequiredIndicatorVisible(true);
        cboAlumnoCurso.setIcon(VaadinIcons.NOTEBOOK);
        cboAlumnoCurso.setRequiredIndicatorVisible(true);
        TextArea txtCursoInscribir = new TextArea();
        txtCursoInscribir.setIcon(VaadinIcons.INFO);
        txtCursoInscribir.setEnabled(false);
        Button btnAlumnoGuardar = new Button("Guardar");
        btnAlumnoGuardar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        lGuardarAlumno.addComponents(txtNombreAlumno, cboAlumnoTipo, cboAlumnoCurso, txtCursoInscribir, btnAlumnoGuardar);
        //Termina layout para guardar alumnos

        //Layout para mostrar estadisticas
        HorizontalLayout layoutEstadistica = new HorizontalLayout();
        TextArea estadisticas1 = new TextArea();
        estadisticas1.setEnabled(false);
        TextArea estadisticas2 = new TextArea();
        estadisticas2.setEnabled(false);
        if (alumnosTodosCurso1.size() > 0)
            estadisticas1.setValue("Curso: " + alumnosTodosCurso1.get(0).getCurso().getNombre() + " Alumnos: " + alumnosTodosCurso1.size());
        if (alumnosTodosCurso2.size() > 0)
            estadisticas2.setValue("Curso: " + alumnosTodosCurso2.get(0).getCurso().getNombre() + " Alumnos: " + alumnosTodosCurso2.size());

        layoutEstadistica.addComponents(estadisticas1, estadisticas2);
        //Termina layout para mostrar estadisticas


        MenuBar menuPrincipal = new MenuBar();
        MenuBar.MenuItem profesores = menuPrincipal.addItem("Profesores", new ExternalResource("https://image.flaticon.com/icons/svg/42/42912.svg"), null);
        MenuBar.MenuItem alumnos = menuPrincipal.addItem("Alumnos", VaadinIcons.ACADEMY_CAP, null);

        //Inicia submenú profesores
        profesores.addSeparator();
        profesores.addItem("Guardar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutProfesorGuardar);
            setContent(layout);
        });
        profesores.addSeparator();
        profesores.addItem("Actualizar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutProfesorActualizar);
            setContent(layout);
        });
        profesores.addSeparator();
        profesores.addItem("Eliminar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutProfesorEliminar);
            setContent(layout);
        });
        profesores.addSeparator();
        profesores.addItem("Mostrar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutProfesorMostrarTodos);
            setContent(layout);
        });
        profesores.addSeparator();
        profesores.addItem("Buscar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutProfesorMostrarId);
            setContent(layout);
        });
        profesores.addSeparator();


        //Inicia submenú alumnos
        alumnos.addSeparator();
        alumnos.addItem("Inscripción", VaadinIcons.HANDSHAKE, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, lGuardarAlumno);
            setContent(layout);
        });
        alumnos.addSeparator();
        alumnos.addItem("Estadísticas", VaadinIcons.TABLE, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutEstadistica);
            setContent(layout);
        });
        layoutPrincipal.addComponents(etiqueta, menuPrincipal);

        //Agregamos componentes al layout principal
        layout.addComponents(layoutPrincipal);
        setContent(layout);


        //Evento del boton para guardar profesores
        btnProfesorGuardar.addClickListener(event -> {
            String pId = txtProfesorId.getValue();
            String pNombre = txtProfesorNombre.getValue();
            String pTipo = txtProfesorTipo.getValue();
            String pDepartamento = txtProfesorDepartamento.getValue();
            Estatus estatus = new Estatus(true, "Registro exitoso");

            if (pId.equals("") || pNombre.equals("") || pTipo.equals("") || pDepartamento.equals("")) {
                estatus.setStatus(false);
                estatus.setMensaje("Rellena todos los campos");
            } else {
                for (Profesor p : profesoresTodos) {
                    if (p.getId().contentEquals(pId)) {
                        estatus.setStatus(false);
                        estatus.setMensaje("Profesor ya registrado");
                        break;
                    }
                }
            }

            if (estatus.isStatus() == true) {
                Profesor profesor = new Profesor(pId, pNombre, pTipo, new Curso("2",
                        "Arte",
                        "5 sem",
                        "2017/12/13",
                        "2018/01/13",
                        "L-J",
                        521.2F), pDepartamento);
                repoProfe.save(profesor);
                txtProfesorId.setValue("");
                txtProfesorNombre.setValue("");
                txtProfesorTipo.setValue("");
                txtProfesorDepartamento.setValue("");
                Notification.show(estatus.getMensaje(), Notification.Type.WARNING_MESSAGE);
                profesoresTodos = repoProfe.findAll();
                cboProfesorMostrarId.clear();
                cboProfesorActualizar.clear();
                cboProfesorEliminar.clear();
                lsProfesorId.clear();
                for (Profesor p : profesoresTodos) {
                    lsProfesorId.add(p.getId());
                }
                cboProfesorMostrarId.setItems((List) lsProfesorId);
                cboProfesorActualizar.setItems((List) lsProfesorId);
                cboProfesorEliminar.setItems((List) lsProfesorId);
                profesoresMostrar.removeAllColumns();
                profesoresMostrar.setItems(profesoresTodos);
                profesoresMostrar.addColumn(Profesor::getId).setCaption("Id");
                profesoresMostrar.addColumn(Profesor::getNombre).setCaption("Nombre");
                profesoresMostrar.addColumn(Profesor::getTipo).setCaption("Tipo");
                profesoresMostrar.addColumn(Profesor::getDepartamento).setCaption("Departamento");

            } else {
                Notification.show(estatus.getMensaje(), Notification.Type.ERROR_MESSAGE);
            }
        });
        //Termina evento del boton para guardar profesores


        //Evento del boton para actualizar profesores
        btnProfesorActualizar.addClickListener(event -> {
            String pId = cboProfesorActualizar.getSelectedItem().toString().replace("Optional[", "").replace("]", "");
            String pNombre = txtProfesorActualizarNombre.getValue();
            String pTipo = txtProfesorActualizarTipo.getValue();
            String pDepartamento = txtProfesorActualizarDepartamento.getValue();
            Estatus estatus = new Estatus(true, "Actualización exitosa");

            if (pId.equals("") || pNombre.equals("") || pTipo.equals("") || pDepartamento.equals("")) {
                estatus.setStatus(false);
                estatus.setMensaje("Rellena todos los campos");
            }

            if (estatus.isStatus() == true) {
                Profesor profesor = new Profesor(pId, pNombre, pTipo, new Curso(), pDepartamento);
                repoProfe.save(profesor);
                cboProfesorActualizar.setPlaceholder("Id");
                txtProfesorActualizarNombre.setValue("");
                txtProfesorActualizarTipo.setValue("");
                txtProfesorActualizarDepartamento.setValue("");
                Notification.show(estatus.getMensaje(), Notification.Type.WARNING_MESSAGE);
                profesoresTodos = repoProfe.findAll();
                cboProfesorMostrarId.clear();
                cboProfesorActualizar.clear();
                cboProfesorEliminar.clear();
                lsProfesorId.clear();
                for (Profesor p : profesoresTodos) {
                    lsProfesorId.add(p.getId());
                }
                cboProfesorMostrarId.setItems((List) lsProfesorId);
                cboProfesorActualizar.setItems((List) lsProfesorId);
                cboProfesorEliminar.setItems((List) lsProfesorId);
                profesoresMostrar.removeAllColumns();
                profesoresMostrar.setItems(profesoresTodos);
                profesoresMostrar.addColumn(Profesor::getId).setCaption("Id");
                profesoresMostrar.addColumn(Profesor::getNombre).setCaption("Nombre");
                profesoresMostrar.addColumn(Profesor::getTipo).setCaption("Tipo");
                profesoresMostrar.addColumn(Profesor::getDepartamento).setCaption("Departamento");
            } else {
                Notification.show(estatus.getMensaje(), Notification.Type.ERROR_MESSAGE);
            }

        });
        //Termina evento del boton para actualizar profesores


        //Evento del boton para guardar alumno
        btnAlumnoGuardar.addClickListener(event -> {
            //Datos del alumno
            String aId = "";
            String aNombre = txtNombreAlumno.getValue();
            String aTipo = cboAlumnoTipo.getSelectedItem().toString().replace("Optional[", "").replace("]", "").toLowerCase();
            StringTokenizer st = new StringTokenizer(aNombre);
            String cId = cboAlumnoCurso.getSelectedItem().toString().replace("Optional[", "").replace("]", "");

            Estatus estatus = new Estatus(true, "Alumno guardado");

            if (aNombre.equals("") || aTipo.equals("optional.empty") || cId.equals("Optional.empty")) {
                estatus.setStatus(false);
                estatus.setMensaje("Rellena todos los campos");
            } else {
                StringTokenizer stC = new StringTokenizer(profe.getCurso().getNombre());
                while (st.hasMoreElements()) {
                    aId += st.nextElement().toString().charAt(0);
                }

                while (stC.hasMoreElements()) {
                    aId += stC.nextElement().toString().charAt(0);
                }

                switch (cId) {
                    case "1":
                        for (Alumno a : alumnosTodosCurso1) {
                            if (a.getId().equals(aId)) {
                                estatus.setStatus(false);
                                estatus.setMensaje("Alumno ya inscrito en este curso");
                                break;
                            } else {
                                if (alumnosTodosCurso1.size() > 2) {
                                    estatus.setStatus(false);
                                    estatus.setMensaje("Curso lleno");
                                }
                            }
                        }
                        break;
                    case "2":
                        for (Alumno a : alumnosTodosCurso2) {
                            if (a.getId().equals(aId)) {
                                estatus.setStatus(false);
                                estatus.setMensaje("Alumno ya inscrito en este curso");
                                break;
                            } else {
                                if (alumnosTodosCurso2.size() > 2) {
                                    estatus.setStatus(false);
                                    estatus.setMensaje("Curso lleno");
                                }
                            }
                        }
                        break;
                }
            }

            if (estatus.isStatus() == true) {
                //Datos del curso
                String cNombre = profe.getCurso().getNombre();
                String cDuracion = profe.getCurso().getDuracion();
                String cInicio = profe.getCurso().getfInicio();
                String cTermino = profe.getCurso().getfTermino();
                String cHorarios = profe.getCurso().getHorarios();
                Float cCosto = profe.getCurso().getCosto();

                Curso curso = new Curso(cId, cNombre, cDuracion, cInicio, cTermino, cHorarios, cCosto);
                Alumno alumno = new Alumno(aId, aNombre, aTipo, curso);
                repoAlumno.save(alumno);
                txtNombreAlumno.setValue("");
                cboAlumnoTipo.setValue("");
                cboAlumnoCurso.setValue("");
                txtCursoInscribir.setValue("");
                alumnosTodosCurso1 = repoAlumno.find1();
                alumnosTodosCurso2 = repoAlumno.find2();
                Notification.show(estatus.getMensaje(), Notification.Type.WARNING_MESSAGE);
            } else {
                Notification.show(estatus.getMensaje(), Notification.Type.WARNING_MESSAGE);
            }
        });
        //Termina evento del boton para guardar alumno


        //Evento del comboBox para buscar profesor por Id
        cboProfesorMostrarId.addValueChangeListener(event -> {
            profesoresMostrarId.removeAllColumns();
            String id = cboProfesorMostrarId.getSelectedItem().toString().replace("Optional[", "").replace("]", "");
            Profesor profe = repoProfe.findOne(id);
            profesoresMostrarId.setItems(profe);
            profesoresMostrarId.addColumn(Profesor::getId).setCaption("Id");
            profesoresMostrarId.addColumn(Profesor::getNombre).setCaption("Nombre");
            profesoresMostrarId.addColumn(Profesor::getTipo).setCaption("Tipo");
            profesoresMostrarId.addColumn(Profesor::getDepartamento).setCaption("Departamento");
            layoutProfesorMostrarId.replaceComponent(profesoresMostrarId, profesoresMostrarId);
        });
        //Termina evento del combobox para buscar profesor por id


        //Evento del comboBox para eliminar profesor por Id
        cboProfesorEliminar.addValueChangeListener(event -> {
            profesoresEliminar.removeAllColumns();
            String id = cboProfesorEliminar.getSelectedItem().toString().replace("Optional[", "").replace("]", "");
            Profesor profe = repoProfe.findOne(id);
            profesoresEliminar.setItems(profe);
            profesoresEliminar.addColumn(Profesor::getId).setCaption("Id");
            profesoresEliminar.addColumn(Profesor::getNombre).setCaption("Nombre");
            profesoresEliminar.addColumn(Profesor::getTipo).setCaption("Tipo");
            profesoresEliminar.addColumn(Profesor::getDepartamento).setCaption("Departamento");
            layoutProfesorEliminar.replaceComponent(profesoresMostrarId, profesoresMostrarId);
        });
        //Termina evento del combobox para eliminar profesor por id


        //Evento del combobox para actualizar profesor
        cboProfesorActualizar.addValueChangeListener(event -> {
            String pId = cboProfesorActualizar.getSelectedItem().toString().replace("Optional[", "").replace("]", "");
            Profesor profe = repoProfe.findOne(pId);
            txtProfesorActualizarNombre.setValue(profe.getNombre());
            txtProfesorActualizarTipo.setValue(profe.getTipo());
            txtProfesorActualizarDepartamento.setValue(profe.getDepartamento());
        });
        //Termina evento del combobox para eliminar profesor


        //Evento del combobox para mostrar curso para inscribir
        cboAlumnoCurso.addValueChangeListener(event -> {
            cId = cboAlumnoCurso.getSelectedItem().toString().replace("Optional[", "").replace("]", "");
            switch (cId) {
                case "1":
                    profe = repoProfe.find1();
                    break;
                case "2":
                    profe = repoProfe.find2();
                    break;
                case "3":
                    profe = repoProfe.find3();
                    break;
                case "4":
                    profe = repoProfe.find4();
                    break;
                case "5":
                    profe = repoProfe.find5();
                    break;
                case "6":
                    profe = repoProfe.find6();
                    break;
                case "7":
                    profe = repoProfe.find7();
                    break;
                case "8":
                    profe = repoProfe.find8();
                    break;
                case "9":
                    profe = repoProfe.find9();
                    break;
                case "10":
                    profe = repoProfe.find10();
                    break;
            }

            txtCursoInscribir.setValue("Nombre: " + profe.getCurso().getNombre() +
                    "\nDuración: " + profe.getCurso().getDuracion() +
                    "\nInicio: " + profe.getCurso().getfInicio() +
                    "\nTérmino: " + profe.getCurso().getfTermino() +
                    "\nHorarios:" + profe.getCurso().getHorarios() +
                    "\nCosto: " + profe.getCurso().getCosto());
            txtCursoInscribir.setRows(6);
        });
        //Termina evento del combobox para mostrar curso para inscribir
    }
}