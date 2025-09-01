-- Update admin user credentials to jeevan/jeevan@123
UPDATE users 
SET username = 'jeevan', 
    email = 'jeevan@shop.com', 
    password_hash = '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 
    full_name = 'Jeevan Admin'
WHERE username = 'admin';
