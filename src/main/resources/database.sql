CREATE TABLE IF NOT EXISTS users (
                                   id_user BIGINT AUTO_INCREMENT,
                                   login VARCHAR(255),
                                   name VARCHAR(255),
                                   password VARCHAR(255),
                                   PRIMARY KEY (id_user)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS roles (
                                   id_role BIGINT AUTO_INCREMENT,
                                   role VARCHAR(255),
                                   PRIMARY KEY (id_role)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS user_roles (
                                        user_id BIGINT NOT NULL,
                                        role_id BIGINT NOT NULL,
                                        FOREIGN KEY (user_id) REFERENCES users(id_user),
                                        FOREIGN KEY (role_id) REFERENCES roles(id_role),
                                        UNIQUE (user_id,role_id)
) ENGINE InnoDB;



INSERT INTO roles VALUES (1,'admin');
INSERT INTO roles VALUES (2,'user');

INSERT INTO users VALUES (1, false, 'admin','admin','admin');
INSERT INTO users VALUES (2, false, 'user','user','user');

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 2);