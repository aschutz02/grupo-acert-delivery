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

    public EntregaDTO encontrarPorNome(String nome) {
        return deEntregaParaEntregaDTO(entregaRepository.findByNome(nome)
                .orElseThrow(() -> new EntregaNotFoundException(String.format(MENSAGEM_DE_ERRO, nome))));
    }

    public EntregaDTO atualizarEntrega(String nome, EntregaDTO entregaDTO) {
        Optional<Entrega> entrega = entregaRepository.findByNome(nome);

        if (entregaExiste(entrega)) {
            Entrega novaEntrega = deEntregaDTOParaEntrega(entregaDTO);
            return deEntregaParaEntregaDTO(entregaRepository.save(novaEntrega));
        } else {
            throw new EntregaNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    public void deletarPorNome(String nome) {
        Optional<Entrega> entrega = entregaRepository.findByNome(nome);

        if (entregaExiste(entrega)) {
            entregaRepository.deleteEntregaByNome(nome);
        } else {
            throw new EntregaNotFoundException(String.format(MENSAGEM_DE_ERRO, nome));
        }
    }

    private boolean entregaExiste(Optional<Entrega> optionalEntrega) {
        return optionalEntrega.isPresent();
    }
}
