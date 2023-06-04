package com.acert.delivery.service.pedido;

import com.acert.delivery.entity.pedido.Pedido;
import com.acert.delivery.repository.pedido.PedidoRepository;
import com.acert.delivery.service.pedido.dto.PedidoDTO;
import com.acert.delivery.service.pedido.exception.PedidoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.acert.delivery.service.pedido.mapper.PedidoMapper.*;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private static final String MENSAGEM_DE_ERRO = "Pedido %s não encontrado.";

    public PedidoDTO cadastrarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = dePedidoDTOParaPedido(pedidoDTO);
        return dePedidoParaPedidoDTO(pedidoRepository.save(pedido));
    }

    public List<PedidoDTO> encontrarTodosOsPedidos() {
        return deListaPedidosParaListaPedidosDTO(pedidoRepository.findAll());
    }

    public PedidoDTO encontrarPeloId(Long id) {
        return dePedidoParaPedidoDTO(buscarPedidoPeloId(id)
                .orElseThrow(() -> new PedidoNotFoundException(String.format(MENSAGEM_DE_ERRO, id))));
    }

    public PedidoDTO atualizarPedido(Long id, PedidoDTO pedidoDTO) {
        Optional<Pedido> pedido = buscarPedidoPeloId(id);

        if (pedidoExiste(pedido)) {
            Pedido novoPedido = dePedidoDTOParaPedido(pedidoDTO);
            return dePedidoParaPedidoDTO(pedidoRepository.save(novoPedido));
        } else {
            throw new PedidoNotFoundException(String.format(MENSAGEM_DE_ERRO, id));
        }
    }

    public void deletarPeloId(Long id) {
        Optional<Pedido> pedido = buscarPedidoPeloId(id);

        if (pedidoExiste(pedido)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new PedidoNotFoundException(String.format(MENSAGEM_DE_ERRO, id));
        }
    }

    private Optional<Pedido> buscarPedidoPeloId(Long id) {
        return pedidoRepository.findById(id);
    }

    private boolean pedidoExiste(Optional<Pedido> optionalPedido) {
        return optionalPedido.isPresent();
    }

}
