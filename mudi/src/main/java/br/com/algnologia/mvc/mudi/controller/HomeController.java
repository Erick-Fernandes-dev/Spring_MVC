package br.com.algnologia.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.algnologia.mvc.mudi.model.Pedido;
import br.com.algnologia.mvc.mudi.model.StatusPedido;
import br.com.algnologia.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping()
	public String itial(Model model) {
		
		return "home";
		
	}
	
	@GetMapping("/home")//busca todos os produtos
	public String home(Model model) {
		
//		List<Pedido> pedidos = repository.recuperaTodosOsPedidos();
		List<Pedido> pedidos = pedidoRepository.findAll();
//		
	
//		Pedido pedido = new Pedido();
//		pedido.setNomeProduto("Xiaomi Redmi Note 8");
//		pedido.setUrlImagem("https://images-na.ssl-images-amazon.com/images/I/81UgYuadkpL._AC_SL1500_.jpg");
//		pedido.setUrlProduto("https://www.amazon.com.br/Smartphone-Xiaomi-Redmi-Note-64GB/dp/B07Y8YWTFL/ref=sr_1_6?__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&dchild=1&keywords=Xiaomi+Redmi+Note+8&qid=1600346040&sr=8-6");
//		pedido.setDescricao("uma descrição qualquer para esse pedido");
//		
//		List<Pedido> pedidos = Arrays.asList(pedido);
		model.addAttribute("pedidos", pedidos);
		return "home";
	}
	
	

	@GetMapping("/home/{status}")// busca pelos status
	public String porStatus(@PathVariable("status") String status, Model model) {
		
		List<Pedido> pedidos = this.pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
}
