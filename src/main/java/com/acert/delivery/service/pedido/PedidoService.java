package com.acert.delivery.service.pedido;

import com.acert.delivery.entity.pedido.Pedido;
import com.acert.delivery.repository.pedido.PedidoRepository;
import com.acert.delivery.service.cliente.ClienteService;
import com.acert.delivery.service.cliente.dto.ClienteResponseDTO;
import com.acert.delivery.service.entrega.EntregaService;
import com.acert.delivery.service.entrega.dto.EntregaDTO;
import com.acert.delivery.service.pedido.dto.PedidoRequestDTO;
import com.acert.delivery.service.pedido.dto.PedidoResponseDTO;
import com.acert.delivery.service.pedido.exception.PedidoNotFoundException;
import com.acert.delivery.service.produto.ProdutoService;
import com.acert.delivery.service.produto.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.acert.delivery.service.pedido.mapper.PedidoMapper.dePedidoDTOParaPedido;
import static com.acert.delivery.service.pedido.mapper.PedidoMapper.dePedidoParaPedidoDTO;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EntregaService entregaService;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private static final String MENSAGEM_DE_ERRO = "Pedido %s não encontrado.";

    public PedidoResponseDTO cadastrarPedido(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = dePedidoDTOParaPedido(pedidoRequestDTO);
        PedidoResponseDTO pedidoResponseDTO = dePedidoParaPedidoDTO(pedidoRepository.save(pedido));
        return criarPedido(pedidoResponseDTO, pedido);
    }

    public List<PedidoResponseDTO> encontrarTodosOsPedidos() {
        List<PedidoResponseDTO> pedidosResponseDTO = new ArrayList<>();
        List<Pedido> pedidos = pedidoRepository.findAll();
        pedidos.forEach(pedido -> {
            PedidoResponseDTO pedidoResponseDTO = dePedidoParaPedidoDTO(pedido);
            pedidosResponseDTO.add(pedidoResponseDTO);
        });

        pedidosResponseDTO.forEach(pedidoResponse -> pedidos.forEach(pedido -> criarPedido(pedidoResponse, pedido)));

        return pedidosResponseDTO;
    }

    public PedidoResponseDTO encontrarPeloId(Long id) {
        Pedido pedido = buscarPedidoPeloId(id);
        PedidoResponseDTO pedidoResponseDTO = dePedidoParaPedidoDTO(pedido);

        return criarPedido(pedidoResponseDTO, pedido);
    }

    public PedidoResponseDTO atualizarPedido(Long id, PedidoRequestDTO pedidoRequestDTO) {
        buscarPedidoPeloId(id);

        Pedido novoPedido = dePedidoDTOParaPedido(pedidoRequestDTO);
        PedidoResponseDTO pedidoResponseDTO = dePedidoParaPedidoDTO(pedidoRepository.save(novoPedido));
        return criarPedido(pedidoResponseDTO, novoPedido);
    }

    public void deletarPeloId(Long id) {
        buscarPedidoPeloId(id);

        pedidoRepository.deleteById(id);
    }

    private PedidoResponseDTO criarPedido(PedidoResponseDTO pedidoResponseDTO, Pedido pedidoEntity) {
        EntregaDTO entregaDTO = entregaService.encontrarPeloId(pedidoEntity.getEntregaId());
        ClienteResponseDTO clienteResponseDTO = clienteService.encontrarPeloId(pedidoEntity.getClienteId());
        ProdutoDTO produtoDTO = produtoService.encontrarPeloId(pedidoEntity.getProdutoId());

        pedidoResponseDTO.setEntrega(entregaDTO);
        pedidoResponseDTO.setCliente(clienteResponseDTO);
        pedidoResponseDTO.setProduto(produtoDTO);
        pedidoResponseDTO.setValorTotal(produtoDTO.getPreco());

        return pedidoResponseDTO;
    }

    private Pedido buscarPedidoPeloId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException(String.format(MENSAGEM_DE_ERRO, id)));
    }
}
