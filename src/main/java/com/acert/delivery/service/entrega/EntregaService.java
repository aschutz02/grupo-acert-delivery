package com.acert.delivery.service.entrega;

import com.acert.delivery.entity.entrega.Entrega;
import com.acert.delivery.repository.entrega.EntregaRepository;
import com.acert.delivery.service.entrega.dto.EntregaDTO;
import com.acert.delivery.service.entrega.exception.EntregaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public EntregaDTO encontrarPeloId(Long id) {
        return deEntregaParaEntregaDTO(buscarEntregaPeloId(id));
    }

    public EntregaDTO atualizarEntrega(Long id, EntregaDTO entregaDTO) {
        buscarEntregaPeloId(id);

        Entrega novaEntrega = deEntregaDTOParaEntrega(entregaDTO);
        return deEntregaParaEntregaDTO(entregaRepository.save(novaEntrega));
    }

    public void deletarPeloId(Long id) {
        buscarEntregaPeloId(id);

        entregaRepository.deleteById(id);
    }

    private Entrega buscarEntregaPeloId(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new EntregaNotFoundException(String.format(MENSAGEM_DE_ERRO, id)));
    }
}
