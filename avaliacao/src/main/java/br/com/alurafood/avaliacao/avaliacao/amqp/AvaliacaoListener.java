package br.com.alurafood.avaliacao.avaliacao.amqp;

import br.com.alurafood.avaliacao.avaliacao.dto.PagamentoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AvaliacaoListener {

    @RabbitListener(queues = "pagamentos.detalhes-avaliacao")
    public void recebeMensagem(@Payload PagamentoDto pagamento) {
        String mensagem = """
                Necessário criar registro de avaliação para o pedido: %s\s
                Id do pagamento: %s
                Nome do cliente: %s
                Valor R$: %s
                Status: %s\s
               \s""".formatted(pagamento.getPedidoId(),
                pagamento.getId(),
                pagamento.getNome(),
                pagamento.getValor(),
                pagamento.getStatus());

        log.info(mensagem);
    }
}