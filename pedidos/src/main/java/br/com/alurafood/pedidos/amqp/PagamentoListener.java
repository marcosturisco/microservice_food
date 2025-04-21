package br.com.alurafood.pedidos.amqp;

import br.com.alurafood.pedidos.dto.PagamentoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PagamentoListener {

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void recebeMensagem(PagamentoDto dto) {
        String mensagem = """
                ID: %s
                Número: %s
                Valor: %s
                Status: %s
                """.formatted(
                dto.getId(),
                dto.getNumero(),
                dto.getValor(),
                dto.getStatus().name()
        );
        log.info(mensagem);
    }
}
