Uma versão simplificada do sistema foi desenvolvida em Java, com o banco de dados SQLite. O SQLite foi escolhido por ser leve e não necessitar de servidor ou qualquer tipo de instalação. O padrão MVC foi seguido, conforme a arquitetura definida para o projeto.

O banco de dados é criado automáticamente (banco.db), caso não exista, na pasta do projeto ou da build. Inicialmente, o banco vem populado com três usuários:
- Um administrador (usuário "admin" e senha "12345").
- Um operador (usuário "operador" e senha "12345").
- Um gestor (usuário "gestor" e senha "12345").

Recomenda-se a utilização do [DB Browser for SQLite](http://sqlitebrowser.org/) para verificar o conteúdo do banco de dados, caso seja necessário.

------

Estão no escopo desta implementação os casos de uso:
- Efetuar login
- Manter funcionários
- Manter lojas

Nota.: aspectos de segurança estão fora do escopo dessa implementação.
