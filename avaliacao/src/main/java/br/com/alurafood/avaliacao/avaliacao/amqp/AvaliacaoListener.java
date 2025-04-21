package br.com.alurafood.avaliacao.avaliacao.amqp;

import br.com.alurafood.avaliacao.avaliacao.dto.PagamentoDto;
import br.com.alurafood.avaliacao.avaliacao.exception.RecebimentoMensagemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AvaliacaoListener {

    @RabbitListener(queues = "pagamentos.detalhes-avaliacao")
    public void recebeMensagem(@Payload PagamentoDto pagamento) {

        if (pagamento.getNumero().equals("0001")) {
            var errorMessage = """
                    Número do Pagamento: %s
                    Nome do cliente: %s
                    Valor R$: %s
                    Status: %s
                    """
                    .formatted(
                            pagamento.getNumero(),
                            pagamento.getNome(),
                            pagamento.getValor(),
                            pagamento.getStatus()
                    );
            log.info(errorMessage);
            throw new RecebimentoMensagemException("não consegui processar");
        }

        String mensagem = """
                 Necessário criar registro de avaliação para o pedido: %s
                 Id do pagamento: %s
                 Nome do cliente: %s
                 Valor R$: %s
                 Status: %s
                """
                .formatted(
                        pagamento.getPedidoId(),
                        pagamento.getId(),
                        pagamento.getNome(),
                        pagamento.getValor(),
                        pagamento.getStatus()
                );
        log.info(mensagem);
    }
}