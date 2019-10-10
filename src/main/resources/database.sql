CREATE TABLE IF NOT EXISTS users (
                                   id BIGINT AUTO_INCREMENT,
                                   login VARCHAR(255),
                                   name VARCHAR(255),
                                   password VARCHAR(255),
                                   PRIMARY KEY (id)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS roles (
                                   id BIGINT AUTO_INCREMENT,
                                   role VARCHAR(255),
                                   PRIMARY KEY (id)
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS user_roles (
                                        user_id BIGINT NOT NULL,
                                        role_id BIGINT NOT NULL,
                                        FOREIGN KEY (user_id) REFERENCES users(id),
                                        FOREIGN KEY (role_id) REFERENCES roles(id),
                                        UNIQUE (user_id,role_id)
) ENGINE InnoDB;



INSERT INTO roles VALUES (1,'admin');
INSERT INTO roles VALUES (2,'user');

INSERT INTO users VALUES (1, true, 'admin','admin','admin');
INSERT INTO users VALUES (2, true, 'user','user','user');

INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 2);