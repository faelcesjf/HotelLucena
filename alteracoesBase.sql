use `HotelLucena`;

ALTER TABLE usuarios
  ADD email varchar(40) 
    AFTER nomeUsuario;
    
    
CREATE TABLE Item (
idItem INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
descricao VARCHAR(150),
valor decimal not null

);


ALTER TABLE usuarios
  ADD cpf varchar(11) 
    AFTER nomeUsuario;
    
ALTER TABLE usuarios
  ADD password varchar(40) 
    AFTER nomeUsuario;
ALTER TABLE usuarios
  ADD ultimoAcesso DATE
    AFTER nomeUsuario;
    

    
    