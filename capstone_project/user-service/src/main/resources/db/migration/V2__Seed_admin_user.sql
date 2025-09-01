-- Seed admin user (password: Admin@123)
INSERT INTO users (username, email, password_hash, full_name, address, phone, enabled, created_at, updated_at)
VALUES ('admin', 'admin@shop.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Admin User', 'Admin Address', '1234567890', TRUE, NOW(), NOW());

-- Add ADMIN role to admin user
INSERT INTO user_roles (user_id, role)
VALUES (1, 'ADMIN');

-- Add CUSTOMER role for future users
INSERT INTO user_roles (user_id, role)
VALUES (1, 'CUSTOMER');
