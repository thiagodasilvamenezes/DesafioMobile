# Roteiro do Projeto Android e Instalação

## Desenvolvimento

### Tecnologias e Ferramentas Utilizadas

- **Android Studio (Giraffe)**: IDE oficial para o desenvolvimento Android.
- **Kotlin**: Linguagem de programação para o desenvolvimento do aplicativo.
- **Room Database**: Componente para abstrair a camada de acesso ao banco de dados SQLite.
- **Retrofit**: Biblioteca para consumo de APIs RESTful.
- **Picasso**: Biblioteca para carregamento de imagens.
- **MVVM (Model-View-ViewModel)**: Padrão arquitetural para separação da lógica de negócios da interface do usuário.

### Implementações Realizadas

1. **Interface de Entrada de Usuário**: Tela inicial com um campo para "apelido" e um botão "salvar".
2. **Navegação e Consumo de API**: Tela adicional para exibir informações da API JSONPlaceholder.
3. **Deep Linking com Custom Scheme**: Configuração para abrir o app através de um link personalizado, exibindo o parâmetro "pat".
4. **Persistência de Dados com Room Database**: Salvamento de uma tupla com "PAT" e "apelido" em um banco de dados local.
5. **Listagem de Dados Salvos**: Tela para listar os apelidos e PATs armazenados.
6. **Acesso à Câmera e Exibição de Fotos**: Funcionalidade bônus para capturar fotos e exibi-las ao lado do apelido correspondente.

## Instalação e Teste

Para instalar e testar o APK do projeto, siga os passos abaixo:

1. **Preparação do Ambiente**:
   - Garanta que o Android Studio esteja instalado em sua máquina.

2. **Criação do Simulador**:
   - No Android Studio, acesse o "AVD Manager".
   - Crie um novo dispositivo virtual (AVD) com as configurações desejadas.

3. **Instalação do APK**:
   - Localize o APK na pasta `APK/desafioMobile.apk`.
   - Arraste o arquivo APK para o emulador aberto ou use o comando `adb install desafioMobile.apk` para instalar o aplicativo em um dispositivo conectado ou emulador ativo.

4. **Teste do Aplicativo**:
   - Abra o aplicativo instalado no dispositivo ou emulador.
   - Siga as funcionalidades implementadas para testar cada aspecto do aplicativo.

### Nota:

- A funcionalidade de captura de foto requer permissão de câmera, que será solicitada ao usuário na primeira utilização da funcionalidade.
