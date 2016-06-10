package com.imd030.sgr.builder;

import com.imd030.sgr.entiitys.Paciente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neto on 09/06/2016.
 */
public class PacienteBuilder {

    private static List<Paciente> pacienteList = new ArrayList<Paciente>();

    static {
        Paciente paciente1 = new Paciente("1", "Machado de Assis", "0000001", "machadoassis@email.com", "89789798798");
        Paciente paciente2 = new Paciente("2", "José de Alencar", "0000002", "josealencar@email.com", "89789798798");
        Paciente paciente3 = new Paciente("3", "Lima Barreto", "0000003", "limabarreto@email.com", "89789798798");
        Paciente paciente4 = new Paciente("4", "Raquel de Queiroz", "0000004", "raquequeiroz@email.com", "89789798798");
        Paciente paciente5 = new Paciente("5", "Policarpo Quaresma", "0000005", "policarpo@email.com", "89789798798");
        Paciente paciente6 = new Paciente("6", "Dom Casmurro", "0000006", "domcasmurro@email.com", "89789798798");
        Paciente paciente7 = new Paciente("7", "Pedro Alvez Cabral", "0000007", "pedrocabral@email.com", "89789798798");
        Paciente paciente8 = new Paciente("8", "João da Silva", "0000008", "joaosilva@email.com", "89789798798");

        pacienteList.add(paciente1);
        pacienteList.add(paciente2);
        pacienteList.add(paciente3);
        pacienteList.add(paciente4);
        pacienteList.add(paciente5);
        pacienteList.add(paciente6);
        pacienteList.add(paciente7);
        pacienteList.add(paciente8);

    }


    public static List<Paciente> getPacientes(){
       return pacienteList;
    }
}
