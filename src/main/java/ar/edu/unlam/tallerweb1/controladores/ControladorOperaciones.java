package ar.edu.unlam.tallerweb1.controladores;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.ExcepcionDatoErroneo;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalcular;


@Controller
public class ControladorOperaciones {

@Inject 	
private ServicioCalcular servicioCalcular;

	@RequestMapping(path="calcular/{numero1}/{operador}/{numero2}")
	public ModelAndView hacerOperacion(@PathVariable Double numero1, @PathVariable String operador, @PathVariable Double numero2 ) throws Exception {
		ModelMap modelo = new ModelMap();
		try {
		Double resultado = servicioCalcular.calcular(numero1,operador,numero2);
		modelo.put("numero1", numero1);
		modelo.put("operador", operador);
		modelo.put("numero2", numero2);
		modelo.put("resultado", resultado);
		return new ModelAndView ("operacion", modelo);
		}
		catch(ExcepcionDatoErroneo e) {	
			String mensaje = e.getMessage();
			ModelMap modelo2 = new ModelMap();
			modelo2.put("error", mensaje);
			return new ModelAndView ("errorDatoErroneo", modelo2);
		}

	}

}

