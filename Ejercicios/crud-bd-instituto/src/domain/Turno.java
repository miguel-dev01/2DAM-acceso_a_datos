package domain;

import static Snippets.Snippet.getString;
import static Snippets.Snippet.*;
import User.Info;
import java.util.List;

import DAO.*;

public class Turno {

    private String codTurno;
    private String horario;

    public Turno() {}

    public Turno(String codTurno, String horario) {
        this.codTurno = codTurno;
        this.horario = horario;
    }

    public String getCodTurno() {
        return codTurno;
    }

    public void setCodTurno(String codTurno) {
        this.codTurno = codTurno;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Turno [codTurno=" + codTurno + ", horario=" + horario + "]";
    }

    public static void turnoCRUD() {
        TurnoDAO turnoDao = new TurnoDAO();
        int option = 0;
        do {
            option = getInt(Info.CRUD_MENU);
            switch(option) {
                case 1:
                    Turno turno_insertar = setDataTurno();
                    turnoDao.insertar(turno_insertar);
                    break;
                case 2:
                    String turno_eliminar = getString("Indica código del turno para eliminarlo de la BD: ");
                    turnoDao.eliminar(turno_eliminar);
                    break;
                case 3:
                    Turno turno_actualizar = setDataTurno();
                    turnoDao.actualizar(turno_actualizar);
                    break;
                case 4:
                    List<Turno> turnos = turnoDao.consultar();
                    for(Turno turno : turnos) {
                        System.out.println(turno);
                    }
                    break;
                case 0:
                    System.out.println("Volviendo atras...");
					System.out.println();
					Info.showMainMenu();
                    break;
                default:
                    System.out.println("\nNo valido");
                    break;
            } 
        } while (option != 0);
    }

    private static Turno setDataTurno() {
        String codTurno = getString("Introduce Código de Turno: ");
        String horario = getString("Introduce Horario de Turno: ");

        Turno turno = new Turno(codTurno, horario);
        return turno;
    }

}
