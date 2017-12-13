package com.ingenieria.proyecto;

import com.vaadin.annotations.Theme;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringUI
@Theme("valo")
public class MiUI extends UI {

    @Autowired
    RepositorioProfesor repoProfe;
    @Autowired
    RepositorioCurso repoCurso;
    @Autowired
    RepositorioAlumno repoAlumno;

    //Grid para mostrar todos los profesores
    Grid<Profesor> profesoresMostrar = new Grid<>();
    //Grid para mostrar profesor por Id
    Grid<Profesor> profesoresMostrarId = new Grid<>();
    //Lista para guardar todos
    List<Profesor> profesoresTodos = new ArrayList<>();

    Grid<Curso> cursosMostrar = new Grid<>();
    Grid<Curso> cursosMostrarId = new Grid<>();
    List<Curso> cursosTodos = new ArrayList<>();

    Grid<Alumno> alumnosMostrar = new Grid<>();
    Grid<Alumno> alumnosMostrarId = new Grid<>();
    List<Alumno>alumnosTodos = new ArrayList<>();


    @Override
    protected void init(VaadinRequest request) {
        //Layout principal, aquí pondré todos los otros layouts
        VerticalLayout layout = new VerticalLayout();

        //Layout del título y menú
        VerticalLayout layoutPrincipal = new VerticalLayout();
        Label etiqueta = new Label("Aplicación de Cursos");
        etiqueta.addStyleName(ValoTheme.LABEL_H1);

        //Inicia layout para guardar profesores
        VerticalLayout layoutProfesorGuardar = new VerticalLayout();
        Label lblProfesorId = new Label("Id: ");
        TextField txtProfesorId = new TextField();
        Label lblProfesorNombre = new Label("Nombre: ");
        TextField txtProfesorNombre = new TextField();
        Label lblProfesorTipo = new Label("Tipo: ");
        TextField txtProfesorTipo = new TextField();
        Label lblProfesorDepartamento = new Label("Departamento: ");
        TextField txtProfesorDepartamento = new TextField();
        Button btnProfesorGuardar = new Button("Guardar");
        btnProfesorGuardar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layoutProfesorGuardar.addComponents(lblProfesorId, txtProfesorId, lblProfesorNombre, txtProfesorNombre, lblProfesorTipo, txtProfesorTipo, lblProfesorDepartamento, txtProfesorDepartamento, btnProfesorGuardar);
        //Termina layout para guardar profesores


        //Layout para mostrar todos los profesores
        VerticalLayout layoutProfesorMostrarTodos = new VerticalLayout();
        profesoresTodos = repoProfe.findAll();
        profesoresMostrar.setItems(profesoresTodos);
        profesoresMostrar.addColumn(Profesor::getId).setCaption("Id");
        profesoresMostrar.addColumn(Profesor::getNombre).setCaption("Nombre");
        profesoresMostrar.addColumn(Profesor::getTipo).setCaption("Tipo");
        layoutProfesorMostrarTodos.addComponent(profesoresMostrar);
        //Termina layout para mostrar todos los profesores


        //Inicia layout para actualizar profesor
        VerticalLayout layoutProfesorActualizar = new VerticalLayout();
        ComboBox<Profesor> cboProfesorActualizar = new ComboBox<>();
        List<String> idProfes = new ArrayList<>();
        cboProfesorActualizar.clear();
        for (Profesor p : profesoresTodos) {
            idProfes.add(p.getId());
        }
        cboProfesorActualizar.setItems((List) idProfes);
        cboProfesorActualizar.setScrollToSelectedItem(true);
        Label lblProfesorActualizarNombre = new Label("Nombre: ");
        TextField txtProfesorActualizarNombre = new TextField();
        Label lblProfesorActualizarTipo = new Label("Tipo: ");
        TextField txtProfesorActualizarTipo = new TextField();
        Label lblProfesorActualizarDepartamento = new Label("Departamento: ");
        TextField txtProfesorActualizarDepartamento = new TextField();
        Label lblProfesorActualizarCurso = new Label("Curso: ");
        TextField txtProfesorActualizarCurso = new TextField();
        Button btnProfesorActualizar = new Button("Actualizar");
        btnProfesorActualizar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layoutProfesorActualizar.addComponents(cboProfesorActualizar, lblProfesorActualizarNombre, txtProfesorActualizarNombre, lblProfesorActualizarTipo, txtProfesorActualizarTipo, lblProfesorActualizarDepartamento, txtProfesorActualizarDepartamento, lblProfesorActualizarCurso, txtProfesorActualizarCurso,btnProfesorActualizar);
        //Termina layout para actualizar profesor

        //Layout para mostrar profesor por Id
        VerticalLayout layoutProfesorMostrarId = new VerticalLayout();
        ComboBox<Profesor> cboProfesorMostrarId = new ComboBox<>();
        List<String> pro = new ArrayList<>();
        cboProfesorMostrarId.clear();
        pro.add("Seleccione");
        for (Profesor p : profesoresTodos) {
            pro.add(p.getId());
        }
        cboProfesorMostrarId.setItems((List) pro);
        layoutProfesorMostrarId.addComponents(cboProfesorMostrarId, profesoresMostrarId);

        //Layout para eliminar profesor por Id
        VerticalLayout layoutProfesorEliminar = new VerticalLayout();
        ComboBox<Profesor> cboProfesorEliminar = new ComboBox<>("Seleccione");
        List<String> eliminarPro = new ArrayList<>();
        cboProfesorEliminar.clear();
        for (Profesor p : profesoresTodos) {
            eliminarPro.add(p.getId());
        }
        cboProfesorEliminar.setItems((List) eliminarPro);

        Button btnEliminarPro = new Button("Eliminar");
        btnEliminarPro.addStyleName(ValoTheme.BUTTON_DANGER );

        layoutProfesorEliminar.addComponents(cboProfesorEliminar, profesoresMostrarId, btnEliminarPro);

        //Inicia layout para guardar cursos
        VerticalLayout layoutCursoGuardar = new VerticalLayout();
        Label lblCursoId = new Label("Id: ");
        TextField txtCursoId = new TextField();
        Label lblCursoNombre = new Label("Nombre: ");
        TextField txtCursoNombre = new TextField();
        Label lblCursoDuracion = new Label("Duración: ");
        TextField txtCursoDuracion = new TextField();
        DateField fechaInicioCurso = new DateField();
        Label lblCursoInicio = new Label("Inicio: ");
        fechaInicioCurso.setValue(LocalDate.now());
        Label lblCursoFin = new Label("Fin: ");
        DateField fechaFinCurso = new DateField();
        fechaFinCurso.setValue(LocalDate.now());
        Label lblCursoHorario = new Label("Horarios: ");
        TextField txtCursoHorario = new TextField();
        Label lblCursoAlumnos = new Label("Alumnos: ");
        TextField txtCursoAlumnos = new TextField();
        Label lblCursoCosto = new Label("Costo: ");
        TextField txtCursoCosto = new TextField();
        Button btnCursoGuardar = new Button("Guardar");
        btnCursoGuardar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layoutCursoGuardar.addComponents(lblCursoId, txtCursoId , lblCursoNombre , txtCursoNombre , lblCursoDuracion, txtCursoDuracion ,lblCursoInicio,fechaInicioCurso,lblCursoFin,
                fechaFinCurso,
                lblCursoHorario,
                txtCursoHorario ,
                lblCursoAlumnos ,
                txtCursoAlumnos ,
                lblCursoCosto ,
                txtCursoCosto,
                btnCursoGuardar);

        //Layout para mostrar todos los cursos
        VerticalLayout layoutCursoMostrarTodos = new VerticalLayout();
        cursosTodos = repoCurso.findAll();
        cursosMostrar.setItems(cursosTodos);
        cursosMostrar.addColumn(Curso::getIdCurso).setCaption("Id");
        cursosMostrar.addColumn(Curso::getNombre).setCaption("Nombre");
        cursosMostrar.addColumn(Curso::getDuracion).setCaption("Duración");
        cursosMostrar.addColumn(Curso::getfInicio).setCaption("Inicio");
        cursosMostrar.addColumn(Curso::getfTermino).setCaption("Fin");
        cursosMostrar.addColumn(Curso::getHorarios).setCaption("Horario");
        cursosMostrar.addColumn(Curso::getAlumnos).setCaption("Alumnos");
        cursosMostrar.addColumn(Curso::getCosto).setCaption("Costo");
        layoutCursoMostrarTodos.addComponent(cursosMostrar);

        //Layout para actualizar Cursos

        VerticalLayout layoutCursosActualizar = new VerticalLayout();

        ComboBox<Profesor> cboActualizarCurso = new ComboBox<>("Seleccione");
        List<String> actualizarCurso = new ArrayList<>();
        cboActualizarCurso.clear();
        for (Curso c : cursosTodos) {
            actualizarCurso.add(c.getIdCurso());
        }
        cboActualizarCurso.setItems((List) actualizarCurso);

        Label lblActualizarCursoNombre = new Label("Nombre: ");
        TextField txtActualizarCursoNombre = new TextField();
        Label lblActualizarCursoDuracion = new Label("Duración: ");
        TextField txtActualizarCursoDuracion = new TextField();
        DateField fechaInicioActualizarCurso = new DateField();
        Label lblActualizarCursoInicio = new Label("Inicio: ");
        fechaInicioActualizarCurso.setValue(LocalDate.now());
        Label lblActualizarCursoFin = new Label("Fin: ");
        DateField fechaFinActualizarCurso = new DateField();
        fechaFinActualizarCurso.setValue(LocalDate.now());
        Label lblActualizarCursoHorario = new Label("Horarios: ");
        TextField txtActualizarCursoHorario = new TextField();
        Label lblActualizarCursoAlumnos = new Label("Alumnos: ");
        TextField txtActualizarCursoAlumnos = new TextField();
        Label lblActualizarCursoCosto = new Label("Costo: ");
        TextField txtActualizarCursoCosto = new TextField();
        Button btnActualizarCurso = new Button("Actualizar");
        btnActualizarCurso.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layoutCursosActualizar.addComponents(cboActualizarCurso , lblActualizarCursoNombre , txtActualizarCursoNombre , lblActualizarCursoDuracion, txtActualizarCursoDuracion ,lblActualizarCursoInicio,fechaInicioActualizarCurso,lblActualizarCursoFin,
                fechaFinActualizarCurso,
                lblActualizarCursoHorario,
                txtActualizarCursoHorario ,
                lblActualizarCursoAlumnos ,
                txtActualizarCursoAlumnos ,
                lblActualizarCursoCosto ,
                txtActualizarCursoCosto,
                btnActualizarCurso);

        //Layout para mostrar curso por Id

        VerticalLayout layoutCursoMostrarId = new VerticalLayout();
        ComboBox<Profesor> cboCursos = new ComboBox<>("Seleccione");
        List<String> buscaCursos = new ArrayList<>();
        cboCursos.clear();
        for (Curso c : cursosTodos) {
            buscaCursos.add(c.getIdCurso());
        }
        cboCursos.setItems((List) buscaCursos);

        layoutCursoMostrarId.addComponents(cboCursos, cursosMostrarId);

        //Layout para eliminar curso por Id

        VerticalLayout layoutCursoEliminarId = new VerticalLayout();
        ComboBox<Profesor> cboEliminarCursos = new ComboBox<>("Seleccione");
        List<String> eliminaCursos = new ArrayList<>();
        cboEliminarCursos.clear();
        for (Curso c : cursosTodos) {
            eliminaCursos.add(c.getIdCurso());
        }
        cboEliminarCursos.setItems((List) eliminaCursos);

        Button btEliminarCursos = new Button("Eliminar");
         btEliminarCursos.addStyleName(ValoTheme.BUTTON_DANGER);

        layoutCursoEliminarId.addComponents(cboEliminarCursos, cursosMostrarId,btEliminarCursos);

        //Inicia layout para guardar Alumnoes
        VerticalLayout layoutAlumnoGuardar = new VerticalLayout();
        Label lblAlumnoId = new Label("Id: ");
        TextField txtAlumnoId = new TextField();
        Label lblAlumnoNombre = new Label("Nombre: ");
        TextField txtAlumnoNombre = new TextField();
        Label lblAlumnoTipo = new Label("Tipo: ");
        TextField txtAlumnoTipo = new TextField();
        Label lblAlumnoCurso = new Label("Curso: ");
        TextField txtAlumnoDepartamento = new TextField();
        Button btnAlumnoGuardar = new Button("Guardar");
        btnAlumnoGuardar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        layoutAlumnoGuardar.addComponents(lblAlumnoId, txtAlumnoId, lblAlumnoNombre, txtAlumnoNombre, lblAlumnoTipo, txtAlumnoTipo, lblAlumnoCurso, txtAlumnoDepartamento, btnAlumnoGuardar);

        //Layout para mostrar todos los alumnos
        VerticalLayout layoutAlumnosMostrarTodos = new VerticalLayout();
        alumnosTodos = repoAlumno.findAll();
        alumnosMostrar.setItems(alumnosTodos);
        alumnosMostrar.addColumn(Alumno::getId).setCaption("Id");
        alumnosMostrar.addColumn(Alumno::getNombre).setCaption("Nombre");
        alumnosMostrar.addColumn(Alumno::getTipo).setCaption("Tipo");
        alumnosMostrar.addColumn(Alumno::getCurso).setCaption("Curso");
        layoutAlumnosMostrarTodos.addComponent(alumnosMostrar);


        MenuBar menuPrincipal = new MenuBar();
        MenuBar.MenuItem profesores = menuPrincipal.addItem("Profesores",new ExternalResource("https://image.flaticon.com/icons/svg/42/42912.svg"),null);
        MenuBar.MenuItem cursos = menuPrincipal.addItem("Cursos" , null);
        MenuBar.MenuItem alumnos = menuPrincipal.addItem("Alumnos", null);

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

        //Inicia submenú cursos
        cursos.addSeparator();
        cursos.addItem("Guardar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutCursoGuardar);
            setContent(layout);
        });

