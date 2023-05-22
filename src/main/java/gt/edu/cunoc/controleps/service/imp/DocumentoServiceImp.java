/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.cunoc.controleps.service.imp;

import gt.edu.cunoc.controleps.model.entity.Elemento;
import gt.edu.cunoc.controleps.model.entity.ElementosProyecto;
import gt.edu.cunoc.controleps.repository.ElementoRepository;
import gt.edu.cunoc.controleps.service.DocumentoService;
import gt.edu.cunoc.controleps.service.StorageService;
import gt.edu.cunoc.controleps.utils.PoiUtils;
import gt.edu.cunoc.controleps.utils.ProyectoUtils;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author edvin
 */
@Service
public class DocumentoServiceImp implements DocumentoService {

    private final StorageService storageService;
    private final PoiUtils poiUtils;
    private final ElementoRepository elementoRepository;

    public DocumentoServiceImp(PoiUtils poiUtils, ElementoRepository elementoRepository,
            StorageService storageService) {
        this.poiUtils = poiUtils;
        this.elementoRepository = elementoRepository;
        this.storageService = storageService;
    }

    @Override
    public String generarConvocatoriaEvaluacion(List<ElementosProyecto> elementosProyecto) throws Exception {
        System.out.println("inicio generar");
        Elemento elementoConvocatoria = elementoRepository.findByIdElemento(ProyectoUtils.ID_ELEMENTO_CONVOCATORIA_OFICIO);
        String url = storageService.getFileUrl(elementoConvocatoria.getTemplate());
        InputStream input = new URL(url).openStream();
        Map<String, Object> data = new HashMap<>();
        elementosProyecto.forEach(elementoProyecto -> {
            data.put(elementoProyecto.getIdElementoFk().getKey(), elementoProyecto.getInformacion());
        });
        if (data.containsKey("${nombreRepresentante}")) {
            data.put("${informacionRepresentante}", "Evaluador: (Representante) Ing. " + data.get("${nombreRepresentante}"));
        } else {
            data.put("${informacionRepresentante}", "");
        }
        MultipartFile file = poiUtils.generarDocumento(data, input, "PDF", "oficioConvocatoria.pdf");
        String cartaEstudianteSupervisorUrl = storageService.saveFile(file);
        return cartaEstudianteSupervisorUrl;
    }

    @Override
    public String generarCartaEstudianteSupervisor(List<ElementosProyecto> elementosProyecto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
