# 🐚 Shellder Connect

O **Shellder Connect** é uma plataforma humanitária que conecta pessoas em situação de vulnerabilidade com redes de apoio locais. A proposta surgiu da necessidade de eliminar a desconexão entre quem precisa de ajuda e quem está disposto a ajudar, especialmente em situações emergenciais. Por meio da localização de abrigos, sistema inteligente de doações e cadastro de profissionais voluntários, o app promove acolhimento, solidariedade e impacto social real com tecnologia acessível e escalável.

## 📌 **Índice**
1. [Sobre o Projeto](#-sobre-o-projeto)
2. [Implementações Java](#️-implementações-java)
3. [Implementações DevOps](#️-implementações-devops)
4. [Vídeos Demonstrativos](#-vídeos-demonstrativos)
5. [Arquitetura do Projeto](#-arquitetura-do-projeto)
6. [Configuração das Pipelines](#-configuração-das-pipelines)
7. [Rodando o Projeto com Docker](#-rodando-o-projeto-com-docker)
8. [Equipe](#-equipe)

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 💡 **Sobre o Projeto**
O sistema é uma aplicação Java com Spring Boot, desenvolvida para gerenciar os dados recebidos a partir do aplicativo mobile voltado à doação e acolhimento. A aplicação web Java serve como um painel administrativo para gestão de usuários, abrigos e doações realizadas no app.

#### **⚙️ Funcionalidades principais**
✅ Dashboard moderno e responsivo para facilitar a navegação dos administradores.  
✅ Cadastro, listagem, edição e exclusão de abrigos e usuários.  
✅ Visualização de doações e distribuições feitas pelo app.  
✅ Geração de resumos automáticos com IA para apoio à tomada de decisão.  
✅ Integração com mensageria RabbitMQ para envio de e-mails.  
✅ Ambiente seguro com controle de acesso por perfil de usuário.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## ⚙️ Implementações Java
Neste projeto a aplicação Java foi finalizada com arquitetura **full MVC**, contemplando os requisitos funcionais propostos:

- 🔐 **Autenticação e Autorização**    
  Implementação do **Spring Security**, com autenticação baseada em formulário e controle de acesso por **perfis de usuário (roles)**, permitindo acesso seguro para o administrador.

- 🌐 **Internacionalização**    
  A aplicação conta com suporte a múltiplos idiomas por meio do recurso de internacionalização do Spring. Os arquivos `.properties` foram configurados para exibir textos em português e inglês de forma dinâmica.

- 📬 **Mensageria com RabbitMQ**    
  Integração com o **RabbitMQ** para envio e consumo de mensagens assíncronas, como no envio de e-mails de cadastro. A configuração foi realizada com produtores e consumidores devidamente registrados.
- ✅ **Testes Unitários e de Integração**  
  Implementação de testes unitários com JUnit e Mockito para validar a lógica de negócio de forma isolada. Também foram desenvolvidos testes de integração, utilizando banco de dados real e contexto completo da aplicação.

- 🤖 **Inteligência Artificial com Spring AI**    
  Recursos de IA foram adicionados utilizando o **Spring AI** com integração à API da OpenAI (GPT-3.5 Turbo). O sistema é capaz de **gerar resumos automáticos**, proporcionando insights inteligentes.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)


## ⚙️ Implementações DevOps
Para essa entrega, foi implementada uma esteira completa de CI/CD utilizando o Azure DevOps, com o objetivo de automatizar o build, push e deploy da aplicação na nuvem.
- 🚀 **Integração Contínua (CI)**  
  A pipeline é disparada automaticamente a cada push no repositório Git. Ela realiza o build do projeto com Maven, empacota a aplicação como arquivo .jar e armazena os artefatos gerados para a próxima etapa.

- 🌐 **Entrega Contínua (CD)**  
  Após a conclusão da etapa de build, a pipeline de CD realiza o deploy automático da aplicação para o Azure App Service (Web App), expondo-a publicamente com uma URL acessível diretamente pelo navegador.
- 🔄 **Automação e Rastreabilidade**  
  O processo automatizado proporciona agilidade, confiabilidade e versionamento controlado, além de facilitar testes e validações em ambiente de produção.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 🎥 **Vídeos Demonstrativos**
Disponibilizamos três vídeos para apresentar as etapas e funcionalidades do projeto:

### 🎥 *1. Vídeo Pitch – Visão Geral do Projeto*
Neste vídeo pitch, apresentamos uma visão completa do projeto Shellder Connect, destacando sua proposta, funcionalidades e estrutura técnica.

📌 Assista aqui:  
[▶ Pitch Shellder Connect](https://youtu.be/-9nke1fOD30)

### 📦 _2. DevOps: CI/CD com Azure DevOps_
Neste vídeo, mostramos todo o fluxo da esteira de integração e entrega contínua:
- Push no repositório Git
- Execução da pipeline de CI (build com Maven e geração do artefato .jar)
- Execução da pipeline de CD (deploy automático no Azure App Service - Web App)
- Verificação da aplicação em execução e acesso via URL pública do Web App

📌 Assista aqui:  
[▶ CI/CD no Azure DevOps](https://youtu.be/NSpdnWQZZNY?si=1JaJRIKZs7bURKsd)

### 💻 _3. Demonstração do Sistema Java_
Este vídeo apresenta as funcionalidades principais da aplicação:
- Login e navegação com interface desenvolvida em Thymeleaf
- Arquitetura final da solução
- Integrações com RabbitMQ e OpenAI

📌 **Assista aqui:** 
[▶ Demonstração do Sistema Java](https://youtu.be/H6_pE5DcYZQ?si=ZRdMvZuo53G_ziQ_)

Credencial usada para testes:

- `patricianaomiyama@gmail.com` / `123456`


[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 🏛️ **Arquitetura do Projeto**

![arquitetura do projeto](https://github.com/shellder-connect/backend-java/blob/main/diagrama.png)

A arquitetura do projeto foi desenvolvida com foco em escalabilidade e integração contínua. Utilizando o Azure DevOps, o pipeline de CI/CD automatiza o processo de build e deploy da aplicação. A imagem Docker é gerada a partir do código Java com Spring Boot e, em seguida, implantada em um Azure Web App, permitindo o acesso público via navegador.

A aplicação se comunica com um banco de dados SQL hospedado no Azure, garantindo persistência em nuvem, e utiliza o RabbitMQ como serviço de mensageria para eventos assíncronos, como o envio automático de e-mails.  
O monitoramento é realizado com Prometheus e Grafana, ambos executando em containers, permitindo o acompanhamento em tempo real das métricas e do estado da aplicação.

Toda a estrutura foi planejada para entregar uma experiência DevOps completa, robusta e confiável, com foco na automação, escalabilidade e visibilidade do sistema em produção.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 💡 **Configuração das Pipelines**
#### 🛠️ Configuração da Pipeline de CI (Continuous Integration)

Na pipeline de Integração Contínua (CI), configurada no Azure DevOps, foi utilizado o pool padrão do Azure Pipelines para a execução das tarefas. O processo automatiza o build da aplicação Java com Spring Boot, sem o uso de containers.  
A tarefa de build utiliza o Maven para compilar o código-fonte localizado na pasta backend-java/ e gerar o pacote .jar. Em seguida, o artefato gerado é publicado e utilizado na pipeline de CD para o deploy automático em um Azure Web App, serviço de hospedagem gerenciado que expõe a aplicação por meio de uma URL pública.  
Esse fluxo garante versionamento, rastreabilidade e facilidade de atualização da aplicação em ambiente de nuvem, alinhando-se às boas práticas de DevOps  
👉 [`link do código de ci-pipeline.yml`](https://github.com/shellder-connect/backend-java/blob/main/azure-pipelines.yml)
#### 🚀 Pipeline de CD (Entrega Contínua)
A pipeline de Continuous Deployment (CD) foi configurada no Azure DevOps com o objetivo de automatizar o processo de publicação da aplicação Java na nuvem. Após a conclusão da etapa de build na pipeline de CI, a pipeline de CD é acionada para realizar o deploy direto no serviço Azure Web App.

O processo segue os seguintes passos:
1. ✅ Autenticação na conta Azure via Azure CLI.
2. 📦 Publicação do artefato gerado (arquivo .jar) diretamente no Azure Web App, utilizando a task de deploy do Azure App Service.
3. 🌐 O Web App já possui URL pública, permitindo acesso imediato via navegador.

   👉 [`link do código de cd-pipeline.yml`](https://github.com/shellder-connect/backend-java/blob/main/cd-pipeline.yml)

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 🐳 Rodando o Projeto com Docker

Para rodar a aplicação completa com **Spring Boot + Oracle + RabbitMQ + Prometheus + Grafana**, siga os passos abaixo:

### 📁 Pré-requisitos

- Docker instalado (link de instalação)
- Docker Compose instalado (link de instalação)

### 🐑 1. Clone o repositório
```  
git clone https://github.com/shellder-connect/backend-java  
```  

### ⚙️ 2. Configure o arquivo `.env`

Crie um arquivo chamado `.env` na raiz do projeto com o seguinte conteúdo (ajuste os valores conforme suas credenciais):
```  
OPENAI_KEY=key-open-ai-aqui  
JWT_SECRET_KEY=sua-chave-jwt-aqui  
EMAIL_USER=seuemail@gmail.com  
EMAIL_PASSWORD=sua-senha-email-smpt-aqui  
GH_CLIENT_ID=cliend-id-aqui  
GH_CLIENT_SECRET=secret-id-aqui  
```  
### 🚀 3. Suba os containers
Na raiz do projeto, execute o seguinte comando:  
``docker compose up --build  
``

Esse comando irá:
- Construir a imagem do projeto Java.
- Subir os containers do **RabbitMQ**, **Oracle** (caso esteja local), **Prometheus** e **Grafana**.
- Inicializar a aplicação acessível em:    
  👉 [`http://localhost:8080`](http://localhost:8080)
### 📊 Interfaces disponíveis
|Serviço|URL|  
|--|--|  
|🌐 Aplicação|[http://localhost:8080](http://localhost:8080)|  
|🐰 RabbitMQ|[http://localhost:15672](http://localhost:15672) _(user: guest / guest)_|  
|📊 Prometheus|[http://localhost:9090](http://localhost:9090)|  
|📈 Grafana|[http://localhost:3000](http://localhost:3000) _(login padrão: admin / admin)_|  

### 🛠️ Inicialização do Banco de Dados
Ao rodar o projeto pela primeira vez, é necessário garantir que o banco de dados seja criado automaticamente. Para isso, o projeto deve ser executado com a propriedade spring.jpa.hibernate.ddl-auto configurada como create. Isso fará com que todas as tabelas definidas pelas entidades JPA sejam geradas automaticamente no banco Oracle. Após a criação inicial, recomenda-se alterar esse modo para update ou none, evitando a recriação acidental do schema em execuções futuras.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)
## 🧑‍🤝‍🧑 Equipe

| <h3>Claudio Bispo</h3><img src="https://avatars.githubusercontent.com/u/110735259?v=4" width=180px> <h6>RM553472</h6> <a href="https://github.com/claubis"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/claudiosbispo"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/_claudiobispo/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|<h3>Patricia Naomi</h3> <img src="https://avatars.githubusercontent.com/u/132932532?v=4" width=180px><h6>RM552981</h6> <a href="https://github.com/patinaomi"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/patinaomi/"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/naomipati/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|  
|--|--|  


[:arrow_up: voltar para o índice :arrow_up:](#-índice)