        cursos.addSeparator();
        cursos.addItem("Actualizar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutCursosActualizar);
            setContent(layout);
        });
        cursos.addSeparator();
        cursos.addItem("Eliminar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutCursoEliminarId);
            setContent(layout);
        });
        cursos.addSeparator();
        cursos.addItem("Mostrar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutCursoMostrarTodos);
            setContent(layout);
        });
        cursos.addSeparator();
        cursos.addItem("Buscar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutCursoMostrarId);
            setContent(layout);
        });

        //Inicia submenú alumnos
        alumnos.addSeparator();
        alumnos.addItem("Guardar", null, selectedItem -> {
            layout.removeAllComponents();
            layout.addComponents(layoutPrincipal, layoutAlumnoGuardar);
            setContent(layout);
        });
        alumnos.addSeparator();
        alumnos.addItem("Actualizar", null, null);
        alumnos.addSeparator();
        alumnos.addItem("Eliminar", null, null);
        alumnos.addSeparator();
        alumnos.addItem("Mostrar", null, null);
        alumnos.addSeparator();
        alumnos.addItem("Buscar", null, null);
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
                Profesor profesor = new Profesor(pId, pNombre, pTipo, new Curso(), pDepartamento);
                repoProfe.save(profesor);
                txtProfesorId.setValue("");
                txtProfesorNombre.setValue("");
                txtProfesorTipo.setValue("");
                txtProfesorDepartamento.setValue("");
                Notification.show(estatus.getMensaje(), Notification.Type.WARNING_MESSAGE);
                profesoresTodos = repoProfe.findAll();
                cboProfesorMostrarId.clear();
                cboProfesorActualizar.clear();
                cboProfesorEliminar
                for (Profesor p : profesoresTodos) {
                    pro.add(p.getId());
                }
                cboProfesorMostrarId.setItems((List) pro);
            } else {
                Notification.show(estatus.getMensaje(), Notification.Type.ERROR_MESSAGE);
            }
        });

        //Evento del comboBox para mostrar profesor por Id
        cboProfesorMostrarId.addValueChangeListener(event -> {
            profesoresMostrarId.removeAllColumns();
            String id = cboProfesorMostrarId.getSelectedItem().toString().replace("Optional[","").replace("]","");
            Profesor profe = repoProfe.findOne(id);
            profesoresMostrarId.setItems(profe);
            profesoresMostrarId.addColumn(Profesor::getId).setCaption("Id");
            profesoresMostrarId.addColumn(Profesor::getNombre).setCaption("Nombre");
            profesoresMostrarId.addColumn(Profesor::getTipo).setCaption("Tipo");
            layoutProfesorMostrarId.replaceComponent(profesoresMostrarId, profesoresMostrarId);
        });

        //Evento del comboBox para eliminar profesor por Id
            cboProfesorEliminar.addValueChangeListener(event -> {
            profesoresMostrarId.removeAllColumns();
            String id = cboProfesorEliminar.getSelectedItem().toString().replace("Optional[","").replace("]","");
            Profesor profe = repoProfe.findOne(id);
            profesoresMostrarId.setItems(profe);
            profesoresMostrarId.addColumn(Profesor::getId).setCaption("Id");
            profesoresMostrarId.addColumn(Profesor::getNombre).setCaption("Nombre");
            profesoresMostrarId.addColumn(Profesor::getTipo).setCaption("Tipo");
            layoutProfesorEliminar.replaceComponent(profesoresMostrarId, profesoresMostrarId);
        });

        //Evento del comboBox para mostrar curso por Id
            cboCursos.addValueChangeListener(event -> {
            cursosMostrarId.removeAllColumns();
            String id = cboCursos.getSelectedItem().toString().replace("Optional[","").replace("]","");
            Curso curso = repoCurso.findOne(id);
            cursosMostrarId.setItems(curso);
                cursosMostrarId.addColumn(Curso::getIdCurso).setCaption("Id");
                cursosMostrarId.addColumn(Curso::getNombre).setCaption("Nombre");
                cursosMostrarId.addColumn(Curso::getDuracion).setCaption("Duración");
                cursosMostrarId.addColumn(Curso::getfInicio).setCaption("Inicio");
                cursosMostrarId.addColumn(Curso::getfTermino).setCaption("Fin");
                cursosMostrarId.addColumn(Curso::getHorarios).setCaption("Horario");
                cursosMostrarId.addColumn(Curso::getAlumnos).setCaption("Alumnos");
                cursosMostrarId.addColumn(Curso::getCosto).setCaption("Costo");
            layoutCursoMostrarId.replaceComponent(cursosMostrarId, cursosMostrarId);
        });

    }
}