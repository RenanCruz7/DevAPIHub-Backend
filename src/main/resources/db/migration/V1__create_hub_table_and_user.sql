CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      github VARCHAR(255),
                      linkedin VARCHAR(255)
);

CREATE TABLE hub (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     api_name VARCHAR(100) NOT NULL,
                     api_description VARCHAR(500) NOT NULL,
                     auth_required BOOLEAN NOT NULL,
                     api_link VARCHAR(120) NOT NULL,
                     api_category VARCHAR(50) NOT NULL,
                     rating FLOAT CHECK (rating BETWEEN 1 AND 5),
                     user_id BIGINT,
                     CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id)
);