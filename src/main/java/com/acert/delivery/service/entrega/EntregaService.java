package com.acert.delivery.service.entrega;

import com.acert.delivery.entity.entrega.Entrega;
import com.acert.delivery.repository.entrega.EntregaRepository;
import com.acert.delivery.service.entrega.dto.EntregaDTO;
import com.acert.delivery.service.entrega.exception.EntregaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.acert.delivery.service.entrega.mapper.EntregaMapper.*;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private static final String MENSAGEM_DE_ERRO = "Entrega %s não encontrada.";

    public EntregaDTO cadastrarEntrega(EntregaDTO entregaDTO) {
        Entrega entrega = deEntregaDTOParaEntrega(entregaDTO);
        return deEntregaParaEntregaDTO(entregaRepository.save(entrega));
    }

    public List<EntregaDTO> encontrarTodasAsEntregas() {
        return deListaEntregasParaListaEntregasDTO(entregaRepository.findAll());
    }

    public EntregaDTO encontrarPorId(Long id) {
        return deEntregaParaEntregaDTO(buscarEntregaPorId(id)
                .orElseThrow(() -> new EntregaNotFoundException(String.format(MENSAGEM_DE_ERRO, id))));
    }

    public EntregaDTO atualizarEntrega(Long id, EntregaDTO entregaDTO) {
        Optional<Entrega> entrega = buscarEntregaPorId(id);

        if (entregaExiste(entrega)) {
            Entrega novaEntrega = deEntregaDTOParaEntrega(entregaDTO);
            return deEntregaParaEntregaDTO(entregaRepository.save(novaEntrega));
        } else {
            throw new EntregaNotFoundException(String.format(MENSAGEM_DE_ERRO, id));
        }
    }

    public void deletarPorId(Long id) {
        Optional<Entrega> entrega = buscarEntregaPorId(id);

        if (entregaExiste(entrega)) {
            entregaRepository.deleteById(id);
        } else {
            throw new EntregaNotFoundException(String.format(MENSAGEM_DE_ERRO, id));
        }
    }

    private Optional<Entrega> buscarEntregaPorId(Long id) {
        return entregaRepository.findById(id);
    }

    private boolean entregaExiste(Optional<Entrega> optionalEntrega) {
        return optionalEntrega.isPresent();
    }
}
