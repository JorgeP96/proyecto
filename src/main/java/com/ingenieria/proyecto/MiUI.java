package com.ingenieria.proyecto;

import com.vaadin.annotations.Theme;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringUI
@Theme("valo")
public class MiUI extends UI {

    @Autowired
    RepositorioProfesor repoProfe;

    Grid<Profesor> profesoresMostrar = new Grid<>();
    Grid<Profesor> profesoresMostrarId = new Grid<>();
    List<Profesor> profesoresTodos = new ArrayList<>();


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
        layoutProfesorGuardar.addComponents(lblProfesorId, txtProfesorId, lblProfesorNombre, txtProfesorNombre, lblProfesorTipo, txtProfesorTipo, lblProfesorDepartamento, txtProfesorDepartamento, btnProfesorGuardar);

        //Layout para mostrar todos los profesores
        VerticalLayout layoutProfesorMostrarTodos = new VerticalLayout();
        profesoresTodos = repoProfe.findAll();
        profesoresMostrar.setItems(profesoresTodos);
        profesoresMostrar.addColumn(Profesor::getId).setCaption("Id");
        profesoresMostrar.addColumn(Profesor::getNombre).setCaption("Nombre");
        profesoresMostrar.addColumn(Profesor::getTipo).setCaption("Tipo");
        profesoresMostrar.addColumn(Profesor::getDepartamento).setCaption("Departamento");
        layoutProfesorMostrarTodos.addComponent(profesoresMostrar);

        //Layout para mostrar profesor por Id
        VerticalLayout layoutProfesorMostrarId = new VerticalLayout();
        ComboBox<Profesor> cboProfesor = new ComboBox<>("Seleccione");
        List<String> pro = new ArrayList<>();
        cboProfesor.clear();
        for (Profesor p : profesoresTodos) {
            pro.add(p.getId());
        }
        cboProfesor.setItems((List) pro);
        layoutProfesorMostrarId.addComponents(cboProfesor, profesoresMostrarId);

        //Inicia menú principal
        MenuBar menuPrincipal = new MenuBar();
        MenuBar.MenuItem profesores = menuPrincipal.addItem("Profesores", null);
        MenuBar.MenuItem cursos = menuPrincipal.addItem("Cursos", null);
        MenuBar.MenuItem alumnos = menuPrincipal.addItem("Alumnos", null);

        //Inicia submenú profesores
        profesores.addSeparator();
        profesores.addItem("Guardar", null, selectedItem -> {
            layout.addComponents(layoutPrincipal, layoutProfesorGuardar);
            setContent(layout);
        });
        profesores.addSeparator();
        profesores.addItem("Actualizar", null, null);
        profesores.addSeparator();
        profesores.addItem("Eliminar", null, null);
        profesores.addSeparator();
        profesores.addItem("Mostrar", null, null);
        profesores.addSeparator();
        profesores.addItem("Buscar", null, selectedItem -> {
            layout.addComponents(layoutPrincipal, layoutProfesorMostrarId);
        });
        profesores.addSeparator();

        //Inicia submenú cursos
        cursos.addSeparator();
        cursos.addItem("Guardar", null, null);
        cursos.addSeparator();
        cursos.addItem("Actualizar", null, null);
        cursos.addSeparator();
        cursos.addItem("Eliminar", null, null);
        cursos.addSeparator();
        cursos.addItem("Mostrar", null, null);
        cursos.addSeparator();
        cursos.addItem("Buscar", null, null);

        //Inicia submenú alumnos
        alumnos.addSeparator();
        alumnos.addItem("Guardar", null, null);
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
                Profesor profesor = new Profesor(pId, pNombre, pTipo, pDepartamento, new Curso());
                repoProfe.save(profesor);
                txtProfesorId.setValue("");
                txtProfesorNombre.setValue("");
                txtProfesorTipo.setValue("");
                txtProfesorDepartamento.setValue("");
                Notification.show(estatus.getMensaje(), Notification.Type.WARNING_MESSAGE);
                profesoresTodos = repoProfe.findAll();
                cboProfesor.clear();
                for (Profesor p : profesoresTodos) {
                    pro.add(p.getId());
                }
                cboProfesor.setItems((List) pro);
            } else {
                Notification.show(estatus.getMensaje(), Notification.Type.ERROR_MESSAGE);
            }
        });

        //Evento del comboBox para mostrar profesor por Id
        cboProfesor.addValueChangeListener(event -> {
            profesoresMostrarId.removeAllColumns();
            String id = cboProfesor.getSelectedItem().toString().replace("Optional[","").replace("]","");
            Profesor profe = repoProfe.findOne(id);
            profesoresMostrarId.setItems(profe);
            profesoresMostrarId.addColumn(Profesor::getId).setCaption("Id");
            profesoresMostrarId.addColumn(Profesor::getNombre).setCaption("Nombre");
            profesoresMostrarId.addColumn(Profesor::getTipo).setCaption("Tipo");
            profesoresMostrarId.addColumn(Profesor::getDepartamento).setCaption("Departamento");
            layoutProfesorMostrarId.replaceComponent(profesoresMostrarId, profesoresMostrarId);
        });
    }
}