package com.mycompany.parqueo;

import paquete1.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapaParqueo extends JFrame {

    private Parqueo parqueo;

    public MapaParqueo(Parqueo parqueo) {
        this.parqueo = parqueo;

        setTitle("Mapa del Parqueo - Vista Completa");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(1, 3)); 

        
        for (Area area : parqueo.getListaAreas()) {
            add(crearPanelArea(area));
        }
    }

    private JPanel crearPanelArea(Area area) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(area.getNombre()));

        List<Spot> spots = area.getSpots();

        for (Spot s : spots) {
            JButton btn = new JButton("<html>" + s.getIdSpot() + "<br>" +
                    s.getTipoVehiculo() + "<br>" +
                    s.getEstado() + "</html>");

            btn.setPreferredSize(new Dimension(90, 60));

            if (s.getEstado().equals("FREE"))
                btn.setBackground(Color.GREEN);
            else if (s.getEstado().equals("OCUPADO"))
                btn.setBackground(Color.RED);
            else
                btn.setBackground(Color.ORANGE); 

            panel.add(btn);
        }

        return panel;
    }
}
