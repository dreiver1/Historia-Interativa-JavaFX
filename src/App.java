public class App {
    public static void main(String[] args) throws Exception {
        Historia tesouro = new Historia();
        Capitulo capitulo1 = new Capitulo("Capítulo 1: A Busca Pelo Tesouro", "Você é um aventureiro em busca de um tesouro lendário. Você chega em uma encruzilhada e precisa decidir qual caminho seguir.");
        tesouro.setInicio(capitulo1);
        Capitulo primeiraCapt01 = new Capitulo("Opção: 01 Seguir o caminho conhecido e mais seguro.", "Você decide seguir o caminho já conhecido, mas descobre que há um grupo de bandidos que bloquearam a estrada. Eles exigem que você pague uma taxa de passagem, mas você não tem dinheiro suficiente. Você tem duas opções:");
        Capitulo primeiraCapt0101 = new Capitulo("Opção: 02 Lutar contra os bandidos", "Você enfrenta os bandidos em uma batalha difícil, mas consegue vencê-los. Você continua sua jornada e chega à base da Montanha Proibida. Você vê a entrada para a trilha que leva ao topo. Você tem duas opções:");
        Capitulo primeiraCapt010101 = new Capitulo("Opção: 01 Descansar antes de subir a trilha", "Você decide descansar antes de subir a trilha. Infelizmente, enquanto você estava dormindo, os bandidos que você lutou antes te encontraram e roubaram seus suprimentos. Você acorda sem nada e é forçado a voltar para casa com as mãos vazias.");
        Capitulo primeiraCapt010102 = new Capitulo("Opção: 02 Subir mediatamente a trilha", "Você decide subir imediatamente a trilha. A subida é íngreme e difícil, mas você é um aventureiro experiente e consegue chegar ao topo. Lá, você encontra o tesouro lendário e volta para casa como um herói.");
        primeiraCapt010101.setFim();
        primeiraCapt0101.setEscolha01(primeiraCapt010101);
        primeiraCapt0101.setEscolha02(primeiraCapt010102);
        Capitulo primeiraCapt0102 = new Capitulo("Opção: 01 Tentar convencer os bandidos a deixá-lo passar", "Você tenta convencer os bandidos a deixá-lo passar, mas eles são implacáveis. Eles o obrigam a trabalhar para eles por um tempo antes de permitir que você passe. Você perde muito tempo e chega tarde demais na Montanha Proibida. Você não encontra o tesouro lendário e volta para casa de mãos vazias.");
        primeiraCapt0102.setFim();
        primeiraCapt01.setEscolha01(primeiraCapt0101);
        primeiraCapt01.setEscolha02(primeiraCapt0102);

        Capitulo segundaCapt01 = new Capitulo("Opção: 02 Tomar um atalho desconhecido e mais arriscado.", "Você decide tomar um atalho desconhecido e mais arriscado. O caminho é perigoso e você quase cai em um penhasco algumas vezes, mas você chega à base da Montanha Proibida em um tempo recorde. Você tem duas opções");
        Capitulo segundaCapt0101 = new Capitulo("Opção: 01 Seguir trilha que leva ao topo da montanha.", "Você segue a trilha que leva ao topo da montanha. A subida é difícil, mas você é um aventureiro experiente e consegue chegar ao topo. Lá, você encontra o tesouro lendário e volta para casa como um herói.");
        Capitulo segundaCapt0102 = new Capitulo("Opção: 02 Procurar por outro caminho mais fácil.", "Você decide procurar por outro caminho mais fácil, mas acaba se perdendo na floresta. Você encontra um grupo de animais selvagens e fica preso lá por um tempo. Quando você finalmente consegue sair da floresta, já é tarde demais. Você perde a chance de encontrar o tesouro lendário e volta para casa de mãos vazias.");
        segundaCapt0102.setFim();
        segundaCapt01.setEscolha01(segundaCapt0101);
        segundaCapt01.setEscolha02(segundaCapt0102);
        capitulo1.setEscolha01(primeiraCapt01);
        capitulo1.setEscolha02(segundaCapt01);
        tesouro.setInicio(capitulo1);
        tesouro.executar();
    }
}
