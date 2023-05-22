/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gt.edu.cunoc.controleps.service;

import gt.edu.cunoc.controleps.model.entity.ElementosProyecto;
import java.util.List;

/**
 *
 * @author edvin
 */
public interface DocumentoService {
    public String generarConvocatoriaEvaluacion(List<ElementosProyecto> elementosProyecto) throws Exception;
    public String generarCartaEstudianteSupervisor(List<ElementosProyecto> elementosProyecto) throws Exception;
}
