CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT,
  login VARCHAR(255),
  name VARCHAR(255),
  password VARCHAR(255),
  PRIMARY KEY (id)
                                 ) ENGINE InnoDB;
CREATE TABLE IF NOT EXISTS user_roles (
                                   userRoleId BIGINT AUTO_INCREMENT,
                                   role VARCHAR(255),
                                   PRIMARY KEY (userRoleId)
) ENGINE InnoDB;
INSERT INTO users VALUES (1, 'admin','admin','admin');
INSERT INTO users VALUES (2, 'user','user','user');
INSERT INTO user_roles VALUES (1, 'admin');
INSERT INTO user_roles VALUES (2, 'user');