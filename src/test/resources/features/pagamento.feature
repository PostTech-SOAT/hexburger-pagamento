# language: pt

Funcionalidade: Gerenciamento de pagamentos - Como um usuário do sistema, quero gerenciar o status de pagamentos para controlar as transações de pedidos

  Cenário: Atualizar status do pagamento para aprovado
    Dado o payload com status de pagamento aprovado
    Quando a atualização do status for solicitada
    Então a resposta deve ter o status 202 Accepted

  Cenário: Atualizar status do pagamento para recusado
    Dado o payload com status de pagamento recusado
    Quando a atualização do status for solicitada
    Então a resposta deve ter o status 202 Accepted

  Cenário: Atualizar status do pagamento sem status definido
    Dado o payload inválido sem o status do pagamento
    Quando a atualização do status for solicitada
    Então a resposta deve ter o status 400 Bad Request

  Cenário: Atualizar status do pagamento com dados inválidos
    Dado o payload com dados inválidos para o pagamento
    Quando a atualização do status for solicitada
    Então a resposta deve ter o status 400 Bad Request

  Cenário: Buscar status do pagamento de um pedido existente
    Dado o ID de pedido existente "b001927d-f946-4bcf-8f4a-5b73694a2f7e"
    Quando o status do pagamento for buscado
    Então o status retornado deve ser "AGUARDANDO"

  Cenário: Buscar status do pagamento de um pedido inexistente
    Dado o ID de pedido inexistente "0"
    Quando o status do pagamento for buscado
    Então a resposta deve ter o status 404 Not Found
