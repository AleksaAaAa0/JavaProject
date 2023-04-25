CREATE TABLE if not exists chats (
 chat_id INT PRIMARY KEY
);

CREATE TABLE links (
 link_id SERIAL PRIMARY KEY,
 updated_at timestamp,
 link_url VARCHAR ( 255 ) UNIQUE NOT NULL
);

CREATE TABLE chat_links (

chat_id BIGINT NOT NULL,
link_id BIGINT NOT NULL,

 FOREIGN KEY (chat_id) REFERENCES chats (chat_id),
 FOREIGN KEY (link_id) REFERENCES links (link_id)
);
