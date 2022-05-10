package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

  @Autowired
  private FornecedorClient fornecedorClient;

  public Compra realizarCompra(CompraDTO compra) {

    InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());

    InfoPedidoDTO infoPedido = fornecedorClient.realizaPedido(compra.getItens());
    System.out.println( infoFornecedorDTO.getEndereco());

    Compra compraSalva = new Compra();
    compraSalva.setPedidoId(infoPedido.getId());
    compraSalva.setTempoDePreparo(infoPedido.getTempoDePreparo());
    compraSalva.setEnderecoDestino(compra.getEndereco().toString());

    return compraSalva;
  }
}
