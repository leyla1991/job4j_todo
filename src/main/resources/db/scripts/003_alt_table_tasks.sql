ALTER TABLE tasks
ADD COLUMN user_id INT NOT NULL references users(id);